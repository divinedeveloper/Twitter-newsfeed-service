package com.karmaworldwide.organisation

import java.util.List;
import java.util.Set;

import javax.swing.Spring.ScaleSpring;

import com.karmaworldwide.location.City;
import com.karmaworldwide.location.Country;
import com.karmaworldwide.location.State;
import com.karmaworldwide.categories.Categories;
import com.karmaworldwide.document.Documents
import com.karmaworldwide.account.HashTags
import com.karmaworldwide.account.TwitterHandles

/**
 *
 * @author karmaworldwide
 * Domain for super class organisation
 */
class Organisation {
	String imageUrl,videoUrl,name,description,tickerSymbol,websiteUrl,addresLine1, addresLine2
	
	String zipCode
	
	Integer totalNoOfBenefeciaries
	
	BigDecimal annualRevenue
	
	boolean isActive, isVerified, isDeleted
	
	Date lastUpdated
	Date dateCreated
	Date deletedAt
	
	Country country
	State state
	City city
	
	Status status
	
	enum Status{
		Registered,RequestedDocuments,DocumentsReceived/*,RequestedPayment,PaymentReceived*/,Approved
	}
	
	static hasMany = [videos:Video,documents:Documents,twitterHandles:TwitterHandles]
	
	static constraints = {
		imageUrl nullable:true
		videoUrl nullable:true
		websiteUrl nullable:true
		name blank:false
		description nullable:true
		tickerSymbol nullable:true,unique:true
		annualRevenue nullable:true
		totalNoOfBenefeciaries nullable:true
		deletedAt nullable:true
		addresLine1 nullable:true
		addresLine2 nullable:true
		country nullable:true
		state nullable:true
		city nullable:true
		zipCode nullable:true
		status blank:false,nullable:true
	}
	
	static mapping = {
		description column: "description", sqlType: "varchar(500)"
		annualRevenue scale: 2
	}
    
	Set<Video> getVideo() {
		Video.findAllByOrganisation(this).collect { it }
	}
 
	 Set<TwitterHandles> getTwitterHandles() {
		 TwitterHandles.findAllByOrganisation(this).collect { it }
	 }
}

