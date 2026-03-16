package co.edu.uptc.model;

import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            var resourceStream = ConfigLoader.class.getClassLoader()
                .getResourceAsStream("message_ES.properties");
            
            if (resourceStream != null) {
                properties.load(resourceStream);
            } else {
                System.err.println(" ");
                loadDefaultMessages();
            }
        } catch (IOException e) {
            System.err.println("Error cargando message_ES.properties: " + e.getMessage());
            loadDefaultMessages();
        }
    }

    public static String getMessage(String key) {
        return properties.getProperty(key, key);
    }

    public static String getMessage(String key, Object... args) {
        String message = properties.getProperty(key, key);
        return String.format(message, args);
    }

    private static void loadDefaultMessages() {
        properties.setProperty("message.list.empty", "La lista está vacía");
        properties.setProperty("message.products.sorted", "===== PRODUCTOS ORDENADOS ALFABÉTICAMENTE =====");
        properties.setProperty("message.product.added", "Producto agregado correctamente");
        properties.setProperty("message.product.deleted", "Se eliminaron %d producto(s) con '%s'");
        properties.setProperty("message.search.empty", "Término de búsqueda vacío o lista vacía");
    }
}
