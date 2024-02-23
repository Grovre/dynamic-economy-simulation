package testing;

import github.grovre.economics.Economy;
import github.grovre.economics.markets.MarketProduct;
import github.grovre.economics.markets.transactions.BuyOrder;
import github.grovre.economics.markets.transactions.SellOrder;
import github.grovre.economics.markets.transactions.Transaction;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    static ArrayList<MarketProduct> products = new ArrayList<>();

    public static void main(String[] args) {
        var economy = new Economy();

        System.out.println("Enter orders");
        System.out.println("[product name] [0/1 for buy/sale] [quantity] [price per item]");
        var scanner = new Scanner(System.in);
        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            var split = line.split(" ");
            var product = new MarketProduct(split[0]);
            var sale = split[1].equals("1");
            var quantity = Integer.parseInt(split[2]);
            var price = Double.parseDouble(split[3]);

            var market = economy.getOrCreateMarket(product);
            if (sale) {
                market.placeSellOrder(quantity, price);
            } else {
                market.placeBuyOrder(quantity, price);
            }

            market.updateActiveOrders();

            System.out.println("Latest orders:");
            var sb = new StringBuilder();
            for (var o : Stream.concat(market.getActiveBuyOrders().stream(), market.getActiveSellOrders().stream()).toList()) {

                sb.append(o.getOrderType())
                        .append(" { price per item: ")
                        .append(o.getPricePerItem())
                        .append(", remaining quantity: ")
                        .append(o.getRemainingQuantity())
                        .append(", order placement instant: ")
                        .append(o.getInstant())
                        .append(" }\n\t")
                        .append(String.join(", ", o.getTransactions().stream().map(Transaction::toString).toList()));

                sb.append('\n');
            }

            System.out.println(sb);
        }

        scanner.close();
    }
}
