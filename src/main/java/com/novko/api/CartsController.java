package com.novko.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.novko.internal.cart.CartRepository;
import com.novko.internal.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.novko.internal.cart.Cart;
import com.novko.internal.products.Product;

@RestController
@RequestMapping("/rest/carts")
public class CartsController {

	private CartRepository cartRepository;
	private ProductRepository productRepository;

    @Autowired
    public CartsController(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

//	@PostMapping(value = "/")
//	public ResponseEntity<String> addProduct(@RequestBody Products product) {
//		jpaCartsRepository.addProductToCart(product);
//		return new ResponseEntity<String>("product added to cart", HttpStatus.OK);
//	}



	@GetMapping(value = "/session/attributes")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
	@ResponseBody
	public List<Cart> getSessionAttributes(HttpSession session) {

		List<Cart> carts = (List<Cart>) session.getAttribute("cart");

		return carts;
	}


	@PostMapping(value = "/session/clear")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
	public ResponseEntity<String> clear(HttpSession session){
		session.setAttribute("cart", null);
		return new ResponseEntity<String>("cleared", HttpStatus.OK);
	}


//add Product to Session attribute "cart"

	//prebaci u CartService
	@PostMapping(value = "")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
	public ResponseEntity<String> addProductToCartSession(@RequestParam String productName, HttpSession session) {
		Product product = productRepository.findByName(productName);
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
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
	public ResponseEntity<List<Cart>> getAllCarts() {
		return new ResponseEntity<List<Cart>>(cartRepository.findAll(), HttpStatus.OK);
	}


	@GetMapping(value = "/products/{cartId}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
	public ResponseEntity<List<Product>> getProductsFromCart(@PathVariable Long cartId) {
		return new ResponseEntity<List<Product>>(cartRepository.getProducts(cartId), HttpStatus.OK);
	}


	@DeleteMapping(value = "")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
	public ResponseEntity<String> deleteProduct(@RequestBody Product product, HttpSession session) {
		List<Cart> carts = (List<Cart>) session.getAttribute("cart");

		//java 8 testiraj
		carts.removeIf( cart -> cart.getProduct().equals(product));

		return new ResponseEntity<String>("product deleted from cart", HttpStatus.OK);
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

}
