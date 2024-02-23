package github.grovre.economics.markets;

import github.grovre.economics.markets.transactions.BuyOrder;
import github.grovre.economics.markets.transactions.Order;
import github.grovre.economics.markets.transactions.SellOrder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MarketHistory {
    private final List<Order> closedOrders = new ArrayList<>();

    public List<Order> getClosedOrders() {
        return closedOrders;
    }

    public static Stream<Order> ordersBetween(Stream<Order> orders, Instant i1, Instant i2) {
        Instant begin;
        Instant end;
        if (i1.isBefore(i2)) {
            begin = i1;
            end = i2;
        } else {
            begin = i2;
            end = i1;
        }

        return orders.filter(order -> {
                    var when = order.getInstant();
                    return begin.isBefore(when) && when.isBefore(end);
                });
    }

    public static double averageBuyOrderPrice(Stream<Order> orders) {
        return orders.filter(BuyOrder.class::isInstance)
                .mapToDouble(Order::getPricePerItem)
                .average()
                .orElse(0);
    }

    public static double averageSellOrderPrice(Stream<Order> orders) {
        return orders.filter(SellOrder.class::isInstance)
                .mapToDouble(Order::getPricePerItem)
                .average()
                .orElse(0);
    }
}
