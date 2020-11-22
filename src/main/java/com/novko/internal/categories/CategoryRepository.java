package com.novko.internal.categories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

//category
   boolean existsCategoryByName(String name);
//   List<Category> findAll();
//   Optional<Category> findById(Long id);
   Optional<Category> findByName(String name);
   void deleteByName(String name);

   @Query("select c.subcategories from Category c where c.name = ?1")
   List<Subcategory> findSubcategories(String name);

//subcategory
//   @Query("select s.products from Subcategory s where s.name = ?1")
//   List<Product> findProducts(String name);

}
