package com.novko.internal.categories;

import com.novko.internal.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

    Subcategory findByName(String name);
    void deleteByName(String name);

    @Query("select s.products from Subcategory s where s.name = ?1")
    List<Product> findProducts(String name);

}
