package com.novko.internal.cart;

import java.util.List;

import com.novko.internal.products.Product;

public interface JpaCarts {
	

	Cart getCart(Integer id);
	List<Product> getProducts();
	void deleteProductFromCart(Product product);
	void clear();
	void addProductToCart(List<Cart> carts);

}
