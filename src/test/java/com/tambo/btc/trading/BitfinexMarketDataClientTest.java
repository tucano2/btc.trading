package com.tambo.btc.trading;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Test;

import com.google.gson.Gson;

public class BitfinexMarketDataClientTest {

	@Test
	public void testLatestQuote() {
		
		System.out.println(Bitfinex.getLatestQuote().last_price);
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
		System.out.println(book);
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
