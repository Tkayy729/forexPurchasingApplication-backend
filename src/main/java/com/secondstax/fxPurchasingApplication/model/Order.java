package com.secondstax.fxPurchasingApplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.secondstax.fxPurchasingApplication.enums.Currency;
import com.secondstax.fxPurchasingApplication.enums.OrderStatus;
import com.secondstax.fxPurchasingApplication.enums.Provider;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Currency exchange;

    private String country;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "trader_email")
    @JsonBackReference
    private Trader trader;

    @ManyToOne
    @JoinColumn(name = "bank_account")
    @JsonBackReference
    private BankAccount bankAccount;

}
