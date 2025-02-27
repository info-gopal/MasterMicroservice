package com.bankofkrish.loan.service;

import com.bankofkrish.loan.dtos.LoansDto;


public interface ILoanService {

	void createLoan(String mobileNumber);

	LoansDto fetchLoanDetails(String mobileNumber);

	boolean updateLoan(LoansDto laonsDto);

	boolean deleteLoan(String mobileNumber);

}
