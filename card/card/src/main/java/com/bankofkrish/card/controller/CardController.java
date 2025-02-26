package com.bankofkrish.card.controller;

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

import com.bankofkrish.card.constants.CardConstants;
import com.bankofkrish.card.dtos.CardDto;
import com.bankofkrish.card.dtos.ErrorResponseDto;
import com.bankofkrish.card.dtos.ResponseDto;
import com.bankofkrish.card.service.ICardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@Tag(name = "CRUD REST APIs for Card in Krish Bank", description = "CRUD REST APIs in krish bank to Card CREAT, UPDATE, DELETE, FETCH loan details")
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@RestController
@Validated
public class CardController {

	ICardService iCardService;

	public CardController(ICardService iCardService) {
		super();
		this.iCardService = iCardService;
	}

	@Operation(summary = "Card Create Rest API", description = " New Card create api inside KrisBank")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Http Status Created"),
			@ApiResponse(responseCode = "500", description = "Http Status Internal server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))

	})
	@PostMapping("/createCard")
	public ResponseEntity<ResponseDto> createCardDetails(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile should be 10 digits") String mobileNumber) {

		iCardService.createCard(mobileNumber);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(CardConstants.MESSAGE_201, CardConstants.STATUS_201));
	}

	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Http Status OK"),
			@ApiResponse(responseCode = "500", description = "Http Status Internal server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@GetMapping("/fetchCard")
	public ResponseEntity<CardDto> fetchCardDetails(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile should be 10 digits") String mobileNumber) {

		CardDto cardDto = iCardService.fetchCard(mobileNumber);

		return ResponseEntity.status(HttpStatus.OK).body(cardDto);

	}

	@PutMapping("/updateCard")
	@Operation(description = "Card details update", summary = "update card details by using card number")
	@ApiResponses({ @ApiResponse(description = "HttpStatus.OK", responseCode = "200"),
			@ApiResponse(description = "Expectation Failed", responseCode = "417", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
			@ApiResponse(description = "Internal server Error", responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	public ResponseEntity<ResponseDto> updateCardDetails(@Valid @RequestBody CardDto cardDto) {
		boolean isUpdated = iCardService.updateCard(cardDto);
		if (isUpdated) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_UPDATE));
		}
	}

	@DeleteMapping("/deleteAccount")
	@Operation(description = "Delete Card API RestAPI", summary = "Delete Card Details by using mobile number")
	@ApiResponses({ @ApiResponse(description = "Status OK", responseCode = "200"),
			@ApiResponse(description = "Expectation Failed", responseCode = "417", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	public ResponseEntity<ResponseDto> deleteCardDetails(
			@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number should be 10 digits") @RequestParam String mobileNumber) {
		boolean deleteAccount = iCardService.deleteCard(mobileNumber);
		if (deleteAccount) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_DELETE));
		}
	}

}
