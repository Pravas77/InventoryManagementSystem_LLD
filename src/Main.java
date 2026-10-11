import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Hello");

        User user1 = new User(1,new Address(101,City.DELHI));
        User user2 = new User(2,new Address(102,City.DELHI));
        UserManager userManager = new UserManager(List.of(user1,user2));

        Product product1 = new Product(1,2,10);
        Product product2 = new Product(2,2,100);
        Product product3 = new Product(3,2,1000);

        Warehouse warehouse1 = new Warehouse(
                1,
                List.of(product1,product2,product3),
                new Address(101,City.DELHI)
        );
        WarehouseManager warehouseManager = new WarehouseManager(List.of(warehouse1),new NearestWarehouseSelectionStrategy());

        InventoryManagementSystem inventoryManagementSystem = new InventoryManagementSystem(userManager,warehouseManager,new OrderManager());


        System.out.println(inventoryManagementSystem.selectWarehouse(user1));
        System.out.println(inventoryManagementSystem.selectWarehouse(user2));
//        System.out.println(inventoryManagementSystem.selectWarehouse(new User(3,new Address(132,City.MUMBAI))));

        Thread thread1 = new Thread(() -> {

            Map<Product,Integer> products = new TreeMap<>((a,b) -> a.getId()-b.getId());
            products.put(product1,2);
            products.put(product2,2);

            System.out.println(inventoryManagementSystem.createOrder(
                    user1,
                    products,
                    warehouse1,
                    new RazorpayPaymentStrategy()));
        });

        Thread thread2 = new Thread(() -> {

            Map<Product,Integer> products = new TreeMap<>((a,b) -> a.getId()-b.getId());
            products.put(product2,1);
            products.put(product3,2);

            System.out.println(inventoryManagementSystem.createOrder(
                    user2,
                    products,
                    warehouse1,
                    new RazorpayPaymentStrategy()));
        });

        thread1.start();
//        Thread.sleep(50);
        thread2.start();

    }
}