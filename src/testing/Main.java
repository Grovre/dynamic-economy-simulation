package testing;

import github.grovre.economics.Economy;
import github.grovre.economics.markets.MarketProduct;

import java.util.ArrayList;
import java.util.Scanner;

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
            for (var o : fulfilledOrders)
                System.out.println(o);
        }

        scanner.close();
    }
}
