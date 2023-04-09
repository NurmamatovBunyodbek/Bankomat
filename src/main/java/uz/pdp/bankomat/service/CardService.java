package uz.pdp.bankomat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.bankomat.entity.Card;
import uz.pdp.bankomat.payload.Result;
import uz.pdp.bankomat.repository.CardRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    CardRepo cardRepo;

    public List<Card> allCardList() {
        List<Card> cardList = cardRepo.findAll();
        return cardList;
    }

    public Card getCardId(Integer id) {
        Optional<Card> optionalCard = cardRepo.findById(id);
        return optionalCard.get();
    }

    public Result addCard(Card card) {
        Card card1 = new Card();
        boolean byCardNumber = cardRepo.existsByCardNumber(card.getCardNumber());
        if (byCardNumber) {
            return new Result("Bunday card mavjud", false);
        }
        card1.setFullName(card.getFullName());
        card1.setLastName(card.getLastName());
        card1.setCardType(card.getCardType());
        card1.setBankName(card.getBankName());
        card1.setValidity(card.getValidity());
        card1.setCode(card.getCode());
        card1.setCodeCVV(card.getCodeCVV());
        cardRepo.save(card1);

        return new Result("Added Card", true);
    }

    public Result editingCard(Integer id, Card card) {
        Optional<Card> optionalCard = cardRepo.findById(id);
        if (optionalCard.isPresent()) {
            Card card1 = optionalCard.get();
            card1.setFullName(card.getFullName());
            card1.setLastName(card.getLastName());
            card1.setCardNumber(card.getCardNumber());
            card1.setCardType(card.getCardType());
            card1.setBankName(card.getBankName());
            card1.setValidity(card.getValidity());
            card1.setCode(card.getCode());
            card1.setCodeCVV(card.getCodeCVV());
            cardRepo.save(card1);
            return new Result("Card editing", true);
        }
        return new Result("Card not found", false);
    }

    public Result deletedCard(Integer id) {
        cardRepo.deleteById(id);
        return new Result("Card deleted", true);
    }


}
