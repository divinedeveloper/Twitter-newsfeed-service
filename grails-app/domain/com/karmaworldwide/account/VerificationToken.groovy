package com.karmaworldwide.account

/**
 * @author karmaworldwide
 * This Domain Class contains the verification token needed to reset password after he has forgot it
 */
class VerificationToken {
	
	String email , verificationToken
	
	static belongsTo = [user:User]

    static constraints = {
		email email:true,blank:false
		verificationToken blank:false
    }
}
