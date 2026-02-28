package co.edu.uptc.model;

import java.io.File;
import java.io.FileNotFoundException;

public class ValidateFile {
    private String path;
    private int typeFile;

    private File file;

    public ValidateFile(String path, int typeFile) {
        file = new File(path);
        this.path = path;
        this.typeFile = typeFile;
    }

    public void validateType() {
        if (typeFile != 0 && typeFile != 1) {
            throw new IllegalArgumentException("El tipo de archivo debe ser 0 (archivo) o 1 (carpeta).");
        }
    }

    public void validatePath() throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException("La ruta especificada no existe: " + path);
        }
    }

    public void validateDirectory() throws FileNotFoundException {
        if (typeFile == 1 && !file.isDirectory()) {
            throw new FileNotFoundException("Se esperaba una carpeta, pero la ruta apunta a un archivo.");
        }
    }

    public void validateFile() throws FileNotFoundException {
        if (typeFile == 0 && !file.isFile()) {
            throw new FileNotFoundException("Se esperaba un archivo, pero la ruta apunta a una carpeta.");
        }
    }


    public void validateAllDirectory() throws FileNotFoundException {
        validateType();
        validatePath();
        validateDirectory();
    }

    public void validateAllFile() throws FileNotFoundException {
        validateType();
        validatePath();
        validateFile();
    }

}
