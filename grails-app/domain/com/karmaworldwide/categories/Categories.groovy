package com.karmaworldwide.categories

import com.karmaworldwide.organisation.Ngo
import com.karmaworldwide.projectactivities.Goals
/*import grails.rest.**/
/**
 * 
 * @author karmaworldwide
 * Domain for categories of project
 *
 */
class Categories {
    static marshalling = {
        shouldOutputIdentifier true
        shouldOutputVersion false
        shouldOutputClass true
    }

    String category, description, imageUrl
	
	SubCategory subcategory
	
	enum SubCategory{
		subjective, objective
	}
	
	boolean isDeleted
	
	Date lastUpdated
	Date dateCreated
	Date deletedAt

    static hasMany = [ngo:Ngo,goals:Goals]
	
	
    static constraints = {
		category blank:false
		subcategory blank:false
		deletedAt nullable:true
		description nullable:true
		imageUrl nullable:true
    }
	
}
