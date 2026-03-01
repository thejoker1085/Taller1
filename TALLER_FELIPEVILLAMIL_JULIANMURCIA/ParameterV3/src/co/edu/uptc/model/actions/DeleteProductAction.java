package co.edu.uptc.model.actions;

import co.edu.uptc.model.LinkedList;
import co.edu.uptc.model.Node;

public class DeleteProductAction {
    private LinkedList list;

    public DeleteProductAction(LinkedList list) {
        this.list = list;
    }

    public String execute(String searchTerm) {
        if (searchTerm == null) {
            return "Término de búsqueda vacío";
        }
        String normalized = searchTerm.trim().toLowerCase();
        int deletedCount = deleteProductsByName(normalized);
        return buildDeleteMessage(searchTerm, deletedCount);
    }

    private int deleteProductsByName(String searchTerm) {
        int count = 0;
        while (list.getHead() != null && containsIgnoreCase(list.getHead().getProduct().getDescription(), searchTerm)) {
            list.setHead(list.getHead().getNext());
            count++;
        }
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
        if (source == null) return false;
        return source.toLowerCase().contains(targetLower);
    }

    private String buildDeleteMessage(String searchTerm, int count) {
        return String.format("Se eliminaron %d producto(s) con '%s'", count, searchTerm);
    }
}