package co.edu.uptc.view;

import java.util.Scanner;

public class MenuCli {
    private void showMenu() {
        System.out.println("\n========== MENÚ PRINCIPAL ==========");
        System.out.println("1. Adicionar producto");
        System.out.println("2. Listar productos");
        System.out.println("3. Listar productos ordenados");
        System.out.println("4. Borrar producto");
        System.out.println("5. Salir");
        System.out.println("====================================\n");
    }

    public int getMenuOption() {
        showMenu();
        return readOption();
    }

    private int readOption() {
        try {
            System.out.print("Seleccione una opción: ");
            Scanner scanner = new Scanner(System.in);
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
