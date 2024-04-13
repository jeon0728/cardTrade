package com.jjh.cardTrade.domain.trade;

import com.jjh.cardTrade.domain.card.Card;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Trade {

    @Id
    @GeneratedValue
    private Long TradeNo;

    @Column(nullable = false)
    private String tradeMoney;

    @Column(nullable = false)
    private String tradeDate;

    @ManyToOne
    @JoinColumn(name = "card_ref_id")
    private Card card;

    public Trade(String tradeMoney, String tradeDate, Card card) {
        this.tradeMoney = tradeMoney;
        this.tradeDate = tradeDate;
        this.card = card;
    }

}
