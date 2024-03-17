package github.grovre.economics.markets;

import github.grovre.economics.markets.transactions.Order;
import github.grovre.economics.markets.transactions.OrderType;

import java.time.Instant;
import java.util.*;
import java.util.stream.Stream;

public class Market {

    private final MarketProduct product;
    private final PriorityQueue<Order> activeBuyOrders;
    private final PriorityQueue<Order> activeSellOrders;
    private final MarketHistory history;

    public Market(MarketProduct product) {
        this.product = product;
        activeBuyOrders = new PriorityQueue<>(new LimitOrderComparator.ForBuyOrders());
        activeSellOrders = new PriorityQueue<>(new LimitOrderComparator.ForSellOrders());
        this.history = new MarketHistory();
    }
    
    public void placeOrder(Order order) {
        switch (order.getOrderType()) {
            case BUY -> activeBuyOrders.add(order);
            case SELL -> activeSellOrders.add(order);
            default -> throw new IllegalArgumentException("Invalid order type");
        }
    }
    
    public void placeOrder(OrderType orderType, double pricePerItem, int quantity, Instant instant) {
        var order = new Order(orderType, pricePerItem, quantity, instant);
        switch (orderType) {
            case BUY -> activeBuyOrders.add(order);
            case SELL -> activeSellOrders.add(order);
            default -> throw new IllegalArgumentException("Invalid order type");
        }
    }

    public List<Order> updateMarketOrders() {
        var fulfilledOrders = new ArrayList<Order>();

        if (activeBuyOrders.isEmpty() || activeSellOrders.isEmpty())
            return fulfilledOrders;

        while (!activeBuyOrders.isEmpty() && !activeSellOrders.isEmpty()) {
            var nextBuyOrder = activeBuyOrders.poll();
            var matchedSellOrder = activeSellOrders.poll();
            
            assert nextBuyOrder != null;
            assert matchedSellOrder != null;

            if (matchedSellOrder.getPricePerItem() > nextBuyOrder.getPricePerItem())
                break;

            nextBuyOrder.fulfill(matchedSellOrder, Instant.now());

            if (nextBuyOrder.isFulfilled()) {
                fulfilledOrders.add(nextBuyOrder);
            }

            if (matchedSellOrder.isFulfilled()) {
                fulfilledOrders.add(matchedSellOrder);
            }
        }

        history.getClosedOrders().addAll(fulfilledOrders);
        return fulfilledOrders;
    }

    public MarketProduct getProduct() {
        return product;
    }

    public Stream<Order> getActiveBuyOrders() {
        return activeBuyOrders.stream();
    }

    public Stream<Order> getActiveSellOrders() {
        return activeSellOrders.stream();
    }
}
