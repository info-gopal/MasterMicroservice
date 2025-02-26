package com.bankofkrish.card.serviceimpl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.bankofkrish.card.CardMapper;
import com.bankofkrish.card.constants.CardConstants;
import com.bankofkrish.card.dtos.CardDto;
import com.bankofkrish.card.entities.Card;
import com.bankofkrish.card.excetions.CardAlreadyExistsException;
import com.bankofkrish.card.excetions.ResourceNotFoundException;
import com.bankofkrish.card.repositories.CardRepository;
import com.bankofkrish.card.service.ICardService;

@Service
public class CardServiceImpl implements ICardService {

	private CardRepository cardRepository;

	public CardServiceImpl(CardRepository cardRepository) {
		super();
		this.cardRepository = cardRepository;
	}

	@Override
	public void createCard(String mobileNumber) {
		Optional<Card> card = cardRepository.findByMobileNumber(mobileNumber);
		if (card.isPresent()) {
			throw new CardAlreadyExistsException("Card already registered with given Mobile Number: " + mobileNumber);

		}
		cardRepository.save(createNewCard(mobileNumber));
	}

	@Override
	public CardDto fetchCard(String mobileNumber) {
		Card card = cardRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Card ", "Mobile Number ", mobileNumber));

		return CardMapper.mapToCardDto(card, new CardDto());

	}

	@Override
	public boolean updateCard(CardDto cardDto) {
		Card card = cardRepository.findByCardNumber(cardDto.getCardNumber())
				.orElseThrow(() -> new ResourceNotFoundException("Loan ", "Loan Number ", cardDto.getCardNumber()));
		CardMapper.mapToCard(cardDto, card);
		cardRepository.save(card);
		return true;
	}

	@Override
	public boolean deleteCard(String mobileNumber) {

		Card card = cardRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Card ", "Mobile Number ", mobileNumber));

		cardRepository.deleteById(card.getCardId());
		return true;
	}

	private Card createNewCard(String mobileNumber) {

		Card card = new Card();
		long rondamLoanNumber = 100000000000l + new Random().nextInt(900000000);
		card.setCardNumber(Long.toString(rondamLoanNumber));
		card.setMobileNumber(mobileNumber);
		card.setCardType(CardConstants.CREDTI_CARD);
		card.setTotalLimit(CardConstants.CARD_TOTAL_LIMIT);
		card.setAmountUsed(0);
		card.setAvailableAmount(CardConstants.CARD_TOTAL_LIMIT);
		return card;

	}

}
