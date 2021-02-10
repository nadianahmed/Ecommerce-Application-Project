// Nadia Ahmed 101172713 //
// Esraa Alaa Aldeen 101151604//

public class StoreManager {
    private Inventory inventory;

    public StoreManager() {
        this.inventory = new Inventory();
    }

    public int getProductStock(Product product) {
        return inventory.getStock(product.getId());
    }


    public double processTransaction(int[][] shoppingCart) {

        for (int[] value : shoppingCart) {
            if (value[1] > inventory.getStock(value[0])) {
                return -1;
            }
        }
        int checkoutTotal = 0;
        for (int[] ints : shoppingCart) {
            if (inventory.inInventory(ints[0])) {
                checkoutTotal += inventory.getProductInfo(ints[0]).getPrice();
                inventory.removeStock(ints[0], ints[0]);
            } else {
                return -1;
            }
        }
        return checkoutTotal;
    }
}
