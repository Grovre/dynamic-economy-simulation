package github.grovre.markets;

import github.grovre.transactions.ClosedPurchase;
import github.grovre.transactions.ClosedSale;
import github.grovre.transactions.PendingPurchase;
import github.grovre.transactions.PendingSale;

import java.time.Instant;
import java.util.*;

public class Market {

    final MarketProduct product;
    final SortedSet<ClosedPurchase> closedPurchases;
    final SortedSet<ClosedSale> closedSales;
    final SortedSet<PendingPurchase> pendingPurchases;
    final SortedSet<PendingSale> pendingSales;

    public Market(MarketProduct product) {
        this.product = product;
        closedPurchases = new TreeSet<>();
        closedSales = new TreeSet<>();
        pendingPurchases = new TreeSet<>();
        pendingSales = new TreeSet<>();
    }

    public MarketProduct getProduct() {
        return product;
    }

    public SortedSet<ClosedPurchase> getClosedPurchases() {
        return Collections.unmodifiableSortedSet(closedPurchases);
    }

    public SortedSet<ClosedSale> getClosedSales() {
        return Collections.unmodifiableSortedSet(closedSales);
    }

    public SortedSet<PendingPurchase> getPendingPurchases() {
        return Collections.unmodifiableSortedSet(pendingPurchases);
    }

    public SortedSet<PendingSale> getPendingSales() {
        return Collections.unmodifiableSortedSet(pendingSales);
    }
}
