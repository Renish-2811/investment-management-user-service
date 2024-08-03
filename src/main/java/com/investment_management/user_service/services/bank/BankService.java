package com.investment_management.user_service.services.bank;

import com.investment_management.user_service.dto.BankDto;

public interface BankService  {

    BankDto saveDetails(BankDto kycDto, String userId);

    BankDto getBankByUserId(String userId);

    String deleteBankByUserId(String userId);

}
