package br.com.bjbraz.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.mercadobitcoin.MercadoBitcoinExchange;
import org.knowm.xchange.service.marketdata.MarketDataService;

/**
 * Test Service with Mercado Bitcoin BTC/BRL prices
 * @author asimas
 */
public class MercadoBitcoinX {
	
	@Rule
	public final TestRule globalTimeout = Timeout.millis(2000);
	
	@Test
	public void obterValorDeCotacao() {
		try {
			Exchange bitstamp = ExchangeFactory.INSTANCE.createExchange(MercadoBitcoinExchange.class.getName());
	
			MarketDataService marketDataService = bitstamp.getMarketDataService();
	
			Ticker ticker = marketDataService.getTicker(CurrencyPair.BTC_BRL);
	
			assertNotNull(ticker.getLast());
			
		}catch(IOException e) {
			fail();
		}catch(Exception e) {
			fail();
		}
	}

}
