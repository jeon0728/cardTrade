package com.jjh.cardTrade.service.card;

import com.jjh.cardTrade.domain.card.Card;
import com.jjh.cardTrade.dto.card.request.CardRequest;
import com.jjh.cardTrade.repository.card.CardRepository;
import com.jjh.cardTrade.utils.crypto.CryptoUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;


    public CardService(
            CardRepository cardRepository
    ) {
        this.cardRepository = cardRepository;
    }

    public Card getCard(CardRequest request) {
        Card card = cardRepository.findByCardNo(request.getCardNo());
        return card;
    }

    public Optional<Card> getCardById(Long cardRefId) {
        Optional<Card> card = cardRepository.findById(cardRefId);
        return card;
    }

    @Transactional
    public String saveCard(CardRequest request) {
        String resultCd = "success";
        if (cardRepository.findByCardNo(request.getCardNo()) != null) {
            throw new IllegalArgumentException("이미 등록되어 있는 카드 정보입니다.");
        }

        try {
            SecretKey key = CryptoUtil.getKey();
            IvParameterSpec ivParameterSpec = CryptoUtil.getIv();
            String specName = "AES/CBC/PKCS5Padding";
            String cardNoEnc = CryptoUtil.encrypt(specName, key, ivParameterSpec, request.getCardNo());

            Card newCard = new Card(request.getUserKey(), request.getCardNo(), cardNoEnc, request.getCardUser());
            cardRepository.save(newCard);
        } catch (Exception e) {
            resultCd = "error";
            e.printStackTrace();
        } finally {
            return resultCd;
        }

    }
}
