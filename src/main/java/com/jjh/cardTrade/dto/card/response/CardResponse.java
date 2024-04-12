package com.jjh.cardTrade.dto.card.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardResponse {
    private String cardRefId;
    private String resultCd;
    private String resultMsg;

//    public CardResponse(Long cardRefId, String resultCd, String resultMsg) {
//        this.cardRefId = cardRefId;
//        this.resultCd = resultCd;
//        this.resultMsg = resultMsg;
//    }
}

