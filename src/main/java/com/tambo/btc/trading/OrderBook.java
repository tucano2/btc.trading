package com.tambo.btc.trading;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderBook {

	List<Order> bids;
	List<Order> asks;

	public List<Order> getTopBids(long numberOfLevels, long minimumBpsFromTop) {
		return getTopLevels(numberOfLevels, minimumBpsFromTop, true);
	}

	public List<Order> getTopAsks(long numberOfLevels, long minimumBpsFromTop) {
		return getTopLevels(numberOfLevels, minimumBpsFromTop, false);
	}

	private List<Order> getTopLevels(long numberOfLevels, long minimumBpsFromTop, boolean isBids) {
		List<Order> sideOfBook = isBids ? this.bids : this.asks;

		Optional<Order> topMaybe = sideOfBook.stream().findFirst();
		if (!topMaybe.isPresent()) {
			return Collections.emptyList();
		}

		return sideOfBook.stream()
				/* filter by bps away */.filter(
						o -> CalculationHelper.bpsAway(topMaybe.get().price, o.price) <= minimumBpsFromTop)
				/* sort by size */.sorted((o1, o2) -> Double.valueOf(o2.amount).compareTo(Double.valueOf(o1.amount)))
				.limit(numberOfLevels)
				/* sort by price */.sorted((o1, o2) -> Double.valueOf(o1.price).compareTo(o2.price) * (isBids ? -1 : 1))
				.collect(Collectors.toList());
	}
}
