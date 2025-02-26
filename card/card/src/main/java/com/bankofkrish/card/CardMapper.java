package com.bankofkrish.card;

import com.bankofkrish.card.dtos.CardDto;
import com.bankofkrish.card.entities.Card;

public class CardMapper {

	public static CardDto mapToCardDto(Card card, CardDto cardDto) {
		cardDto.setAmountUsed(card.getAmountUsed());
		cardDto.setAvailableAmount(card.getAvailableAmount());
		cardDto.setCardType(card.getCardType());
		cardDto.setMobileNumber(card.getMobileNumber());
		cardDto.setTotalLimit(card.getTotalLimit());
		return cardDto;
	}
	public static Card mapToCard(CardDto cardDto,Card card) {
		card.setAmountUsed(cardDto.getAmountUsed());
		card.setAvailableAmount(cardDto.getAvailableAmount());
		card.setCardType(cardDto.getCardType());
		card.setMobileNumber(cardDto.getMobileNumber());
		card.setTotalLimit(cardDto.getTotalLimit());
		return card;
	}
	
	
	

}
