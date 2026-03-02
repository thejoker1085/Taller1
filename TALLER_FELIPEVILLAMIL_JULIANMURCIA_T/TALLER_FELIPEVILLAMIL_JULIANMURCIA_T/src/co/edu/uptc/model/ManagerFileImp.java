package co.edu.uptc.model;

import co.edu.uptc.interfaces.ModelInterface;
import co.edu.uptc.model.actions.AddProductAction;
import co.edu.uptc.model.actions.ListProductsAction;
import co.edu.uptc.model.actions.SortProductsAction;
import co.edu.uptc.model.actions.DeleteProductAction;

public class ManagerFileImp implements ModelInterface {
    private LinkedList productList;

    public ManagerFileImp() {
        this.productList = new LinkedList();
    }

    @Override
    public void addProduct(String description, int price, String unit) {
        AddProductAction addAction = new AddProductAction(productList);
        addAction.execute(description, price, unit);
    }

    @Override
    public String listProducts() {
        ListProductsAction listAction = new ListProductsAction(productList);
        return listAction.execute();
    }

    @Override
    public String sortProducts() {
        SortProductsAction sortAction = new SortProductsAction(productList);
        return sortAction.execute();
    }

    @Override
    public String deleteProducts(String searchTerm) {
        DeleteProductAction deleteAction = new DeleteProductAction(productList);
        return deleteAction.execute(searchTerm);
    }
}
