package uz.pdp.bankomat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.bankomat.entity.Card;
import uz.pdp.bankomat.payload.Result;
import uz.pdp.bankomat.service.CardService;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @GetMapping
    public List<Card> cardList() {
        List<Card> list = cardService.allCardList();
        return list;
    }

    @GetMapping("/{id}")
    public Card getId(@PathVariable Integer id) {
        Card cardId = cardService.getCardId(id);
        return cardId;
    }

    @PostMapping
    public Result add(@RequestBody Card card) {
        Result result = cardService.addCard(card);
        return result;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Card card) {
        Result result = cardService.editingCard(id, card);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleted(@PathVariable Integer id) {
        Result result = cardService.deletedCard(id);
        return result;
    }
}
