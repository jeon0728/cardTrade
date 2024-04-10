package com.jjh.cardTrade.repository.card;

import com.jjh.cardTrade.domain.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {

    Card findByCardNo(String cardNo);
}
