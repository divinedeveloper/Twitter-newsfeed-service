import groovy.json.JsonSlurper



myapp.developmentJson = "/home/devuser/projects/karma-configuration/development.backend.json"

email.send = "on"   //value will be "on" or "off"

//email configurations
myapp.jobFailSubject = "Newsfeed job failed"
myapp.jobFailBody = '''Hi $email, <br><br> Karma system has failed to pull Newsfeed tweets from Twitter. <br><br> Please take appropriate action to resolve it.<br><br> Following is the cause of error. <br><br> $error'''


try {
	File file = new File(myapp.developmentJson)
	
	JsonSlurper jsonSlurper = new JsonSlurper();
	def configJson = jsonSlurper.parse(file);
//	println "config json from file is "+configJson
	
	configJson.each { k ,v  ->
		if(v == null || v == ""){
			println "Please provide value for key "+k
			System.exit(0)
		}
	}

	//trigger cron job in minutes
	myapp.triggerInMinutes = configJson.triggerInMinutes
	//mail settings
	grails {
		mail{
			host = configJson.mailHost
			port = configJson.mailPort
			username = configJson.mailUsername
			password = configJson.mailPassword
			props = ["mail.debug":"true",
				"mail.smtp.auth":"true"]
			disabled = false

		}
	}
	myapp.email.from = configJson.fromEmail
	myapp.email.replyTo= configJson.replyTo
	myapp.email.to= configJson.replyTo
	
	
	//twitter settings
	//twitter4j configuration
	twitter4j {
	//	enableTwitter4jController = true  // To avoid intruders to use controller all together.
	//	'default' {
	//	  debugEnabled           = true
	  OAuthConsumerKey       = configJson.TWITTER_KEY
	  OAuthConsumerSecret    = configJson.TWITTER_SECRET
	  //get following values from database for that particular user
	//	  OAuthAccessToken       = ''
	//	  OAuthAccessTokenSecret = ''
	//	}
	}
	
	//database settings from configuration
	//database settings for production environment
	dataSource.url="jdbc:mysql://"+configJson.DB_HOST+":"+configJson.DB_PORT+"/"+configJson.DB_SCHEMA+"?autoreconnect=true&zeroDateTimeBehavior=convertToNull"
	dataSource.username=configJson.DB_USER
	dataSource.password=configJson.DB_PASSWORD
	
}
catch (Exception e) {
	println e.message
	System.exit(0)
}

