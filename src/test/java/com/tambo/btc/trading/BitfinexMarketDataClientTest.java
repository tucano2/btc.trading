package com.tambo.btc.trading;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;

import com.google.gson.Gson;

public class BitfinexMarketDataClientTest {

	@Test
	public void testLatestQuote() {

		System.out.println(Bitfinex.getLatestQuote().last_price);
	}

	@Test
	public void testCalcHelper() {

		assertEquals(150, CalculationHelper.bpsAway(100, 101.50), 0);
	}

	@Test
	public void testOrderBook() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("https://api.bitfinex.com/v1");
		target = target.path("/book/BTCUSD");
		Invocation.Builder builder = target.request();
		Response response = builder.get();
		String quoteString = response.readEntity(String.class);
		Gson gson = new Gson();
		OrderBook book = gson.fromJson(quoteString, OrderBook.class);

		System.out.println("Top 5 Ask Levels (Sorted by level):");
		book.getTopAsks(5, 100).forEach(b -> print(b));
		;

		System.out.println("Top 5 Bid Levels (Sorted by level):");
		book.getTopBids(5, 100).forEach(b -> print(b));

	}

	private void print(Object o) {
		System.out.println(ToStringBuilder.reflectionToString(o));

	}

	@Test
	public void testTrades() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("https://api.bitfinex.com/v1");
		target = target.path("/trades/BTCUSD");
		Invocation.Builder builder = target.request();
		Response response = builder.get();
		String quoteString = response.readEntity(String.class);
		Gson gson = new Gson();
		List<Trade> trades = gson.fromJson(quoteString, List.class);
		System.out.println(trades);
	}
}
