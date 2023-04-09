package uz.pdp.bankomat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.bankomat.payload.BankDirectorDto;
import uz.pdp.bankomat.payload.Result;
import uz.pdp.bankomat.service.BankDirectorService;

@RestController
@RequestMapping("/bankDirector")
public class BankDirectorController {

    @Autowired
    BankDirectorService bankDirectorService;

    @PostMapping
    public Result add(@RequestBody BankDirectorDto bankDirectorDto) {
        Result result = bankDirectorService.addBankDirector(bankDirectorDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleted(@PathVariable Integer id) {
        Result result = bankDirectorService.deletedBankDirector(id);
        return result;
    }

}


