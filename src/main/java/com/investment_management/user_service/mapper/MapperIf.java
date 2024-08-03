package com.investment_management.user_service.mapper;

import com.investment_management.user_service.dto.BankDto;
import com.investment_management.user_service.dto.DematDto;
import com.investment_management.user_service.dto.KycDto;
import com.investment_management.user_service.entity.Bank;
import com.investment_management.user_service.entity.Demat;
import com.investment_management.user_service.entity.Kyc;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MapperIf {
    KycDto mapToKycDto(Kyc kyc);

    Kyc mapToKyc(KycDto kycDto);

    BankDto mapToBankDto(Bank bank);

    Bank mapToBank(BankDto bankDto);

    DematDto mapToDematDto(Demat demat);

    Demat mapToDemat(DematDto dematDto);
}
