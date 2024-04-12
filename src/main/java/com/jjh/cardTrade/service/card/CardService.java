package com.jjh.cardTrade.service.card;

import com.jjh.cardTrade.domain.card.Card;
import com.jjh.cardTrade.dto.card.request.CardRequest;
import com.jjh.cardTrade.dto.card.response.CardResponse;
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

    public Card getCardById(String cardRefId) {
        Card card = cardRepository.findByCardRefId(cardRefId);
        return card;
    }

    @Transactional
    public CardResponse saveCard(CardRequest request) {
        String resultCd = "success";
        String resultMsg = "카드 등록이 성공하였습니다.";
        String cardRefId = "";
        CardResponse response = new CardResponse();

        try {
            if (cardRepository.findByCardNo(request.getCardNo()) != null) {
                resultCd = "error";
                resultMsg = "이미 등록되어 있는 카드 정보입니다.";
                Card card = getCard(request);
                cardRefId = card.getCardRefId();
            } else {
                SecretKey key = CryptoUtil.getKey();
                IvParameterSpec ivParameterSpec = CryptoUtil.getIv();
                String specName = "AES/CBC/PKCS5Padding";
                String cardNoEnc = CryptoUtil.encrypt(specName, key, ivParameterSpec, request.getCardNo());

                Card newCard = new Card(cardNoEnc, request.getUserKey(), request.getCardNo(), request.getCardUser());
                cardRepository.save(newCard);
            }
        } catch (Exception e) {
            resultCd = "error";
            resultMsg = "카드 등록이 실패하였습니다.";
            e.printStackTrace();
        } finally {
            if (resultCd == "success") {
                Card card = getCard(request);
                cardRefId = card.getCardRefId();
            }

            response.setCardRefId(cardRefId);
            response.setResultCd(resultCd);
            response.setResultMsg(resultMsg);

            return response;
        }

    }
}
