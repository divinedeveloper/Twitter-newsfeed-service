package com.karmaworldwide.projectactivities

import com.karmaworldwide.categories.Categories


/**
 *
 * @author karmaworldwide
 * Domain for project goals mainly subgoals
 *
 */
class Goals {

	Project project
	
	Integer goalTarget,goalAchieved
	
	String goal, description
	
	boolean isDeleted
	
	Date lastUpdated,dateCreated,deletedAt
		
	Categories categories
	
	static belongsTo = [project:Project,categories:Categories]
	
    static constraints = {
		project blank:false
		goal blank:false
		deletedAt nullable:true
		description nullable:true
    }
	
	static mapping = {
		description column: "description", sqlType: "varchar(500)"
	 }
}
