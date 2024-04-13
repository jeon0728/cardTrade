package com.jjh.cardTrade.domain.card;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@IdClass(CardPk.class)
public class Card {

    @Id
    @Column(name = "card_ref_id")
    private String cardRefId;
    @Column(nullable = false)
    private String userKey;
    @Column(nullable = false)
    private String cardNo;

    @Column(nullable = false)
    private String cardUser;

    public Card(String cardRefId, String userKey, String cardNo, String cardUser) {
        this.cardRefId = cardRefId;
        this.userKey = userKey;
        this.cardNo = cardNo;
        this.cardUser = cardUser;
    }




}
