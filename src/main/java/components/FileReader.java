package components;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@AllArgsConstructor
public class FileReader {
    private final String pathname;
    public List<String> read(){
        List<String> listOfDicionary = new ArrayList<>();
        File file = new File(pathname);
        if(file.exists()){
            try {
                listOfDicionary = Files.readAllLines(file.toPath(), Charset.defaultCharset());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return listOfDicionary;
    }
}
