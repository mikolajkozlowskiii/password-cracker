import components.FileReader;
import components.WordFormater;
import consumers.Consumer;
import producers.*;
import components.strategies.CapitalizeStrategy;
import components.strategies.NumberStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final String passwordsPathname = "sample_inputs/fruits/randomFruits.txt";
        final String dictionaryPathname = "sample_inputs/fruits/fruitsDictionary.txt";
        final String crackedPasswordsPathname = "sample_outputs/fruits/randomFruitsCracked.txt";

        final List<String> passwordsToCrack = new FileReader(passwordsPathname).read();
        final List<String> dictionary = new FileReader(dictionaryPathname).read();
        final List<String> crackedPasswordsBuffer = new ArrayList<>();

        List<Runnable> producers = new ArrayList<>();
        // 9 producers: single word with punctuation
        producers.addAll(getProducers(dictionary,passwordsToCrack,crackedPasswordsBuffer,
                getAllUniqueWordFormaters(),true,true));
        // 9 producers: double words with punctuation
        producers.addAll(getProducers(dictionary,passwordsToCrack,crackedPasswordsBuffer,
                getAllUniqueWordFormaters(),true,false));
        // 9 producers: single words without punctuation
        producers.addAll(getProducers(dictionary,passwordsToCrack,crackedPasswordsBuffer,
                getAllUniqueWordFormaters(),false,true));
        // 9 producers: double words without punctuation
        producers.addAll(getProducers(dictionary,passwordsToCrack,crackedPasswordsBuffer,
                getAllUniqueWordFormaters(),false,false));

        List<Thread> producersThreads = new ArrayList<>();
        for(Runnable producer : producers){
            producersThreads.add(new Thread(producer));
        }
        System.out.printf("Number of passwords to be cracked: %d%n",passwordsToCrack.size());
        Thread consumerThread = new Thread(new Consumer(crackedPasswordsBuffer,crackedPasswordsPathname));
        consumerThread.start();
        for(Thread thread : producersThreads){
            thread.start();
        }

    }
    public static List<WordFormater> getAllUniqueWordFormaters(){
        List<WordFormater> wordFormaters = new ArrayList<>();
        for(CapitalizeStrategy capitalizeStrategy : CapitalizeStrategy.values()) {
            for (NumberStrategy numberStrategy : NumberStrategy.values()) {
                wordFormaters.add(new WordFormater(capitalizeStrategy,numberStrategy));
            }
        }
        return wordFormaters;
    }
    public static List<Producer> getProducers(List<String> dictionary,
                                             List<String> passwordsToCrack,
                                             List<String> crackedPasswordsBuffer,
                                             List<WordFormater> wordFormaters,
                                             boolean isPunctuation,
                                             boolean isSingleWord){
        return wordFormaters
                .stream()
                .map(s->Producer
                        .builder()
                        .wordFormater(s)
                        .passwords(passwordsToCrack)
                        .crackedPasswordsBuffer(crackedPasswordsBuffer)
                        .dictionary(dictionary)
                        .isPunctuation(isPunctuation)
                        .isSingleWord(isSingleWord)
                        .build())
                .toList();
    }

}