package com.jjh.cardTrade.domain.card;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
@IdClass(CardPk.class)
public class Card {

    @Id
    @Column(name = "card_ref_id")
    private String cardRefId;
    @Id
    @Column(name = "user_key")
    private String userKey;
    @Id
    @Column(name = "card_no")
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
