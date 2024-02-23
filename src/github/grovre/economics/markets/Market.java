package github.grovre.economics.markets;

import github.grovre.economics.markets.transactions.BuyOrder;
import github.grovre.economics.markets.transactions.Order;
import github.grovre.economics.markets.transactions.SellOrder;

import java.time.Instant;
import java.util.*;

public class Market {

    private final MarketProduct product;
    private final List<BuyOrder> activeBuyOrders;
    private final List<SellOrder> activeSellOrders;
    private final MarketHistory history;

    public Market(MarketProduct product) {
        this.product = product;
        activeBuyOrders = new ArrayList<>();
        activeSellOrders = new ArrayList<>();
        this.history = new MarketHistory();
    }

    public BuyOrder placeBuyOrder(int quantity, double maximumPrice) {
        var order = new BuyOrder(maximumPrice, quantity, Instant.now());
        activeBuyOrders.add(order);
        return order;
    }

    public SellOrder placeSellOrder(int quantity, double sellPrice) {
        var order = new SellOrder(sellPrice, quantity, Instant.now());
        activeSellOrders.add(order);
        return order;
    }

    public List<Order> updateActiveOrders() {
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

    public List<BuyOrder> getActiveBuyOrders() {
        return Collections.unmodifiableList(activeBuyOrders);
    }

    public List<SellOrder> getActiveSellOrders() {
        return Collections.unmodifiableList(activeSellOrders);
    }
}
