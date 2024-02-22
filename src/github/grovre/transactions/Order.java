package github.grovre.transactions;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public abstract class Order {

    private final UUID id;
    private FulfilledOrderInfo fulfilledOrderInfo;
    private final Instant instant;
    private final int initialQuantity;
    private int remainingQuantity;

    public Order(int initialQuantity, Instant instant, UUID id) {
        this.id = id;
        this.fulfilledOrderInfo = null;
        this.instant = instant;
        this.initialQuantity = initialQuantity;
        this.remainingQuantity = initialQuantity;
    }

    public Order(int initialQuantity, Instant instant) {
        this(initialQuantity, instant, UUID.randomUUID());
    }

    public void fulfill(int quantity, Instant when) {
        if (quantity > remainingQuantity)
            throw new ArithmeticException
                    ("Can't fulfill an order with quantity greater than what's asked for");

        remainingQuantity -= quantity;

        if (remainingQuantity == 0)
            this.fulfilledOrderInfo = new FulfilledOrderInfo(when);
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
}
