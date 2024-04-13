package com.jjh.cardTrade.domain.card;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
@Data
public class CardPk implements Serializable {
    private String cardRefId;

}
