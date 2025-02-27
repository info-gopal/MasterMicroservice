package com.bankofkrish.loan.serviceimpl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.bankofkrish.loan.constants.LoanConstants;
import com.bankofkrish.loan.dtos.LoansDto;
import com.bankofkrish.loan.entities.Loans;
import com.bankofkrish.loan.exceptions.LoanAlreadyExistsException;
import com.bankofkrish.loan.exceptions.ResourceNotFoundException;
import com.bankofkrish.loan.mapper.LoansMapper;
import com.bankofkrish.loan.repositories.LoanRepository;
import com.bankofkrish.loan.service.ILoanService;

@Service
public class LoanServiceImpl implements ILoanService {

	public LoanServiceImpl(LoanRepository loanRepository) {
		super();
		this.loanRepository = loanRepository;
	}

	LoanRepository loanRepository;

	@Override
	public void createLoan(String mobileNumber) {

		Optional<Loans> loans = loanRepository.findByMobileNumber(mobileNumber);
		if (loans.isPresent()) {
			throw new LoanAlreadyExistsException("Loan already registered with given Mobile Number: " + mobileNumber);

		}
		loanRepository.save(createNewLoan(mobileNumber));

	}

	@Override
	public LoansDto fetchLoanDetails(String mobileNumber) {

		Loans loans = loanRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Loan ", "Mobile Number ", mobileNumber));

		return LoansMapper.mapToLoansDto(loans, new LoansDto());
	}

	@Override
	public boolean updateLoan(LoansDto laonsDto) {
		Loans loans = loanRepository.findByLoanNumber(laonsDto.getLoanNumber())
				.orElseThrow(() -> new ResourceNotFoundException("Loan ", "Loan Number ", laonsDto.getLoanNumber()));
		LoansMapper.mapToLoans(laonsDto, loans);
		loanRepository.save(loans);
		return true;
	}

	@Override
	public boolean deleteLoan(String mobileNumber) {
		Loans loans = loanRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Loan ", "Mobile Number ", mobileNumber));

		loanRepository.deleteById(loans.getLoanId());
		return true;
	}

	private Loans createNewLoan(String mobileNumber) {

		Loans loans = new Loans();
		long rondamLoanNumber = 100000000000l + new Random().nextInt(900000000);
		loans.setLoanNumber(Long.toString(rondamLoanNumber));
		loans.setMobileNumber(mobileNumber);
		loans.setLoanType(LoanConstants.HOME_LOAN);
		loans.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
		loans.setAmountPaid(0);
		loans.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);

		return loans;

	}

}
