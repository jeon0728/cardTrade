package com.jjh.cardTrade.dto.trade.response;

import com.jjh.cardTrade.domain.card.Card;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeResponse {
    private Card card;
    private String resultCd;

    public TradeResponse(Card card, String resultCd) {
        this.card = card;
        this.resultCd = resultCd;
    }
}
