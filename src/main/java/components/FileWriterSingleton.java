package components;

import lombok.Setter;

public class FileWriterSingleton {
    private static final FileWriterSingleton inst = new FileWriterSingleton();
    private String pathname;
    private FileWriterSingleton(){
        super();
    }
    public synchronized void writeToFile(String str){

    }
    public static FileWriterSingleton getInstance(){
        return inst;
    }

    public String getPathname() {
        return pathname;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }
}
