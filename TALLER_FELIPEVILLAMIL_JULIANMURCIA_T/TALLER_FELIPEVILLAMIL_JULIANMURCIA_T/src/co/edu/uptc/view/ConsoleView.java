package co.edu.uptc.view;

import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.interfaces.ViewInterface;
import co.edu.uptc.presenter.MainPresenter;
import java.util.Scanner;

public class ConsoleView implements ViewInterface {
    private MainPresenter presenter;
    private MenuCli menu;
    private UnitMenu unitMenu;
    private Scanner scanner;

    @Override
    public void setPresenter(PresenterInterface presenter) {
        this.presenter = (MainPresenter) presenter;
    }

    @Override
    public void start() {
        menu = new MenuCli();
        unitMenu = new UnitMenu();
        scanner = new Scanner(System.in);
        runMenuLoop();
    }

    private void runMenuLoop() {
        int option;
        do {
            option = menu.getMenuOption();
            processMenuOption(option);
        } while (option != 5);
        System.out.println("¡Hasta luego!");
    }

    private void processMenuOption(int option) {
        switch (option) {
            case 1 -> handleAddProduct();
            case 2 -> handleListProducts();
            case 3 -> handleSortProducts();
            case 4 -> handleDeleteProduct();
            case 5 -> System.out.println("");
            default -> showError("Opción no válida");
        }
    }

    private String readInput(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private void handleAddProduct() {
        String description = readInput("Descripción: ");
        int price = readValidIntInput();
        String unit = selectValidUnit();
        if (unit != null) {
            presenter.addProduct(description, price, unit);
            System.out.println("✓ Producto agregado correctamente\n");
        } else {
            showError("Unidad no válida");
        }
    }

    private String selectValidUnit() {
        String unit = null;
        while (unit == null) {
            unit = unitMenu.selectUnit();
            if (unit == null) {
                System.out.print("Opción inválida. Intente de nuevo: ");
            }
        }
        return unit;
    }

    private void handleListProducts() {
        System.out.println("\n" + presenter.getProductsList());
    }

    private void handleSortProducts() {
        System.out.println("\n" + presenter.getSortedProducts());
    }

    private void handleDeleteProduct() {
        System.out.print("Nombre o parte del nombre a buscar: ");
        String searchTerm = scanner.nextLine();
        String result = presenter.deleteProductsByName(searchTerm);
        System.out.println("✓ " + result + "\n");
    }

    private int readIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Precio inválido. Usando 0");
            return 0;
        }
    }

    private int readValidIntInput() {
    int price = -1;
        while (price < 0) {
            try {
                price = Integer.parseInt(readInput("\nPrecio: "));
            } catch (NumberFormatException e) {
                System.out.print("Precio inválido. Intente de nuevo: ");
            }
        }
        return price;
}

    @Override
    public void showError(String message) {
        System.err.println("\n**** ERROR ****");
        System.err.println(message);
        System.err.println("***************\n");
    }
}



