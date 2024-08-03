package com.investment_management.user_service.controller;

import com.investment_management.user_service.services.user.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

        @Autowired
        DefaultUserService defaultUserService;

        @GetMapping(path = "/id")
        public ResponseEntity<String> getUserId(){
            String userId = defaultUserService.getUserInfo("id");
            return new ResponseEntity<>(userId, HttpStatus.OK);
        }
}
