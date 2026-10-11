import java.util.List;

public class WarehouseManager {
    private List<Warehouse> warehouseList;
    private WarehouseSelectionStrategy warehouseSelectionStrategy;

    public WarehouseManager(List<Warehouse> warehouseList, WarehouseSelectionStrategy warehouseSelectionStrategy) {
        this.warehouseList = warehouseList;
        this.warehouseSelectionStrategy = warehouseSelectionStrategy;
    }

    public Warehouse selectWarehouse(Address address) {
        return warehouseSelectionStrategy.selectWarehouse(warehouseList, address);
    }

    public List<Product> getProduct(Warehouse warehouse) {
        return warehouse.getProducts();
    }
}
