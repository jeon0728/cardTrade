package com.jjh.cardTrade.controller.trade;

import com.jjh.cardTrade.common.authority.JwtProvider;
import com.jjh.cardTrade.common.authority.TokenResponse;
import com.jjh.cardTrade.domain.card.Card;
import com.jjh.cardTrade.dto.card.response.CardResponse;
import com.jjh.cardTrade.dto.trade.request.TradeRequest;
import com.jjh.cardTrade.dto.trade.response.TradeResponse;
import com.jjh.cardTrade.service.card.CardService;
import com.jjh.cardTrade.service.trade.TradeService;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.*;

@RestController
public class TradeController {

    private final JwtProvider jwtProvider;
    private final CardService cardService;

    private final TradeService tradeService;

    public TradeController(JwtProvider jwtProvider, CardService cardService, TradeService tradeService) {
        this.jwtProvider = jwtProvider;
        this.cardService = cardService;
        this.tradeService = tradeService;
    }

    @PostMapping("/token")
    public TokenResponse createToken(@RequestBody TradeRequest request) throws Exception {
        if (cardService.getCardById(request.getCardRefId()) == null) {
            throw new IllegalArgumentException("등록되어 있지 않은 카드 정보입니다.");
        }
        String token = jwtProvider.createToken(request.getCardRefId()); // 토큰 생성
        Claims claims = jwtProvider.parseJwtToken("Bearer "+ token); // 토큰 검증

        TokenResponse tokenResponse = new TokenResponse("200", "OK", token);

        return tokenResponse;
    }

    @PostMapping("/trade")
    public TradeResponse trade(@RequestHeader(value = "Authorization") String token, @RequestBody TradeRequest request) {
        String resultCd = tradeService.saveTradeInfo(request, token);
        Card card = cardService.getCardById(request.getCardRefId());
        TradeResponse response = new TradeResponse(card, resultCd);
        return response;
    }
}
