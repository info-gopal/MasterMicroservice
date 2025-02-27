package com.bankofkrish.accounts.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
@Schema(name = "Customer", description = "Schema to hold Customer and Account information")
public class CustomerDto {
	@NotEmpty(message = "Name can't be null or empty")
	@Size(min = 5,max = 30,message = "The lenght of the customer Name should be between 5 to 30")
	@Schema(description = "CustomerName of Account holder",example = "Gopalakrishna")
	private String name;
	@Schema(description = "email of Account holder",example = "krish.xyz@gmail.com")
	@NotEmpty(message = "Email can't be null or empty")
	@Email(message = "Email should be a valid value")
	private String email;
	@Schema(description = "MobileNumber of Account holder",example = "9181918989")
	@Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number should be 10 digit")
	private String mobileNumber;
	@Schema(description = "Account Details",name = "Account")
	private AccountDto accountDto;
	
	public AccountDto getAccountDto() {
		return accountDto;
	}
	public void setAccountDto(AccountDto accountDto) {
		this.accountDto = accountDto;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}
