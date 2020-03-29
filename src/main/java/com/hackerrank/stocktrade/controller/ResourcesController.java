package com.hackerrank.stocktrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.stocktrade.service.TradeService;

@RestController
@RequestMapping(value = "/erase")
public class ResourcesController {
	
	@Autowired
	TradeService tradeService;
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void removeAllTrades() {
		System.out.println(" Setting up before removing all trades !! ");
		tradeService.deleteAllTrades();
	}
    
}
