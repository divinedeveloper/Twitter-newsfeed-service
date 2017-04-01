package com.karmaworldwide

import grails.util.Holders;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

//import org.grails.plugins.quartz.JobManagerService;
import twitter4j.TwitterException

import com.karmaworldwide.exceptions.AccountException

import org.codehaus.groovy.grails.web.json.JSONObject
/**
 * 
 * @author karmaworldwide
 * This job will run after every 5 minutes and polls twitters search api for particular hashtags
 * Get tweet details and save then in Tweets table
 */

class TwitterFeedsJob {
	
	def twitterService
	
	static triggers = {
		cron name: 'twitterFeedsTrigger', cronExpression: "0 0/"+Holders.config.myapp.triggerInMinutes.toString()+" * * * ?"
	}
	def group = "twitterFeedsGroup"
	def description = "Job to pull tweets from twitter and save them after every 5 minutes"

	def execute(JobExecutionContext context) throws JobExecutionException{
		try{
			log.info "###################Starting Job to pull tweets from Twitter###################"
			boolean tweetsSaved = twitterService.saveTweets()
			println "are tweets saved "+tweetsSaved
			log.info "Tweets Saved "+tweetsSaved
			log.info "###################Successfully Completed Job to pull tweets from Twitter###################"
		}catch(AccountException e){
		println "inside account exception "+e
			JSONObject errorJson = e.errorResponse()
			log.error errorJson
			twitterService.createAndSendMail(errorJson)
		}
		catch(TwitterException e){
			println "inside twitter exception "+e
			JSONObject errorJson = twitterService.createAndSendTwitterException(e)
			log.error errorJson
			twitterService.createAndSendMail(errorJson)
		}catch (Throwable e) {
		println "inside throwable exception "+e
			throw new JobExecutionException(e.getMessage(), e);
		}
	}
}
