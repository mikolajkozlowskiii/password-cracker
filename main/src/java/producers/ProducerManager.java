package producers;

import components.Converter;
import lombok.*;
import producers.strategies.CapitalizeStrategy;
import producers.strategies.NumberStrategy;
import producers.strategies.PunctuationStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

@Data
@AllArgsConstructor
@Builder
public class ProducerManager{
    private final CopyOnWriteArrayList<String> dictionary;
    private final CopyOnWriteArrayList<String> listOfPasswords;
    private final BlockingQueue<String> listOfCrackedPasswords;
    private final WordFormater wordFormater;
    private final boolean isPunctuation;
    public BlockingQueue<String> getCrackedDoubleWordPasswords(int number) {
        for(String firstWord : dictionary){
            for(String secondWord : dictionary){
                if(isPunctuation){
                    for(PunctuationStrategy punctuation : PunctuationStrategy.values()){
                        for(PunctuationStrategy.Position position : PunctuationStrategy.Position.values()){
                            final String formattedWord = wordFormater
                                    .getFormattedWord(firstWord,secondWord,punctuation,position, number);
                            final String hashedWord = new Converter().convertToMD5ByGuava(formattedWord);
                            if(isWordOnPasswordsList(hashedWord)){
                                removeWordFromPasswordsList(hashedWord);
                                addWordToCrackedPasswordsList(formattedWord);
                            }
                        }
                    }
                }
                else{
                    final String formattedWord = wordFormater
                            .getFormattedWord(firstWord,secondWord, number);
                    final String hashedWord = new Converter().convertToMD5ByGuava(formattedWord);
                    if(isWordOnPasswordsList(hashedWord)){
                        removeWordFromPasswordsList(hashedWord);
                        addWordToCrackedPasswordsList(formattedWord);
                    }
                }
            }
        }
        return listOfCrackedPasswords;
    }

    private void addWordToCrackedPasswordsList(String formattedWord) {
        synchronized (listOfCrackedPasswords){
            listOfCrackedPasswords.add(formattedWord);
        }
    }

    private void removeWordFromPasswordsList(String hashedWord) {
        synchronized (listOfPasswords){
            listOfPasswords.removeAll(List.of(hashedWord));
        }
    }

    public boolean checkValidateInputs() {
        synchronized (listOfPasswords){
            if(!dictionary.isEmpty() || !listOfPasswords.isEmpty()){
                return false;
            }
            return true;
        }
    }


    private boolean isWordOnPasswordsList(String hashedWord) {
        synchronized (listOfPasswords) {
            return !listOfPasswords
                    .stream()
                    .filter(s -> Objects.equals(s, hashedWord))
                    .toList()
                    .isEmpty();
        }
    }

    public boolean checkIfAllPasswordsCracked() {
        synchronized (listOfPasswords) {
            return listOfPasswords.isEmpty();
        }
    }
}
