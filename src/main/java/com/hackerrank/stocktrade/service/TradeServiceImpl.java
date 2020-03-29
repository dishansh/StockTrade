package com.hackerrank.stocktrade.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.stocktrade.exception.BadResourceRequestException;
import com.hackerrank.stocktrade.exception.NoSuchResourceFoundException;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.repository.TradeRepository;
import com.hackerrank.stocktrade.repository.UserRepository;

@Service("tradeService")
public class TradeServiceImpl implements TradeService {

	@Autowired
	private TradeRepository tradeRepository;
	
	@Autowired
	UserRepository userRepo;

	@Override
	public void deleteAllTrades() {

		tradeRepository.deleteAllInBatch();
	}

	@Override
	public void createTrade(Trade trade) {

		Optional<Trade> existingTrade = tradeRepository.findById(trade.getId());
		if (existingTrade.isPresent()) {
			throw new BadResourceRequestException("Trade with same id exists.");
		}
		tradeRepository.save(trade);
	}

	@Override
	public Trade getTradeById(Long id) {

		Optional<Trade> existingTrade = tradeRepository.findById(id);
		if (existingTrade.isPresent() == false) {
			throw new NoSuchResourceFoundException("No trade with given id found.");
		}
		return existingTrade.get();
	}

	@Override
	public List<Trade> getAllTrades() {

		return tradeRepository.findAll();
	}

	@Override
	public List<Trade> getTradeByUserId(Long id) {
		
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			List<Trade> userFilteredTradeList = tradeRepository.findByUser(user.get());
			if (userFilteredTradeList.size() == 0) {
				throw new NoSuchResourceFoundException("No trade available for given User Id");
			}
			return userFilteredTradeList;
		} else {
			throw new NoSuchResourceFoundException("No User available for given User Id");
		}	
	}

	@Override
	public List<Trade> getTradeByStock(String stock) {

		List<Trade> tradeList = tradeRepository.findBySymbol(stock);
		if(tradeList.size()<1) {
			throw new NoSuchResourceFoundException("Stock not found for given stock -" + stock);
		}
		return tradeList;
	}

	@Override
	public List<Trade> getTradeByStockAndUserId(String stock, Long id) {
		
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			List<Trade> tradeListByStockAndUser = tradeRepository.findByUserAndSymbol(user.get(), stock);
			if (tradeListByStockAndUser.size() == 0) {
				throw new NoSuchResourceFoundException("Trade not found for given stock and user id");
			}
			return tradeListByStockAndUser;
		}else {
			throw new NoSuchResourceFoundException("User not found with given user id");
		}
		
		
	}
}
