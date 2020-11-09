//package com.novko.api;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.novko.internal.customers.Customer;
//import com.novko.internal.customers.JpaCustomersRepository;
//
//@RestController
//@RequestMapping("/rest/customers")
//public class CustomersController {
//
//	private JpaCustomersRepository jpaCustomersRepository;
//
//	@Autowired
//	public void setJpaCustomersRepository(JpaCustomersRepository jpaCustomersRepository) {
//		this.jpaCustomersRepository = jpaCustomersRepository;
//	}
//
//	@PostMapping(value = "")
//	public ResponseEntity<String> saveAccount(@RequestBody Customer customer) {
//		jpaCustomersRepository.add(customer);
//		return new ResponseEntity<String>("account added", HttpStatus.OK);
//	}
//
//	@GetMapping(value = "/get")
//	public ResponseEntity<List<Customer>> getAccounts() {
//		return new ResponseEntity<List<Customer>>(jpaCustomersRepository.getAll(), HttpStatus.OK);
//	}
//
//}
