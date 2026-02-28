package co.edu.uptc.model.actions;

import co.edu.uptc.model.LinkedList;
import co.edu.uptc.model.Node;

public class DeleteProductAction {
    private LinkedList list;

    public DeleteProductAction(LinkedList list) {
        this.list = list;
    }

    public String execute(String searchTerm) {
        int deletedCount = deleteProductsByName(searchTerm);
        return buildDeleteMessage(searchTerm, deletedCount);
    }

    private int deleteProductsByName(String searchTerm) {
        int count = 0;
        while (shouldDeleteFirst(searchTerm)) {
            list.setHead(list.getHead().getNext());
            count++;
        }
        count += deleteFromMiddleOrEnd(searchTerm);
        return count;
    }

    private boolean shouldDeleteFirst(String searchTerm) {
        return list.getHead() != null && 
               list.getHead().getProduct().getDescription().contains(searchTerm);
    }

    private int deleteFromMiddleOrEnd(String searchTerm) {
        int count = 0;
        Node current = list.getHead();
        while (current != null && current.getNext() != null) {
            if (current.getNext().getProduct().getDescription().contains(searchTerm)) {
                current.setNext(current.getNext().getNext());
                count++;
            } else {
                current = current.getNext();
            }
        }
        return count;
    }

    private String buildDeleteMessage(String searchTerm, int count) {
        return String.format("Se eliminaron %d producto(s) con '%s'", count, searchTerm);
    }
}
