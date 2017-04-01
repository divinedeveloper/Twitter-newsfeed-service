package com.karmaworldwide.account

/**
 * @author karmaworldwide
 * This Domain Class contains the authentication token needed to login
 */
class AuthenticationToken {
    String username , authToken
	
    static constraints = {
		username blank:false
		authToken blank:false
    }
}
