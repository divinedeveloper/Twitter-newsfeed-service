package com.karmaworldwide.organisation

import com.karmaworldwide.categories.Categories

class CategoriesNgo implements Serializable{
	
	Categories categories
	
	Ngo ngo

	static mapping = {
		version false
		id composite: ['categories', 'ngo']
		table 'categories_ngo'
	}
}

