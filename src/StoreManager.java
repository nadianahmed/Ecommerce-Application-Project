// Nadia Ahmed 101172713 //
// Esraa Alaa Aldeen 101151604//

import java.util.Arrays;

public class StoreManager {
    private Inventory inventory;

    public StoreManager() {
        this.inventory = new Inventory();
    }

    public int getProductStock(Product product) {
        return inventory.getStock(product.getId());
    }

    private boolean inventorySufficient(String id, int quantity) { return  (inventory.getStock(id) - quantity >= 0); }

    public double shoppingCart(Arrays shoppingList) {
        return -1;
    }
}
