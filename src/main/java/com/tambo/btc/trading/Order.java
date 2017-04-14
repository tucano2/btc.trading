package com.tambo.btc.trading;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Order {
	double price, amount;
	long timestamp;

	public double getPrice() {
		return price;
	}

	public double getAmount() {
		return amount;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public LocalDateTime getDateTime() {
		return Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

}
