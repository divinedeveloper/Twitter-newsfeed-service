package com.karmaworldwide.projectactivities

import java.util.Date;

class ProjectReportVideo {

	ProjectReport projectReport
	
	String url
	
	boolean isDeleted,isActive
	
	Date dateCreated,lastUpdated,deletedAt
	
	static belongsTo = [projectReport:ProjectReport]
	
    static constraints = {
		deletedAt nullable:true
		url nullable:false
    }
}
