package com.secondstax.fxPurchasingApplication.repository;

import com.secondstax.fxPurchasingApplication.model.Token;
import com.secondstax.fxPurchasingApplication.model.Trader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
            select t from Token t inner join Trader tr\s
            on t.trader.email = tr.email\s
            where tr.email = :email and (t.expired = false or t.revoked = false)\s
            """)
    List<Token> findAllValidTokenByUser(String email);

    Optional<Token> findByToken(String token);

    void deleteByTrader(Trader trader);

}