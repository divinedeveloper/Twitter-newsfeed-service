package com.karmaworldwide.location


/**
 * @author karmaworldwide
 * This Domain Class contains Country details
 */
class Country {
	
	String name
    String code
	
	Date lastUpdated,dateCreated
	
	static hasMany = [state: State]

    static constraints = {
		name blank: false, nullable: false , unique:true
		code nullable : false , unique:true
    }
	
	
}
