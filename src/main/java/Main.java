import components.FileReader;
import consumers.Consumer;
import producers.ProducerDoubleWord;
import producers.ProducerSingleWord;
import producers.strategies.CapitalizeStrategy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<String> passwordsToCrack =
                new FileReader("src/sample_inputs/fruits/doubleWordFruits.txt").read();
        List<String> dictionary =
                new FileReader("src/sample_inputs/fruits/fruitsDictionary.txt").read();
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
        Thread consumerThread = new Thread(new Consumer(crackedPasswords));
        consumerThread.start();
        for(Thread thread : producersThreads){
            thread.start();
        }
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

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
