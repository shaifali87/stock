package com.hackerrank.stocktrade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.service.TradeService;

@RestController
public class StockTradeApiRestController {
	@Autowired
	private TradeService tradeService;

	@RequestMapping(value = "/erase/", method = RequestMethod.DELETE)
	public ResponseEntity<Trade> deleteAllTrade() {
		tradeService.erasingAllTrades();
		return new ResponseEntity<Trade>(HttpStatus.OK);
	}

	@RequestMapping(value = "/trades/", method = RequestMethod.POST)
	public ResponseEntity<?> addTrade(@RequestBody Trade trade) {
		if (tradeService.isTradeExist(trade)) {
			return new ResponseEntity<Trade>(trade, HttpStatus.BAD_REQUEST);
		}
		tradeService.addTrade(trade);
		return new ResponseEntity<Trade>(trade, HttpStatus.OK);
	}

	@RequestMapping(value = "trades/", method = RequestMethod.GET)
	public ResponseEntity<List<Trade>> getAllTrades() {
		List<Trade> trades = tradeService.getAllTrade();
		return new ResponseEntity<List<Trade>>(trades, HttpStatus.OK);
	}

	@RequestMapping(value = "trades/users/{userId}", method = RequestMethod.GET)
	public ResponseEntity<?> getTradeById(@PathVariable("userId") long id) {
		Trade trade = tradeService.getByID(id);
		if (trade == null) {
			return new ResponseEntity<Trade>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Trade>(trade, HttpStatus.OK);
	}

	@RequestMapping(value = "trades/stocks/{stockSymbol}", method = RequestMethod.GET)
	public ResponseEntity<?> getTradeByStockSymbol(@PathVariable("stockSymbol") String stockSymbol) {
		Trade trade = tradeService.getByStockSymbol(stockSymbol);
		if (trade == null) {
			return new ResponseEntity<Trade>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Trade>(trade, HttpStatus.OK);
	}
	@RequestMapping(value = "trades/users/{userId}/stocks/{stockSymbol}", method = RequestMethod.GET)
	public ResponseEntity<?> getTradeByIdAndStockSymbol(@PathVariable("userId") long id , @PathVariable("stockSymbol") String stockSymbol) {
		Trade trade = tradeService.getByIdAndStockSymbol(id,stockSymbol);
		if (trade == null) {
			return new ResponseEntity<Trade>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Trade>(trade, HttpStatus.OK);
	}

}
