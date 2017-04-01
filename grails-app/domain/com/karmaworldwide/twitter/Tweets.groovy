package com.karmaworldwide.twitter


import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import com.karmaworldwide.account.HashTags
import com.karmaworldwide.account.TwitterHandles
/**
 * Tweets
 * A domain class describes the data object and it's mapping to the database
 */

@ToString(includeNames = true, includeFields = true, excludes = 'dateCreated,lastUpdated,metaClass')
@EqualsAndHashCode

class Tweets {

    /* Default (injected) attributes of GORM */
    Long id
    Long version
	Long tweetId
	Long twitterUserId	//user id of the user who has tweeted
	
	String tweetText, userScreenName, name	//name of user who has tweeted
	String userProfileImageUrl,expandedUrls
	HashTags hashTags
	TwitterHandles twitterHandles
	

	boolean isDeleted
	
	TweetStatus tweetStatus
	
	enum TweetStatus{
		New,Approved,Rejected
	}
	
    /* Automatic timestamping of GORM */
    Date dateCreated
    Date lastUpdated
	Date tweetCreatedAt, deletedAt

    // static belongsTo = [] // tells GORM to cascade commands: e.g., delete this object if the "parent" is deleted.
    // static hasOne = [] // tells GORM to associate another domain object as an owner in a 1-1 mapping
    // static hasMany = [] // tells GORM to associate other domain objects for a 1-n or n-m mapping
    // static mappedBy = [] // specifies which property should be used in a mapping

    static mapping = {
        // table name: "a table name", schema: "a schema(optional)", catalog: "a catalog (optional)"
		expandedUrls column: "expanded_urls", sqlType: "mediumtext"
    }
    static constraints = {
		deletedAt nullable:true
		userScreenName nullable:true
		name nullable:true
		userProfileImageUrl nullable:true
		hashTags nullable:true
		twitterHandles nullable:true
		expandedUrls nullable:true
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
