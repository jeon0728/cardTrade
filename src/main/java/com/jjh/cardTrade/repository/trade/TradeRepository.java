package com.jjh.cardTrade.repository.trade;

import com.jjh.cardTrade.domain.card.Card;
import com.jjh.cardTrade.domain.trade.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Long> {

    //List<Trade> findAllOrderByTradeDate();

}
