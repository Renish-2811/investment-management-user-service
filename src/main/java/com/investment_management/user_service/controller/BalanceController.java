package com.investment_management.user_service.controller;


import com.investment_management.user_service.dto.DematDto;
import com.investment_management.user_service.services.balance.DefaultBalanceService;
import com.investment_management.user_service.services.user.DefaultUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BalanceController {

    @Autowired
    private DefaultBalanceService defaultBalanceService;

    @Autowired
    private DefaultUserService defaultUserService;

    @PostMapping(path = "/add-fund")
    public ResponseEntity<DematDto> addFund(@Valid @RequestBody DematDto dematDto){
        DematDto dematDto1 =  defaultBalanceService.updateWalletBalance(dematDto,defaultUserService.getUserInfo("id"));
        return new ResponseEntity<>(dematDto1, HttpStatus.OK);
    }

    @GetMapping(path = "/get-funds")
    public ResponseEntity<List<DematDto>> getFunds(){
       List< DematDto> dematDtosList =  defaultBalanceService.getAllBalances(defaultUserService.getUserInfo("id"));
        return new ResponseEntity<>(dematDtosList, HttpStatus.OK);
    }

    @GetMapping(path = "/balance/{type}")
    public ResponseEntity<DematDto> getWalletFund(@PathVariable String type){
        DematDto dematDto=  defaultBalanceService.getSpecificTypeBalance(defaultUserService.getUserInfo("id"),type);
        return new ResponseEntity<>(dematDto, HttpStatus.OK);
    }

    @PostMapping(path = "/add-trades")
    public ResponseEntity<DematDto> addFundsTrade(@Valid @RequestBody DematDto dematDto){
        DematDto dematDto1 = defaultBalanceService.addFundsTrade(dematDto,defaultUserService.getUserInfo("id"));
        return new ResponseEntity<>(dematDto1,HttpStatus.OK);
    }


}
