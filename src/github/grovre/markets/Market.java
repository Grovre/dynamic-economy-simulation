package github.grovre.markets;

import github.grovre.transactions.BuyOrder;
import github.grovre.transactions.Order;
import github.grovre.transactions.SellOrder;

import java.time.Instant;
import java.util.*;

public class Market {

    final MarketProduct product;
    private final List<BuyOrder> activeBuyOrders;
    private final List<SellOrder> activeSellOrders;

    public Market(MarketProduct product) {
        this.product = product;
        activeBuyOrders = new ArrayList<>();
        activeSellOrders = new ArrayList<>();
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

            Order last = activeBuyOrders.removeLast();
            assert nextBuyOrder == last;
            last = activeSellOrders.removeLast();
            assert matchedSellOrder == last;

            nextBuyOrder.fulfill(matchedSellOrder, Instant.now());

            if (nextBuyOrder.isFulfilled())
                fulfilledOrders.add(nextBuyOrder);
            if (matchedSellOrder.isFulfilled())
                fulfilledOrders.add(matchedSellOrder);
        }

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
