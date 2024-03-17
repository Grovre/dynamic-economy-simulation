package github.grovre.economics.markets.transactions;

import java.time.Instant;
import java.util.*;

public class Order implements Comparable<Order> {

    private final UUID id;
    private final Instant instant;
    private final OrderType orderType;
    private final int initialQuantity;
    private int remainingQuantity;
    private final double pricePerItem;
    private final ArrayList<Transaction> transactions;

    public Order(OrderType orderType, double pricePerItem, int initialQuantity, Instant instant, UUID id) {
        if (pricePerItem < 0 || initialQuantity < 0) {
            throw new IllegalArgumentException("Price and quantity must be non-negative");
        }
        
        this.orderType = orderType;
        this.id = id;
        this.instant = instant;
        this.initialQuantity = initialQuantity;
        this.remainingQuantity = initialQuantity;
        this.pricePerItem = pricePerItem;
        this.transactions = new ArrayList<>();
    }

    public Order(OrderType orderType, double pricePerItem, int initialQuantity, Instant instant) {
        this(orderType, pricePerItem, initialQuantity, instant, UUID.randomUUID());
    }

    public Transaction fulfill(Order other, Instant when) {
        if (this == other) {
            throw new RuntimeException("An order cannot fulfill itself");
        }
        
        if (this.getOrderType() == other.getOrderType()) {
            throw new RuntimeException("An order cannot fulfill another order of the same type");
        }

        var maxFulfilledQuantity = Math.min(remainingQuantity, other.remainingQuantity);
        remainingQuantity -= maxFulfilledQuantity;
        other.remainingQuantity -= maxFulfilledQuantity;

        if (remainingQuantity < 0 || other.remainingQuantity < 0) {
            throw new ArithmeticException("Remaining quantity of an order cannot go below 0");
        }

        Order buyOrder;
        Order sellOrder;

        if (this.orderType == OrderType.BUY) {
            buyOrder = this;
            sellOrder = other;
        } else {
            buyOrder = other;
            sellOrder = this;
        }

        var transaction = new Transaction(buyOrder, sellOrder, maxFulfilledQuantity, sellOrder.getPricePerItem(), when);
        this.transactions.add(transaction);
        other.transactions.add(transaction);

        return transaction;
    }

    public UUID getId() {
        return id;
    }

    public boolean isFulfilled() {
        assert remainingQuantity >= 0;
        return remainingQuantity == 0;
    }

    public Instant getInstant() {
        return instant;
    }

    public int getInitialQuantity() {
        return initialQuantity;
    }

    public int getRemainingQuantity() {
        return remainingQuantity;
    }

    public double getPricePerItem() {
        return pricePerItem;
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Order o) {
        return instant.compareTo(o.instant);
    }

    public OrderType getOrderType() {
        return orderType;
    }

    @Override
    public String toString() {
        return getOrderType() + "{" +
                "initialQuantity=" + initialQuantity +
                ", remainingQuantity=" + remainingQuantity +
                ", price=" + pricePerItem +
                '}';
    }
}
