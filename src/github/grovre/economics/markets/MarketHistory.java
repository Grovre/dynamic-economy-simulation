package github.grovre.economics.markets;

import github.grovre.economics.markets.transactions.Order;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MarketHistory {
    private final List<Order> closedOrders = new ArrayList<>();

    public List<Order> getClosedOrders() {
        return closedOrders;
    }

    public Stream<Order> ordersBetween(@NotNull Instant i1, @NotNull Instant i2) {
        Instant begin;
        Instant end;
        if (i1.isBefore(i2)) {
            begin = i1;
            end = i2;
        } else {
            begin = i2;
            end = i1;
        }

        return getClosedOrders().stream()
                .filter(order -> {
                    var when = order.getInstant();
                    return begin.isBefore(when) && when.isBefore(end);
                });
    }
}
