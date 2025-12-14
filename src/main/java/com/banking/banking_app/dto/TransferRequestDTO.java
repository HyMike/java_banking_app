package com.banking.banking_app.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransferRequestDTO {
    @NotNull
    private Long fromAccountId;
    @NotNull
    private Long toAccountId;

    @Positive
    private double amount;
}
