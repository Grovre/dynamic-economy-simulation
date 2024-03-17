package github.grovre.economics.markets;

import github.grovre.economics.markets.transactions.Order;
import github.grovre.economics.markets.transactions.OrderType;

import java.time.Instant;
import java.util.*;

public class Market {

    private final MarketProduct product;
    private final List<Order> activeBuyOrders;
    private final List<Order> activeSellOrders;
    private final MarketHistory history;

    public Market(MarketProduct product) {
        this.product = product;
        activeBuyOrders = new ArrayList<>();
        activeSellOrders = new ArrayList<>();
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

        activeBuyOrders.sort(Comparator.comparing(Order::getPricePerItem)
                .reversed()
                .thenComparing(Order::getInstant)
                .reversed());
        activeSellOrders.sort(Comparator.comparing(Order::getPricePerItem)
                .thenComparing(Order::getInstant)
                .reversed());

        while (!activeBuyOrders.isEmpty() && !activeSellOrders.isEmpty()) {
            var nextBuyOrder = activeBuyOrders.getLast();
            var matchedSellOrder = activeSellOrders.getLast();

            if (matchedSellOrder.getPricePerItem() > nextBuyOrder.getPricePerItem())
                break;

            nextBuyOrder.fulfill(matchedSellOrder, Instant.now());

            if (nextBuyOrder.isFulfilled()) {
                fulfilledOrders.add(nextBuyOrder);
                activeBuyOrders.remove(nextBuyOrder);
            }

            if (matchedSellOrder.isFulfilled()) {
                fulfilledOrders.add(matchedSellOrder);
                activeSellOrders.remove(matchedSellOrder);
            }
        }

        history.getClosedOrders().addAll(fulfilledOrders);
        return fulfilledOrders;
    }

    public MarketProduct getProduct() {
        return product;
    }

    public List<Order> getActiveBuyOrders() {
        return Collections.unmodifiableList(activeBuyOrders);
    }

    public List<Order> getActiveSellOrders() {
        return Collections.unmodifiableList(activeSellOrders);
    }
}
