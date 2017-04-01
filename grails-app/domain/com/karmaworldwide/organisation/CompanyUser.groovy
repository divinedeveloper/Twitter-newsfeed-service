package com.karmaworldwide.organisation

import com.karmaworldwide.account.User

/**
 * 
 * @author karmaworldwide
 * Domain for company users 
 *
 */
class CompanyUser {
	
	Company company
	
	User user
	
	Date lastUpdated
	Date dateCreated
	
	static belongsTo = [company:Company,user:User]

    static constraints = {
		company blank:false
		user blank:false
	}
}
