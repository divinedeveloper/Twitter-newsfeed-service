package com.karmaworldwide.organisation

import com.karmaworldwide.account.User
import com.karmaworldwide.projectactivities.Project
import com.karmaworldwide.projectactivities.ProjectContent

/**
 *
 * @author karmaworldwide
 * Domain for ngo users
 *
 */
class NgoUser {

	Ngo ngo
	
	User user
	
	Project project
	
	static hasMany = [project:Project,projectContent:ProjectContent]
	
	static belongsTo = [Ngo,User,Project]
	
    static constraints = {
		ngo blank:false
		user blank:false
		project nullable:true
    }
}
