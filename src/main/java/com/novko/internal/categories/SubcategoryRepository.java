package com.novko.internal.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

    Subcategory findByName(String name);
    void deleteByName(String name);

}
