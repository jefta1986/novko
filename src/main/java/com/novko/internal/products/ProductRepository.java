package com.novko.internal.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findAll();

	//nije dobar query
	@Query("SELECT p FROM Product p LEFT JOIN FETCH p.images i WHERE i.defaultPicture=TRUE")
	List<Product> findAllWithDefaultImage();
	Product findByCode(String code);
	Product findByName(String name);
	void deleteByName(String name);
	void deleteById(Long id);
	//find product with default picture
//	@Query("select p from Product p left join fetch Images i where i.product is not null and p.name = ?1")
//	Product findProduct(String productName);
//
//	@Query("select p from Product p left join fetch p.images i where p.name = ?1")
//	Product findProductWithImages(String productName);

}
