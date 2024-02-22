package github.grovre.economics.markets.transactions;

import java.time.Instant;
import java.util.UUID;

public record Transaction(BuyOrder buyingOrder, SellOrder sellingOrder, int quantityPurchased, double pricePerItem, Instant when, UUID id) {
    public Transaction(BuyOrder buyingOrder, SellOrder sellingOrder, int quantityPurchased, double pricePerItem, Instant when, UUID id) {
        this.buyingOrder = buyingOrder;
        this.sellingOrder = sellingOrder;
        this.quantityPurchased = quantityPurchased;
        this.pricePerItem = pricePerItem;
        this.when = when;

        if (id == null)
            id = UUID.randomUUID();

        this.id = id;
    }

    public Transaction(BuyOrder buyingOrder, SellOrder sellingOrder, int quantityPurchased, double pricePerItem, Instant when) {
        this(buyingOrder, sellingOrder, quantityPurchased, pricePerItem, when, null);
    }
}
