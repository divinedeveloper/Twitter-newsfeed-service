package com.karmaworldwide.user

import com.karmaworldwide.account.User
import com.karmaworldwide.location.City
import com.karmaworldwide.location.Country
import com.karmaworldwide.location.State

/**
 * @author karmaworldwide
 * This Domain Class contains profile details of user
 */
class Profile {
	
	Gender gender
	
	Long  phoneNumber, mobile
	
	String  description, imageUrl, addresLine1, addresLine2 
	
	String zipCode
	
	Date dob, dateCreated, lastUpdated
	
	enum Gender{
		Male, Female , Unknown
	}
	
	Country country
	
	State state
	
	City city 
	
	User user
	
	static belongsTo = [country: Country, state: State, city: City, user:User]
	
	static mapping = {
		version false
		description column: "description", sqlType: "varchar(500)"
	}
	
	static constraints = {
		dob max : new Date(),nullable:true
		phoneNumber nullable:true
		mobile nullable:true
		description nullable:true
		gender nullable:true
		imageUrl nullable:true
		country nullable:true
		state nullable:true
		city nullable:true
		user nullable:false
		addresLine1 nullable:true
		addresLine2 nullable:true
		zipCode nullable:true
	}	
	
}
