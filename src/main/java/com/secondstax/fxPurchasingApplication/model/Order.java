package com.secondstax.fxPurchasingApplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.secondstax.fxPurchasingApplication.enums.OrderStatus;
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

    private String fores;

    private String country;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String provider;

    @ManyToOne
    @JoinColumn(name = "trader_email")
    @JsonBackReference
    private Trader trader;

    @ManyToOne
    @JoinColumn(name = "bank_account")
    private BankAccount bankAccount;

}
