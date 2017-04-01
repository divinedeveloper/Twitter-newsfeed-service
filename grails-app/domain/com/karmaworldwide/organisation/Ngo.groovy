package com.karmaworldwide.organisation

import java.util.Set;

import com.karmaworldwide.account.User
import com.karmaworldwide.categories.Categories
import com.karmaworldwide.document.Documents;
import com.karmaworldwide.projectactivities.Project
import com.karmaworldwide.organisation.CategoriesNgo

/**
 * @author karmaworldwide
 * Domain for Ngo which is of type organisation
 */
class Ngo extends Organisation{
	
	NgoType ngoType
	
	User user
	
	String registrationNo
	
	enum NgoType{
		CommunityNgo, CityNgo, StateNgo, RegionNgo, CountryNgo, InternationalNgo
	}
	
	static hasMany = [companyNgo:CompanyNgo,categories:Categories, ngoUser:NgoUser,project:Project]
	
	static belongsTo = [Categories]
	
    static constraints = {
		ngoType blank:false
		registrationNo nullable:true,unique:true
		user nullable:true
    }
	
	Set<Categories> getCategoriesByNgo() {
		CategoriesNgo.findAllByNgo(this).collect { it.categories }
		
	}
	
	
	Set<Documents> getDocument() {
		Documents.findAllByOrganisation(this).collect { it }
	}
}
