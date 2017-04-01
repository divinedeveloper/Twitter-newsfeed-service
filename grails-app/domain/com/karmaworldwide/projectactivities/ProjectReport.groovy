package com.karmaworldwide.projectactivities

import com.karmaworldwide.account.User

/**
 *
 * @author karmaworldwide
 * Domain for project report
 *
 */
class ProjectReport {
	
	String report
	
	Project project
	
	User user
	
	Goals goal
	
	Integer currentGoal
	
	boolean isDeleted
	
	String description
	Date dateCreated
	Date lastUpdated
	Date deletedAt
	
	static hasMany = [projectContent:ProjectContent,rojectReportVideo:ProjectReportVideo,projectReportImage:ProjectReportImage]
	
	static belongsTo = [project:Project]

    static constraints = {
		report blank:false
		project blank:false
		deletedAt nullable:true
		goal nullable:true
		description nullable:true
    }
	static mapping = {
		description column: "description", sqlType: "varchar(500)"
	}
}
