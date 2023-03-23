package com.secondstax.fxPurchasingApplication.model;

import com.secondstax.fxPurchasingApplication.enums.Currency;
import com.secondstax.fxPurchasingApplication.enums.Provider;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class ProviderData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Provider provider;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private Currency exchange;
}
