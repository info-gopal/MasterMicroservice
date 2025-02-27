package com.bankofkrish.accounts.serviceImpl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.bankofkrish.accounts.constants.AccountConstants;
import com.bankofkrish.accounts.dtos.AccountDto;
import com.bankofkrish.accounts.dtos.CustomerDto;
import com.bankofkrish.accounts.entities.Account;
import com.bankofkrish.accounts.entities.Customer;
import com.bankofkrish.accounts.exceptions.CustomerAlreadyExistsException;
import com.bankofkrish.accounts.exceptions.ResourceNotFoundException;
import com.bankofkrish.accounts.mapper.AccountMapper;
import com.bankofkrish.accounts.mapper.CustomerMapper;
import com.bankofkrish.accounts.repositories.AccountRepository;
import com.bankofkrish.accounts.repositories.CustomerRepository;
import com.bankofkrish.accounts.service.IAccountService;

/**
 * 
 */
@Service
public class AccountServiceImpl implements IAccountService {

	public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository) {
		this.accountRepository = accountRepository;
		this.customerRepository = customerRepository;
	}

	private AccountRepository accountRepository;
	private CustomerRepository customerRepository;

	/**
	 * @param customerDto
	 */
	@Override
	public void createAccount(CustomerDto customerDto) {
		// TODO Auto-generated method stub
		Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
		Optional<Customer> byMobileNumber = customerRepository.findByMobileNumber(customer.getMobileNumber());
		if (byMobileNumber.isPresent()) {
			throw new CustomerAlreadyExistsException(
					"Customer Already Register wiht given Mobile Number" + customer.getMobileNumber());
		}

		Customer createdCustomer = customerRepository.save(customer);
		Account newAccount = createNewAccount(createdCustomer);
		accountRepository.save(newAccount);
	}

	@Override
	public CustomerDto fetchAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber));

		Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				() -> new ResourceNotFoundException("Account", "customerId: ", customer.getCustomerId().toString()));

		CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
		AccountDto accountDto = AccountMapper.mapAaccountDto(account, new AccountDto());
		customerDto.setAccountDto(accountDto);

		return customerDto;
	}

	private Account createNewAccount(Customer customer) {

		Account newAccount = new Account();
		newAccount.setCustomerId(customer.getCustomerId());
		long randomAccountNumber = 10000000000L + new Random().nextInt(900000000);
		newAccount.setAccountNumber(randomAccountNumber);
		newAccount.setAccountType(AccountConstants.SAVINGS);
		newAccount.setBranchAddress(AccountConstants.ADDRESS);
//		newAccount.setCreateAt(LocalDateTime.now());
//		newAccount.setCreatedBy("GOPAL");
		return newAccount;

	}

	@Override
	public boolean updateAccount(CustomerDto customerDto) {
		boolean isUpdated = false;
		AccountDto accountDto = customerDto.getAccountDto();
		if (accountDto != null) {

			Account account = accountRepository.findById(accountDto.getAccountNumber())
					.orElseThrow(() -> new ResourceNotFoundException("Account", "Account",
							accountDto.getAccountNumber().toString()));

			account = AccountMapper.mapAaccounts(accountDto, account);
			accountRepository.save(account);
			Long customerId = account.getCustomerId();

			Customer customer = customerRepository.findById(account.getCustomerId()).orElseThrow(
					() -> new ResourceNotFoundException("Customer", "CustomerId",customerId.toString()));
			
			customer = CustomerMapper.mapToCustomer(customerDto,customer);
			customerRepository.save(customer);
			isUpdated=true;

		}
		// TODO Auto-generated method stub
		return isUpdated;
	}

	@Override
	public boolean deleteAccount(String mobileNumber) {

		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber));

		customerRepository.deleteById(customer.getCustomerId());
		accountRepository.deleteByCustomerId(customer.getCustomerId());

		return true;

	}

}
