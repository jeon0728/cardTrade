package com.jjh.cardTrade.controller.card;

import com.jjh.cardTrade.domain.card.Card;
import com.jjh.cardTrade.dto.card.request.CardRequest;
import com.jjh.cardTrade.dto.card.response.CardResponse;
import com.jjh.cardTrade.service.card.CardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/card")
    public CardResponse saveCard(@RequestBody CardRequest request) {
        CardResponse response = cardService.saveCard(request);
        return response;
    }
}
