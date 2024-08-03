package com.investment_management.user_service.controller;

import com.investment_management.user_service.dto.BankDto;
import com.investment_management.user_service.services.bank.DefaultBankService;
import com.investment_management.user_service.services.user.DefaultUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

@RestController
@RequestMapping("/api/v1")
public class BankController {
    @Autowired
    private DefaultUserService defaultUserService;

    @Autowired
    private DefaultBankService defaultBankService;

    @PostMapping(path = "/bank")
    public ResponseEntity<BankDto> addBank(@Valid @RequestBody BankDto bankDto){
        BankDto bankDto1 =  defaultBankService.saveDetails(bankDto,defaultUserService.getUserInfo("id"));
        return new ResponseEntity<>(bankDto, HttpStatus.OK);
    }

    @GetMapping(path = "/bank")
    public ResponseEntity<BankDto> getBankInfo(){
        return new ResponseEntity<>(defaultBankService.getBankByUserId(defaultUserService.getUserInfo("id")),HttpStatus.OK);
    }

    @RequestMapping(value = "/bank", method = DELETE)
    public ResponseEntity deleteBank(){
        return new ResponseEntity<>(defaultBankService.deleteBankByUserId(defaultUserService.getUserInfo("id")),HttpStatus.OK);
    }

}
