package com.novko.internal.categories;

import com.novko.api.exception.CustomResourceNotFoundException;
import com.novko.internal.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
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
    public Category saveCategory(String categoryName) {
            return categoryRepository.save(new Category(categoryName));
    }


    @Transactional
    //	@CacheEvict(value = "subcategory", allEntries = true)
    public Category addSubcategory(String subcategoryName, String categoryName) {
        Category category = this.findByName(categoryName);

//        Optional<Category> optionalCategory = Optional.ofNullable(categoryRepository.findByName(categoryName).orElseThrow(() -> new CustomResourceNotFoundException("Category with that name doesn't exist")));
        return category.addSubcategory(subcategoryName);
    }


    @Transactional
    //	@CacheEvict(value = "subcategory", allEntries = true)
    public void deleteSubcategory(String categoryName, String subcategoryName) {
        Optional<Category> optionalCategory = Optional.ofNullable(categoryRepository.findByName(categoryName).orElseThrow(() -> new CustomResourceNotFoundException("Category with that name doesn't exist")));

        Optional<Subcategory> optionalSubcategory = optionalCategory.get().getSubcategoryByName(subcategoryName);
        Set<Product> products = optionalSubcategory.get().getProducts();
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            p.setSubcategory(null);
        }

        optionalCategory.get().deleteSubcategory(subcategoryName);
    }

    @Transactional
    public void deleteByName(String categoryName) {
        Optional<Category> optionalCategory = Optional.ofNullable(categoryRepository.findByName(categoryName).orElseThrow(() -> new CustomResourceNotFoundException("Category with that name doesn't exist")));

        Set<Subcategory> subcategories = optionalCategory.get().getSubcategories();
        Iterator<Subcategory> subcategoryIterator = subcategories.iterator();
        while (subcategoryIterator.hasNext()) {
            Subcategory subcategory = subcategoryIterator.next();

            Set<Product> products = subcategory.getProducts();
            Iterator<Product> productIterator = products.iterator();
            while (productIterator.hasNext()) {
                productIterator.next().setSubcategory(null);
            }
            subcategory.getProducts().clear();
        }

        categoryRepository.deleteByName(categoryName);
    }


    //Subcategory methods
    @Transactional
    //	@CacheEvict(value = "subcategory", allEntries = true)
    public Subcategory updateSubcategory(String categoryName, Long subcategoryId, String subcategoryName, String newName) {
        Optional<Category> optionalCategory = Optional.ofNullable(categoryRepository.findByName(categoryName).orElseThrow(() -> new CustomResourceNotFoundException("Category with that name doesn't exist")));
        return optionalCategory.get().updateSubcategory(subcategoryName, newName);
    }


    //Product methods
    @Transactional
//	@CacheEvict(value = "subcategory", allEntries = true)
    public void addProductToSubcategory(String subcategoryName, Product product) {
        subcategoryRepository.findByName(subcategoryName).addProduct(product);
    }


    @Transactional
    public Category updateCategory(Long id, String name) {
        Category category = categoryRepository.findById(id).get();
        category.setName(name);
        return categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new CustomResourceNotFoundException("Category with that name doesn't exist"));
    }

    @Transactional(readOnly = true)
    public Category findByName(String categoryName) {
        return categoryRepository.findByName(categoryName).orElseThrow(() -> new CustomResourceNotFoundException("Category with that name doesn't exist"));
    }

    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Subcategory> findSubcategories(String categoryName) {
        return categoryRepository.findSubcategories(categoryName);
    }

    @Transactional(readOnly = true)
    public Optional<Subcategory> findSubcategoryById(Long id) {
        return subcategoryRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Product> findProducts(String subcategoryName) {
        return subcategoryRepository.findProducts(subcategoryName);
    }

}
