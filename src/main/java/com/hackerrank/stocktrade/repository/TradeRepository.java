package com.hackerrank.stocktrade.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;

@Repository("tradeRepository")
public interface TradeRepository extends JpaRepository<Trade, Long> {
    @Transactional
    void deleteById(Long id);
    
    List<Trade> findBySymbol(String symbol);
    
    List<Trade> findByUser(User user);
    
    List<Trade> findByUserAndSymbol(User user, String symbol);
}
