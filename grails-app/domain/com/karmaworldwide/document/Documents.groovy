package com.karmaworldwide.document

import java.util.Date;
import java.util.Set;

import com.karmaworldwide.account.User
import com.karmaworldwide.organisation.Organisation
import grails.rest.*

/**
 * @author karmaworldwide
 * This Domain Class contains details of Mail format
 */

class Documents {

	String title,description,link
	
	boolean isPublic,isDeleted
	
	Date deletedAt
	Date lastUpdated
	Date dateCreated
	
	User createdBy
	
	static belongsTo = [organisation : Organisation]
	
    static constraints = {
		title nullable:true
		description nullable:true
		link nullable:true
		isPublic nullable:true
		isDeleted nullable:true
		deletedAt nullable:true
		createdBy nullable:true
    }
}
