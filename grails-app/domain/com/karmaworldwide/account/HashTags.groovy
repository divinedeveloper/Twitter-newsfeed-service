package com.karmaworldwide.account


import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode

import com.karmaworldwide.organisation.Organisation
import com.karmaworldwide.projectactivities.Project
/**
 * HashTags
 * A domain class describes the data object and it's mapping to the database
 */

@ToString(includeNames = true, includeFields = true, excludes = 'dateCreated,lastUpdated,metaClass')
@EqualsAndHashCode

class HashTags {

    /* Default (injected) attributes of GORM */
    Long id
//    Long version
	
	//since id of hashtag is used to get latest tweets greater than sinceId for that particular hashtag from twitter
	Long hashTagSinceId																																																																																																																																												

	String hashTag
	
	boolean isActive,isDeleted,isDefault
	
    /* Automatic timestamping of GORM */
    Date dateCreated
    Date lastUpdated
	Date deletedAt

    // static belongsTo = [] // tells GORM to cascade commands: e.g., delete this object if the "parent" is deleted.
    // static hasOne = [] // tells GORM to associate another domain object as an owner in a 1-1 mapping
    //static hasMany = [] // tells GORM to associate other domain objects for a 1-n or n-m mapping
    // static mappedBy = [] // specifies which property should be used in a mapping

    static mapping = {
        // http://grails.org/doc/latest/ref/Database%20Mapping/table.html
        // table name: "a table name", schema: "a schema(optional)", catalog: "a catalog (optional)"
		version false
    }
    static constraints = {
        // http://grails.org/doc/latest/ref/Constraints/Usage.html
		hashTag nullable:false,blank:false
		deletedAt nullable:true
		isDeleted nullable:true
		isActive nullable:true
		hashTagSinceId nullable:true
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
