import java.util.List;
import java.util.Map;

public class InventoryManagementSystem {
    private UserManager userManager;
    private WarehouseManager warehouseManager;
    private OrderManager orderManager;

    public InventoryManagementSystem(UserManager userManager, WarehouseManager warehouseManager, OrderManager orderManager) {
        this.userManager = userManager;
        this.warehouseManager = warehouseManager;
        this.orderManager = orderManager;
    }

    public Warehouse selectWarehouse(User user) {
        return warehouseManager.selectWarehouse(user.getAddress());
    }

    public List<Product> getProducts(Warehouse warehouse) {
        return warehouseManager.getProduct(warehouse);
    }

    public Order createOrder(User user, Map<Product, Integer> products, Warehouse warehouse, PaymentStrategy paymentStrategy) {
        return orderManager.createOrder(user, products, warehouse, paymentStrategy);
    }

}
