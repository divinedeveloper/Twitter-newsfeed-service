import groovy.json.JsonSlurper

// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.config.locations = [
	"classpath:${appName}-${grails.util.Environment.current.name}-config-myapp.groovy"
]
grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        // filteringCodecForContentType.'text/html' = 'html'
    }
	
}

grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false

String configFile
environments {
	development {
		grails.logging.jul.usebridge = true
	}
	
	test {
		grails.logging.jul.usebridge = true
	}

	production {
		grails.logging.jul.usebridge = false
	}
	
}

// log4j configuration
log4j.main = {
    // Example of changing the log pattern for the default console appender:
    //
    appenders {
        console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    }
	
    debug "grails.app.services"
    debug "grails.app.domains"
	debug 'grails.app.jobs'

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate',
		   'grails.app.service',
		   'grails.app.domain'
}


customExceptions{
	account{
		fourZeroFour{
			socialUser{
				errorCode = 3404
				status = 404
				message = "Social user not found."
				extendedMessage= "Social user was not found in system based on social user id"
				moreInfo = "Social user was not found in system based on social user id"
			}
		}
		fourZeroZero{
			tweetDetailsNotSaved{
				errorCode = 3400
				status = 400
				message = "Validation error. Failed to save tweet details."
				extendedMessage= "There is a validation error in saving tweet details. Fields failed validation checks."
				moreInfo = "There is a validation error in saving tweet details. Fields failed validation checks."

			}
			hashTagNotSaved{
				errorCode = 3400
				status = 400
				message = "Validation error. Failed to save or update hashtag."
				extendedMessage= "There is a validation error in saving or updating hashtag. Fields failed validation checks."
				moreInfo = "There is a validation error in saving or updating hashtag. Fields failed validation checks."

			}
			twitterHandleNotSaved{
				errorCode = 3400
				status = 400
				message = "Validation error. Failed to save or update twitter handle."
				extendedMessage= "There is a validation error in saving or updating twitter handle. Fields failed validation checks."
				moreInfo = "There is a validation error in saving or updating twitter handle. Fields failed validation checks."

			}
			twitterTokensAreNull{
				errorCode = 3400
				status = 400
				message = "Please provide twitter access token and secret."
				extendedMessage= "News feed job failed because your twitter account is not connected to Karma. To start pulling tweets, please go to profile page on Karma platform and connect to Twitter."
				moreInfo = "News feed job failed because your twitter account is not connected to Karma. To start pulling tweets, please go to profile page on Karma platform and connect to Twitter."

			}
		}
	}

	error{
		fiveZeroZero{
			errorCode = 11500
			status = 500
			message = "Internal server error. Something went wrong."
			extendedMessage= "Internal server error. Something went wrong in api mostly run time exceptions"
			moreInfo = "Internal server error. Something went wrong in api mostly run time exceptions"
		}
	}
}
