package com.karmaworldwide.organisation

import com.karmaworldwide.categories.Categories

class CategoriesCompany implements Serializable{
	
	Categories categories
	
	Company company

	static mapping = {
		version false
		id composite: ['categories', 'company']
		table 'categories_company'		
	}
}
