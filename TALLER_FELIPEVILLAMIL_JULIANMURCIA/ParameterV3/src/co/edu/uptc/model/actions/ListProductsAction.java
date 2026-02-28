package co.edu.uptc.model.actions;

import co.edu.uptc.model.LinkedList;
import co.edu.uptc.model.Node;

public class ListProductsAction {
    private LinkedList list;

    public ListProductsAction(LinkedList list) {
        this.list = list;
    }

    public String execute() {
        if (list.getHead() == null) {
            return "La lista está vacía";
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
}
