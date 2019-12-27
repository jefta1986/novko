package com.novko.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.novko.internal.cart.Cart;
import com.novko.internal.cart.JpaCartsRepository;
import com.novko.internal.products.JpaProductsRepository;
import com.novko.internal.products.Product;

@RestController
@RequestMapping("/rest/carts")
public class CartsController {
	
	private JpaCartsRepository jpaCartsRepository;
	private JpaProductsRepository jpaProductsRepository;
	
	
	@Autowired
	public void setJpaCartsRepository(JpaCartsRepository jpaCartsRepository) {
		this.jpaCartsRepository = jpaCartsRepository;
	}

	@Autowired
	public void setJpaProductsRepository(JpaProductsRepository jpaProductsRepository) {
		this.jpaProductsRepository = jpaProductsRepository;
	}
	
	
	
//	@PostMapping(value = "/")
//	public ResponseEntity<String> addProduct(@RequestBody Products product) {
//		jpaCartsRepository.addProductToCart(product);
//		return new ResponseEntity<String>("product added to cart", HttpStatus.OK);
//	}
	
	
	public JpaProductsRepository getJpaProductsRepository() {
		return jpaProductsRepository;
	}



	@GetMapping(value = "/session/attributes")
	@ResponseBody
	public String getSessionAttributes(HttpSession session) {
	
		List<Cart> carts = (List<Cart>) session.getAttribute("cart");
		
		StringBuffer sb = new StringBuffer();
		for (Cart cart : carts) {
			sb.append(cart.getProduct().getName()).append(" ---- ").append(cart.getQuantity()).append("\n");
		}
		return sb.toString();
	}
	
	
	@PostMapping(value = "/session/clear")
	public ResponseEntity<String> clear(HttpSession session){
		session.setAttribute("cart", null);
		return new ResponseEntity<String>("cleared", HttpStatus.OK);
	}


//add Product to Session attribute "cart"
	@PostMapping(value = "")
	public ResponseEntity<String> addProductToCartSession(@RequestParam String productName, HttpSession session) {
		Product product = jpaProductsRepository.getByName(productName);
		if(product == null) {
			return new ResponseEntity<String>("product doesn't exists", HttpStatus.NO_CONTENT);
		}
		

		if(session.getAttribute("cart") == null ) {
			List<Cart> carts = new ArrayList<>();
			carts.add(new Cart(1, product));
			session.setAttribute("cart", carts);
		}else {

			List<Cart> carts = (List<Cart>) session.getAttribute("cart");
			int index = exists(productName, carts);
			if( index == -1 ) {
				carts.add(new Cart(1, product));
			}else {
				int quantity = carts.get(index).getQuantity()+1;
				carts.get(index).setQuantity(quantity);
			}
			session.setAttribute("cart", carts);
		}
		
	
		return new ResponseEntity<String>("product added to cart", HttpStatus.OK);
	}


	@GetMapping(value = "")
	public ResponseEntity<List<Cart>> getAllCarts() {
		return new ResponseEntity<List<Cart>>(jpaCartsRepository.getAll(), HttpStatus.OK);
	}


	@GetMapping(value = "/products/{cartId}")
	public ResponseEntity<List<Product>> getProductsFromCart(@PathVariable Long cartId) {
		return new ResponseEntity<List<Product>>(jpaCartsRepository.getProducts(cartId), HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "")
	public ResponseEntity<String> deleteProduct(@RequestBody Product product, HttpSession session) {
		List<Cart> carts = (List<Cart>) session.getAttribute("cart");
		
		//java 8 testiraj
		carts.removeIf( cart -> cart.getProduct().equals(product));

		return new ResponseEntity<String>("product deleted from cart", HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "/clear")
	public ResponseEntity<String> clear() {
		jpaCartsRepository.clear();
		return new ResponseEntity<String>("cart cleared", HttpStatus.OK);
	}


//private method if exists Product in Cart lists,    equalsIgnoreCase used !!
	private Integer exists(String productName, List<Cart> carts) {
		for (int i = 0; i < carts.size(); i++) {
			if( carts.get(i).getProduct().getName().equalsIgnoreCase(productName) ) {
				return i;
			}
		}
		return -1;
	}
	
	
	
	
	
//	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
//	public String remove(@PathVariable("id") String id, HttpSession session) {
//		ProductModel productModel = new ProductModel();
//		List<Item> cart = (List<Item>) session.getAttribute("cart");
//		int index = this.exists(id, cart);
//		cart.remove(index);
//		session.setAttribute("cart", cart);
//		return "redirect:/cart/index";
//	}
//
//	private int exists(String id, List<Item> cart) {
//		for (int i = 0; i < cart.size(); i++) {
//			if (cart.get(i).getProduct().getId().equalsIgnoreCase(id)) {
//				return i;
//			}
//		}
//		return -1;
//	}
	
	
	
	
	
}
