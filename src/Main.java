import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Integer> quantity = new ArrayList<>();

        Product juice = new Product("Juice", "01", 3.59);
        Inventory supermarket = new Inventory(products, quantity);

        products.add(juice);
        quantity.add(5);

        supermarket.addStock("01", 7);
        int amountJuice = supermarket.getStock("01");

        System.out.println("get stock: " + amountJuice);

        Inventory bookstore = new Inventory();
        bookstore.addStock("01", 7);
    }

}
