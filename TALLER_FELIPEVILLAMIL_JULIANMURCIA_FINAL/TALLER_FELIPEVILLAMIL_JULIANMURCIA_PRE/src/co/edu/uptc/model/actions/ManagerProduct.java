package co.edu.uptc.model.actions;

import co.edu.uptc.model.ConfigLoader;
import co.edu.uptc.model.LinkedList;
import co.edu.uptc.model.Node;
import co.edu.uptc.model.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManagerProduct {
    private LinkedList list;

    public ManagerProduct(LinkedList list) {
        this.list = list;
    }

    public void addProduct(String description, int price, String unit) {
        Product product = createProduct(description, price, unit);
        list.addProduct(product);
    }

    private Product createProduct(String description, int price, String unit) {
        return new Product(description, price, unit);
    }

    public String listProducts() {
        if (list.getHead() == null) {
            return ConfigLoader.getMessage("message.list.empty");
        }
        StringBuilder result = new StringBuilder();
        result.append(formatHeader());
        appendProductsToResult(result);
        return result.toString();
    }

    private String formatHeader() {
        return String.format("%-20s | %-12s | %s%n", "Descripción", "Precio", "Unidad");
    }

    private void appendProductsToResult(StringBuilder result) {
        Node current = list.getHead();
        while (current != null) {
            result.append(current.getProduct()).append("\n");
            current = current.getNext();
        }
    }

    public String sortProducts() {
        if (list.getHead() == null) {
            return ConfigLoader.getMessage("message.list.empty");
        }
        List<Node> products = extractProductsToList();
        sortProductsByName(products);
        rebuildLinkedList(products);
        return formatSortedList(products);
    }

    private List<Node> extractProductsToList() {
        List<Node> products = new ArrayList<>();
        Node current = list.getHead();
        while (current != null) {
            products.add(current);
            current = current.getNext();
        }
        return products;
    }

    private void sortProductsByName(List<Node> products) {
        Collections.sort(products,
                (n1, n2) -> n1.getProduct().getDescription().compareToIgnoreCase(n2.getProduct().getDescription()));
    }

    private void rebuildLinkedList(List<Node> products) {
        if (products.isEmpty()) {
            return;
        }
        for (Node node : products) {
            node.setNext(null);
        }
        for (int i = 0; i < products.size() - 1; i++) {
            products.get(i).setNext(products.get(i + 1));
        }
        list.setHead(products.get(0));
    }

    private String formatSortedList(List<Node> products) {
        StringBuilder result = new StringBuilder();
        result.append(ConfigLoader.getMessage("message.products.sorted")).append("\n");
        result.append(String.format("%-20s | %-12s | %s%n", "Descripción", "Precio", "Unidad"));
        for (Node node : products) {
            result.append(node.getProduct()).append("\n");
        }
        return result.toString();
    }

    public String deleteProducts(String searchTerm) {
        if (searchTerm == null || list.getHead() == null) {
            return ConfigLoader.getMessage("message.search.empty");
        }
        String normalized = searchTerm.trim().toLowerCase();
        int deletedCount = deleteProductsByName(normalized);
        return buildDeleteMessage(searchTerm, deletedCount);
    }

    private int deleteProductsByName(String searchTerm) {
        int count = deleteFromHead(searchTerm);
        count += deleteFromBody(searchTerm);
        return count;
    }

    private int deleteFromHead(String searchTerm) {
        int count = 0;
        while (list.getHead() != null && containsIgnoreCase(list.getHead().getProduct().getDescription(), searchTerm)) {
            list.setHead(list.getHead().getNext());
            count++;
        }
        return count;
    }

    private int deleteFromBody(String searchTerm) {
        int count = 0;
        Node current = list.getHead();
        while (current != null && current.getNext() != null) {
            if (containsIgnoreCase(current.getNext().getProduct().getDescription(), searchTerm)) {
                current.setNext(current.getNext().getNext());
                count++;
            } else {
                current = current.getNext();
            }
        }
        return count;
    }

    private boolean containsIgnoreCase(String source, String targetLower) {
        if (source == null) {
            return false;
        }
        return source.toLowerCase().contains(targetLower);
    }

    private String buildDeleteMessage(String searchTerm, int count) {
        return ConfigLoader.getMessage("message.product.deleted", count, searchTerm);
    }
}
