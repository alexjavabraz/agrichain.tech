package br.com.bjbraz.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

/**
 * Test Service with Mercado Bitcoin BTC/BRL prices
 * @author asimas
 */
public class MercadoBitcoinX {
	
	@Rule
	public final TestRule globalTimeout = Timeout.millis(2000);
	
	@Test
	public void obterValorDeCotacao() {
//		try {
//			Exchange bitstamp = ExchangeFactory.INSTANCE.createExchange(MercadoBitcoinExchange.class.getName());
//	
//			MarketDataService marketDataService = bitstamp.getMarketDataService();
//	
//			Ticker ticker = marketDataService.getTicker(CurrencyPair.BTC_BRL);
//	
//			assertNotNull(ticker.getLast());
//			
//		}catch(IOException e) {
//			fail();
//		}catch(Exception e) {
//			fail();
//		}
	}

}
