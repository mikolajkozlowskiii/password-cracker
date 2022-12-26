import components.FileReader;
import consumers.Consumer;
import producers.ProducerDoubleWord;
import producers.ProducerSingleWord;
import producers.strategies.CapitalizeStrategy;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        final String passwordsPathname = "src/sample_inputs/fruits/doubleWordFruits.txt";
        final String dictionaryPathname = "src/sample_inputs/fruits/fruitsDictionary.txt";
        final String crackedPasswordsPathname = "src/sample_outputs/fruits/doubleWorfdFruitsCracked.txt";

        List<String> passwordsToCrack = new FileReader(passwordsPathname).read();
        List<String> dictionary = new FileReader(dictionaryPathname).read();
        List<String> crackedPasswords = new ArrayList<>();

        List<Runnable> producers = new ArrayList<>();
        producers.addAll(getListOfDoubleWordProducers(dictionary,passwordsToCrack,crackedPasswords,true));
        producers.addAll(getListOfDoubleWordProducers(dictionary,passwordsToCrack,crackedPasswords,false));
        producers.addAll(getListOfSingleWordProducers(dictionary,passwordsToCrack,crackedPasswords,true));
        producers.addAll(getListOfSingleWordProducers(dictionary,passwordsToCrack,crackedPasswords,false));
        List<Thread> producersThreads = new ArrayList<>();
        for(Runnable producer : producers){
            producersThreads.add(new Thread(producer));
        }
        System.out.printf("Number of passwords to be cracked: %d%n",passwordsToCrack.size());
        Thread consumerThread = new Thread(new Consumer(crackedPasswords,crackedPasswordsPathname));
        consumerThread.start();
        for(Thread thread : producersThreads){
            thread.start();
        }

    }
    public static List<Runnable> getListOfSingleWordProducers(List<String> dictionary,
                                                       List<String> passwordToCrack,
                                                       List<String> crackedPasswords,
                                                       boolean isPunctuation){
        List<Runnable> producers = new ArrayList<>();
        for(CapitalizeStrategy strategy : CapitalizeStrategy.values()){
            producers.add(ProducerSingleWord
                    .builder()
                    .capitalizeStrategy(strategy)
                    .dictionary(dictionary)
                    .listOfPasswords(passwordToCrack)
                    .listOfCrackedPasswords(crackedPasswords)
                    .isPunctuation(isPunctuation)
                    .build());
        }
        return producers;
    }
    public static List<Runnable> getListOfDoubleWordProducers(List<String> dictionary,
                                                       List<String> passwordsToCrack,
                                                       List<String> crackedPasswords,
                                                       boolean isPunctuation){
        List<Runnable> producers = new ArrayList<>();
        for(CapitalizeStrategy strategy : CapitalizeStrategy.values()){
            producers.add(ProducerDoubleWord
                    .builder()
                    .capitalizeStrategy(strategy)
                    .dictionary(dictionary)
                    .listOfPasswords(passwordsToCrack)
                    .listOfCrackedPasswords(crackedPasswords)
                    .isPunctuation(isPunctuation)
                    .build());
        }
        return producers;
    }
}
//zrobic commit
//usprawnic wszystkie klasy zbey ladnie wygladlay
//potestowac czy dziala wiele konsumentow
//dodac metode ktora tworzy stringa z inoutu do output
//testy jednostkowe do tworonych hasel
//potestowac