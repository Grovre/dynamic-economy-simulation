package github.grovre.markets;

import github.grovre.transactions.Buy;
import github.grovre.transactions.Sale;

import java.util.*;

public class Market {

    final MarketProduct product;
    final SortedSet<Buy> closedBuys;
    final SortedSet<Sale> closedSales;
    final SortedSet<Buy> buyRequests;
    final SortedSet<Sale> saleRequests;

    public Market(MarketProduct product) {
        this.product = product;
        closedBuys = new TreeSet<>();
        closedSales = new TreeSet<>();
        buyRequests = new TreeSet<>();
        saleRequests = new TreeSet<>();
    }

    public MarketProduct getProduct() {
        return product;
    }

    public SortedSet<Buy> getClosedBuys() {
        return Collections.unmodifiableSortedSet(closedBuys);
    }

    public SortedSet<Sale> getClosedSales() {
        return Collections.unmodifiableSortedSet(closedSales);
    }

    public SortedSet<Buy> getBuyRequests() {
        return Collections.unmodifiableSortedSet(buyRequests);
    }

    public SortedSet<Sale> getSaleRequests() {
        return Collections.unmodifiableSortedSet(saleRequests);
    }
}
