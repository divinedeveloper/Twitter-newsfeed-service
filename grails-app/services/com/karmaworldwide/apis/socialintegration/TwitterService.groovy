package com.karmaworldwide.apis.socialintegration

import grails.transaction.Transactional

import org.codehaus.groovy.grails.web.json.JSONObject

import twitter4j.MediaEntity
import twitter4j.Query
import twitter4j.QueryResult
import twitter4j.Status
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.URLEntity
import twitter4j.auth.AccessToken

import com.karmaworldwide.account.HashTags
import com.karmaworldwide.account.SocialUser
import com.karmaworldwide.account.TwitterHandles
import com.karmaworldwide.account.User
import com.karmaworldwide.twitter.Tweets

@Transactional
class TwitterService {
	
	def sessionFactory
    def propertyInstanceMap = org.codehaus.groovy.grails.plugins.DomainClassGrailsPlugin.PROPERTY_INSTANCE_MAP
	def userExceptionService
	def grailsApplication
	def sendMailService
	
	private static final int TWEETS_PER_QUERY = 100;
	private long superAdminRoleId = 1L
	private long maxID = -1;

		
	JSONObject createAndSendTwitterException(exception){
		int errorCode = exception.getErrorCode()
		int status
		//if twitter sends 401 status code change it to 400
		if(exception.getStatusCode() == grailsApplication.config.responseStatus.unAuthorized){
			status = grailsApplication.config.responseStatus.badRequest
		}
		status = exception.getStatusCode()
		String message = exception.getMessage()
		String extendedMessage = exception.getMessage()
		String moreInfo = exception.getErrorMessage()
		
		JSONObject errorJson = new JSONObject();
		errorJson.put("status",status);
		errorJson.put("errorCode",errorCode);
		errorJson.put("message",message);
		errorJson.put("extendedMessage",extendedMessage);
		errorJson.put("moreInfo",moreInfo);
		errorJson.put("error",true);
		errorJson.put("dateTimeStamp", new Date().time);
		
		return errorJson
	}
	
	boolean saveTweets(){
		boolean areTweetsSaved
		SocialUser karmaAdminSocialDetails = findSuperAdminSocialDetails()
		String adminTwitterAccessToken = karmaAdminSocialDetails?.twitterAccessToken
		String adminTwitterAccessTokenSecret = karmaAdminSocialDetails?.twitterAccessTokenSecret
		//check if superadmins twitter tokens are null then throw exception
		if((adminTwitterAccessToken == null || adminTwitterAccessToken == "") || (adminTwitterAccessTokenSecret == null || adminTwitterAccessTokenSecret == "")){
			userExceptionService.twitterTokensAreNull()
		}
		AccessToken twitterAccessToken = new AccessToken(adminTwitterAccessToken, adminTwitterAccessTokenSecret)
		Twitter twitter = new TwitterFactory().instance
		twitter.setOAuthConsumer(grailsApplication.config.twitter4j.OAuthConsumerKey.toString(), grailsApplication.config.twitter4j.OAuthConsumerSecret.toString())
		twitter.setOAuthAccessToken(twitterAccessToken)

		//verify user by passing his accesstokens
		def user = twitter.verifyCredentials()
		List hashTagList  =  getListOfHashTags()
		hashTagList.each { hashtag ->
			areTweetsSaved = fetchTweetsBasedOnHashTagsAndSaveThem(hashtag, twitter)
		}
		
		List twitterHandleList  =  getListOfTwitterHandles() 
		twitterHandleList.each { twitterHandle -> 
			areTweetsSaved = fetchTweetsBasedOnHandleAndSaveThem(twitterHandle, twitter)
		}
		return areTweetsSaved = true
	}
	
	SocialUser findSuperAdminSocialDetails(){
		User superUser = User.findByRoleId(superAdminRoleId)
		SocialUser socialUser = SocialUser.findByUser(superUser)
		userExceptionService.checkSocialUser(socialUser) 	//if social user is null throw exception
		return socialUser
	}
	
	List getListOfHashTags(){
		List hashTagList
		if(HashTags.count > 0){
			hashTagList = HashTags.findAllByIsActive(true)
		}
		return hashTagList
	}
	
	List getListOfTwitterHandles(){
		List twitterHandleList
		if(TwitterHandles.count > 0){
			twitterHandleList = TwitterHandles.list()
		}
		return twitterHandleList
	}
	
	boolean saveSinceIdForHashTag(hashtag,sinceId){
		boolean isSinceIdSaved 
		hashtag.hashTagSinceId = sinceId
		if(!hashtag.save(flush:true)){
			log.error(hashtag?.errors)
			userExceptionService.hashTagsNotSaved()
		}
		return isSinceIdSaved = true
	}
	
	boolean fetchTweetsBasedOnHandleAndSaveThem(twitterHandle, twitter){
		boolean areTweetsSaved
		Query query = new Query("@"+twitterHandle.handleName);
		if(twitterHandle.handleSinceId != null){
			Long sinceId = twitterHandle.handleSinceId
			query.sinceId(sinceId)
		}
		query.setCount(TWEETS_PER_QUERY)
		query.setLang("en")
		
//		query.setQuery("@"+twitterHandle.handleName)
		QueryResult result;
		result = twitter.search(query)
		List<Status> tweets = result.getTweets();
		//adding new stuff
		result = null
		query = new Query("from:"+twitterHandle.handleName);
//		query.setQuery("from:"+twitterHandle.handleName)
		/*if(twitterHandle.handleSinceId != null){
			Long sinceId = twitterHandle.handleSinceId
			query.sinceId(sinceId)
		}*/
		query.setCount(TWEETS_PER_QUERY)
		query.setLang("en")
		result = twitter.search(query)
		List<Status> tweetsFrom = result.getTweets();
		tweets.addAll(tweetsFrom)
		
		result = null
		query = new Query("to:"+twitterHandle.handleName);
//		query.setQuery("to:"+twitterHandle.handleName)
		/*if(twitterHandle.handleSinceId != null){
			Long sinceId = twitterHandle.handleSinceId
			query.sinceId(sinceId)
		}*/
		query.setCount(TWEETS_PER_QUERY)
		query.setLang("en")
		result = twitter.search(query)
		List<Status> tweetsTo = result.getTweets();
		tweets.addAll(tweetsTo)
		
		areTweetsSaved = saveTweetsFromHandle(twitterHandle, tweets)
		println "tweets saved by Handle is "+areTweetsSaved
		return areTweetsSaved
	}
	
	boolean fetchTweetsBasedOnHashTagsAndSaveThem(hashtag, twitter){
		boolean areTweetsSaved
		Query query = new Query("#"+hashtag.hashTag);
		if(hashtag.hashTagSinceId != null){
			Long sinceId = hashtag.hashTagSinceId
			query.sinceId(sinceId)
		}
		query.setCount(TWEETS_PER_QUERY)
		query.setLang("en")

		
		QueryResult result;
		result = twitter.search(query)
		List<Status> tweets = result.getTweets();
		areTweetsSaved = saveTweetsFromHashTag(hashtag, tweets)
		println "tweets saved by hashtag is "+areTweetsSaved
		return areTweetsSaved
	}
	
	boolean saveTweetsFromHandle(twitterHandle, tweets){
		boolean areTweetsSaved
		tweets.eachWithIndex { tweet, index ->
			//twitter gives us results in descending format i.e. latest one first
			//thats why save the first tweet id for each twitter handle as a since id for that particular handle
			//this since id will be sent to twitter to fetch only the latest tweets,
			//so twitter doesnt send those tweets which are already persisted in our DB.
			
			if(index == 0){
				//save the first tweetid as sinceid for that particular handle
				boolean isSinceIdSaved = saveSinceIdForTwitterHandle(twitterHandle,tweet.getId())
			}
			
			//if tweet as retweetedStatus object which is not null,
			//this retweetedStatus is original tweet WITHOUT any truncated values which we must save in our DB.
			if(tweet.getRetweetedStatus() != null ){
				tweet = tweet.getRetweetedStatus()
			}
			//check if this tweet is already saved in DB
			boolean  isTweetAlreadyPersisted = chechkIfTweetIsAlreadyPersisted(tweet.getId())
			if(isTweetAlreadyPersisted == false){
				boolean isSingleTweetSaved  = saveSingleTweet(tweet, null, twitterHandle)
			}
		}
		return areTweetsSaved = true
	}
	
	boolean saveTweetsFromHashTag(hashtag,tweets){
		boolean areTweetsSaved
		tweets.eachWithIndex { tweet, index ->
			//twitter gives us results in descending format i.e. latest one first
			//thats why save the first tweet id for each hashtag as a since id for that particular hashtag
			//this since id will be sent to twitter to fetch only the latest tweets,
			//so twitter doesnt send those tweets which are already persisted in our DB.
			boolean isSinceIdSaved
			
			if(index == 0){
				//save the first tweetid as sinceid for that particular hashtag
				isSinceIdSaved = saveSinceIdForHashTag(hashtag,tweet.getId())
			}
			
			//if tweet as retweetedStatus object which is not null,
			//this retweetedStatus is original tweet WITHOUT any truncated values which we must save in our DB.
			if(tweet.getRetweetedStatus() != null ){
				tweet = tweet.getRetweetedStatus()
			}
			//check if this tweet is already saved in DB
			boolean  isTweetAlreadyPersisted = chechkIfTweetIsAlreadyPersisted(tweet.getId())
			if(isTweetAlreadyPersisted == false){
				boolean isSingleTweetSaved  = saveSingleTweet(tweet, hashtag, null)
			}
			
		}
		return areTweetsSaved = true
	}
	
	boolean saveSinceIdForTwitterHandle(twitterHandle,sinceId){
		boolean isSinceIdSaved
		twitterHandle = twitterHandle.merge()
		twitterHandle.handleSinceId = sinceId
		if(!twitterHandle.save(flush:true)){
			log.error(twitterHandle?.errors)
			userExceptionService.twitterHandlesNotSaved()
		}
		return isSinceIdSaved = true
	}
	
	boolean saveSingleTweet(tweet, hashtag, twitterHandle){
		boolean isTweetSaved
		String expandedUrls
		
		//save 	expanded_url of image url links inside a tweet
		URLEntity[] urls = tweet.getURLEntities();
		urls.eachWithIndex{ it,index ->
			if(index == 0){
				expandedUrls = it.getExpandedURL()
			}else{
				expandedUrls = expandedUrls + ","+ it.getExpandedURL()
			}
		}
		
		//save 	expanded_url of image url links inside a tweet
		MediaEntity[] mediaUrls = tweet.getMediaEntities();
		mediaUrls.eachWithIndex{ it,index ->
			if(index == 0){
				if(expandedUrls == null){
					expandedUrls = it.getMediaURL()
				}else{
					expandedUrls = expandedUrls + ","+ it.getMediaURL()
				}
				
			}else{
				expandedUrls = expandedUrls + ","+ it.getMediaURL()
			}
		}
		
		Tweets tweetDetails = new Tweets(tweetId : tweet.getId(),
			twitterUserId : tweet.getUser().getId(),
			tweetText : tweet.getText(),
			userScreenName : tweet.getUser().getScreenName(),
			name : tweet.getUser().getName(),
			userProfileImageUrl : tweet.getUser().getProfileImageURL(),
			tweetCreatedAt : tweet.getCreatedAt(),
			tweetStatus : "New",
			hashTags : hashtag,
			twitterHandles : twitterHandle,
			expandedUrls:expandedUrls
			)

		tweetDetails.validate()
		if(tweetDetails.hasErrors()){
			println "tweet details errors are "+tweetDetails.errors
			
			}
			if(!tweetDetails.save()){
				log.error(tweetDetails?.errors)
				userExceptionService.tweetDetailsNotSaved()
			}
			//clearing hibernate session after save to improve performance of saving tweets on large records 
			def session = sessionFactory.currentSession
			session.flush()
			session.clear()
			propertyInstanceMap.get().clear()
			return isTweetSaved = true
	}
	
	
	boolean chechkIfTweetIsAlreadyPersisted(tweetId){
		boolean isTweetAlreadyPersisted
		Tweets tweet = Tweets.findByTweetId(tweetId)
		if(tweet == null){
			return isTweetAlreadyPersisted = false
		}else{
			return isTweetAlreadyPersisted = true
		}
	}
	
	
	/*
	 * get email remove value before @  and return it
	 */
	String getEmailTrimValue(email){
		return email.split("@")[0]
	}
	
	protected String evaluate(s, binding) {
		def engine = new groovy.text.SimpleTemplateEngine()
		def template = engine.createTemplate(s).make(binding)
		return template.toString()
	}
	
	//send job mail and create a template to send
	void sendJobFailureMailToAdmin(from, email, subject, body, error){
		String trimEmail = getEmailTrimValue(email)
		if(body.contains('$')){
			body = evaluate(body, [email:trimEmail,error:error])
		}
		sendMailService.mail(from,email,subject,body.toString())
	}
	
	void createAndSendMail(exception){
		String error = exception.extendedMessage
		String from = grailsApplication.config.myapp.email.from.toString()
		String email = grailsApplication.config.myapp.email.to.toString()
		String subject = grailsApplication.config.myapp.jobFailSubject.toString()
		String body = grailsApplication.config.myapp.jobFailBody.toString()
		
		sendJobFailureMailToAdmin(from, email, subject, body, error)
	}
	
	
}
