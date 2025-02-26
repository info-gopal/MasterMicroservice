package com.bankofkrish.card.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(name = "Card", description = "Schema to hold Card information")
public class CardDto {
	
	@Schema(description = "MobileNumber of Card holder",example = "9181918989")
	@Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number should be 10 digit")
	private String mobileNumber;
	
	@Pattern(regexp = "(^$|[0-9]{12})",message = "CardNumber should be 12 digits")
	@NotEmpty(message = "CardNumber shouldn't be empty or null")
	@Schema(description = "CardNumber",example = "101020305012")
	private String cardNumber;
	
	@NotEmpty(message = "CardType shouldn't be empty or null")
	@Schema(description = "CardType of Card",example = "Credit Card")
	private String cardType;
	
	@Positive(message = "Total card limit should be greaterThan Zero")
	@Schema(description = "Total amount limit available againest a card ", example = "100000")
	private int totalLimit;
	
	@PositiveOrZero(message = "AmountUsed amount paid should be greaterThen Zero or equal")
	@Schema(description = "AmountPaid amount",example = "100000")
	private int amountUsed;
	
	@PositiveOrZero(message = "Available amount paid should be greaterThen Zero or equal")
	@Schema(description = "AmountPaid amount",example = "100000")
	private int availableAmount;
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public int getTotalLimit() {
		return totalLimit;
	}
	public void setTotalLimit(int totalLimit) {
		this.totalLimit = totalLimit;
	}
	public int getAmountUsed() {
		return amountUsed;
	}
	public void setAmountUsed(int amountUsed) {
		this.amountUsed = amountUsed;
	}
	public int getAvailableAmount() {
		return availableAmount;
	}
	public void setAvailableAmount(int availableAmount) {
		this.availableAmount = availableAmount;
	}
	
}
