package com.bankofkrish.accounts.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Schema(name = "Account", description = "Schema to hold Account information")
public class AccountDto {
	@Pattern(regexp = "(^$|[0-9]{10})",message = "AccountNumber should be 10 digits")
	@NotEmpty(message = "AccountNumber shouldn't be empty or null")
	@Schema(description = "AccountNumber",example = "1010203050")
	private Long accountNumber;
	@NotEmpty(message = "AccountType shouldn't be empty or null")
	@Schema(description = "AccountType of account",example = "saving")
	private String accountType;
	@Schema(description = "BranchAddress of Bank")
	@NotEmpty(message = "BranchAddress shouldn't be empty or null")
	private String branchAddress;
	
	
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

}
