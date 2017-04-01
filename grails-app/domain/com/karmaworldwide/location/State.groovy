package com.karmaworldwide.location


/**
 * @author karmaworldwide
 * This Domain Class contains State details
 */
class State {
	
	String name
	
	Country country
	
	Date lastUpdated,dateCreated
	
	static hasMany = [city: City]
	
	static belongsTo = [country: Country]
	
    static constraints = {
		name blank: false, nullable: false
		country blank: false , nullable:false
	}
	
	
}
