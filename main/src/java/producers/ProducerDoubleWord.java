package producers;

import components.Converter;
import constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import producers.strategies.CapitalizeStrategy;
import producers.strategies.NumberStrategy;
import producers.strategies.PunctuationStrategy;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
@Builder
public class ProducerDoubleWord implements Runnable{
    private final List<String> dictionary;
    private final List<String> listOfPasswords;
    private final List<String> listOfCrackedPasswords;
    private final CapitalizeStrategy capitalizeStrategy;
    private final NumberStrategy numberStrategy;
    private final boolean isPunctuation;

    @Override
    public void run() {
        if (checkIfInputsEmpty()){
            return;
        }
        AtomicInteger countMainLoop = new AtomicInteger(0);
        final WordFormater wordFormater = new WordFormater(capitalizeStrategy,numberStrategy);
        while(true) {
            crackPasswords(countMainLoop.get(), wordFormater);
            if (checkIfAllPasswordsCracked()) {
                return;
            }
            countMainLoop.incrementAndGet();
        }
    }

    private void crackPasswords(int counter, WordFormater wordFormater) {
        for(String firstWord : dictionary){
            for(String secondWord : dictionary){
                if(isPunctuation){
                    for(PunctuationStrategy punctuation : PunctuationStrategy.values()){
                        for(PunctuationStrategy.Position position : PunctuationStrategy.Position.values()){
                            String formattedWord = wordFormater
                                    .getFormattedWord(firstWord,secondWord,punctuation,position, counter);
                            String hashedWord = new Converter(formattedWord).convertToMD5ByGuava();
                            compareWordWithPasswords(formattedWord, hashedWord);
                        }
                    }
                }
                else{
                    String formattedWord = wordFormater
                            .getFormattedWord(firstWord,secondWord, counter);
                    String hashedWord = new Converter(formattedWord).convertToMD5ByGuava();
                    compareWordWithPasswords(formattedWord, hashedWord);
                }
            }
        }
    }

    private boolean checkIfInputsEmpty() {
        synchronized (listOfPasswords){
            if(dictionary.isEmpty() || listOfPasswords.isEmpty()){
                notifyEndConsumer();
                return true;
            }
            return false;
        }
    }


    private void compareWordWithPasswords(String formattedWord, String hashedWord) {
        synchronized (listOfPasswords) {
            List<String> foundPasswords = listOfPasswords
                    .stream()
                    .filter(s -> Objects.equals(s, hashedWord))
                    .toList();
            if(!foundPasswords.isEmpty()){
                addWordToConsumer(formattedWord, foundPasswords);
            }
        }
    }

    private void addWordToConsumer(String formattedWord, List<String> foundPasswords) {
        synchronized (listOfCrackedPasswords) {
            listOfPasswords.removeAll(foundPasswords);
            listOfCrackedPasswords.add(formattedWord);
            listOfCrackedPasswords.notifyAll();
        }
    }



    private boolean checkIfAllPasswordsCracked() {
        synchronized (listOfPasswords) {
            if (listOfPasswords.isEmpty()) {
                notifyEndConsumer();
                return true;
            }
            return false;
        }
    }
    private void notifyEndConsumer() {
        synchronized (listOfCrackedPasswords){
            listOfCrackedPasswords.add(Constants.allPasswordsCracked);
            listOfCrackedPasswords.notifyAll();
        }
    }

}
