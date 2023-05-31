package rikkei.academy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rikkei.academy.model.Customer;
import rikkei.academy.repository.ICustomerRepository;

import java.util.Optional;
@Service
public class CustomerServiceIMPL implements ICustomerService{
@Autowired
private ICustomerRepository repository;


    @Override
    public Iterable<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public void remove(Long id) {
    repository.deleteById(id);
    }
}

