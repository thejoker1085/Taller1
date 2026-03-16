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
            throw new IllegalArgumentException(ConfigLoader.getMessage("message.error1"));
        }
    }

    public void validatePath() throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException(ConfigLoader.getMessage("message.error2") + path);
        }
    }

    public void validateDirectory() throws FileNotFoundException {
        if (typeFile == 1 && !file.isDirectory()) {
            throw new FileNotFoundException(ConfigLoader.getMessage("message.error3") + path);
        }
    }

    public void validateFile() throws FileNotFoundException {
        if (typeFile == 0 && !file.isFile()) {
            throw new FileNotFoundException(ConfigLoader.getMessage("message.error4") + path);
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
