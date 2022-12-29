package producers;

import components.Converter;
import components.WordFormater;
import components.strategies.PunctuationStrategy;
import constants.PoisonPill;
import lombok.Builder;


import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;


@Builder
public class Producer implements Runnable {
    private final List<String> dictionary;
    private final List<String> passwords;
    private final List<String> crackedPasswordsBuffer;
    private final WordFormater wordFormater;
    private final boolean isPunctuation;
    private final boolean isSingleWord;

    @Override
    public void run() {
        if (checkIfInputsEmpty()){
            return;
        }
        AtomicInteger countMainLoop = new AtomicInteger(0);
        while (true) {
            crackPasswords(countMainLoop.get());
            if (checkIfAllPasswordsCracked()) {
                return;
            }
            countMainLoop.incrementAndGet();
        }
    }

    private void crackPasswords(int number){
        if(isSingleWord){
            crackDoubleWordPasswords(number);
        }
        else{
            crackSingleWordPasswords(number);
        }
    }

    private void crackDoubleWordPasswords(int number) {
        for (String word : dictionary) {
            if(isPunctuation){
                for (PunctuationStrategy punctuation : PunctuationStrategy.values()) {
                    for (PunctuationStrategy.Position position : PunctuationStrategy.Position.values()) {
                        final String formattedWord = wordFormater.getFormattedWord(word, punctuation,
                                position, number);
                        final String hashedWord = new Converter().convertToMD5ByGuava(formattedWord);
                        compareWordWithPasswords(formattedWord, hashedWord);
                        if (checkIfAllPasswordsCracked()) {
                            return;
                        }
                    }
                }
            }
            else{
                final String formattedWord = wordFormater.getFormattedWord(word,number);
                final String hashedWord = new Converter().convertToMD5ByGuava(formattedWord);
                compareWordWithPasswords(formattedWord, hashedWord);
                if (checkIfAllPasswordsCracked()) {
                    return;
                }
            }

        }
    }

    private void crackSingleWordPasswords(int number) {
        for(String firstWord : dictionary){
            for(String secondWord : dictionary){
                if(isPunctuation){
                    for(PunctuationStrategy punctuation : PunctuationStrategy.values()){
                        for(PunctuationStrategy.Position position : PunctuationStrategy.Position.values()){
                            final String formattedWord = wordFormater.getFormattedWord(firstWord, secondWord,punctuation,
                                    position, number);
                            final String hashedWord = new Converter().convertToMD5ByGuava(formattedWord);
                            compareWordWithPasswords(formattedWord, hashedWord);
                            if (checkIfAllPasswordsCracked()) {
                                return;
                            }
                        }
                    }
                }
                else{
                    final String formattedWord = wordFormater.getFormattedWord(firstWord, secondWord,number);
                    final String hashedWord = new Converter().convertToMD5ByGuava(formattedWord);
                    compareWordWithPasswords(formattedWord, hashedWord);
                    if (checkIfAllPasswordsCracked()) {
                        return;
                    }
                }
            }
        }
    }

    private boolean checkIfInputsEmpty() {
        synchronized (passwords){
            if(dictionary.isEmpty() || passwords.isEmpty()){
                notifyEndConsumer();
                return true;
            }
            return false;
        }
    }

    private void compareWordWithPasswords(String formattedWord, String hashedWord) {
        synchronized (passwords) {
            List<String> foundPasswords = passwords
                    .stream()
                    .filter(s -> Objects.equals(s, hashedWord))
                    .toList();
            if(!foundPasswords.isEmpty()){
                addWordToConsumer(formattedWord, foundPasswords);
            }
        }
    }

    private void addWordToConsumer(String formattedWord, List<String> foundPasswords) {
        synchronized (crackedPasswordsBuffer) {
            passwords.removeAll(foundPasswords);
            crackedPasswordsBuffer.add(formattedWord);
            crackedPasswordsBuffer.notifyAll();
        }
    }

    private boolean checkIfAllPasswordsCracked() {
        synchronized (passwords) {
            if (passwords.isEmpty()) {
                notifyEndConsumer();
                return true;
            }
            return false;
        }
    }
    private void notifyEndConsumer() {
        synchronized (crackedPasswordsBuffer){
            crackedPasswordsBuffer.add(PoisonPill.allPasswordsCracked);
            crackedPasswordsBuffer.notifyAll();
        }
    }
}