package co.edu.uptc.model;

public class Node {
    private Product product;
    private Node next;

    public Node(Product product) {
        this.product = product;
        this.next = null;
    }

    public Product getProduct() {
        return product;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
