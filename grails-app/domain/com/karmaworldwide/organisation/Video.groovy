package com.karmaworldwide.organisation

class Video{
	
	String videoUrl
	
	boolean isDeleted
	
	Date lastUpdated
	Date dateCreated
	Date deletedAt
	
	static belongsTo = [organisation:Organisation]
	
	static constraints = {
		videoUrl nullable:true
		lastUpdated nullable:true
		dateCreated nullable:true
		deletedAt nullable:true
		isDeleted nullable:true
	}
}