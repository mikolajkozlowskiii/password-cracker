import java.util.List;

public class ProducerDoubleWord implements Runnable{
    private List<String> dictionary;
    private List<String> listOfPasswords;
    private List<String> listOfCrackedPasswords;
    private WordStrategy strategy;
    private volatile boolean isStopped;

    public ProducerDoubleWord(List<String> dictionary, List<String> listOfPasswords
            , List<String> listOfCrackedPasswords, WordStrategy strategy) {
        this.dictionary = dictionary;
        this.listOfPasswords = listOfPasswords;
        this.listOfCrackedPasswords = listOfCrackedPasswords;
        this.strategy = strategy;
        this.isStopped = false;
    }
    public void setStopped(boolean stopped) {
        isStopped = stopped;
    }
    @Override
    public void run() {
        int iteration = 0;
        while(true) {
            for(String firstWord : dictionary){
                for(String secondWord : dictionary){
                    for(PunctuationStrategy punctuation : PunctuationStrategy.values()){
                        for(PunctuationStrategy.POSITION position : PunctuationStrategy.POSITION.values()){
                            if(isStopped){
                                synchronized (listOfCrackedPasswords){
                                    listOfCrackedPasswords.add("-1");
                                    listOfCrackedPasswords.notifyAll();
                                    return;
                                }
                            }
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
