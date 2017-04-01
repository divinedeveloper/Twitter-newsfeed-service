dataSource {
	pooled = false
	jmxExport = true
	driverClassName = "com.mysql.jdbc.Driver"
	//dialect = org.hibernate.dialect.MySQLMyISAMDialect
	zeroDateTimeBehavior="convertToNull" //Java can't convert ''0000-00-00 00:00:00' to TIMESTAMP
	dialect = org.hibernate.dialect.MySQL5InnoDBDialect
	logSql = false
}
hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = true
	//    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
	cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
	singleSession = true // configure OSIV singleSession mode
    format_sql = false
}

// environment specific settings
environments {
    development {
		dataSource {
			//change to update locally if we  need to experiment a database change
			//change back to validate before committing
			dbCreate = "validate" // one of 'create', 'create-drop', 'update', 'validate', ''
			url = ""
			username = ""
			password = ""
		}
	}
    test {
		dataSource {
			dbCreate = "validate"
			url = ""
			username = ""
			password = ""
		}
	}
    production {
		dataSource {
			dbCreate = "validate"
			url = ""
			username = ""
			password = ""
			properties {
				// See http://grails.org/doc/latest/guide/conf.html#dataSource for documentation
			   jmxEnabled = true
               initialSize = 5
               maxActive = 50
               minIdle = 5
               maxIdle = 25
               maxWait = 10000
               maxAge = 10 * 60000
               timeBetweenEvictionRunsMillis = 5000
               minEvictableIdleTimeMillis = 60000
               validationQuery = "SELECT 1"
               validationQueryTimeout = 3
               validationInterval = 15000
               testOnBorrow = true
               testWhileIdle = true
               testOnReturn = false
               jdbcInterceptors = "ConnectionState"
               defaultTransactionIsolation = java.sql.Connection.TRANSACTION_READ_COMMITTED
			}
		}
	}
}
