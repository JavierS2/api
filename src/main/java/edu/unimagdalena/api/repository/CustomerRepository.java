package edu.unimagdalena.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.unimagdalena.api.model.entities.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.email = ?1")
    Customer findByEmail(String email);

    @Query("SELECT c FROM Customer c WHERE c.address = ?1")
    List<Customer> findByAddress(String address);

    @Query("SELECT c FROM Customer c WHERE lower(c.name) LIKE lower(concat(?1, '%'))")
    List<Customer> findByNameStartsWith(String name);

}