package com.karmaworldwide.exception

import grails.transaction.Transactional

import com.karmaworldwide.account.SocialUser
import com.karmaworldwide.exceptions.AccountException

/**
 * 
 * @author swapnil
 *
 */
@Transactional
class UserExceptionService {

	def grailsApplication

	
	/**
	 * check if tweet details is not saved then throw exception
	 * @return
	 */
	void tweetDetailsNotSaved() {
		int errorCode = grailsApplication.config.customExceptions.account.fourZeroZero.tweetDetailsNotSaved.errorCode
		int status = grailsApplication.config.customExceptions.account.fourZeroZero.tweetDetailsNotSaved.status
		String message = grailsApplication.config.customExceptions.account.fourZeroZero.tweetDetailsNotSaved.message
		String extendedMessage = grailsApplication.config.customExceptions.account.fourZeroZero.tweetDetailsNotSaved.extendedMessage
		String moreInfo = grailsApplication.config.customExceptions.account.fourZeroZero.tweetDetailsNotSaved.moreInfo
		throw new AccountException(status,errorCode,message,extendedMessage,moreInfo)
	}
	
	/**
	 * check if hashtag is not saved or updated then throw exception
	 * @return
	 */
	void hashTagsNotSaved() {
		int errorCode = grailsApplication.config.customExceptions.account.fourZeroZero.hashTagNotSaved.errorCode
		int status = grailsApplication.config.customExceptions.account.fourZeroZero.hashTagNotSaved.status
		String message = grailsApplication.config.customExceptions.account.fourZeroZero.hashTagNotSaved.message
		String extendedMessage = grailsApplication.config.customExceptions.account.fourZeroZero.hashTagNotSaved.extendedMessage
		String moreInfo = grailsApplication.config.customExceptions.account.fourZeroZero.hashTagNotSaved.moreInfo
		throw new AccountException(status,errorCode,message,extendedMessage,moreInfo)
	}
	
	/**
	 * check if twitter handle is not saved or updated then throw exception
	 * @return
	 */
	void twitterHandlesNotSaved() {
		int errorCode = grailsApplication.config.customExceptions.account.fourZeroZero.twitterHandleNotSaved.errorCode
		int status = grailsApplication.config.customExceptions.account.fourZeroZero.twitterHandleNotSaved.status
		String message = grailsApplication.config.customExceptions.account.fourZeroZero.twitterHandleNotSaved.message
		String extendedMessage = grailsApplication.config.customExceptions.account.fourZeroZero.twitterHandleNotSaved.extendedMessage
		String moreInfo = grailsApplication.config.customExceptions.account.fourZeroZero.twitterHandleNotSaved.moreInfo
		throw new AccountException(status,errorCode,message,extendedMessage,moreInfo)
	}
	
	
	/**
	 * check if social user is null throw social user not found exception
	 * @return
	 */
	void checkSocialUser(SocialUser socialUser) {
		if(socialUser == null){
			int errorCode = grailsApplication.config.customExceptions.account.fourZeroFour.socialUser.errorCode
			int status = grailsApplication.config.customExceptions.account.fourZeroFour.socialUser.status
			String message = grailsApplication.config.customExceptions.account.fourZeroFour.socialUser.message
			String extendedMessage = grailsApplication.config.customExceptions.account.fourZeroFour.socialUser.extendedMessage
			String moreInfo = grailsApplication.config.customExceptions.account.fourZeroFour.socialUser.moreInfo

			throw new AccountException(status,errorCode,message,extendedMessage,moreInfo)
		}
	}
	
	/**
	 * check if twitter access tokens are null then throw exception
	 * @return
	 */
	void twitterTokensAreNull() {
		int errorCode = grailsApplication.config.customExceptions.account.fourZeroZero.twitterTokensAreNull.errorCode
		int status = grailsApplication.config.customExceptions.account.fourZeroZero.twitterTokensAreNull.status
		String message = grailsApplication.config.customExceptions.account.fourZeroZero.twitterTokensAreNull.message
		String extendedMessage = grailsApplication.config.customExceptions.account.fourZeroZero.twitterTokensAreNull.extendedMessage
		String moreInfo = grailsApplication.config.customExceptions.account.fourZeroZero.twitterTokensAreNull.moreInfo
		throw new AccountException(status,errorCode,message,extendedMessage,moreInfo)
	}


}
