package com.novko.internal.cart;

import com.novko.internal.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

   List<Cart> findAll();

   @Query("select c.product from Cart c where c.id = ?1")
   List<Product> getProducts(Long cartId);

   @Query("select count(c)>0 from Cart c where c.product.code= ?1")
   boolean productExistsByCode(String code);

   //   void deleteProductFromCart(Product product);
//   void clear();
//   void addProductToCart(List<Cart> carts);

//   Cart findByID(Integer id);
   //   void deleteProductFromCart(Product product);
   //   void clear();

}
