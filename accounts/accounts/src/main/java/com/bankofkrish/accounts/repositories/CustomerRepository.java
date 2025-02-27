package com.bankofkrish.accounts.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankofkrish.accounts.entities.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
	Optional<Customer> findByMobileNumber(String mobileNumber);

}
