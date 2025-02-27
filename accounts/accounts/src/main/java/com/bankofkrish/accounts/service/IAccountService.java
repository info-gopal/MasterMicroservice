package com.bankofkrish.accounts.service;

import org.springframework.http.ResponseEntity;

import com.bankofkrish.accounts.dtos.CustomerDto;
import com.bankofkrish.accounts.dtos.ResponseDto;

public interface IAccountService {
	/**
	 * @param customerDto
	 */
	void createAccount(CustomerDto customerDto);

	CustomerDto fetchAccount(String mobileNumber);

	boolean updateAccount(CustomerDto customerDto);

	boolean deleteAccount(String mobileNumber);

}
