package com.novko.internal.products;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

	Page<Product> findAll(Predicate predicate, Pageable pageable);
	Page<Product> findAll(Pageable pageable);
	List<Product> findAll();
	Product findByCode(String code);
	Product findByName(String name);
	void deleteByCode(String code);
	void deleteById(Long id);

	List<Product> findBySubcategoryIsNull();


	//sa slikama
//	@Query("SELECT p FROM Product p LEFT JOIN FETCH p.images i WHERE i.defaultPicture=TRUE")
//	List<Product> findAllWithDefaultImage();


	//find product with default picture
//	@Query("select p from Product p left join fetch Images i where i.product is not null and p.name = ?1")
//	Product findProduct(String productName);
//
//	@Query("select p from Product p left join fetch p.images i where p.name = ?1")
//	Product findProductWithImages(String productName);

}
