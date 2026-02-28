package co.edu.uptc.model.actions;

import co.edu.uptc.model.LinkedList;
import co.edu.uptc.model.Node;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortProductsAction {
    private LinkedList list;

    public SortProductsAction(LinkedList list) {
        this.list = list;
    }

    public String execute() {
        if (list.getHead() == null) {
            return "La lista está vacía";
        }
        List<Node> products = extractProductsToList();
        sortProductsByName(products);
        return buildSortedOutput(products);
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
        Collections.sort(products, (n1, n2) -> 
            n1.getProduct().getDescription().compareTo(n2.getProduct().getDescription())
        );
    }

    private String buildSortedOutput(List<Node> products) {
        StringBuilder result = new StringBuilder();
        result.append(String.format("%-20s | %-12s | %s%n", "Descripción", "Precio", "Unidad"));
        products.forEach(node -> result.append(node.getProduct()).append("\n"));
        return result.toString();
    }
}
