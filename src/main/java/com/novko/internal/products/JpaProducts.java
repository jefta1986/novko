package com.novko.internal.products;

import java.util.Set;

public interface JpaProducts {
	
	void add(Product product);
	void update(Product product);
	void delete(Product product);
	Set<Product> getProducts();
	Product getByCode(String productCode);
	Product getByName(String productName);
	
	
}
