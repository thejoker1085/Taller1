package co.edu.uptc.interfaces;

public interface ModelInterface {
    String listProducts();
    String sortProducts();
    String deleteProducts(String searchTerm);
    void addProduct(String description, int price, String unit);
}
