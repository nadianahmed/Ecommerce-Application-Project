// Nadia Ahmed 101172713 //
// Esraa Alaa Aldeen 101151604//

import java.util.ArrayList;

public class Product {
    private final String name;
    private final String id;
    private final double price;

    protected Product(String name, String id, double price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    protected String getName() {
        return name;
    }

    protected String getId() {
        return id;
    }

    protected double getPrice() {
        return price;
    }
}
