package com.investment_management.user_service.controller;


import com.investment_management.user_service.dto.KycDto;
import com.investment_management.user_service.services.kyc.DefaultKycService;
import com.investment_management.user_service.services.user.DefaultUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class KycController {

    @Autowired
    DefaultKycService defaultKycService;

    @Autowired
    DefaultUserService defaultUserService;

    @PostMapping(path = "/kyc")
    public ResponseEntity<KycDto> addKyc(@Valid @RequestBody KycDto kycDto){
       KycDto kycDto1 =  defaultKycService.saveDetails(kycDto,defaultUserService.getUserInfo("id"));
        return new ResponseEntity<>(kycDto1, HttpStatus.OK);
    }

    @GetMapping(path = "/kyc/status")
    public ResponseEntity<String> getKycInfo(){
        return new ResponseEntity<>(defaultKycService.getKycByUserId(defaultUserService.getUserInfo("id")),HttpStatus.OK);
    }


}
