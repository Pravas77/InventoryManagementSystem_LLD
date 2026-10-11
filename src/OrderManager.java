import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

public class OrderManager {
    private List<Order> orders;

    public OrderManager() {
        orders = new CopyOnWriteArrayList<>();
    }

    public Order createOrder(User user, Map<Product, Integer> products, Warehouse warehouse, PaymentStrategy paymentStrategy) {

        for (Map.Entry<Product, Integer> val : products.entrySet()) {
            Product product = val.getKey();
            int requiredQuantity = val.getValue();
            if (!product.checkForBooking(requiredQuantity))
                throw new RuntimeException(product + " is not available in required quantity");
        }

        List<StampedLock> locks = new ArrayList<>();
        List<Long> stamps = new ArrayList<>();
        try {

            for (Map.Entry<Product, Integer> val : products.entrySet()) {
                StampedLock lock = val.getKey().getLock();
                long stamp = lock.writeLock();
                locks.add(lock);
                stamps.add(stamp);
            }

            int cost = 0;
            for (Map.Entry<Product, Integer> val : products.entrySet()) {
                Product product = val.getKey();
                int requiredQuantity = val.getValue();
                if (!product.chekRequiredQuantity(requiredQuantity))
                    throw new RuntimeException(product + " is not available in required quantity");

                cost += requiredQuantity * product.getPrice();
            }

            boolean status = paymentStrategy.pay(cost);

            if (status) {
                for (Map.Entry<Product, Integer> val : products.entrySet()) {
                    Product product = val.getKey();
                    int requiredQuantity = val.getValue();
                    product.removeProduct(requiredQuantity);
                }
                Order order = new Order(user, products, warehouse, cost);
                orders.add(order);
                return order;
            } else throw new RuntimeException("Payment failed please try again");
        } finally {
            for (int i = 0; i < locks.size(); i++) locks.get(i).unlockWrite(stamps.get(i));
        }
    }
}
