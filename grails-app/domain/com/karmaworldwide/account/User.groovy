package com.karmaworldwide.account

import com.karmaworldwide.organisation.Organisation
import com.karmaworldwide.projectactivities.ProjectFunding
import com.karmaworldwide.projectactivities.ProjectReport
import com.karmaworldwide.user.*


/**
 * @author karmaworldwide
 * This Domain Class contains the user account information
 */
class User {

	transient springSecurityService

	String username,tokenValue,firstName, lastName
	String password,unsubscribeReason,unsubscribeSuggestion
	String email
	String roleAuthority
	String salt
	
	Long roleId
	
	boolean isActive,isSubscribedToNewsLetter
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	
	Date lastUpdated
	Date dateCreated
	Date deletedAt,projectAssignedOn

	RegistrationStatus registrationStatus 
	Organisation organisation
	enum RegistrationStatus{
		Registered,PendingRequestDocuments,ApprovedRequestDocuments,PendingRequestPayment,ApprovedRequestPayment
	}
	String title
	
	static hasMany = [projectFunding:ProjectFunding,projectReport:ProjectReport]
	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: true
		email	blank:false ,email:true , unique: true
		deletedAt nullable:true
		projectAssignedOn nullable:true
		tokenValue nullable:true
		roleAuthority nullable:true
		roleId nullable:true
		firstName nullable:true
		lastName nullable:true
		registrationStatus nullable:true
		title nullable:true
		organisation nullable:true
		unsubscribeReason nullable:true
		unsubscribeSuggestion nullable:true
	}

	static mapping = {
		password column: '`password`'
		unsubscribeReason type: 'text'
		unsubscribeSuggestion type: 'text'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role }
	}
	Set<Profile> getProfile() {
		Profile.findAllByUser(this).collect { it }
	}
	Set<SocialUser> getSocialUser() {
		SocialUser.findAllByUser(this).collect { it }
	}
	String getSalt() {
		if (!this.salt) {
			this.salt = this.email
		}
		this.salt
	}
}
