import java.util.List;

public class ProducerDoubleWord implements Runnable{
    private List<String> dictionary;
    private List<String> listOfPasswords;
    private List<String> listOfCrackedPasswords;
    private WordStrategy strategy;

    public ProducerDoubleWord(List<String> dictionary, List<String> listOfPasswords
            , List<String> listOfCrackedPasswords, WordStrategy strategy) {
        this.dictionary = dictionary;
        this.listOfPasswords = listOfPasswords;
        this.listOfCrackedPasswords = listOfCrackedPasswords;
        this.strategy = strategy;
    }

    @Override
    public void run() {
        int iteration = 0;
        int iterationFirstWord = 0;
        while(true) {
            for(String firstWord : dictionary){
                //System.out.println(iterationFirstWord);
                for(String secondWord : dictionary){
                    for(PunctuationStrategy punctuation : PunctuationStrategy.values()){
                        for(PunctuationStrategy.POSITION position : PunctuationStrategy.POSITION.values()){
                            String wordToHash = new ProducerManager(strategy)
                                    .getDoubleWordToHash(firstWord,secondWord,punctuation,position,iteration);

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
                    }
                }
                //iterationFirstWord++;
            }
            iteration++;
            System.out.println(iteration);
        }
    }
}
