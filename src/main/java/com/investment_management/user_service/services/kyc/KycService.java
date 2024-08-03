package com.investment_management.user_service.services.kyc;

import com.investment_management.user_service.dto.KycDto;

public interface KycService {

    KycDto saveDetails(KycDto kycDto, String userId);

    String getKycByUserId(String userId);

    Boolean isKycVerified(String userId);

}
