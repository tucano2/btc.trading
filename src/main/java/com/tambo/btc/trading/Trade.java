package com.tambo.btc.trading;

public class Trade {
	int tid;
	double timestamp, price, amount;
	String exchange, type;

	public int getTradeId() {
		return tid;
	}

	public double getTimestamp() {
		return timestamp;
	}

	public double getPrice() {
		return price;
	}

	public double getAmount() {
		return amount;
	}

	public String getExchange() {
		return exchange;
	}

	public String getType() {
		return type;
	}

}
