package com.investment_management.user_service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KycDto {
    @Valid
    @NotNull(message = "Aadhar Card is mandatory")
    @NotBlank(message = "Aadhar Card is mandatory")
    @Pattern(regexp = "\\b\\w{10,10}\\b",message = "Format is not proper")
    private String aadharCard;

    @NotNull(message = "Pan Card is mandatory")
    @NotBlank(message = "Pan Card is mandatory")
    @Pattern(regexp = "\\b\\w{10,10}\\b",message = "Format is not proper")
    private String panCard;

    public KycDto( String aadharCard, String panCard) {
        this.aadharCard = aadharCard;
        this.panCard = panCard;
    }

}
