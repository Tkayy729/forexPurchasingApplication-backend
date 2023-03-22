package com.secondstax.fxPurchasingApplication.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Trader {
    @Id
    private String email;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "trader", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<BankAccount> bankAccounts;

    @OneToMany(mappedBy = "traders", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Order> orders;

}
