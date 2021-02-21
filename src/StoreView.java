// Team Cup O' Java
// Nadia Ahmed 101172713
// Esraa Alaa Aldeen 101151604

import java.util.Scanner;

public class StoreView {
    private StoreManager storeManager;
    private int cartID = storeManager.assignNewCartID();

    public StoreView(StoreManager storeManager, int cartID) {
        this.storeManager = storeManager;
        this.cartID = cartID;
    }

    public static void main(String[] args) {
    }
}
