package uz.pdp.bankomat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.bankomat.entity.BankManager;
import uz.pdp.bankomat.payload.BankManagerDto;
import uz.pdp.bankomat.payload.Result;
import uz.pdp.bankomat.repository.BankManagerRepo;

import java.util.List;
import java.util.Optional;

@Service
public class BankManagerService {

    @Autowired
    BankManagerRepo bankManagerRepo;

    public List<BankManager> bankManagerList() {
        List<BankManager> all = bankManagerRepo.findAll();
        return all;
    }

    public Result addManager(BankManagerDto bankManagerDto) {
        BankManager bankManager = new BankManager();
        bankManager.setFullName(bankManagerDto.getFullName());
        bankManager.setEmail(bankManagerDto.getEmail());
        bankManager.setPassword(bankManagerDto.getPassword());
        bankManagerRepo.save(bankManager);
        return new Result("Added bankManager", true);
    }

    public Result editingManager(Integer id, BankManagerDto bankManagerDto) {
        Optional<BankManager> optionalBankManager = bankManagerRepo.findById(id);
        if (optionalBankManager.isPresent()) {
            BankManager bankManager = optionalBankManager.get();
            bankManager.setFullName(bankManagerDto.getFullName());
            bankManager.setEmail(bankManagerDto.getEmail());
            bankManager.setPassword(bankManagerDto.getPassword());
            return new Result("Editing", true);
        }
        return new Result("BankManager not found", false);
    }

    public Result deletedBankManager(Integer id) {
        bankManagerRepo.deleteById(id);
        return new Result("BankManager deleted", true);
    }


}
