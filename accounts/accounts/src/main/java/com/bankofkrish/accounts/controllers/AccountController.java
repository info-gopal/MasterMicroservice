package com.bankofkrish.accounts.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

import com.bankofkrish.accounts.constants.AccountConstants;
import com.bankofkrish.accounts.dtos.CustomerDto;
import com.bankofkrish.accounts.dtos.ErrorResponseDto;
import com.bankofkrish.accounts.dtos.ResponseDto;
import com.bankofkrish.accounts.service.IAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Tag(description = "Curd Rest Api for Accounts in KrishBank to create , update, get, delete account details", name = "Curd Rest Api for Accounts in KrishBank")
public class AccountController {

	private IAccountService iAccountService;

	public AccountController(IAccountService iAccountService) {
		super();
		this.iAccountService = iAccountService;
	}

	/**
	 * @param customerDto
	 * @return
	 */
	@PostMapping("/createAccount")
	@Operation(summary = "Create Account Details Api", description = "Create new account api for krish bank api details")
	@ApiResponse(responseCode = "201", description = "Https Status OK")
	public ResponseEntity<ResponseDto> creatAccount(@Valid @RequestBody CustomerDto customerDto) {

		iAccountService.createAccount(customerDto);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));

	}

	@Operation(summary = "GetAccount Details Api", description = "GetAccount details by mobile number in krishBank")
	@ApiResponse(responseCode = "200", description = "GetAccount details api in Krishbank ")
	@GetMapping("/fetchAccount")
	public ResponseEntity<CustomerDto> fetchAccount(
			@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number should be 10 digits") @RequestParam String mobileNumber) {

		CustomerDto customerDto = iAccountService.fetchAccount(mobileNumber);

		return ResponseEntity.status(HttpStatus.OK).body(customerDto);
	}

	@PutMapping("/updateAccount")
	@Operation(description = "AccountDetails update By customer details", summary = "update account details by using account number")
	@ApiResponses({ @ApiResponse(description = "HttpStatus.OK", responseCode = "200"),
		@ApiResponse(description = "Expectation Failed", responseCode = "417",content=@Content(schema=@Schema(implementation = ErrorResponseDto.class)))})
	public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
		boolean isUpdated = iAccountService.updateAccount(customerDto);
		if (isUpdated) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_UPDATE));
		}
	}

	@DeleteMapping("/deleteAccount")
	@Operation(description = "Delete Account By mobile Number", summary = "Delete Account Details by using mobile number")
	@ApiResponses({ @ApiResponse(description = "Status OK", responseCode = "200"),
			@ApiResponse(description = "Expectation Failed", responseCode = "417",content=@Content(schema=@Schema(implementation = ErrorResponseDto.class))) })
	public ResponseEntity<ResponseDto> deleteAccount(
			@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number should be 10 digits") @RequestParam String mobileNumber) {
		boolean deleteAccount = iAccountService.deleteAccount(mobileNumber);
		if (deleteAccount) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_DELETE));
		}
	}
}
