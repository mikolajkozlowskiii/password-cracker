import components.FileReader;
import producers.Producer;
import components.WordFormater;
import consumers.Consumer;
import producers.*;
import components.strategies.CapitalizeStrategy;
import components.strategies.NumberStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final String passwordsPathname = "sample_inputs/english_words/randomEngWords.txt";
        final String dictionaryPathname = "sample_inputs/english_words/engDictionary.txt";
        final String crackedPasswordsPathname = "sample_outputs/english_words/randomEngWords.txt";

        final List<String> passwordsToCrack = new FileReader(passwordsPathname).read();
        final List<String> dictionary = new FileReader(dictionaryPathname).read();
        final List<String> crackedPasswordsBuffer = new ArrayList<>();

        List<Runnable> producers = new ArrayList<>();
        producers.addAll(getProducers(getProducerManagers(dictionary,passwordsToCrack,
                getAllUniqueWordFormaters(),true,true), crackedPasswordsBuffer));
        producers.addAll(getProducers(getProducerManagers(dictionary,passwordsToCrack,
                getAllUniqueWordFormaters(),true,false), crackedPasswordsBuffer));
        producers.addAll(getProducers(getProducerManagers(dictionary,passwordsToCrack,
                getAllUniqueWordFormaters(),false,true),crackedPasswordsBuffer));
        producers.addAll(getProducers(getProducerManagers(dictionary,passwordsToCrack,
                getAllUniqueWordFormaters(),false,false), crackedPasswordsBuffer));

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
    public static List<ProducerManager> getProducerManagers(List<String> dictionary,
                                                            List<String> passwordsToCrack,
                                                            List<WordFormater> wordFormaters,
                                                            boolean isPunctuation,
                                                            boolean isSingleWord){
        return wordFormaters
                .stream()
                .map(s->ProducerManager
                        .builder()
                        .wordFormater(s)
                        .listOfPasswords(passwordsToCrack)
                        .dictionary(dictionary)
                        .isPunctuation(isPunctuation)
                        .isSingleWord(isSingleWord)
                        .build())
                .toList();
    }
    public static List<Producer> getProducers(List<ProducerManager> producerManagers,
                                              List<String> buffer){
        return producerManagers
                .stream()
                .map(s->Producer
                        .builder()
                        .producerManager(s)
                        .crackedPasswordsBuffer(buffer)
                        .build())
                .toList();
    }
}