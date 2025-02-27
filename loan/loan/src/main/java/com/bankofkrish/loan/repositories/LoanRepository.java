package com.bankofkrish.loan.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankofkrish.loan.entities.Loans;
@Repository
public interface LoanRepository extends JpaRepository<Loans, Long> {

	Optional<Loans> findByMobileNumber(String mobileNumber);
	Optional<Loans> findByLoanNumber(String mobileNumber);

}
