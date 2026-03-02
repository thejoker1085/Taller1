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
        rebuildLinkedList(products);
        return "Productos ordenados alfabéticamente";
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
            n1.getProduct().getDescription().compareToIgnoreCase(n2.getProduct().getDescription())
        );
    }

    private void rebuildLinkedList(List<Node> products) {
        for (Node node : products) {
            node.setNext(null);
        }
        for (int i = 0; i < products.size() - 1; i++) {
            products.get(i).setNext(products.get(i + 1));
        }
        list.setHead(products.get(0));
    }
}
