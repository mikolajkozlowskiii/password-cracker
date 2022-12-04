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
    Converter converter = new Converter("aachenaardvark.");
    System.out.println(converter.convertToMD5ByMD());
    System.out.println(converter.convertToMD5ByGuava());
     /*   System.out.println("\n\n\n\n");
        String a = "5";
        String aa = "45";
        String aaa = "aaa";
        System.out.println(aa.substring(0,1));
        System.out.println("aa".length());


        ProducerManager producerManager = new ProducerManager(ProducerStrategy.SINGLE_WORD_TO_lOWER_RCASE);
        System.out.println(producerManager.getWordWithNumbers("czesc",3));
        System.out.println(producerManager.getWordWithNumbers("czesc",45));
        System.out.println(producerManager.getWordWithNumbers("czesc",765));
        System.out.println(producerManager.getWordWithNumbers("czesc",1034));
        System.out.println(producerManager.getWordWithNumbers("czesc",44521));*/
    }
}
