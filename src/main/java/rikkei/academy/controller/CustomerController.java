package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rikkei.academy.model.Customer;
import rikkei.academy.service.CustomerServiceIMPL;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerServiceIMPL customerServiceIMPL;
    @GetMapping
    public ResponseEntity<Iterable<Customer>>  findAll(){
        List<Customer> customers = (List<Customer>) customerServiceIMPL.findAll();
        if (customers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(customers,HttpStatus.OK);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable("id")Long id){
        Optional<Customer> customer = customerServiceIMPL.findById(id);
        if (customer.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(customer.get(),HttpStatus.OK);
        }
    }
    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody Customer customer){
        return new ResponseEntity<>(customerServiceIMPL.save(customer),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id")Long id,@RequestBody Customer customer){
        Optional<Customer> optionalCustomer = customerServiceIMPL.findById(id);
        if (!optionalCustomer.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            customer.setId(optionalCustomer.get().getId());
            return new ResponseEntity<>(customerServiceIMPL.save(optionalCustomer.get()),HttpStatus.OK);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id")Long id){
        Optional<Customer> optionalCustomer = customerServiceIMPL.findById(id);
        if (!optionalCustomer.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            customerServiceIMPL.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }



}
