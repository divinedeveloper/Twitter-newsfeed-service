package com.karmaworldwide.apis.error


import org.codehaus.groovy.grails.web.json.JSONObject;

class ErrorController {

    def grailsApplication
	
	public static final String STATUS = "status"
	public static final String ERRORCODE = "errorCode"
	public static final String MESSAGE = "message"
	public static final String EXTENDEDMESSAGE = "extendedMessage"
	public static final String MOREINFO = "moreInfo"
	public static final String ERROR = "error"
	public static final String DATETIMESTAMP = "dateTimeStamp"
	
	/**
	 * In any api, if there are server error like 500 internal server error
	 * throw error response object 
	 * @return
	 */
    def sendErrorResponse() { 
		
		def moreInfoOfException = request.exception
		
		int errorCode = grailsApplication.config.customExceptions.error.fiveZeroZero.errorCode
		int status = grailsApplication.config.customExceptions.error.fiveZeroZero.status
		String message = grailsApplication.config.customExceptions.error.fiveZeroZero.message
		String extendedMessage = grailsApplication.config.customExceptions.error.fiveZeroZero.extendedMessage
		String moreInfo = grailsApplication.config.customExceptions.error.fiveZeroZero.moreInfo
		
		JSONObject errorJson = new JSONObject();
		errorJson.put(STATUS,status);
		errorJson.put(ERRORCODE,errorCode);
		errorJson.put(MESSAGE,message);
		errorJson.put(EXTENDEDMESSAGE,extendedMessage);
		errorJson.put(MOREINFO,moreInfoOfException);
		errorJson.put(ERROR,true);
		errorJson.put(DATETIMESTAMP, new Date().time);
		
		log.error errorJson
		response.setStatus(status)
		render errorJson
	}
}
