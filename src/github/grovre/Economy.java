package github.grovre;

import github.grovre.markets.Market;
import github.grovre.markets.MarketProduct;

import java.util.*;


public class Economy {
    private final HashMap<MarketProduct, Market> markets;

    public Economy() {
        markets = new HashMap<>();
    }

    public Economy(Market... markets) {
        this();
        for (var m : markets)
            this.markets.put(m.getProduct(), m);
    }

    public Market getOrCreateMarket(MarketProduct product) {
        return markets.computeIfAbsent(product, Market::new);
    }

    public Optional<Market> getMarket(MarketProduct product) {
        return Optional.ofNullable(markets.get(product));
    }
}
