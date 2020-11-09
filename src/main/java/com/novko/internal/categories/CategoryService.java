package com.novko.internal.categories;

import com.novko.internal.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.Set;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, SubcategoryRepository subcategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    @Transactional
    //	@CacheEvict(value = "subcategory", allEntries = true)
    public void addSubcategory(String subcategoryName, String categoryName) {
        categoryRepository.findByName(categoryName).addSubcategory(new Subcategory(subcategoryName));
    }


    @Transactional
    //	@CacheEvict(value = "subcategory", allEntries = true)
    public void deleteSubcategory(String categoryName, String subcategoryName) {
        Subcategory subcategory = categoryRepository.findByName(categoryName).getSubcategoryByName(subcategoryName);
        Set<Product> products = subcategory.getProducts();
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            p.setSubcategory(null);
        }

		categoryRepository.findByName(categoryName).deleteSubcategory(subcategoryName);
    }

    @Transactional
    public void deleteByName(String categoryName) {
        Set<Subcategory> subcategories = categoryRepository.findByName(categoryName).getSubcategories();
        Iterator<Subcategory> subcategoryIterator = subcategories.iterator();
        while (subcategoryIterator.hasNext()){
            Subcategory subcategory = subcategoryIterator.next();

            Set<Product> products = subcategory.getProducts();
            Iterator<Product> productIterator = products.iterator();
            while (productIterator.hasNext()){
                productIterator.next().setSubcategory(null);
            }
            subcategory.getProducts().clear();
        }

        categoryRepository.deleteByName(categoryName);
    }


//Subcategory methods
    @Transactional
    //	@CacheEvict(value = "subcategory", allEntries = true)
    public void updateSubcategory(String categoryName, String subcategoryName, String newName) {
        categoryRepository.findByName(categoryName).updateSubcategory(subcategoryName, newName);
    }


//Product methods
    @Transactional
//	@CacheEvict(value = "subcategory", allEntries = true)
    public void addProductToSubcategory(String subcategoryName, Product product) {
        subcategoryRepository.findByName(subcategoryName).addProduct(product);
    }

}
