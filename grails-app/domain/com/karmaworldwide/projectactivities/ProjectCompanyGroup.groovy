package com.karmaworldwide.projectactivities

import com.karmaworldwide.organisation.Company

class ProjectCompanyGroup implements Serializable{

	ProjectGroup projectGroup
	
	Company company
   
	static mapping = {
		version false
		id composite: ['projectGroup', 'company']
	}
}
