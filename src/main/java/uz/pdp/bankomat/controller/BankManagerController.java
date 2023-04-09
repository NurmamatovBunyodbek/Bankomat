package uz.pdp.bankomat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.bankomat.entity.BankManager;
import uz.pdp.bankomat.payload.BankManagerDto;
import uz.pdp.bankomat.payload.Result;
import uz.pdp.bankomat.service.BankManagerService;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class BankManagerController {

    @Autowired
    BankManagerService bankManagerService;

    @GetMapping
    public List<BankManager> all() {
        List<BankManager> bankManagers = bankManagerService.bankManagerList();
        return bankManagers;
    }

    @PostMapping
    public Result add(@RequestBody BankManagerDto bankManagerDto) {
        Result result = bankManagerService.addManager(bankManagerDto);
        return result;
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, @RequestBody BankManagerDto bankManagerDto) {
        Result result = bankManagerService.editingManager(id, bankManagerDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleted(@PathVariable Integer id) {
        Result result = bankManagerService.deletedBankManager(id);

        return result;
    }
}
