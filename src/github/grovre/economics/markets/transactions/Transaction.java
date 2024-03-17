package github.grovre.economics.markets.transactions;

import java.time.Instant;
import java.util.UUID;

public record Transaction(Order buyingOrder, Order sellingOrder, int quantityPurchased, double pricePerItem, Instant when, UUID id) {
    public Transaction(Order buyingOrder, Order sellingOrder, int quantityPurchased, double pricePerItem, Instant when, UUID id) {
        if (buyingOrder == null || sellingOrder == null)
            throw new IllegalArgumentException("Orders cannot be null");
        
        if (buyingOrder.getOrderType() != OrderType.BUY)
            throw new IllegalArgumentException("The buying order must be of type BUY");
        if (sellingOrder.getOrderType() != OrderType.SELL)
            throw new IllegalArgumentException("The selling order must be of type SELL");
        
        this.buyingOrder = buyingOrder;
        this.sellingOrder = sellingOrder;
        this.quantityPurchased = quantityPurchased;
        this.pricePerItem = pricePerItem;
        this.when = when;

        if (id == null)
            id = UUID.randomUUID();

        this.id = id;
    }

    public Transaction(Order buyingOrder, Order sellingOrder, int quantityPurchased, double pricePerItem, Instant when) {
        this(buyingOrder, sellingOrder, quantityPurchased, pricePerItem, when, null);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "quantityPurchased=" + quantityPurchased +
                ", pricePerItem=" + pricePerItem +
                '}';
    }
}
