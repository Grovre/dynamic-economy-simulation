package github.grovre.transactions;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public abstract class Order implements Comparable<Order> {

    private final UUID id;
    private FulfilledOrderInfo fulfilledOrderInfo;
    private final Instant instant;
    private final int initialQuantity;
    private int remainingQuantity;
    private double price;

    public Order(double price, int initialQuantity, Instant instant, UUID id) {
        this.id = id;
        this.fulfilledOrderInfo = null;
        this.instant = instant;
        this.initialQuantity = initialQuantity;
        this.remainingQuantity = initialQuantity;
        this.price = price;
    }

    public Order(double price, int initialQuantity, Instant instant) {
        this(price, initialQuantity, instant, UUID.randomUUID());
    }

    public void fulfill(int quantity, Instant when) {
        if (quantity > remainingQuantity)
            throw new ArithmeticException
                    ("Can't fulfill an order with quantity greater than what's asked for");

        remainingQuantity -= quantity;

        if (remainingQuantity == 0)
            this.fulfilledOrderInfo = new FulfilledOrderInfo(when);
    }

    public void fulfill(Order other, Instant when) {
        var maxFulfilledQuantity = Math.min(remainingQuantity, other.remainingQuantity);
        remainingQuantity -= maxFulfilledQuantity;
        other.remainingQuantity -= maxFulfilledQuantity;

        assert remainingQuantity >= 0;
        assert other.remainingQuantity >= 0;

        if (remainingQuantity == 0)
            fulfilledOrderInfo = new FulfilledOrderInfo(when);

        if (other.remainingQuantity == 0)
            other.fulfilledOrderInfo = new FulfilledOrderInfo(when);
    }

    public UUID getId() {
        return id;
    }

    public boolean isFulfilled() {
         if (fulfilledOrderInfo == null)
             return false;

         assert remainingQuantity == 0
                 : "invalid remaining quantity";
         return true;
    }

    public Optional<FulfilledOrderInfo> getFulfilledOrderInfo() {
        var orderInfo = Optional.ofNullable(fulfilledOrderInfo);

        if (orderInfo.isEmpty()) {
            assert !isFulfilled() && remainingQuantity > 0
                    : "returning non null fulfilled order info despite not being fulfilled";
        } else {
            assert isFulfilled() && remainingQuantity == 0
                    : "returning null fulfilled order despite being fulfilled";
        }

        return orderInfo;
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

    public double getPrice() {
        return price;
    }

    public double getPricePerItem() {
        return price / initialQuantity;
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", fulfilledOrderInfo=" + fulfilledOrderInfo +
                ", instant=" + instant +
                ", initialQuantity=" + initialQuantity +
                ", remainingQuantity=" + remainingQuantity +
                ", price=" + price +
                '}';
    }
}
