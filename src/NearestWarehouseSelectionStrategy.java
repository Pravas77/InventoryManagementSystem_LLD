import java.util.List;

public class NearestWarehouseSelectionStrategy implements WarehouseSelectionStrategy {
    @Override
    public Warehouse selectWarehouse(List<Warehouse> warehouseList, Address address) {

        for (Warehouse warehouse : warehouseList)
            if (warehouse.getAddress().equals(address)) return warehouse;

        for (Warehouse warehouse : warehouseList)
            if (warehouse.getAddress().getCity().equals(address.getCity())) return warehouse;

        throw new RuntimeException("Service is not available in your area");

    }
}
