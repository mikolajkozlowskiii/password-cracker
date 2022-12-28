import components.FileReader;
import consumers.Consumer;
import producers.ProducerDoubleWord;
import producers.ProducerManager;
import producers.ProducerSingleWord;
import producers.WordFormater;
import producers.strategies.CapitalizeStrategy;
import producers.strategies.NumberStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        final String passwordsPathname = "sample_inputs/fruits/randomFruits.txt";
        final String dictionaryPathname = "sample_inputs/fruits/fruitsDictionary.txt";
        final String crackedPasswordsPathname = "sample_outputs/fruits/randomFruitsCracked.txt";

        final List<String> passwordsList = new FileReader(passwordsPathname).read();
        final List<String> dictionaryList = new FileReader(dictionaryPathname).read();

        final CopyOnWriteArrayList<String> dictionary = new CopyOnWriteArrayList<>(dictionaryList);
        final CopyOnWriteArrayList<String> passwordsToCrack = new CopyOnWriteArrayList<>(passwordsList);
        final BlockingQueue crackedPasswords = new ArrayBlockingQueue(10);

        List<Runnable> producers = new ArrayList<>();
        producers.addAll(getListOfDoubleWordProducers(getListOfProducerManagers
                (dictionary,passwordsToCrack,crackedPasswords,getListOfWordFormater(),true)));
        producers.addAll(getListOfDoubleWordProducers(getListOfProducerManagers
                (dictionary,passwordsToCrack,crackedPasswords,getListOfWordFormater(),false)));
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
    public static List<WordFormater> getListOfWordFormater(){
        List<WordFormater> wordFormaters = new ArrayList<>();
        for(CapitalizeStrategy capitalizeStrategy : CapitalizeStrategy.values()) {
            for (NumberStrategy numberStrategy : NumberStrategy.values()) {
                wordFormaters.add(new WordFormater(capitalizeStrategy,numberStrategy));
            }
        }
        return wordFormaters;
    }
    public static List<Runnable> getListOfSingleWordProducers(CopyOnWriteArrayList<String> dictionary,
                                                       CopyOnWriteArrayList<String> passwordToCrack,
                                                       CopyOnWriteArrayList<String> crackedPasswords,
                                                       boolean isPunctuation){
        List<Runnable> producers = new ArrayList<>();
        for(CapitalizeStrategy capitalizeStrategy : CapitalizeStrategy.values()){
            for(NumberStrategy numberStrategy : NumberStrategy.values()){
                producers.add(ProducerSingleWord
                        .builder()
                        .capitalizeStrategy(capitalizeStrategy)
                        .dictionary(dictionary)
                        .listOfPasswords(passwordToCrack)
                        .listOfCrackedPasswords(crackedPasswords)
                        .isPunctuation(isPunctuation)
                        .numberStrategy(numberStrategy)
                        .build());
            }
        }
        return producers;
    }
    public static List<ProducerManager> getListOfProducerManagers(CopyOnWriteArrayList<String> dictionary,
                                                       CopyOnWriteArrayList<String> passwordsToCrack,
                                                       CopyOnWriteArrayList<String> crackedPasswords,
                                                       List<WordFormater> wordFormaters,
                                                       boolean isPunctuation){
        return wordFormaters
                .stream()
                .map(s->ProducerManager
                        .builder()
                        .wordFormater(s)
                        .listOfCrackedPasswords(crackedPasswords)
                        .listOfPasswords(passwordsToCrack)
                        .dictionary(dictionary)
                        .isPunctuation(isPunctuation)
                        .build())
                .toList();
    }
    public static List<ProducerDoubleWord> getListOfDoubleWordProducers(List<ProducerManager> producerManagers){
        return producerManagers
                .stream()
                .map(s->ProducerDoubleWord
                        .builder()
                        .producerManager(s)
                        .build())
                .toList();
    }
}
//stworzyc WordFormatera i go przypisac