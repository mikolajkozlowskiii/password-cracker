package producers;

import components.Converter;
import components.WordFormater;
import lombok.*;
import components.strategies.PunctuationStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@Builder
public class ProducerManager{
    private final List<String> dictionary;
    private final List<String> listOfPasswords;
    private final WordFormater wordFormater;
    private final boolean isPunctuation;
    private final boolean isSingleWord;

    public List<String> getCrackedPasswords(int number){
        return (isSingleWord)?getCrackedSingleWordPasswords(number):
                getCrackedDoubleWordPasswords(number);
    }

    public boolean checkIfAllPasswordsCracked() {
        synchronized (listOfPasswords) {
            return listOfPasswords.isEmpty();
        }
    }

    public boolean checkValidateInputs() {
        synchronized (listOfPasswords){
            return dictionary.isEmpty() && listOfPasswords.isEmpty();
        }
    }

    private List<String> getCrackedDoubleWordPasswords(int number) {
        List<String> listOfCrackedPasswords = new ArrayList<>();
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
                                listOfCrackedPasswords.add(formattedWord);
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
                        listOfCrackedPasswords.add(formattedWord);
                    }
                }
            }
        }
        return listOfCrackedPasswords;
    }
    private List<String> getCrackedSingleWordPasswords(int number) {
        List<String> listOfCrackedPasswords = new ArrayList<>();
            for(String word : dictionary){
                if(isPunctuation){
                    for(PunctuationStrategy punctuation : PunctuationStrategy.values()){
                        for(PunctuationStrategy.Position position : PunctuationStrategy.Position.values()){
                            final String formattedWord = wordFormater
                                    .getFormattedWord(word,punctuation,position, number);
                            final String hashedWord = new Converter().convertToMD5ByGuava(formattedWord);
                            if(isWordOnPasswordsList(hashedWord)){
                                removeWordFromPasswordsList(hashedWord);
                                listOfCrackedPasswords.add(formattedWord);
                            }
                        }
                    }
                }
                else{
                    final String formattedWord = wordFormater
                            .getFormattedWord(word, number);
                    final String hashedWord = new Converter().convertToMD5ByGuava(formattedWord);
                    if(isWordOnPasswordsList(hashedWord)){
                        removeWordFromPasswordsList(hashedWord);
                        listOfCrackedPasswords.add(formattedWord);
                    }
                }
            }
        return listOfCrackedPasswords;
    }

    private void removeWordFromPasswordsList(String hashedWord) {
        synchronized (listOfPasswords){
            listOfPasswords.removeAll(List.of(hashedWord));
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
}
