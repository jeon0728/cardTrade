package com.jjh.cardTrade.domain.trade;

import com.jjh.cardTrade.domain.card.Card;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    @JoinColumn(name = "user_key")
    @JoinColumn(name = "card_no")
    private Card card;

    public Trade(String tradeMoney, String tradeDate, Card card) {
        this.tradeMoney = tradeMoney;
        this.tradeDate = tradeDate;
        this.card = card;
    }

    //private String formatDate() {
    //    return tradeDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    //}
}
