package com.investment_management.user_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DematDto {

    @NotNull(message = "Type can not be blank")
    @Pattern(regexp = "^(WALLET|STOCKS|F&O|COMMODITY|CURRENCY|MF)$", message = "Type must be WALLET or STOCKS or F&O or COMMODITY or CURRENCY or MF ")
    private String type;

    @NotNull (message = "Amount can not be blank or negative")
    @Min(value= 0)
    private Long amount;

    @NotNull(message = "add-type can't be blank")
    @Pattern(regexp = "^(ADD|DEDUCT)$", message = "add-type must be add or deduct")
    private String addType;

    public DematDto(String type, Long amount) {
        this.type = type;
        this.amount = amount;
    }
}
