package producers;

import components.Converter;
import components.FileWriterSingleton;
import constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import producers.strategies.CapitalizeStrategy;
import producers.strategies.PunctuationStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
public class ProducerSingleWord implements Runnable {
    private List<String> dictionary;
    private List<String> listOfPasswords;
    private List<String> listOfCrackedPasswords;
    private final CapitalizeStrategy capitalizeStrategy;
    private final boolean isPunctuation;
    private FileWriterSingleton writerSingleton;
    @Override
    public void run() {
        if(dictionary.isEmpty() || listOfPasswords.isEmpty()){
            synchronized (listOfCrackedPasswords){
                listOfCrackedPasswords.add(Constants.allPasswordsCracked);
                listOfCrackedPasswords.notifyAll();
                return;
            }
        }
        writerSingleton = FileWriterSingleton.getInstance();


        int iterationMainLoop = 0;
        final ProducerManager producerManager = new ProducerManager(capitalizeStrategy, isPunctuation);
        while (true) {
            for (String word : dictionary) {
                for (PunctuationStrategy punctuation : PunctuationStrategy.values()) {
                    for (PunctuationStrategy.Position position : PunctuationStrategy.Position.values()) {
                        final String formattedWord = producerManager.getHashedWord(word, punctuation,
                                        position, iterationMainLoop);
                        final String hashedWord = new Converter(formattedWord).convertToMD5ByGuava();
                        synchronized (listOfPasswords) {
                            if (checkIsAllPasswordsCracked()) {
                                return;
                            }
                            checkIsPasswordOnListToCrack(formattedWord, hashedWord);
                        }
                    }
                }
            }
            iterationMainLoop++;
        }
    }
    private void checkIsPasswordOnListToCrack(String formattedWord, String hashedWord) {
        for (int j = 0; j < listOfPasswords.size(); j++) {
            if (listOfPasswords.get(j).equals(hashedWord)) {
                synchronized (listOfCrackedPasswords) {
                    listOfPasswords.remove(j);
                    listOfCrackedPasswords.add(formattedWord);
                    listOfCrackedPasswords.notifyAll();
                }
            }
        }
    }

    private boolean checkIsAllPasswordsCracked() {
        if (listOfPasswords.isEmpty()) {
            synchronized (listOfCrackedPasswords) {
                listOfCrackedPasswords.add(Constants.allPasswordsCracked);
                listOfCrackedPasswords.notifyAll();
                return true;
            }
        }
        return false;
    }

}

//dodac zeby z listy usunely sie wszystkie hasla a nie jedo
//dodac FileWriter