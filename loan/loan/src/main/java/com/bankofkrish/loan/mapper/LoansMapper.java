package com.bankofkrish.loan.mapper;

import com.bankofkrish.loan.dtos.LoansDto;
import com.bankofkrish.loan.entities.Loans;

public class LoansMapper {

	public static LoansDto mapToLoansDto(Loans loans, LoansDto loansDto) {
		loansDto.setAmountPaid(loans.getAmountPaid());
		loansDto.setLoanNumber(loans.getLoanNumber());
		loansDto.setLoanType(loans.getLoanType());
		loansDto.setMobileNumber(loans.getMobileNumber());
		loansDto.setOutstandingAmount(loans.getOutstandingAmount());
		loansDto.setTotalLoan(loans.getTotalLoan());
		return loansDto;
	}

	public static Loans mapToLoans(LoansDto laonsDto, Loans loans) {
		loans.setAmountPaid(laonsDto.getAmountPaid());
		loans.setLoanNumber(laonsDto.getLoanNumber());
		loans.setLoanType(laonsDto.getLoanType());
		loans.setMobileNumber(laonsDto.getMobileNumber());
		loans.setOutstandingAmount(laonsDto.getOutstandingAmount());
		loans.setTotalLoan(laonsDto.getTotalLoan());
		return loans;
	}

}
