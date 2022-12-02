import java.util.List;

public abstract class AbstractProducer implements Runnable{
    private List<String> dictionary;
    private List<String> listOfPasswords;
    private List<String> listOfCrackedPasswords;

    public AbstractProducer(List<String> dictionary, List<String> listOfPasswords, List<String> listOfCrackedPasswords) {
        this.dictionary = dictionary;
        this.listOfPasswords = listOfPasswords;
        this.listOfCrackedPasswords = listOfCrackedPasswords;
    }

    @Override
    public void run() {
        while(true) {
            for(String word : dictionary){
                String wordToHash = word.toUpperCase();
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
                                listOfCrackedPasswords.add(word);
                                listOfCrackedPasswords.notifyAll();
                            }
                        }
                    }
                }
            }
        }
    }

    public List<String> getDictionary() {
        return dictionary;
    }

    public void setDictionary(List<String> dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> getListOfPasswords() {
        return listOfPasswords;
    }

    public void setListOfPasswords(List<String> listOfPasswords) {
        this.listOfPasswords = listOfPasswords;
    }

    public List<String> getListOfCrackedPasswords() {
        return listOfCrackedPasswords;
    }

    public void setListOfCrackedPasswords(List<String> listOfCrackedPasswords) {
        this.listOfCrackedPasswords = listOfCrackedPasswords;
    }
}
