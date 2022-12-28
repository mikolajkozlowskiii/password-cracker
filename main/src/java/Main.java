import components.FileReader;
import consumers.Consumer;
import producers.ProducerDoubleWord;
import producers.ProducerSingleWord;
import producers.strategies.CapitalizeStrategy;
import producers.strategies.NumberStrategy;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        final String passwordsPathname = "sample_inputs/fruits/randomFruits.txt";
        final String dictionaryPathname = "sample_inputs/fruits/fruitsDictionary.txt";
        final String crackedPasswordsPathname = "sample_outputs/fruits/randomFruitsCracked.txt";

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
    public static List<Runnable> getListOfDoubleWordProducers(List<String> dictionary,
                                                       List<String> passwordsToCrack,
                                                       List<String> crackedPasswords,
                                                       boolean isPunctuation){
        List<Runnable> producers = new ArrayList<>();
        for(CapitalizeStrategy capitalizeStrategy : CapitalizeStrategy.values()){
            for(NumberStrategy numberStrategy : NumberStrategy.values()){
                producers.add(ProducerDoubleWord
                        .builder()
                        .capitalizeStrategy(capitalizeStrategy)
                        .dictionary(dictionary)
                        .listOfPasswords(passwordsToCrack)
                        .listOfCrackedPasswords(crackedPasswords)
                        .isPunctuation(isPunctuation)
                        .numberStrategy(numberStrategy)
                        .build());
            }
            }
        return producers;
    }
}
//