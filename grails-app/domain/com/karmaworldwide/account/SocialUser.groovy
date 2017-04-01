package com.karmaworldwide.account


import java.sql.Timestamp;
import java.util.Date;

import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode
import grails.rest.Resource
/**
 * SocialUser
 * A domain class describes the data object and it's mapping to the database
 */

@ToString(includeNames = true, includeFields = true, excludes = 'dateCreated,lastUpdated,metaClass')
@EqualsAndHashCode

@Resource(formats=['json', 'xml'])
class SocialUser {

    /* Default (injected) attributes of GORM */
    Integer id
//    Long version
	
	String fbExtendedAccessToken, twitterAccessToken, twitterAccessTokenSecret
	String displayName,picture,facebook,twitter
	
	Date fbExtendedAccessTokenExpires, deletedAt
	
	boolean isCorporatesApproved, isNgosApproved, isPeopleSignUpApproved
	boolean isFundingRoundsClosed, ourFunding, isDeleted
	boolean ourProjectActivities, otherProjectUpdates

    /* Automatic timestamping of GORM */
    Date dateCreated
    Date lastUpdated
	
	User user

     static belongsTo = [user:User] // tells GORM to cascade commands: e.g., delete this object if the "parent" is deleted.
    // static hasOne = [] // tells GORM to associate another domain object as an owner in a 1-1 mapping
    // static hasMany = [] // tells GORM to associate other domain objects for a 1-n or n-m mapping
    // static mappedBy = [] // specifies which property should be used in a mapping

    static mapping = {
        // http://grails.org/doc/latest/ref/Database%20Mapping/table.html
        // table name: "a table name", schema: "a schema(optional)", catalog: "a catalog (optional)"
		version false
		id size: 11 , generator: 'identity'
		dateCreated column: 'created_at'
		lastUpdated column: 'updated_at'
		displayName column: 'displayName'
    }
    static constraints = {
        // http://grails.org/doc/latest/ref/Constraints/Usage.html
		fbExtendedAccessToken nullable:true
		fbExtendedAccessTokenExpires nullable:true
		twitterAccessToken nullable:true
		twitterAccessTokenSecret nullable:true
		user nullable:false
		displayName nullable:true
		picture nullable:true
		facebook nullable:true
		twitter nullable:true
		deletedAt nullable:true
		
    }
    /*
     * Methods of the Domain Class
    */
    // @Override
    // Override toString for a nicer / more descriptive UI
    // public String toString() {
    // return "${name}";
    // }
}
