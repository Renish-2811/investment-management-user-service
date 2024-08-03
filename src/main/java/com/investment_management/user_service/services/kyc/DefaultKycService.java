package com.investment_management.user_service.services.kyc;

import com.investment_management.user_service.constants.Status;
import com.investment_management.user_service.dto.KycDto;
import com.investment_management.user_service.entity.Kyc;
import com.investment_management.user_service.exception.ResourceAlreadyFoundException;
import com.investment_management.user_service.exception.ResourceNotFoundException;
import com.investment_management.user_service.mapper.MapperIf;
import com.investment_management.user_service.repository.KycRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import static com.investment_management.user_service.mapper.Mapper.KycDtoToKycMapper;
//import static com.investment_management.user_service.mapper.Mapper.KycToKycDtoMapper;

@Slf4j
@Service
public class DefaultKycService implements KycService {

    @Autowired
    private KycRepo kycRepo;

    @Autowired
    private MapperIf mapperIf;


    @Override
    public KycDto saveDetails(KycDto kycDto, String userId) {
        KycDto kycDto1;
        if(kycRepo.findByUserId(userId).isPresent()){
            Kyc kyc = kycRepo.findByUserId(userId).get();
            if (kyc.getStatus().equals(Status.VERIFIED.toString())){
                throw new ResourceAlreadyFoundException("Kyc is already verified");
            }
            else{
                kyc.setStatus(Status.VERIFIED.toString());
            }
            kycDto1 = mapperIf.mapToKycDto(kyc);
        }
        else {
           Kyc kyc =  mapperIf.mapToKyc(kycDto);
           kyc.setUserId(userId);
           kyc.setStatus(Status.VERIFIED.toString());
           kycDto1 = mapperIf.mapToKycDto(kycRepo.save(kyc));
        }
        return kycDto1;
    }


    @Override
    public String getKycByUserId(String userId) {
        if (kycRepo.findByUserId(userId).isPresent()){
            return kycRepo.findByUserId(userId).get().getStatus();
        }
        throw new ResourceNotFoundException("Please enter proper KYC Details before hitting this end-point");
    }

    @Override
    public Boolean isKycVerified(String userId){
        if(kycRepo.findByUserId(userId).isPresent()){
            if(kycRepo.findByUserId(userId).get().getStatus().equals(Status.VERIFIED.toString())){
                return true;
            }
        }
        else{
            throw new ResourceNotFoundException("Please enter proper KYC Details before hitting this end-point");
        }
        return false;
    }
}
