package com.jjh.cardTrade.repository.trade;

import com.jjh.cardTrade.domain.card.Card;
import com.jjh.cardTrade.domain.trade.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {

}
