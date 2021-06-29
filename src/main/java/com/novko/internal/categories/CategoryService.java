package com.novko.internal.categories;

import com.novko.api.exception.CustomResourceNotFoundException;
import com.novko.internal.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    public Category saveCategory(String categoryName, String categoryNameSr) {
        return categoryRepository.save(new Category(categoryName, categoryNameSr));
    }


    @Transactional
    //	@CacheEvict(value = "subcategory", allEntries = true)
    public Category addSubcategory(String subcategoryName, String subcategoryNameSr, String categoryName) {
        Category category = this.findByName(categoryName);
        return category.addSubcategory(subcategoryName, subcategoryNameSr);
    }


    @Transactional
    //	@CacheEvict(value = "subcategory", allEntries = true)
    public void deleteSubcategory(String categoryName, String subcategoryName) {
        Optional<Category> optionalCategory = Optional.ofNullable(categoryRepository.findByName(categoryName).orElseThrow(() -> new CustomResourceNotFoundException("Category with that name doesn't exist")));

        if (!optionalCategory.isPresent()) {
            throw new CustomResourceNotFoundException("Category doesn't exist");
        }

        Category category = optionalCategory.get();
        Optional<Subcategory> optionalSubcategory = category.getSubcategoryByName(subcategoryName);

        if (!optionalSubcategory.isPresent()) {
            throw new CustomResourceNotFoundException("Subcategory doesn't exist");
        }

        Subcategory subcategory = optionalSubcategory.get();

        Set<Product> products = subcategory.getProducts();
        if (products == null || products.isEmpty()) {
            category.deleteSubcategory(subcategoryName);
        }

        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            p.setSubcategory(null);
        }

        category.deleteSubcategory(subcategoryName);
    }

    @Transactional
    public void deleteByName(String categoryName) {
        Optional<Category> optionalCategory = Optional.ofNullable(categoryRepository.findByName(categoryName).orElseThrow(() -> new CustomResourceNotFoundException("Category with that name doesn't exist")));

        Set<Subcategory> subcategories = optionalCategory.get().getSubcategories();
        if (subcategories == null || subcategories.isEmpty()) {
            categoryRepository.deleteByName(categoryName);
        }

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
    public Subcategory updateSubcategory(String categoryName, Long subcategoryId, String subcategoryName, String newName, String newNameSr) {
        Optional<Category> optionalCategory = Optional.ofNullable(categoryRepository.findByName(categoryName).orElseThrow(() -> new CustomResourceNotFoundException("Category with that name doesn't exist")));
        Subcategory subcategory = optionalCategory.get().updateSubcategory(subcategoryId, newName, newNameSr);
        return subcategoryRepository.save(subcategory);
    }


    //Product methods
    @Transactional
//	@CacheEvict(value = "subcategory", allEntries = true)
    public void addProductToSubcategory(String subcategoryName, Product product) {
        Subcategory subcategory = subcategoryRepository.findByName(subcategoryName);
        if (subcategory != null) {
            subcategory.addProduct(product);
        }
    }


    @Transactional
    public Category updateCategory(Long id, String name, String nameSr) {
        Category category = categoryRepository.findById(id).get();
        category.setName(name);
        category.setNameSr(nameSr);
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
    public List<Category> findAllCategoriesWithSubcategories() {
        return categoryRepository.findCategoriesWithSubcategories();
    }

    // subcategory methods

    @Transactional(readOnly = true)
    public List<Subcategory> findAllSubcategories() {
        return subcategoryRepository.findAll();
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

    @Transactional(readOnly = true)
    public List<Product> findProductsInSubcategory(Pageable pageable, String subcategoryName) {
        List<Product> products = subcategoryRepository.findProductsPages(pageable, subcategoryName).getContent();
        return products;

    }

}
