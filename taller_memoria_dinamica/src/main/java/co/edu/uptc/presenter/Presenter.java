package co.edu.uptc.presenter;

import co.edu.uptc.model.Product;
import co.edu.uptc.model.SimpleList; // Ajusta según tu paquete
import co.edu.uptc.view.adminVisual;
import java.util.Scanner;

public class Presenter {
    private SimpleList list;
    private Scanner sc;
    private adminVisual view;

    public Presenter() {
        list = new SimpleList();
        sc = new Scanner(System.in);
        view = new adminVisual();
    }

    public void mainMenu() throws Exception {
        int option = 0;
        do {
            view.showMessage("\n--- Bienvenido al Menuu ---");
            view.showMessage("1. Añadir producto");
            view.showMessage("2. Listar productos");
            view.showMessage("3. Ordenar productos");
            view.showMessage("4. Borrar productos");
            view.showMessage("5. Salir");
            view.showMessage("Seleccione una opcion: ");
            option = sc.nextInt();
            sc.nextLine();

            executeOption(option);
        } while (option != 5);
    }

    private void executeOption(int option) throws Exception {
        switch (option) {
            case 1:
                addProduct();
                break;
            case 2:
                list.listProducts();
                break;
            case 3:
                list.listOrderedByName();
                break;
            case 4:
                deleteProduct();
                break;
            case 5:
                view.showMessage("Que vuelvas pronto :)");
                break;
            default:
                view.showMessage("Opcion invalida.");
        }
    }

    public void addProduct() {
        view.showMessage("Añada la Descripción: ");
        String desc = sc.nextLine();
        view.showMessage("Añada el Precio: ");
        int price = sc.nextInt();
        sc.nextLine();
        view.showMessage("Añada la Unidad: ");
        String unit = sc.nextLine();
        Product p = new Product(desc, price, unit);
        list.addProduct(p);
        view.showMessage("Producto añadido correctamente.");
    }

    public void deleteProduct() {
        view.showMessage("Ingrese el nombre del producto a eliminar: ");
        String name = sc.nextLine();
        if (list.deleteProduct(name)) {
            view.showMessage("Producto eliminado.");
        } else {
            view.showMessage("Producto no econtrado.");
        }
    }
}
