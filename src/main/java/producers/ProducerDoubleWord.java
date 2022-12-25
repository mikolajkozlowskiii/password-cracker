package producers;

import components.Converter;
import constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import producers.strategies.CapitalizeStrategy;
import producers.strategies.PunctuationStrategy;

import java.util.List;

@AllArgsConstructor
@Builder
public class ProducerDoubleWord implements Runnable{
    private List<String> dictionary;
    private List<String> listOfPasswords;
    private List<String> listOfCrackedPasswords;
    private CapitalizeStrategy capitalizeStrategy;

    @Override
    public void run() {
        int mainLoopIteration = 0;
        final ProducerManager producerManager = new ProducerManager(capitalizeStrategy);
        while(true) {
            for(String firstWord : dictionary){
                for(String secondWord : dictionary){
                    for(PunctuationStrategy punctuation : PunctuationStrategy.values()){
                        for(PunctuationStrategy.POSITION position : PunctuationStrategy.POSITION.values()){
                            String wordToHash = producerManager
                                    .getHashedWord(firstWord,secondWord,punctuation,position,mainLoopIteration);
                            String hashedWord = new Converter(wordToHash).convertToMD5ByGuava();
                            synchronized (listOfPasswords) {
                                if (isAllPasswordsCracked()) {
                                    return;
                                }
                                checkIsHashedWordOnPasswordList(wordToHash, hashedWord);
                            }
                        }
                    }
                }
            }
            mainLoopIteration++;
        }
    }

    private void checkIsHashedWordOnPasswordList(String wordToHash, String hashedWord) {
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

    private boolean isAllPasswordsCracked() {
        if (listOfPasswords.isEmpty()) {
            synchronized (listOfCrackedPasswords){
                listOfCrackedPasswords.add(Constants.allPasswordsCracked);
                listOfCrackedPasswords.notifyAll();
                return true;
            }
        }
        return false;
    }
}
