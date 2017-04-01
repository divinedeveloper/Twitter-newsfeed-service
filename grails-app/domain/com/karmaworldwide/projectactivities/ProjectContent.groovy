package com.karmaworldwide.projectactivities

import com.karmaworldwide.organisation.NgoUser

/**
 *
 * @author karmaworldwide
 * Domain for project content
 *
 */
class ProjectContent {
	
	Project project
	
	NgoUser ngoUser
	
	String workImagesUrl, workVideoUrl
	
	boolean isDeleted 
	
	Date dateCreated
	Date lastUpdated
	Date deletedAt

	static belongsTo = [ngoUser:NgoUser,project:Project]
	
    static constraints = {
		ngoUser blank:false
		project blank:false
		workImagesUrl nullable:true
		workVideoUrl nullable:true
		deletedAt nullable:true
    }
}
