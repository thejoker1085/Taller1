package co.edu.uptc.model.actions;

import co.edu.uptc.model.ConfigLoader;
import co.edu.uptc.model.LinkedList;
import co.edu.uptc.model.Node;
import co.edu.uptc.model.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Gestor avanzado de productos con funcionalidades adicionales
 * Proporciona operaciones como búsqueda, filtrado, reportes y estadísticas
 * 
 * @author Daniel Felipe Villamil - Julian Estiven Murcia
 * @version 2.0
 */
public class AdvancedProductManager {
    private LinkedList list;

    public AdvancedProductManager(LinkedList list) {
        this.list = list;
    }

    // ========================================
    // ACCIÓN 1: BUSCAR PRODUCTO POR DESCRIPCIÓN
    // ========================================

    /**
     * Busca productos cuya descripción contenga el término especificado
     * 
     * @param searchTerm Término de búsqueda (insensible a mayúsculas)
     * @return String con los productos encontrados formateados
     */
    public String searchProductByDescription(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty() || list.getHead() == null) {
            return ConfigLoader.getMessage("message.search.empty");
        }

        List<Product> foundProducts = findProductsMatching(searchTerm.toLowerCase());

        if (foundProducts.isEmpty()) {
            return String.format("No se encontraron productos con '%s'", searchTerm);
        }

        return formatSearchResults(searchTerm, foundProducts);
    }

    /**
     * Extrae productos que coinciden con el término de búsqueda
     */
    private List<Product> findProductsMatching(String searchTerm) {
        List<Product> found = new ArrayList<>();
        Node current = list.getHead();

        while (current != null) {
            String productDescription = current.getProduct().getDescription().toLowerCase();
            if (productDescription.contains(searchTerm)) {
                found.add(current.getProduct());
            }
            current = current.getNext();
        }

        return found;
    }

    /**
     * Formatea los resultados de búsqueda en una tabla
     */
    private String formatSearchResults(String searchTerm, List<Product> products) {
        StringBuilder result = new StringBuilder();
        result.append("===== RESULTADOS DE BÚSQUEDA =====\n");
        result.append(String.format("Término: '%s' | Resultados encontrados: %d\n", searchTerm, products.size()));
        result.append(String.format("%-20s | %-12s | %s%n", "Descripción", "Precio", "Unidad"));
        result.append("-".repeat(50)).append("\n");

        for (Product product : products) {
            result.append(product).append("\n");
        }

        return result.toString();
    }

    // ========================================
    // ACCIÓN 2: OBTENER ESTADÍSTICAS
    // ========================================

    /**
     * Genera estadísticas completas del inventario de productos
     * Incluye: cantidad total, precio promedio, máximo, mínimo y valor total
     * 
     * @return String con las estadísticas formateadas
     */
    public String getProductStatistics() {
        if (list.getHead() == null) {
            return ConfigLoader.getMessage("message.list.empty");
        }

        List<Product> allProducts = extractAllProducts();
        ProductStatistics stats = calculateStatistics(allProducts);

        return formatStatistics(stats);
    }

    /**
     * Extrae todos los productos de la lista enlazada
     */
    private List<Product> extractAllProducts() {
        List<Product> products = new ArrayList<>();
        Node current = list.getHead();

        while (current != null) {
            products.add(current.getProduct());
            current = current.getNext();
        }

        return products;
    }

    /**
     * Calcula estadísticas de los productos
     */
    private ProductStatistics calculateStatistics(List<Product> products) {
        ProductStatistics stats = new ProductStatistics();
        stats.totalProducts = products.size();
        stats.totalValue = 0;
        stats.minPrice = Integer.MAX_VALUE;
        stats.maxPrice = Integer.MIN_VALUE;

        for (Product product : products) {
            int price = (int) product.getPrice();
            stats.totalValue += price;
            stats.minPrice = Math.min(stats.minPrice, price);
            stats.maxPrice = Math.max(stats.maxPrice, price);
        }

        stats.averagePrice = stats.totalProducts > 0 ? stats.totalValue / stats.totalProducts : 0;

        return stats;
    }

    /**
     * Formatea las estadísticas en una tabla legible
     */
    private String formatStatistics(ProductStatistics stats) {
        StringBuilder result = new StringBuilder();
        result.append("===== ESTADÍSTICAS DEL INVENTARIO =====\n");
        result.append("-".repeat(50)).append("\n");
        result.append(String.format("%-30s: %d%n", "Total de productos", stats.totalProducts));
        result.append(String.format("%-30s: $%d%n", "Precio máximo", stats.maxPrice));
        result.append(String.format("%-30s: $%d%n", "Precio mínimo", stats.minPrice));
        result.append(String.format("%-30s: $%d%n", "Precio promedio", stats.averagePrice));
        result.append(String.format("%-30s: $%d%n", "Valor total inventario", stats.totalValue));
        result.append("-".repeat(50)).append("\n");

        return result.toString();
    }

    // ========================================
    // ACCIÓN 3: ACTUALIZAR PRODUCTO
    // ========================================

    /**
     * Actualiza los datos de un producto existente buscándolo por su descripción actual
     * 
     * @param oldDescription Descripción actual del producto
     * @param newDescription Nueva descripción
     * @param newPrice Nuevo precio
     * @param newUnit Nueva unidad de medida
     * @return String con mensaje de confirmación o error
     */
    public String updateProduct(String oldDescription, String newDescription, int newPrice, String newUnit) {
        if (oldDescription == null || oldDescription.trim().isEmpty() || list.getHead() == null) {
            return "Error: Descripción inválida o lista vacía";
        }

        Node productNode = findNodeByDescription(oldDescription);

        if (productNode == null) {
            return String.format("Error: No se encontró producto con descripción '%s'", oldDescription);
        }

        return updateProductNode(productNode, newDescription, newPrice, newUnit);
    }

    /**
     * Encuentra el nodo que contiene el producto buscado
     */
    private Node findNodeByDescription(String description) {
        Node current = list.getHead();

        while (current != null) {
            if (current.getProduct().getDescription().equalsIgnoreCase(description)) {
                return current;
            }
            current = current.getNext();
        }

        return null;
    }

    /**
     * Actualiza los datos del nodo del producto
     */
    private String updateProductNode(Node productNode, String newDescription, int newPrice, String newUnit) {
        Product oldProduct = productNode.getProduct();
        Product updatedProduct = new Product(newDescription, newPrice, newUnit);
        
        // Reemplazar el producto en el nodo
        reflectProductUpdate(productNode, updatedProduct);

        StringBuilder result = new StringBuilder();
        result.append("===== PRODUCTO ACTUALIZADO EXITOSAMENTE =====\n");
        result.append("-".repeat(50)).append("\n");
        result.append(String.format("Descripción anterior: %s%n", oldProduct.getDescription()));
        result.append(String.format("Descripción nueva:   %s%n", newDescription));
        result.append(String.format("Precio anterior: $%d%n", (int)oldProduct.getPrice()));
        result.append(String.format("Precio nuevo:    $%d%n", newPrice));
        result.append(String.format("Unidad anterior: %s%n", oldProduct.getUnit()));
        result.append(String.format("Unidad nueva:    %s%n", newUnit));
        result.append("-".repeat(50)).append("\n");

        return result.toString();
    }

    /**
     * Refleja la actualización del producto en el nodo
     * Nota: Esto requiere que LinkedList tenga método para actualizar producto en nodo
     * Por ahora, hacemos actualización manual al nodo
     */
    private void reflectProductUpdate(Node node, Product updatedProduct) {
        // Acceso directo mediante reflexión o método en Node
        // Para este caso, asumimos que Node permite actualizar su producto
        node.setProduct(updatedProduct);
    }

    // ========================================
    // ACCIÓN 4: FILTRAR POR RANGO DE PRECIOS
    // ========================================

    /**
     * Filtra productos cuyo precio se encuentra dentro del rango especificado
     * 
     * @param minPrice Precio mínimo (inclusive)
     * @param maxPrice Precio máximo (inclusive)
     * @return String con los productos en el rango formateados
     */
    public String filterByPriceRange(int minPrice, int maxPrice) {
        if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice) {
            return "Error: Rango de precios inválido (deben ser positivos y mín <= máx)";
        }

        if (list.getHead() == null) {
            return ConfigLoader.getMessage("message.list.empty");
        }

        List<Product> productsInRange = findProductsInPriceRange(minPrice, maxPrice);

        if (productsInRange.isEmpty()) {
            return String.format("No hay productos en el rango $%d - $%d", minPrice, maxPrice);
        }

        return formatPriceRangeResults(minPrice, maxPrice, productsInRange);
    }

    /**
     * Extrae productos cuyo precio está en el rango especificado
     */
    private List<Product> findProductsInPriceRange(int minPrice, int maxPrice) {
        List<Product> products = new ArrayList<>();
        Node current = list.getHead();

        while (current != null) {
            int productPrice = (int) current.getProduct().getPrice();
            if (productPrice >= minPrice && productPrice <= maxPrice) {
                products.add(current.getProduct());
            }
            current = current.getNext();
        }

        return products;
    }

    /**
     * Formatea los resultados del filtrado por rango de precios
     */
    private String formatPriceRangeResults(int minPrice, int maxPrice, List<Product> products) {
        StringBuilder result = new StringBuilder();
        result.append("===== FILTRADO POR RANGO DE PRECIOS =====\n");
        result.append(String.format("Rango: $%d - $%d | Productos encontrados: %d%n", minPrice, maxPrice, products.size()));
        result.append(String.format("%-20s | %-12s | %s%n", "Descripción", "Precio", "Unidad"));
        result.append("-".repeat(50)).append("\n");

        for (Product product : products) {
            result.append(product).append("\n");
        }

        return result.toString();
    }

    // ========================================
    // ACCIÓN 5: GENERAR REPORTE DE INVENTARIO
    // ========================================

    /**
     * Genera un reporte completo del inventario agrupado por unidad de medida
     * Incluye cantidad de productos por unidad y análisis de precios
     * 
     * @return String con el reporte formateado
     */
    public String generateInventoryReport() {
        if (list.getHead() == null) {
            return ConfigLoader.getMessage("message.list.empty");
        }

        List<Product> allProducts = extractAllProducts();
        java.util.Map<String, List<Product>> productsByUnit = groupProductsByUnit(allProducts);

        return formatInventoryReport(productsByUnit, allProducts);
    }

    /**
     * Agrupa productos por su unidad de medida
     */
    private java.util.Map<String, List<Product>> groupProductsByUnit(List<Product> products) {
        java.util.Map<String, List<Product>> grouped = new java.util.LinkedHashMap<>();

        for (Product product : products) {
            grouped.computeIfAbsent(product.getUnit(), k -> new ArrayList<>()).add(product);
        }

        return grouped;
    }

    /**
     * Formatea el reporte completo del inventario
     */
    private String formatInventoryReport(java.util.Map<String, List<Product>> productsByUnit, List<Product> allProducts) {
        StringBuilder result = new StringBuilder();
        
        result.append("╔════════════════════════════════════════════╗\n");
        result.append("║       REPORTE COMPLETO DE INVENTARIO       ║\n");
        result.append("╚════════════════════════════════════════════╝\n\n");

        // Encabezado de resumen
        result.append("📊 RESUMEN GENERAL\n");
        result.append("-".repeat(50)).append("\n");
        result.append(String.format("Total de productos diferentes: %d%n", allProducts.size()));
        result.append(String.format("Total de categorías (unidades): %d%n", productsByUnit.size()));
        result.append("\n");

        // Detalle por unidad
        result.append("📦 DESGLOSE POR UNIDAD DE MEDIDA\n");
        result.append("-".repeat(50)).append("\n");

        for (String unit : productsByUnit.keySet()) {
            List<Product> productsOfUnit = productsByUnit.get(unit);
            result.append(formatUnitSection(unit, productsOfUnit));
        }

        result.append("\n");
        result.append("═".repeat(50)).append("\n");
        result.append("Fin del Reporte\n");

        return result.toString();
    }

    /**
     * Formatea una sección de reporte para una unidad específica
     */
    private String formatUnitSection(String unit, List<Product> products) {
        StringBuilder section = new StringBuilder();
        section.append(String.format("\n▸ Unidad: %s (%d productos)%n", unit, products.size()));
        section.append("  " + "-".repeat(46)).append("\n");

        int totalUnitPrice = 0;
        int minUnitPrice = Integer.MAX_VALUE;
        int maxUnitPrice = Integer.MIN_VALUE;

        for (Product product : products) {
            int price = (int) product.getPrice();
            section.append(String.format("    %-16s | $%-10d%n", product.getDescription(), price));
            totalUnitPrice += price;
            minUnitPrice = Math.min(minUnitPrice, price);
            maxUnitPrice = Math.max(maxUnitPrice, price);
        }

        int averageUnitPrice = products.size() > 0 ? totalUnitPrice / products.size() : 0;

        section.append("  " + "-".repeat(46)).append("\n");
        section.append(String.format("    Subtotal: $%d | Promedio: $%d | Máx: $%d | Mín: $%d%n",
                totalUnitPrice, averageUnitPrice, maxUnitPrice, minUnitPrice));

        return section.toString();
    }

    // ========================================
    // CLASE INTERNA PARA ESTADÍSTICAS
    // ========================================

    /**
     * Clase auxiliar para almacenar estadísticas de productos
     */
    private static class ProductStatistics {
        int totalProducts;
        int maxPrice;
        int minPrice;
        int averagePrice;
        int totalValue;
    }
}
