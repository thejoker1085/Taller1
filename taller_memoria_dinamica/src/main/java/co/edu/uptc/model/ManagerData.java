package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import co.edu.uptc.interfaces.ModelInterface;
import co.edu.uptc.view.adminVisual;

public class ManagerData implements ModelInterface{

    private Node head;
    private Node last;
    private adminVisual view = new adminVisual();
    
    @Override
    public String excec() throws Exception {
        return null;
        
    }

    @Override
    public void listOrderedByName1() throws Exception {
        try {
            if (head == null) {
                view.showMessage("La lista está vacía");
                return;
            }
            List<Product> tempList = new ArrayList<>();
            Node aux = head;

            while (aux != null){
                tempList.add(aux.product);
                aux = aux.next;
            }
            Collections.sort(tempList,(p1,p2)->p1.description.compareToIgnoreCase(p2.description));
            for (Product p: tempList){
                view.showProduct(p);
            }
        } catch (Exception e) {
            view.showMessage("Error al ordenar productos: " + e.getMessage());
        }
    }

    @Override
    public void deleteProduct1() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProduct1'");
    }
    
}
