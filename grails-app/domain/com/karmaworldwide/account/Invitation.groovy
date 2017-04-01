package com.karmaworldwide.account

import com.karmaworldwide.organisation.Company
import com.karmaworldwide.organisation.Ngo

/**
 * Domain to store email invitations for corporate and ngo users
 * @author karmaworldwide
 *
 */
class Invitation {
	
	String email, randomCode,firstName, lastName
	
	Role role
	
	Company company
	
	Ngo ngo
	
	boolean isDeleted
	
	Date lastUpdated,dateCreated,deletedAt
	
	Status status
	
	enum Status{
		Pending, Accepted, Declined, Expired
	}
		
    static constraints = {
		email blank:false ,email:true
		role nullable:false
		company nullable:true
		ngo nullable:true
		randomCode nullable:true
		deletedAt nullable:true
		status nullable:true
		firstName nullable:true
		lastName nullable:true
	}
}
