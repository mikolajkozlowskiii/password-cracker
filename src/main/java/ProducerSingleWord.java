import java.util.List;

public class ProducerSingleWord implements Runnable{
    private List<String> dictionary;
    private List<String> listOfPasswords;
    private List<String> listOfCrackedPasswords;
    private WordStrategy strategy;

    public ProducerSingleWord(List<String> dictionary, List<String> listOfPasswords
            , List<String> listOfCrackedPasswords, WordStrategy strategy) {
        this.dictionary = dictionary;
        this.listOfPasswords = listOfPasswords;
        this.listOfCrackedPasswords = listOfCrackedPasswords;
        this.strategy = strategy;
    }

    @Override
    public void run() {
        int iteration = 0;
        while(true) {
            for(String word : dictionary){
                String wordToHash = new ProducerManager(strategy).getSingleWordToHash(word,iteration);
                String hashedWord = new Converter(wordToHash).convertToMD5ByGuava();
                synchronized (listOfPasswords) {
                    if (listOfPasswords.isEmpty()) {
                        synchronized (listOfCrackedPasswords){
                            //System.out.println("end of production " + Thread.currentThread().getName());
                            listOfCrackedPasswords.add("-1");
                            listOfCrackedPasswords.notifyAll();
                            return;
                        }
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
            iteration++;
        }
    }
}
