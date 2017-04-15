package com.tambo.btc.trading;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Bitfinex {

	public static Quote getLatestQuote() {
		Client client = ClientBuilder.newBuilder().newClient();
		WebTarget target = client.target("https://api.bitfinex.com/v1");
		target = target.path("/pubticker/BTCUSD");
		Invocation.Builder builder = target.request();
		Response response = builder.get();
		String quoteString = response.readEntity(String.class);
		Gson gson = new Gson();
		Quote quote = gson.fromJson(quoteString, Quote.class);
		return quote;
	}

	public static OrderBook getOrderBook() {
		Client client = ClientBuilder.newBuilder().newClient();
		WebTarget target = client.target("https://api.bitfinex.com/v1");
		target = target.path("/book/BTCUSD");
		Invocation.Builder builder = target.request();
		Response response = builder.get();
		String quoteString = response.readEntity(String.class);
		Gson gson = new Gson();
		OrderBook book = gson.fromJson(quoteString, OrderBook.class);
		return book;
	}

	public static List<Trade> getTrades() {
		Client client = ClientBuilder.newBuilder().newClient();
		WebTarget target = client.target("https://api.bitfinex.com/v1");
		target = target.path("/trades/BTCUSD");
		Invocation.Builder builder = target.request();
		Response response = builder.get();
		String quoteString = response.readEntity(String.class);
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		Trade[] trades = gson.fromJson(quoteString, Trade[].class);
		return Arrays.asList(trades);
	}
}
