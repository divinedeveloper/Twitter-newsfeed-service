package com.karmaworldwide.account

/**
 * @author karmaworldwide
 * This Domain Class contains the role details
 */
class Role {

    static marshalling = {
        shouldOutputIdentifier true
        shouldOutputVersion false
        shouldOutputClass false
    }

    String authority
	String displayAuthority
	
	static mapping = {
		cache true
		
	}

	static constraints = {
		authority blank: false, unique: true
		displayAuthority blank: false, unique: true
	}
}
