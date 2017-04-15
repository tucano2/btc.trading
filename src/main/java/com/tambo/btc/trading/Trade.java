package com.tambo.btc.trading;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Trade {

	int tid;
	double price, amount;
	String exchange, type;
	long timestamp;

	public int getTradeId() {
		return tid;
	}

	public long getTimestamp() {
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

	public LocalDateTime getDateTime() {
		return Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

}
