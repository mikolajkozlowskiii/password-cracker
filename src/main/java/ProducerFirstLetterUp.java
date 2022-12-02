import java.util.List;

public class ProducerFirstLetterUp implements Runnable{
    private List<String> dictionary;
    private List<String> listOfPasswords;
    private List<String> listOfCrackedPasswords;

    public ProducerFirstLetterUp(List<String> dictionary, List<String> listOfPasswords, List<String> listOfCrackedPasswords) {
        this.dictionary = dictionary;
        this.listOfPasswords = listOfPasswords;
        this.listOfCrackedPasswords = listOfCrackedPasswords;
    }

    @Override
    public void run() {
        while(true) {
            for(String word : dictionary){
                String wordToHash = capitalizeFirstLetter(word);
                String hashedWord = new Converter(wordToHash).convertToMD5ByGuava();
                synchronized (listOfPasswords) {
                    if (listOfPasswords.isEmpty()) {
                        synchronized (listOfCrackedPasswords){
                            System.out.println("end of production");
                            listOfCrackedPasswords.add("-1");
                            listOfCrackedPasswords.notifyAll();
                        }
                        return;
                    }
                    for(int j = 0; j<listOfPasswords.size(); j++){
                        if(listOfPasswords.get(j).equals(hashedWord)){
                            synchronized (listOfCrackedPasswords){
                                listOfPasswords.remove(j);
                                listOfCrackedPasswords.add(wordToHash);
                                listOfCrackedPasswords.notifyAll();
                            }
                        }
                    }
                }
            }
        }
    }
    private String capitalizeFirstLetter(String word){
        if(word.length()>1){
            return word.substring(0,1).toUpperCase()+word.substring(1);
        }
        return word.toUpperCase();
    }

}
