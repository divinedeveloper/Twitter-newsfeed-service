package com.karmaworldwide.location


/**
 * @author karmaworldwide
 * This Domain Class contains city details
 */
class City {

    String name
	
	Date lastUpdated,dateCreated
	
	static belongsTo = [state: State]

    static constraints = {
		name blank: false, nullable: false
	}
	
	
}
