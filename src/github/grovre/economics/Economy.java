package github.grovre.economics;

import github.grovre.economics.markets.Market;
import github.grovre.economics.markets.MarketProduct;

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

    public Economy(MarketProduct... products) {
        this();
        for (var p : products)
            this.markets.put(p, new Market(p));
    }

    public Economy(String... products) {
        this();
        for (var p : products) {
            var product = new MarketProduct(p);
            this.markets.put(product, new Market(product));
        }
    }

    public Market getOrCreateMarket(MarketProduct product) {
        return markets.computeIfAbsent(product, Market::new);
    }

    public Optional<Market> getMarket(MarketProduct product) {
        return Optional.ofNullable(markets.get(product));
    }

    public Set<MarketProduct> getMarkets() {
        return markets.keySet();
    }
}
