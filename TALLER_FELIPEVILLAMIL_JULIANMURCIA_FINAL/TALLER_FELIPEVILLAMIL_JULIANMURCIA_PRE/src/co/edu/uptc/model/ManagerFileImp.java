package co.edu.uptc.model;

import co.edu.uptc.interfaces.ModelInterface;
import co.edu.uptc.model.actions.ManagerProduct;

public class ManagerFileImp implements ModelInterface {
    private LinkedList productList;
    private ManagerProduct manager;

    public ManagerFileImp() {
        this.productList = new LinkedList();
        this.manager = new ManagerProduct(productList);
    }

    @Override
    public void addProduct(String description, int price, String unit) {
        manager.addProduct(description, price, unit);
    }

    @Override
    public String listProducts() {
        return manager.listProducts();
    }

    @Override
    public String sortProducts() {
        return manager.sortProducts();
    }

    @Override
    public String deleteProducts(String searchTerm) {
        return manager.deleteProducts(searchTerm);
    }
}
