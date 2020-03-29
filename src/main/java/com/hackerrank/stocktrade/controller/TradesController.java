package com.hackerrank.stocktrade.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.service.TradeService;

@RestController
@RequestMapping(value = "/trades")
public class TradesController {
	
	@Autowired
	TradeService tradeService;
	
	// Method to create trades
	// @PostMapping(path="/create", consumes=MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method=RequestMethod.POST, consumes ="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public void createTrade(@RequestBody @Valid Trade trade) {
		tradeService.createTrade(trade);
	}
	
	// Fetch Trades by filtering Id
	@GetMapping(path="/{id}")
	@ResponseStatus(HttpStatus.OK)
	protected Trade getTradeByTradeId(@PathVariable("id") Long tradeId) {
		return tradeService.getTradeById(tradeId);
	}
	
	// Get all trades
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	protected List<Trade> getAllTrades(){
		return tradeService.getAllTrades();
	}
	
	// Get trade list based on stock symbol
	@GetMapping(path="/stocks/{stock}")
	@ResponseStatus(HttpStatus.OK)
	public List<Trade> getTradeByStock(@PathVariable("stock") String stockName){
		return tradeService.getTradeByStock(stockName);
	}
    
	// Get trade list based on user id
	@GetMapping(path="/users/{id}")
	public List<Trade> getTradeByUserId(@PathVariable("id") Long userId){
		return tradeService.getTradeByUserId(userId);
	}
	
	// Get trade list based on user and stock symbol
	@GetMapping(path="/users/{id}/stocks/{symbol}")
	public List<Trade> getTradeBySymbolAndUser(@PathVariable("id") Long id, @PathVariable("symbol") String symbol){
		return tradeService.getTradeByStockAndUserId(symbol, id);
	}
}
