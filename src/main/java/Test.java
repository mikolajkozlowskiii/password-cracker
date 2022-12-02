public class Test {
    public static void main(String[] args) {/*
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
    FileReader fileReader = new FileReader("md5test.txt","english3.txt");
    System.out.println(fileReader.readFileForPasswords());
    System.out.println(fileReader.readFileForDictionary().get(100001));
    Converter converter = new Converter("Orthoclase");
    System.out.println(converter.convertToMD5ByMD());
    System.out.println(converter.convertToMD5ByGuava());
    }
}
