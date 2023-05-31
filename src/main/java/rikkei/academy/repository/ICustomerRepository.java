package rikkei.academy.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rikkei.academy.model.Customer;

@Repository
public interface ICustomerRepository extends PagingAndSortingRepository<Customer, Long> {
}
