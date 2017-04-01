package com.karmaworldwide.organisation

import com.karmaworldwide.projectactivities.Project

/**
 * 
 * @author karmaworldwide
 * Domain for company ngo relationship
 *
 */
class CompanyNgo {
	
	String status
	
	Company company
	
	Ngo ngo
	
	Project project 
	
	RelationshipStatus relationshipStatus
	
	enum RelationshipStatus{
		Requested, Approved, Declined
	}

	boolean isDeleted
	
	Date lastUpdated
	Date dateCreated
	Date deletedAt
	
	static belongsTo = [company:Company, ngo:Ngo]
	
    static constraints = {
		company blank:false
		ngo blank:false
		relationshipStatus nullable:true
		id composite: ['company', 'ngo']
		project nullable:true
		deletedAt nullable:true 
		status nullable:true
    }
}
