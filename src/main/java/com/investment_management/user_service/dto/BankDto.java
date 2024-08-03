package com.investment_management.user_service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BankDto {

    @Valid
    @NotNull(message = "Account code must be there")
    @Digits(integer =10 , fraction = 0, message = "Invalid Account code")
//    @Pattern(regexp = "\\b\\w{11,11}\\b", message ="Invalid Account code")
    private Number accountCode;

    @Valid
    @NotBlank(message = "Account Name must not be blank")
    private String name;

    @Valid
    @NotBlank(message = "IFSC must not be blank")
    private String ifsc;

    public BankDto(Number accountCode, String name, String ifsc) {
        this.accountCode = accountCode;
        this.name = name;
        this.ifsc = ifsc;
    }
}
