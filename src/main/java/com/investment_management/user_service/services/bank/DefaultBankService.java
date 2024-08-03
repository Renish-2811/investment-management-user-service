package com.investment_management.user_service.services.bank;

import com.investment_management.user_service.dto.BankDto;
import com.investment_management.user_service.entity.Bank;
import com.investment_management.user_service.exception.ResourceAlreadyFoundException;
import com.investment_management.user_service.exception.ResourceNotFoundException;
import com.investment_management.user_service.mapper.MapperIf;
import com.investment_management.user_service.repository.BalanceRepo;
import com.investment_management.user_service.repository.BankRepo;
import com.investment_management.user_service.services.kyc.DefaultKycService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultBankService implements BankService {
    @Autowired
    DefaultKycService defaultKycService;

    @Autowired
    private BankRepo bankRepo;

    @Autowired
    private BalanceRepo balanceRepo;

    @Autowired
    private MapperIf mapperIf;

    @Override
    public BankDto saveDetails(BankDto bankDto, String userId) {
        if (!defaultKycService.isKycVerified(userId)){
            throw new ResourceNotFoundException("Please Complete Kyc Process First");
        }
        if(bankRepo.findByUserId(userId).isPresent()){
            throw new ResourceAlreadyFoundException("We support only bank account as of now");
        }
        Bank bank = mapperIf.mapToBank(bankDto);
        bank.setUserId(userId);
        //Setting Some initial amount
        bank.setAmount(1000L);
        //Setting up wallet details as soon as bank account is set up
        return mapperIf.mapToBankDto(bankRepo.save(bank));
    }

    @Override
    public BankDto getBankByUserId(String userId) {
        if (!bankRepo.findByUserId(userId).isPresent()){
            throw new ResourceNotFoundException("No Bank Present for this account");
        }
        return mapperIf.mapToBankDto(bankRepo.findByUserId(userId).get());
    }

    @Override
    public String deleteBankByUserId(String userId) {
        if (!bankRepo.findByUserId(userId).isPresent()){
            throw new ResourceNotFoundException("No Bank Present for this account");
        }
        bankRepo.delete(bankRepo.findByUserId(userId).get());
        return "Bank Deleted Successfully";
    }
}
