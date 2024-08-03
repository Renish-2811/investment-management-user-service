package com.investment_management.user_service.services.balance;

import com.investment_management.user_service.constants.WalletType;
import com.investment_management.user_service.dto.DematDto;
import com.investment_management.user_service.entity.Demat;
import com.investment_management.user_service.exception.InsufficientResourceException;
import com.investment_management.user_service.exception.ResourceNotFoundException;
import com.investment_management.user_service.mapper.MapperIf;
import com.investment_management.user_service.repository.BalanceRepo;
import com.investment_management.user_service.repository.BankRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//import com.investment_management.user_service.mapper.Mapper;

//import static com.investment_management.user_service.mapper.Mapper.DematToDematDtoMapper;

@Service
public class DefaultBalanceService implements BalanceService{

    @Autowired
    private BalanceRepo balanceRepo;

    @Autowired
    private BankRepo bankRepo;

    @Autowired
    private MapperIf mapperIf;

    @Override
    public DematDto updateWalletBalance(DematDto dematDto, String userId) {

        DematDto responseDematDto;
        if (!bankRepo.findByUserId(userId).isPresent()){
            throw new ResourceNotFoundException("Please add bank first");
        }

        //Initializing empty wallet balances if wallets are not present
        initialBalanceInitializer(userId);
        if (dematDto.getType().equals("WALLET")) {
            if (!balanceRepo.findByUserIdAndType(userId,"WALLET").isPresent()){
                Demat demat = new Demat("WALLET",0l);
                demat.setUserId(userId);
                balanceRepo.save(demat);
            }
                Demat demat = balanceRepo.findByUserIdAndType(userId,"WALLET").orElseThrow(()->new ResourceNotFoundException("Wallet Not found"));
                if(dematDto.getAddType().equals("ADD"))
                {   demat.setAmount(demat.getAmount()+dematDto.getAmount());
                }
                else{
                    if(demat.getAmount()< dematDto.getAmount()){
                        throw new InsufficientResourceException("Can Deallocate more than wallet balance");
                    }
                    demat.setAmount(demat.getAmount()-dematDto.getAmount());
                }
                DematDto dematDto2 = mapperIf.mapToDematDto(balanceRepo.save(demat));
                responseDematDto = dematDto2;
            }
        else{
            Long walletBalance = balanceRepo.findByUserIdAndType(userId,"WALLET").orElseThrow(()->new ResourceNotFoundException("Wallet not found")).getAmount();
            if (dematDto.getAmount()>walletBalance && dematDto.getAddType().equals("ADD")){
                throw new ResourceNotFoundException("Please add sufficient wallet balance");
            }
            else{
                //updating that balance
                Demat demat = balanceRepo.findByUserIdAndType(userId,dematDto.getType()).orElseThrow(()->new ResourceNotFoundException("Wallet not found"));
                if (dematDto.getAddType().equals("ADD")){
                    demat.setAmount(demat.getAmount()+dematDto.getAmount());
                }
                else{
                    if(demat.getAmount()< dematDto.getAmount()){
                        throw new InsufficientResourceException("Can Deallocate more than "+dematDto.getType()+ "balance");
                    }
                    demat.setAmount(demat.getAmount()-dematDto.getAmount());
                }
                DematDto dematDto2 = mapperIf.mapToDematDto(balanceRepo.save(demat));
                responseDematDto = dematDto2;
                //updating the wallet
                Demat demat1 = balanceRepo.findByUserIdAndType(userId,"WALLET").orElseThrow(()->new ResourceNotFoundException("Wallet Not found"));
                if(dematDto.getAddType().equals("ADD"))
                {
                    demat1.setAmount(demat1.getAmount()-dematDto.getAmount());
                }
                else{
                    demat1.setAmount(demat1.getAmount()+dematDto.getAmount());
                }
                balanceRepo.save(demat1);
            }
        }
        responseDematDto.setAddType(dematDto.getAddType());
        return responseDematDto;
    }


    public void initialBalanceInitializer(String userId){
        for (String walletType: WalletType.walletTypes()){
            if (!balanceRepo.findByUserIdAndType(userId,walletType).isPresent()){
                Demat demat = new Demat(walletType,0l);
                demat.setUserId(userId);
                balanceRepo.save(demat);
            }
        }
    }

    @Override
    public List<DematDto> getAllBalances(String userId){
        List<Demat> dematList = balanceRepo.findByUserId(userId);
        return dematList.stream().map(
                demat -> mapperIf.mapToDematDto(demat)
        ).toList();
    }

    @Override
    public DematDto addFundsTrade(DematDto dematDto, String userId) {
        Demat demat = balanceRepo.findByUserIdAndType(userId,dematDto.getType()).orElseThrow(()->new ResourceNotFoundException("Invalid Code"));
        demat.setAmount(demat.getAmount()+dematDto.getAmount());
        demat.setUserId(userId);
        return (mapperIf.mapToDematDto(balanceRepo.save(demat)));
    }

    public DematDto getSpecificTypeBalance(String userId,String type){
        List<Demat> dematList = balanceRepo.findByUserId(userId);
        return mapperIf.mapToDematDto(dematList.stream().filter(
                demat ->
                    demat.getType().equals(type)

        ).findFirst().orElseThrow(()-> new ResourceNotFoundException("Wallet type "+type+" found")));
    }
}
