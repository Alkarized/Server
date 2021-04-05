package main;

import java.io.File;
import java.io.Serializable;

public class Serial implements Serializable {
    private static final long serialVersionUID = 23112;
    private File file;

    public Serial(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public Serial() {

    }
}

