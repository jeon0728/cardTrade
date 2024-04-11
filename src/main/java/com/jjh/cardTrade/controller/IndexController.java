package com.jjh.cardTrade.controller;

import com.jjh.cardTrade.common.authority.JwtProvider;
import com.jjh.cardTrade.service.card.CardService;
import com.jjh.cardTrade.service.trade.TradeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final CardService cardService;
    private final TradeService tradeService;

    public IndexController(CardService cardService, TradeService tradeService) {
        this.cardService = cardService;
        this.tradeService = tradeService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("trade", tradeService.getTradeAll());
        return "trade";
    }
}
