package com.novko.internal.categories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.novko.internal.dto.CategoryDto;
import com.novko.internal.dto.SubcategoryDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.novko.internal.products.Product;



@Repository
public class JpaCategoriesRepository implements JpaCategories {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(JpaCategoriesRepository.class.getName());

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
	
	
	
	@Override
	@Transactional
	public void add(Category category) {
		entityManager.persist(category);
	}

	@Override
	@Transactional
	public void update(Category category) {
		Category categoryDb = getCategoryById(category.getId());
		categoryDb.setName(category.getName());
		entityManager.merge(categoryDb);
	}

	@Override
	@Transactional(readOnly = true)
	public Category getCategoryById(Long id) {
		return entityManager.find(Category.class, id);
	}


	@Override
	@Transactional
	public void delete(String categoryName) {
		entityManager.remove(getCategoryByName(categoryName));
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Set<Category> getAllCategories() {
		return new HashSet<Category>( entityManager.createQuery("from Category").getResultList() );
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Set<Subcategory> getAllSubcategories() {
		return new HashSet<Subcategory>( entityManager.createQuery("from Subcategory").getResultList() );
	}

	@Override
	@Transactional(readOnly = true)
	public Set<Subcategory> getAllSubcategoriesWithProducts() {
		HashSet<Subcategory> subcategories = new HashSet<Subcategory>( entityManager.createQuery("select s from Subcategory s left join fetch s.products p").getResultList() );

		return subcategories;
	}

	@Override
	@Transactional(readOnly = true)
	public Category getCategoryByName(String categoryName) {
		return entityManager.createQuery("select c from Category c WHERE c.name = ?1", Category.class).setParameter(1, categoryName).getSingleResult();
	}

	
	
	
// ####### SubCategories  ##########	
	
	
	
	@Override
	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "subcategoryCache",key = "#subcategoryName")
	public Subcategory getSubcategoryByName(String subcategoryName) {
		return entityManager.createQuery("select s from Subcategory s left join fetch s.products p where s.name = ?1", Subcategory.class).setParameter(1, subcategoryName).getSingleResult();
	}
	
	
	@Override
	@Transactional
	public void addSubcategory(Subcategory subcategory, String categoryName) {
		Category category = getCategoryByName(categoryName);
		category.addSubcategory(subcategory);
	}


	@Override
	@Transactional
	public void deleteSubcategory(String categoryName, String subcategoryName) {
		getCategoryByName(categoryName).deleteSubcategory(subcategoryName);
		entityManager.remove(this.getSubcategoryByName(subcategoryName));
	}
	

	
//product methods
	@Override
	@Transactional
	public void addProductToSubcategory(String subcategoryName, Product product) {
		getSubcategoryByName(subcategoryName).addProduct(product);
	}



	@Override
	@Transactional
	public void deleteProductFromSubcategory(String subcategoryName, Product product) {
		getSubcategoryByName(subcategoryName).deleteProduct(product);
	}


	@Override
	@Transactional
	public void updateSubcategory(String categoryName, String subcategoryName, String newName) {
		getCategoryByName(categoryName).updateSubcategory(subcategoryName, newName);
	}


	@Override
	@Transactional(readOnly = true)
	public List<CategoryDto> getCategoriesWithSubcategories() {

		List<CategoryDto> categoryDtoList = new ArrayList<>();
		for (Category category : getAllCategories()) {
			CategoryDto categoryDto = new CategoryDto();
			categoryDto.setName(category.getName());

			Set<SubcategoryDto> subcategoryDtoList = new HashSet<>();

			Set<Subcategory> subcategoryList = category.getSubcategories();
			for (Subcategory subcategory : subcategoryList) {
				SubcategoryDto subcategoryDto = new SubcategoryDto();
				subcategoryDto.setName(subcategory.getName());
				subcategoryDtoList.add(subcategoryDto);
			}
			categoryDto.setSubcategories(subcategoryDtoList);

			categoryDtoList.add(categoryDto);
		}
		return  categoryDtoList;
    }





}
