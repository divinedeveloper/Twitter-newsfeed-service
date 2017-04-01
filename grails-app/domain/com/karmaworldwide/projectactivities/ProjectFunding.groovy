package com.karmaworldwide.projectactivities

import com.karmaworldwide.account.User
import com.karmaworldwide.categories.Categories
import com.karmaworldwide.organisation.Company

/**
 *
 * @author karmaworldwide
 * Domain of project funding details
 *
 */
class ProjectFunding {
	
	Integer funds
	
	Company company
	
	User user
	
	Project project
	
	Date lastUpdated
	Date dateCreated
	
	static belongsTo = [user:User,company:Company,project:Project]

    static constraints = {
		company blank:false, nullable:true
		user blank:false, nullable:true
		project blank:false
		funds blank:false
    }
}
