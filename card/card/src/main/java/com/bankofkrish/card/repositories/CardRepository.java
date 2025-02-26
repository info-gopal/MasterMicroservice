package com.bankofkrish.card.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankofkrish.card.entities.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long>{
	
	Optional<Card> findByMobileNumber(String mobileNumber);
	Optional<Card> findByCardNumber(String cardNumber);

}
