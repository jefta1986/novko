//package com.novko.internal.cart;
//
//import java.util.List;
//import java.util.logging.Logger;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.novko.internal.products.Product;
//
//@Repository
//public class JpaCartsRepository implements JpaCarts {
//
//	@SuppressWarnings("unused")
//	private static final Logger log = Logger.getLogger(JpaCartsRepository.class.getName());
//
//	private EntityManager entityManager;
//
//	@PersistenceContext
//	public void setEntityManager(EntityManager entityManager) {
//		this.entityManager = entityManager;
//	}
//
//
//	@Override
//	@Transactional
//	public void save(Cart cart) {
//		entityManager.persist(cart);
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public List<Product> getProducts(Long cartId) {
//		return entityManager.createQuery("select c.product from Cart c where c.id = ?1").setParameter(1, cartId).getResultList();
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public List<Cart> getAll() {
//		return entityManager.createQuery("select c from Cart c").getResultList();
//	}
//
//
//	@Override
//	@Transactional
//	public void deleteProductFromCart(Product product) {
//		entityManager.remove(product);
//	}
//
//
//	@Override
//	@Transactional
//	public void clear() {
//		entityManager.clear();
//	}
//
//	@Override
//	@Transactional(readOnly = true)
//	public Cart getCart(Integer id) {
//		return entityManager.find(Cart.class, id);
//	}
//
//
//
////metoda uzima List<Carts> iz SESIJE
//	@Override
//	@Transactional
//	public void addProductToCart(List<Cart> carts) {
//		for (Cart cart : carts) {
//			cart.setProduct(cart.getProduct());
//			cart.setQuantity(cart.getQuantity());
//			entityManager.persist(cart);
////			cart.getProduct().setCarts(cart);
//	}
//
//
//}
//}
