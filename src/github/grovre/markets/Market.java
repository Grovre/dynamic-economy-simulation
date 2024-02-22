package github.grovre.markets;

import github.grovre.transactions.Buy;
import github.grovre.transactions.Sale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Market {

    final MarketProduct product;
    final ArrayList<Buy> closedBuys;
    final ArrayList<Sale> closedSales;
    final ArrayList<Buy> buyRequests;
    final ArrayList<Sale> saleRequests;

    public Market(MarketProduct product) {
        this.product = product;
        closedBuys = new ArrayList<>();
        closedSales = new ArrayList<>();
        buyRequests = new ArrayList<>();
        saleRequests = new ArrayList<>();
    }

    public MarketProduct getProduct() {
        return product;
    }

    public List<Buy> getClosedBuys() {
        return Collections.unmodifiableList(closedBuys);
    }

    public List<Sale> getClosedSales() {
        return Collections.unmodifiableList(closedSales);
    }

    public List<Buy> getBuyRequests() {
        return Collections.unmodifiableList(buyRequests);
    }

    public List<Sale> getSaleRequests() {
        return Collections.unmodifiableList(saleRequests);
    }
}
