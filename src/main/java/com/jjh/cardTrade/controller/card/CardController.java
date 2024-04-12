package com.jjh.cardTrade.controller.card;

import com.jjh.cardTrade.domain.card.Card;
import com.jjh.cardTrade.dto.card.request.CardRequest;
import com.jjh.cardTrade.dto.card.response.CardResponse;
import com.jjh.cardTrade.service.card.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "카드 관련 API", description = "카드 정보 등록 프로세스")
@RestController
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/card")
    @Operation(summary = "카드 등록", description = "사용자 식별키와 카드의 정보를 등록합니다.")
    @Parameter(name = "userKey", description = "사용자 식별키")
    @Parameter(name = "cardNo", description = "카드 번호")
    @Parameter(name = "cardUser", description = "카드 명의자")
    public CardResponse saveCard(@RequestBody CardRequest request) {
        CardResponse response = cardService.saveCard(request);
        return response;
    }
}
