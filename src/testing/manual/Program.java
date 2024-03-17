package testing.manual;

import github.grovre.economics.markets.Market;
import github.grovre.economics.markets.MarketProduct;
import github.grovre.economics.markets.transactions.Order;
import github.grovre.economics.markets.transactions.OrderType;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {

    static ArrayList<MarketProduct> products = new ArrayList<>();

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var market = new Market(new MarketProduct("Gold"));

        System.out.println("[b/s] [quantity] [price]\nType 'exit' to quit.");
        while (scanner.hasNextLine()) {
            var line = scanner.nextLine();
            if (line.equalsIgnoreCase("exit")) {
                break;
            }
            
            var parts = line.split(" ");
            if (parts.length < 3) {
                System.out.println("Invalid input");
                continue;
            }
            
            // Parse line input
            var type = parts[0].equalsIgnoreCase("b") ? OrderType.BUY : OrderType.SELL;
            var price = Double.parseDouble(parts[2]);
            var quantity = Integer.parseInt(parts[1]);
            var time = Instant.now();

            // Add order to market
            var order = new Order(type, price, quantity, time);
            market.placeOrder(order);
            market.updateMarketOrders();
            
            // Print all existing orders in market
            System.out.println("Active buy orders:");
            market.getActiveBuyOrders().forEach(System.out::println);
            System.out.println("Active sell orders:");
            market.getActiveSellOrders().forEach(System.out::println);
        }
    }
}
