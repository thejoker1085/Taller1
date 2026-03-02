package co.edu.uptc.view;

import java.util.Scanner;

public class UnitMenu {
    private String[] units = {
        "Gramos (g)",
        "Kilogramos (kg)",
        "Onzas (oz)",
        "Libras (lb)",
        "Miligramos (mg)",
        "Mililitros (ml)",
        "Litros (L)",
        "Galón (gal)"
    };

    public String selectUnit() {
        showUnitMenu();
        return getSelectedUnit();
    }

    private void showUnitMenu() {
        System.out.println("\n===== SELECCIONAR UNIDAD =====");
        for (int i = 0; i < units.length; i++) {
            System.out.println((i + 1) + ". " + units[i]);
        }
        System.out.println("==============================\n");
    }

    private String getSelectedUnit() {
        int option = readUnitOption();
        if (option > 0 && option <= units.length) {
            return units[option - 1];
        }
        return null;
    }

    private int readUnitOption() {
        try {
            System.out.print("Seleccione el número de la unidad: ");
            Scanner scanner = new Scanner(System.in);
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
