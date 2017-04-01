package com.karmaworldwide.projectactivities

import com.karmaworldwide.categories.Categories
import com.karmaworldwide.location.City
import com.karmaworldwide.location.Country
import com.karmaworldwide.location.State
import com.karmaworldwide.organisation.Ngo
import com.karmaworldwide.organisation.NgoUser
import com.karmaworldwide.account.HashTags

/**
 *
 * @author karmaworldwide
 * Domain of project details
 *
 */
class Project {
	
	String title,shortDescription,longDescription,imageUrl,videoUrl,region, microSite, status
	
	Integer totalBenefeciaries,overAllGoal,goalAmount,currentAmount,noOfPeopleInvolved
	
	boolean isFeaturedProject, isCrowdSourced, isDeleted, isActive, trackGoal
	
	Date lastUpdated
	Date dateCreated
	Date deletedAt
	
	static hasMany = [projectFunding:ProjectFunding,ngoUser:NgoUser,
		projectContent:ProjectContent,projectReport:ProjectReport,goals:Goals,
		country:Country,state:State,city:City,hashTags:HashTags]
	
	static belongsTo = [ngo:Ngo]
	

    static constraints = {
		title blank:false
		shortDescription nullable:true
		longDescription nullable:true
		imageUrl nullable:true
		videoUrl nullable:true
		region nullable:true
		deletedAt nullable:true
		microSite nullable:true
		status nullable:true
	}
	
	static mapping = {
		longDescription column: "long_description", sqlType: "varchar(500)"
	 }
	
	Set<ProjectFunding> getFund() {
		ProjectFunding.findAllByCompany(this).collect { it.company }
	}
}
