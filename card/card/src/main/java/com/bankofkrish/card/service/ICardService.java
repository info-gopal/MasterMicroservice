package com.bankofkrish.card.service;

import com.bankofkrish.card.dtos.CardDto;

public interface ICardService {
	
	void createCard(String mobileNumber);

	CardDto fetchCard(String cardNumber);

	boolean updateCard(CardDto cardDto);

	boolean deleteCard(String mobileNumber);

}
