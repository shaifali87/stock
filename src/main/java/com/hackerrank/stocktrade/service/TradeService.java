package com.hackerrank.stocktrade.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.repository.TradeRepository;

@Service
public class TradeService {

	@Autowired
	private TradeRepository tradeRepository;

	public void erasingAllTrades() {
		tradeRepository.deleteAll();
	}

	public void addTrade(Trade trade) {
		tradeRepository.save(trade);
	}

	public List<Trade> getAllTrade() {
		List<Trade> trades = new ArrayList<Trade>();
		tradeRepository.findAll().forEach(trades::add);
		return trades;
	}
	
	public Trade getByID(Long id) {
		return tradeRepository.findOne(id);
	}
	
	public boolean isTradeExist(Trade trade) {
		return getByID(trade.getId())!= null; 
	}
	
	public Trade getByStockSymbol(String stockSymbol) {
		for(Trade trade :getAllTrade()) {
			if(trade.getStockSymbol() == stockSymbol) {
				return trade;
			}
		}
		return null;
	}
	
	public Trade getByIdAndStockSymbol(long id ,String stockSymbol) {
		for(Trade trade :getAllTrade()) {
			if(trade.getId() == id && trade.getStockSymbol() == stockSymbol) {
				return trade;
			}
		}
		return null;
	}
	
}
