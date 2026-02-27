package co.edu.uptc.model;
import co.edu.uptc.interfaces.ModelInterface;
import co.edu.uptc.view.adminVisual;
import co.edu.uptc.model.ManagerData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleList{
    private Node head;
    private Node last;
    private adminVisual view = new adminVisual();

    public void addProduct(Product product){
        try {
            if (product == null) {
                return;
            }
            Node n = new Node(product);
            if(head == null){
                head = n;
                last = n;
            }else {
                last.next = n;
                last = n;
            }
            view.showMessage("Producto agregado exitosamente"); 
        } catch (Exception e) {
            view.showMessage("Error al agregar producto: " + e.getMessage());
        }
    }

    public void listProducts(){
        try {
            if (head == null) {
                view.showMessage("La lista está vacía");
                return;
            }
            Node aux = head;
            while(aux != null){
                view.showProduct(aux.product);
                aux = aux.next;
            }
        } catch (Exception e) {
            view.showMessage("Error al listar productos: " + e.getMessage());
        }
    }

    public void listOrderedByName() throws Exception{
        ManagerData manager = new ManagerData();
        manager.listOrderedByName1();
    }

    public boolean deleteProduct(String nameToId) {
        try {
            if (nameToId == null || nameToId.trim().isEmpty()) {
                view.showMessage("Error: El nombre no puede estar vacío");
                return false;
            }
            if (head == null) {
                view.showMessage("La lista está vacía");
                return false;
            }
            
            Node aux = head;
            Node prev = null;

            while (aux != null) {
                if (aux.product.description.equalsIgnoreCase(nameToId)) {
                    if (prev == null) {
                        head = head.next;
                        if (head == null) {
                            last = null;
                        }
                    } else {
                        prev.next = aux.next;
                        if (aux.next == null) {
                            last = prev;
                        }
                    }
                    view.showMessage("Producto eliminado exitosamente");
                    return true;
                }
                prev = aux;
                aux = aux.next;
            }
            view.showMessage("Producto no encontrado: " + nameToId);
            return false;
        } catch (Exception e) {
            view.showMessage("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }
}
