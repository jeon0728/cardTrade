package com.jjh.cardTrade.service.trade;

import com.jjh.cardTrade.common.authority.JwtProvider;
import com.jjh.cardTrade.domain.card.Card;
import com.jjh.cardTrade.domain.trade.Trade;
import com.jjh.cardTrade.dto.trade.request.TradeRequest;
import com.jjh.cardTrade.repository.card.CardRepository;
import com.jjh.cardTrade.repository.trade.TradeRepository;
import io.jsonwebtoken.Claims;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TradeService {

    private final CardRepository cardRepository;
    private final TradeRepository tradeRepository;
    private final JwtProvider jwtProvider;


    public TradeService(
            TradeRepository tradeRepository,
            JwtProvider jwtProvider,
            CardRepository cardRepository
    ) {
        this.tradeRepository = tradeRepository;
        this.jwtProvider = jwtProvider;
        this.cardRepository = cardRepository;
    }

    public List<Trade> getTradeAll() {
        List<Trade> tradeList = tradeRepository.findAll(Sort.by(Sort.Direction.DESC, "tradeDate"));
        return tradeList;
    }

    @Transactional
    public String saveTradeInfo(TradeRequest request, String token) {
        String resultCd = "success";

        Claims claims = jwtProvider.parseJwtToken(token);

        if (claims == null) {
            throw new IllegalArgumentException("잘못된 토큰입니다.");
        }

        try {
            Card card = cardRepository.findByCardRefId(request.getCardRefId());

            Trade newTrade = new Trade(request.getTradeMoney(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()), card);
            tradeRepository.save(newTrade);
        } catch (Exception e) {
            resultCd = "error";
            e.printStackTrace();
        } finally {
            return resultCd;
        }
    }
}
