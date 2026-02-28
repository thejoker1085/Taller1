package co.edu.uptc.model.actions;

import co.edu.uptc.model.LinkedList;
import co.edu.uptc.model.Product;

public class AddProductAction {
    private LinkedList list;

    public AddProductAction(LinkedList list) {
        this.list = list;
    }

    public void execute(String description, double price, String unit) {
        Product product = createProduct(description, price, unit);
        list.addProduct(product);
    }

    private Product createProduct(String description, double price, String unit) {
        return new Product(description, price, unit);
    }
}
