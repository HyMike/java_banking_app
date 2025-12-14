package com.banking.banking_app.model;

import com.banking.banking_app.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    private double balance=0.0;

    @ManyToOne 
    @JoinColumn(name="user_id", nullable = false)
    private User user;


}
