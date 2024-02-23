package github.grovre.economics.markets;

import github.grovre.economics.markets.transactions.Order;

import java.util.ArrayList;
import java.util.List;

public class MarketHistory {
    private final List<Order> closedOrders = new ArrayList<>();

    public List<Order> getClosedOrders() {
        return closedOrders;
    }
}
