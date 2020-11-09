//package com.novko.api;
//
//
//import com.novko.internal.categories.JpaCategories;
//import com.novko.internal.categories.JpaCategoriesRepository;
//import com.novko.internal.products.JpaProductsRepository;
//import com.novko.security.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//public class HomeController {
//
//    private JpaProductsRepository jpaProductsRepository;
//    private JpaCategoriesRepository jpaCategoriesRepository;
//
//
//    @Autowired
//    public HomeController(JpaProductsRepository jpaProductsRepository, JpaCategoriesRepository jpaCategoriesRepository) {
//        this.jpaProductsRepository = jpaProductsRepository;
//        this.jpaCategoriesRepository = jpaCategoriesRepository;
//    }
//
//
//
//
//    @GetMapping(value = "/user")
//    public String user() {
//        return "<h1>User Page</h1>";
//    }
//
//    @GetMapping(value = "/admin")
//    public String admin() {
//        return "<h1>Admin Page</h1>";
//    }
//
//
//    @GetMapping(value = "/home")
//    public ResponseEntity<Map<String, Object>> home() {
//        Map<String, Object> homeMapObjects = new HashMap<>();
//        homeMapObjects.put("products", jpaProductsRepository.getProducts());
//        homeMapObjects.put("categories", jpaCategoriesRepository.getCategoriesWithSubcategories());
//
//        return new ResponseEntity<Map<String, Object>>( homeMapObjects  , HttpStatus.OK);
//    }
//
//
////    @PostMapping(value = "/")
////    public String createUser(@RequestBody User user){
////        jpaUserRepository.saveAndFlush(user);
////        return "ok";
////    }
//
//
//}
