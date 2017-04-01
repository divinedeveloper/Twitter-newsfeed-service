package com.karmaworldwide.projectactivities

import java.util.Date;

class ProjectGroup {

	Project project
	
	String notification,name
	
	boolean isDeleted,isActive
	
	Date dateCreated,lastUpdated,deletedAt
	
	static belongsTo = [project:Project]
	
    static constraints = {
		notification nullable:true
		name nullable:true
		deletedAt nullable:true
    }
	
	static mapping = {
		notification sqlType:"text"
	}
}
