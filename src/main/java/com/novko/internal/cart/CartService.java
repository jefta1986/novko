package com.novko.internal.cart;

import com.novko.internal.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public Cart findById(Long id) {
        return cartRepository.findById(id).get();
    }


    //metoda uzima List<Carts> iz SESION STORAGE
    @Transactional
    public void addProductToCart(List<Cart> carts) {
        for (Cart cart : carts) {
            cart.setProduct(cart.getProduct());
            cart.setQuantity(cart.getQuantity());
            cartRepository.save(cart);
//			cart.getProduct().setCarts(cart);
        }
    }


        //    @Override
//    @Transactional
//    public void clear() {
//        cartRepository.clear();
//    }

}
