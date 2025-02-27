package com.bankofkrish.loan.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
@Schema(description = "Schema to hold Loan information")
public class LoansDto {
	@NotEmpty(message = "mobileNumber can not be a null or empty")
	@Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
	@Schema(description = "Mobile number of Customer",example = "9897989898")
	private String mobileNumber;
	
	@NotEmpty(message = "Loan Number can not be a empty or null")
	@Pattern(regexp = "(^$|[0-9]{12})",message = "Mobile Number must be 12 digits")
	@Schema(description = "loanNumber of Customer",example = "989798989845")
	private String loanNumber;
	
	@NotEmpty(message = "Loan Type can not be a empty or null")
	@Schema(description = "Type of the Loan",example = "HOME LOAN")
	private String loanType;
	
	@Positive(message = "Loan amount greaterThen Zero")
	@Schema(description = "totalLoan Ammount",example = "100000")
	private int totalLoan;
	
	@PositiveOrZero(message = "AmountPaid amount paid should be greaterThen Zero or equal")
	@Schema(description = "AmountPaid amount",example = "100000")
	private int amountPaid;
	@PositiveOrZero(message = "Total outstandingAmount should be greaterThen Zero or equal")
	@Schema(description = "Total outstandingAmount against a loan",example = "100000")
	private int outstandingAmount;
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public int getTotalLoan() {
		return totalLoan;
	}
	public void setTotalLoan(int totalLoan) {
		this.totalLoan = totalLoan;
	}
	public int getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}
	public int getOutstandingAmount() {
		return outstandingAmount;
	}
	public void setOutstandingAmount(int outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}
	
	

}
