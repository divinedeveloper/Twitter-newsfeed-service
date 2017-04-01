package com.karmaworldwide.account

/**
 * @author karmaworldwide
 * This Domain Class contains the registration token need to verify user
 */
class RegistrationToken {
	String regToken
	
	static belongsTo = [user:User]

	static constraints = {
		regToken blank:false
	}
}
