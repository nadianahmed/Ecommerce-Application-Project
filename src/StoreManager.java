// Nadia Ahmed 101172713 //
// Esraa Alaa Aldeen 101151604//

import java.lang.reflect.Array;

public class StoreManager {
    private Inventory inventory;

    public StoreManager() {
        this.inventory = new Inventory();
    }

    public int getProductStock(Product product) {
        return inventory.getStock(product.getId());
    }


    public double processTransaction(int[][] shoppingList) {
        int checkoutTotal;
        for (int i = 0; i < shoppingList.length; i++) {
            if (inventory.inInventory(shoppingList[i][0])
                    && shoppingList[i][1] >= inventory.getStock(shoppingList[i][0])) {
                checkoutTotal += shoppingList[i][]
            }
        }
        return -1;
    }
}
