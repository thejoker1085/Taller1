package co.edu.uptc.presenter;

import co.edu.uptc.interfaces.ModelInterface;
import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.interfaces.ViewInterface;

public class MainPresenter implements PresenterInterface {
    private ViewInterface view;
    private ModelInterface model;

    @Override
    public void setView(ViewInterface view) {
        this.view = view;
    }

    @Override
    public void setModel(ModelInterface model) {
        this.model = model;
    }

    public void addProduct(String description, double price, String unit) {
        model.addProduct(description, price, unit);
    }

    public String getProductsList() {
        return model.listProducts();
    }

    public String getSortedProducts() {
        return model.sortProducts();
    }

    public String deleteProductsByName(String searchTerm) {
        return model.deleteProducts(searchTerm);
    }
}

