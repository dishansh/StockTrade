package com.hackerrank.stocktrade.service;

import java.util.List;

import com.hackerrank.stocktrade.model.Trade;

public interface TradeService {
	void deleteAllTrades();

	void createTrade(Trade trade);

	Trade getTradeById(Long id);

	List<Trade> getAllTrades();

	List<Trade> getTradeByUserId(Long id);

	List<Trade> getTradeByStock(String stock);

	List<Trade> getTradeByStockAndUserId(String stock, Long id);

}
