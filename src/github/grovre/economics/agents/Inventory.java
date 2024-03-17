package github.grovre.economics.agents;

import github.grovre.economics.markets.MarketProduct;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Inventory {
    private final Map<MarketProduct, Integer> products;
    public final UUID uuid;

    public Inventory(UUID uuid) {
        this.products = new HashMap<>();
        this.uuid = uuid;
    }

    public Inventory() {
        this(UUID.randomUUID());
    }
    
    public void addProduct(MarketProduct product, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be non-negative");
        }
        
        products.compute(product, (k, v) -> (v == null) ? quantity : v + quantity);
    }
    
    public void removeProduct(MarketProduct product, int quantity) {
        var currentQuantity = products.get(product);
        if (currentQuantity == null) {
            throw new IllegalArgumentException("Product not in the inventory");
        } else if (currentQuantity < quantity) {
            throw new IllegalArgumentException("Not enough product in the inventory");
        } else if (currentQuantity == quantity) {
            products.remove(product);
        } else {
            products.put(product, currentQuantity - quantity);
        }
    }
}
