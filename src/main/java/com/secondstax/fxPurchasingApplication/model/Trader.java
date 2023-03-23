package com.secondstax.fxPurchasingApplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.secondstax.fxPurchasingApplication.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin(origins = "*")
public class Trader implements UserDetails {
    @Id
    private String email;

    private String firstname;

    private String lastname;

    private String password;

    @OneToMany(mappedBy = "trader", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "trader-bankAccounts")
    private List<BankAccount> bankAccounts;

    @OneToMany(mappedBy = "trader", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Order> orders;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "trader")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
