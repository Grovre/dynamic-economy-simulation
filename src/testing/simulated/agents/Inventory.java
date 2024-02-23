package testing.simulated.agents;

import github.grovre.economics.markets.MarketProduct;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    final Map<MarketProduct, Integer> productQuantities = new HashMap<>();

    public void addProduct(MarketProduct product, int quantity) {
        productQuantities.compute(product, (p, c) -> {
            if (c == null)
                c = 0;

            c += quantity;
            return c;
        });
    }

    public int getProductQuantity(MarketProduct product) {
        return productQuantities.getOrDefault(product, 0);
    }
}
