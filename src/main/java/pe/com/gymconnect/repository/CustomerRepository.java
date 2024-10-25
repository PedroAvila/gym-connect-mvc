package pe.com.gymconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.com.gymconnect.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
