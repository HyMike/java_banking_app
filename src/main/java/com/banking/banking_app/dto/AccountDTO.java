package com.banking.banking_app.dto;


import com.banking.banking_app.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AccountDTO {

    @NotNull
    private AccountType type;

    @PositiveOrZero
    private double balance = 0.0;

    @NotNull
    private long userId;

}
