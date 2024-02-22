package testing;

import github.grovre.markets.Market;
import github.grovre.markets.MarketProduct;
import github.grovre.transactions.PendingPurchase;

import java.time.Instant;
import java.util.List;
import java.util.TreeSet;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        var prod1 = new MarketProduct("product1", UUID.randomUUID());
        var market = new Market(prod1);


    }
}
