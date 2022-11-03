package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {
  
  CustomerRepository customerRepository;
  
  @Autowired
  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }
  
  public List<Customer> getUsers() {
    return customerRepository.findAll();
  }
  
  public void addNewCustomer(Customer customer) {
    
    System.out.println(customer.getName());
    
    Optional<Customer> customerByUserId =
      customerRepository.findCustomerById(customer.getName());
    System.out.println(customerByUserId.isPresent());
    
    if (customerByUserId.isPresent()) {
      throw  new IllegalStateException("Not find user ID");
    }
    customerRepository.save(customer);
  }
  
  public void deleteCustomer(Long id) {
    boolean exists = customerRepository.existsById(id);
    System.out.println(exists);
    if (!exists) {
      throw new IllegalStateException("not exists");
    }
    System.out.println(id);
    customerRepository.deleteById(id);
    
  }
  @Transactional
  public void updateCustomer(Long id, String name, String userId) {
    
    Customer customer = customerRepository.findById(id)
      .orElseThrow(() -> new IllegalStateException(
        "There s no user with that ID"
      ));
    
    if (name != null
      && name.length() > 0
      && !Objects.equals(customer.getName(), name)) {
      customer.setName(name);
    }
    
    if (userId != null
      && userId.length() > 0
      && !Objects.equals(customer.getUserId(), userId)) {
      Optional<Customer> customerOptional = customerRepository
        .findCustomerById(userId);
      
      if (customerOptional.isPresent()) {
        throw new IllegalStateException("Duplicated User Name");
      }
      
      customer.setUserId(userId);
    }
  }
}
