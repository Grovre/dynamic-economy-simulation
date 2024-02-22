package github.grovre.markets;

import github.grovre.transactions.Buy;
import github.grovre.transactions.BuyRequest;
import github.grovre.transactions.Sale;
import github.grovre.transactions.SaleRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Market {

    final MarketProduct product;
    final ArrayList<Buy> closedBuys;
    final ArrayList<Sale> closedSales;
    final ArrayList<BuyRequest> buyRequests;
    final ArrayList<SaleRequest> saleRequests;

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

    public List<BuyRequest> getBuyRequests() {
        return Collections.unmodifiableList(buyRequests);
    }

    public List<SaleRequest> getSaleRequests() {
        return Collections.unmodifiableList(saleRequests);
    }
}
