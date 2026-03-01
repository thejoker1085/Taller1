package co.edu.uptc.model;

public class Product {
    private String description;
    private int price;
    private String unit;

    public Product(String description, int price, String unit) {
        this.description = description;
        this.price = price;
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return String.format("%-20s | $%-10.2f | %s", description, (double)price, unit);
    }
}
