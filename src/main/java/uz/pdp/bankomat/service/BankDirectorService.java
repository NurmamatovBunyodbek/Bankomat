package uz.pdp.bankomat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.bankomat.entity.BankDirector;
import uz.pdp.bankomat.entity.Bankomat;
import uz.pdp.bankomat.payload.BankDirectorDto;
import uz.pdp.bankomat.payload.Result;
import uz.pdp.bankomat.repository.BankDirectorRepo;
import uz.pdp.bankomat.repository.BankomatRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankDirectorService {

    @Autowired
    BankDirectorRepo bankDirectorRepo;

    @Autowired
    BankomatRepo bankomatRepo;
    public Result addBankDirector(BankDirectorDto bankDirectorDto){
        BankDirector bankDirector = new BankDirector();
        bankDirector.setFullName(bankDirectorDto.getFullName());
        bankDirector.setInputHeadDay(bankDirectorDto.getInputHeadDay());
        bankDirector.setOutputHeadDay(bankDirectorDto.getOutputHeadDay());

//        List<Bankomat> bankomatList=new ArrayList<>();
//        for (Bankomat bankomat:bankomatList) {
//            Bankomat bankomat1 = new Bankomat(
//                    bankomat.getCardType(),
//                    bankomat.getMaxAmount(),
//                    bankomat.getRemainingAmount(),
//                    bankomat.getPasswordCard(),
//            );
//              bankomatList.add(bankomat1);
//        }
        bankDirector.setBankomatList(bankDirectorDto.getBankomatList());
         bankDirectorRepo.save(bankDirector);
           return new Result("Added BankDirector",true);
    }


    public  Result deletedBankDirector(Integer id){
        bankDirectorRepo.deleteById(id);
        return  new Result("Deleted",true);
    }


}
