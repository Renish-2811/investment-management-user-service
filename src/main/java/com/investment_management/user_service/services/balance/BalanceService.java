package com.investment_management.user_service.services.balance;

import com.investment_management.user_service.dto.DematDto;

import java.util.List;

public interface BalanceService {
    DematDto updateWalletBalance(DematDto dematDto, String userId); // this would have common logic for all

    List<DematDto> getAllBalances(String userId);

    DematDto addFundsTrade(DematDto dematDto, String userId);

}
