package com.bankofkrish.accounts.mapper;

import com.bankofkrish.accounts.dtos.AccountDto;
import com.bankofkrish.accounts.entities.Account;

public class AccountMapper {

	public static AccountDto mapAaccountDto( Account account,AccountDto accountDto) {

		accountDto.setAccountType(account.getAccountType());
		accountDto.setBranchAddress(account.getBranchAddress());
		accountDto.setAccountNumber(account.getAccountNumber());
		return accountDto;
	}

	public static Account mapAaccounts( AccountDto accountDto,Account account) {

		account.setAccountType(accountDto.getAccountType());
		account.setBranchAddress(accountDto.getBranchAddress());
		account.setAccountNumber(accountDto.getAccountNumber());
		return account;
	}

}
