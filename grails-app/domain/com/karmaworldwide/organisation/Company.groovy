package com.karmaworldwide.organisation

import java.util.Set;

import com.karmaworldwide.account.HashTags
import com.karmaworldwide.account.TwitterHandles
import com.karmaworldwide.account.User
import com.karmaworldwide.categories.Categories
import com.karmaworldwide.document.Documents;
import com.karmaworldwide.projectactivities.ProjectFunding

/**
 *
 * @author karmaworldwide
 * Domain of company which is of type organisation
 *
 */
class Company extends Organisation{
		
	CompanyType companyType
	
	User user
	
	Integer annualRevenueImpact, annualInvestmentImpact
	
	String socialMission
	
	enum CompanyType{
		Public, Private
	}
	
	static hasMany = [companyUser:CompanyUser, companyNgo:CompanyNgo,projectFunding:ProjectFunding]
	
	static constraints = {
		companyType blank:false
		annualRevenueImpact nullable:true
		annualInvestmentImpact nullable:true
		socialMission nullable:true
		user nullable:true
	}
	
	static mapping = {
		socialMission column: "social_mission", sqlType: "varchar(500)"
	}
	
	Set<Categories> getCategories() {
		CategoriesCompany.findAllByCompany(this).collect { it.categories }
	}
	Set<Documents> getDocument() {
		Documents.findAllByOrganisation(this).collect { it }
	}
	
}
