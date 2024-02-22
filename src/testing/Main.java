package testing;

import github.grovre.economics.Economy;
import github.grovre.economics.markets.MarketProduct;
import github.grovre.economics.markets.transactions.BuyOrder;
import github.grovre.economics.markets.transactions.SellOrder;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    static ArrayList<MarketProduct> products = new ArrayList<>();

    public static void main(String[] args) {
        var economy = new Economy();

        System.out.println("Enter orders");
        System.out.println("[product name] [0/1 for buy/sale] [quantity] [price]");
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

            var fulfilledOrders = market.updateActiveOrders();

            System.out.println();
            var sb = new StringBuilder();
            for (var o : Stream.concat(market.getActiveBuyOrders().stream(), market.getActiveSellOrders().stream()).toList()) {
                if (o instanceof BuyOrder bo) {
                    sb.append("Buy order");
                } else if (o instanceof SellOrder so) {
                    sb.append("Sell order");
                } else {
                    throw new RuntimeException();
                }

                sb.append(" { ppi: ").append(o.getPricePerItem())
                        .append(", remaining quantity: ")
                        .append(o.getRemainingQuantity())
                        .append(", instant: ")
                        .append(o.getInstant())
                        .append(" } ");

                sb.append('\n');

                System.out.println(sb);
            }
        }

        scanner.close();
    }
}
