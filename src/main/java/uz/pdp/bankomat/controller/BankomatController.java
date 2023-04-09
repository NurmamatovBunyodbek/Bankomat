package uz.pdp.bankomat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.bankomat.entity.Bankomat;
import uz.pdp.bankomat.payload.Result;
import uz.pdp.bankomat.service.BankomatService;

import java.util.List;

@RestController
@RequestMapping("/bankomat")
public class BankomatController {

    @Autowired
    BankomatService bankomatService;

    @GetMapping
    public List<Bankomat> bankomats() {
        List<Bankomat> bankomats = bankomatService.bankomatList();
        return bankomats;
    }

    @GetMapping("/{id}")
    public Bankomat getId(@PathVariable Integer id) {
        Bankomat idBankomat = bankomatService.getIdBankomat(id);
        return idBankomat;
    }

    @PostMapping
    public Result add(@RequestBody Bankomat bankomat) {
        Result result = bankomatService.addBankomat(bankomat);
        return result;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody Bankomat bankomat) {
        Result result = bankomatService.editingBankomat(id, bankomat);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleted(@PathVariable Integer id) {
        Result result = bankomatService.deletedBankomat(id);
        return result;
    }
}
