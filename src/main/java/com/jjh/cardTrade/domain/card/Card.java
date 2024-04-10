package com.jjh.cardTrade.domain.card;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@Getter
public class Card {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "card_ref_id")
    private Long cardRefId;
    @Column(nullable = false)
    private String userKey;

    @Column(nullable = false)
    private String cardNo;

    @Column(nullable = false)
    private String cardNoEnc;

    @Column(nullable = false)
    private String cardUser;

    public Card(String userKey, String cardNo, String cardNoEnc, String cardUser) {
        this.userKey = userKey;
        this.cardNo = cardNo;
        this.cardNoEnc = cardNoEnc;
        this.cardUser = cardUser;
    }




}
