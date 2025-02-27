package com.bankofkrish.loan.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankofkrish.loan.constants.LoanConstants;
import com.bankofkrish.loan.dtos.ErrorResponseDto;
import com.bankofkrish.loan.dtos.LoansDto;
import com.bankofkrish.loan.dtos.ResponseDto;
import com.bankofkrish.loan.service.ILoanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@Tag(name = "CRUD REST APIs for loans in Krish Bank", description = "CRUD REST APIs in krish bank to Loan CREAT UPDATE DELETE FETCH loan details")
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@RestController
@Validated
public class LoanController {

	public LoanController(ILoanService iLoanService) {
		super();
		this.iLoanService = iLoanService;
	}

	ILoanService iLoanService;

	@Operation(summary = "Loan Create Rest API", description = " New Loan create api inside KrisBank")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Http Status Created"),
			@ApiResponse(responseCode = "500", description = "Http Status Internal server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))

	})
	@PostMapping("/createLoan")
	public ResponseEntity<ResponseDto> createLoan(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile should be 10 digits") String mobileNumber) {

		iLoanService.createLoan(mobileNumber);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(LoanConstants.MESSAGE_201, LoanConstants.STATUS_201));
	}

	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Http Status OK"),
			@ApiResponse(responseCode = "500", description = "Http Status Internal server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))

	})
	@GetMapping("/fetchLoan")
	public ResponseEntity<LoansDto> fetchLoanDetails(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile should be 10 digits") String mobileNumber) {

		LoansDto loansDto = iLoanService.fetchLoanDetails(mobileNumber);

		return ResponseEntity.status(HttpStatus.OK).body(loansDto);

	}

	@PutMapping("/updateLoan")
	@Operation(description = "Loan details update", summary = "update loan details by using account number")
	@ApiResponses({ @ApiResponse(description = "HttpStatus.OK", responseCode = "200"),
			@ApiResponse(description = "Expectation Failed", responseCode = "417", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
			@ApiResponse(description = "Internal server Error", responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody LoansDto laonsDto) {
		boolean isUpdated = iLoanService.updateLoan(laonsDto);
		if (isUpdated) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_UPDATE));
		}
	}

	@DeleteMapping("/deleteAccount")
	@Operation(description = "Delete Loan API RestAPI", summary = "Delete Loan Details by using mobile number")
	@ApiResponses({ @ApiResponse(description = "Status OK", responseCode = "200"),
			@ApiResponse(description = "Expectation Failed", responseCode = "417", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	public ResponseEntity<ResponseDto> deleteAccount(
			@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number should be 10 digits") @RequestParam String mobileNumber) {
		boolean deleteAccount = iLoanService.deleteLoan(mobileNumber);
		if (deleteAccount) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_DELETE));
		}
	}

}
