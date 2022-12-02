import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileReader {
    private String pathnameOfPasswords;
    private String pathnameOfDictionary;

    public FileReader(String pathnameOfPasswords, String pathnameOfDictionary) {
        this.pathnameOfPasswords = pathnameOfPasswords;
        this.pathnameOfDictionary = pathnameOfDictionary;
    }

    public List<String> readFileForPasswords(){
        List<String> list = new ArrayList<>();
        List<String> listTargetWords = new ArrayList<>();
        File file = new File(pathnameOfPasswords);
        if(file.exists()){
            try {
                list = Files.readAllLines(file.toPath(), Charset.defaultCharset());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if(list.isEmpty())
                return listTargetWords;
        }
        for(String line : list){
            String[] res = line.split(":");
            listTargetWords.add(res[1]);
        }
        return listTargetWords;
    }

    public List<String> readFileForDictionary(){
        List<String> listOfDicionary = new ArrayList<>();
        File file = new File(pathnameOfDictionary);
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
