package co.edu.uptc.model;

public class Product {
    String description;
    int price;
    String unit;

    public Product(String description, int price, String unit){
        this.description = description;
        this.price = price;
        this.unit = unit;
    }

    @Override
    public String toString(){
        return "producto:" + description + ", precio:" + price + ", unidad de medida:" + unit;
    }
}