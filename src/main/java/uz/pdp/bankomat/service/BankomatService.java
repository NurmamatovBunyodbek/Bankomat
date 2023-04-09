package uz.pdp.bankomat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.bankomat.entity.Bankomat;
import uz.pdp.bankomat.payload.Result;
import uz.pdp.bankomat.repository.BankomatRepo;

import java.util.List;
import java.util.Optional;

@Service
public class BankomatService {

    @Autowired
    BankomatRepo bankomatRepo;

    public List<Bankomat> bankomatList() {
        List<Bankomat> list = bankomatRepo.findAll();
        return list;
    }

    public Bankomat getIdBankomat(Integer id) {
        Optional<Bankomat> optionalBankomat = bankomatRepo.findById(id);
        return optionalBankomat.get();
    }

    public Result addBankomat(Bankomat bankomat) {
        Bankomat bankomat1 = new Bankomat();
        bankomat1.setCardType(bankomat.getCardType());
        bankomat1.setMaxAmount(bankomat.getMaxAmount());
        bankomat1.setRemainingAmount(bankomat.getRemainingAmount());
        bankomat1.setPasswordCard(bankomat.getPasswordCard());
        bankomat1.setCardBlock(bankomat.isCardBlock());
        bankomatRepo.save(bankomat1);
        return new Result("Bankomat added", true);
    }

    public Result editingBankomat(Integer id, Bankomat bankomat) {
        Optional<Bankomat> byId = bankomatRepo.findById(id);
        if (byId.isPresent()) {
            Bankomat bankomat1 = byId.get();
            bankomat1.setCardType(bankomat.getCardType());
            bankomat1.setMaxAmount(bankomat.getMaxAmount());
            bankomat1.setRemainingAmount(bankomat.getRemainingAmount());
            bankomat1.setPasswordCard(bankomat.getPasswordCard());
            bankomat1.setCardBlock(bankomat.isCardBlock());
            bankomatRepo.save(bankomat1);
            return new Result("Bankomat editing", true);

        }
        return new Result("Bankomat not fount", false);
    }

    public Result deletedBankomat(Integer id) {
        bankomatRepo.deleteById(id);
        return new Result("Bankomat deleted", true);
    }


}
