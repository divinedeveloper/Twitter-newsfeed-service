package com.karmaworldwide.account


import java.util.Date;

import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode

import com.karmaworldwide.organisation.Organisation
/**
 * TwitterHandles
 * A domain class describes the data object and it's mapping to the database
 */

@ToString(includeNames = true, includeFields = true, excludes = 'dateCreated,lastUpdated,metaClass')
@EqualsAndHashCode

class TwitterHandles {

    /* Default (injected) attributes of GORM */
    Long id
//    Long version
	
	//since id of handle is used to get latest tweets greater than sinceId for that particular twitter handle from twitter
	Long handleSinceId

	String handleName
	
	boolean isActive,isDeleted
	
    /* Automatic timestamping of GORM */
    Date dateCreated
    Date lastUpdated
	Date deletedAt
	
    static belongsTo = [organisation:Organisation] // tells GORM to cascade commands: e.g., delete this object if the "parent" is deleted.
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
		handleName nullable:false,blank:false,unique:true
		deletedAt nullable:true
		isDeleted nullable:true
		isActive nullable:true
		handleSinceId nullable:true
		organisation nullable:true
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
