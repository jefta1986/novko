package com.novko.internal.categories;

import java.util.Set;

import com.novko.internal.products.Product;

public interface JpaCategories {
	
	// Category
	void add(Category category);
	void update(Category category);
	void delete(String categoryName);
	Set<Category> getAllCategories();
	Category getCategoryByName(String categoryName);
	Category getCategoryById(Long id);

	// Subcategory
	void addSubcategory(Subcategory subcategory, String categoryName);
	void updateSubcategory(String categoryName, String subcategoryName, String newName);
	void deleteSubcategory(String categoryName, String subcategoryName);
	Subcategory getSubcategoryByName(String subcategoryName);
	
	//add Product to Subcategory
	void addProductToSubcategory(String subcategoryName, Product product);
	void deleteProductFromSubcategory(String subcategoryName, Product product);

}
