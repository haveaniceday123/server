package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class CustomerController {
  
  private final CustomerService customerService;
  
  @Autowired
  public CustomerController(CustomerService userService) {
    this.customerService = userService;
  }
  
  @GetMapping
  public List<Customer> getUsers() {
    return customerService.getUsers();
  }
  
  @PostMapping(path = "/add")
  public void registerCustomer(@RequestBody Customer customer) {
    System.out.println(customer.toString());
    
    customerService.addNewCustomer(customer);
  }
  
  @DeleteMapping(path = "{studentId}")
  public void deleteStudent(@PathVariable("studentId") Long id) {
    customerService.deleteCustomer(id);
  }
  
  @PutMapping(path = "{customerId}")
  public void updateStudent(
    @PathVariable("customerId") Long id,
    @RequestParam(required = false) String name,
    @RequestParam(required = false) String userId
  ) {
    customerService.updateCustomer(id, name, userId);
  }
  
}
