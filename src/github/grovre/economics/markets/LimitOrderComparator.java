package github.grovre.economics.markets;

import github.grovre.economics.markets.transactions.Order;

import java.util.Comparator;

public class LimitOrderComparator {

    private LimitOrderComparator() {
        // No purpose for instantiation
    }

    private static int cmpPxPyThenTxTy(Order x, Order y, boolean highToLow) {
        if (x.getOrderType() != y.getOrderType())
            throw new IllegalArgumentException("Cannot compare orders of different types in the context of a limit order market");
        
        // compare px to py
        var cmpPxPy = Double.compare(x.getPricePerItem(), y.getPricePerItem());
        if (cmpPxPy != 0)
            return highToLow ? -cmpPxPy : cmpPxPy;
        
        // then by time if needed
        var cmpTxTy = x.getInstant().compareTo(y.getInstant());
        return highToLow ? -cmpTxTy : cmpTxTy;
    }

    public class ForBuyOrders implements Comparator<Order> {
        @Override
        public int compare(Order x, Order y) {
            if (x == y)
                return 0;
            if (x == null)
                return -1;
            if (y == null)
                return 1;
            
            return cmpPxPyThenTxTy(x, y, true);
        }
    }
    
    public class ForSellOrders implements Comparator<Order> {
        @Override
        public int compare(Order x, Order y) {
            if (x == y)
                return 0;
            if (x == null)
                return -1;
            if (y == null)
                return 1;
            
            return cmpPxPyThenTxTy(x, y, false);
        }
    }
}
