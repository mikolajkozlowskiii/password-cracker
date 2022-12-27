import components.Converter;
import components.FileReader;

import java.util.List;

public class Test {
    public static void main(String[] args) {

        /*
        List<String> list = new ArrayList<>();
        List<String> hasla = new ArrayList<>();
        File file = new File("md5test.txt");
        if(file.exists()){
            try {
                list = Files.readAllLines(file.toPath(), Charset.defaultCharset());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if(list.isEmpty())
                return;
        }
        for(String line : list){
            String[] res = line.split(":");
            hasla.add(res[1]);
        }
        System.out.println(hasla);
    }*/
 /*   FileReader fileReader = new FileReader("md5test.txt","english3.txt");
    System.out.println(fileReader.readFileForPasswords());
    System.out.println(fileReader.readFileForDictionary().get(100001));
    Converter converter = new Converter("apple");

    System.out.println(converter.convertToMD5ByGuava());
        converter = new Converter("Banana");

        System.out.println(converter.convertToMD5ByGuava());
         converter = new Converter("ORANGE");

        System.out.println(converter.convertToMD5ByGuava());
        converter = new Converter("0Apple2");

        System.out.println(converter.convertToMD5ByGuava());
        converter = new Converter("0APPLE1");

        System.out.println(converter.convertToMD5ByGuava());
         converter = new Converter("appleorange");

        System.out.println(converter.convertToMD5ByGuava());
        converter = new Converter("lemon_lime");

        System.out.println(converter.convertToMD5ByGuava());
        converter = new Converter("kiwipeach.");

        System.out.println(converter.convertToMD5ByGuava());
         converter = new Converter("!durian");

        System.out.println(converter.convertToMD5ByGuava());
         converter = new Converter("Coconutmango");

        System.out.println(converter.convertToMD5ByGuava());
        converter = new Converter("MANGOPLUM");

        System.out.println(converter.convertToMD5ByGuava());
        converter = new Converter("fruit-durian");

        System.out.println(converter.convertToMD5ByGuava());

*/
        test(List.of("car!",
                "_ANYBODY",
        "0Anthony4",
                "Cloud$",
        "2December4",
                "Diego",
        "DOCTOR.",
                "/Goverment",
        "admin-adidas",
        "APOLLOBARBIE",
        "beach_cargo"));

    }
    public static void test(List<String> list){
        for(String str : list){
            Converter converter = new Converter(str);
            System.out.println(converter.convertToMD5ByGuava());
        }

    }

    public void testSmth(){

    }
}
