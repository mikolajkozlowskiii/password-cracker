import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    List<ProducerDoubleWord> producerDoubleWordList;
    List<ProducerSingleWord> producerSingleWordList;
    public static void main(String[] args) {
        Main main = new Main();
        Scanner scan = new Scanner(System.in);
        List<String> listOfPasswordsToCrack = new ArrayList<>();
        listOfPasswordsToCrack.add("d154dfaf148b768ef9e0141f69f1e357");
        listOfPasswordsToCrack.add("0d1d04685b9709a7aab52c0f1c9ad21d");
        listOfPasswordsToCrack.add("098b3e2ac3be9a4f1fadf6f9979f2b3d");
        listOfPasswordsToCrack.add("58994a7861109c87a8d8e6f4443e2e02");

/*


098b3e2ac3be9a4f1fadf6f9979f2b3d
58994a7861109c87a8d8e6f4443e2e02
*
* */
        List<String> listOfCrackedPasswords = new ArrayList<String>();
        List<String> listOfDictionary =
                new FileReader("md5test.txt","fruits.txt").readFileForDictionary();

        main.initializeSingleWordThreads(listOfDictionary,listOfPasswordsToCrack,listOfCrackedPasswords);
        main.initializeDoubleWordThreads(listOfDictionary,listOfPasswordsToCrack,listOfCrackedPasswords);
        List<Thread> threads = new ArrayList<>();
        for(ProducerSingleWord producerSingleWord : main.producerSingleWordList){
            threads.add(new Thread(producerSingleWord));
        }
        for(ProducerDoubleWord producerDoubleWord : main.producerDoubleWordList){
            threads.add(new Thread(producerDoubleWord));
        }
        Thread consumerThread1 = new Thread(new Consumer(listOfCrackedPasswords));
        System.out.println("number of passwords to be cracked: "+listOfPasswordsToCrack.size());
        for(Thread thread : threads){
            thread.start();
        }
        consumerThread1.start();
        while(true){
            String input = scan.nextLine();
            System.out.println(input.toUpperCase());
            if(input.equals("quit")){
                for(ProducerSingleWord producerSingleWord : main.producerSingleWordList){
                    producerSingleWord.setStopped(true);
                }
                for(ProducerDoubleWord producerDoubleWord : main.producerDoubleWordList){
                    producerDoubleWord.setStopped(true);
                }
                return;
            }
            else{
                for(ProducerSingleWord producerSingleWord : main.producerSingleWordList){
                    producerSingleWord.setStopped(true);
                }
                for(ProducerDoubleWord producerDoubleWord : main.producerDoubleWordList){
                    producerDoubleWord.setStopped(true);
                }
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){}
                listOfCrackedPasswords.removeAll(listOfCrackedPasswords);
                listOfPasswordsToCrack.removeAll(listOfPasswordsToCrack);
                listOfPasswordsToCrack = new FileReader("md5test.txt",input).readFileForDictionary();
                main.initializeSingleWordThreads(listOfDictionary,listOfPasswordsToCrack,listOfCrackedPasswords);
                main.initializeDoubleWordThreads(listOfDictionary,listOfPasswordsToCrack,listOfCrackedPasswords);
                System.out.println("number of passwords to be cracked: "+listOfPasswordsToCrack.size());
                threads = new ArrayList<>();
                for(ProducerSingleWord producerSingleWord : main.producerSingleWordList){
                    producerSingleWord.setStopped(false);
                    threads.add(new Thread(producerSingleWord));
                }
                for(ProducerDoubleWord producerDoubleWord : main.producerDoubleWordList){
                    producerDoubleWord.setStopped(false);
                    threads.add(new Thread(producerDoubleWord));
                }
                consumerThread1 = new Thread(new Consumer(listOfCrackedPasswords));
                for(Thread thread : threads){
                    thread.start();
                }
                consumerThread1.start();



            }
        }
    }


    public void initializeSingleWordThreads(List<String> listOfDictionary, List<String> listOfPasswordsToCrack, List<String> listOfCrackedPasswords){
        producerSingleWordList = List.of(new ProducerSingleWord(listOfDictionary,listOfPasswordsToCrack
                ,listOfCrackedPasswords, WordStrategy.WORD_FIRST_LETTER_UP),new ProducerSingleWord(listOfDictionary,listOfPasswordsToCrack,
                listOfCrackedPasswords, WordStrategy.WORD_TO_UPPER_CASE), new ProducerSingleWord(listOfDictionary,listOfPasswordsToCrack
                ,listOfCrackedPasswords, WordStrategy.WORD_TO_lOWER_CASE));
    }
    public void initializeDoubleWordThreads(List<String> listOfDictionary, List<String> listOfPasswordsToCrack, List<String> listOfCrackedPasswords){
        producerDoubleWordList = List.of(new ProducerDoubleWord(listOfDictionary,listOfPasswordsToCrack
                ,listOfCrackedPasswords, WordStrategy.WORD_FIRST_LETTER_UP),new ProducerDoubleWord(listOfDictionary,listOfPasswordsToCrack
                ,listOfCrackedPasswords, WordStrategy.WORD_TO_lOWER_CASE), new ProducerDoubleWord(listOfDictionary,listOfPasswordsToCrack
                ,listOfCrackedPasswords, WordStrategy.WORD_TO_UPPER_CASE));
    }
}

//stworzyc funkcje do tworzenia watkow, zawsze taka sama
//ona sie bedzie wywolywac i jakby stworzyc nowe watki jesli przekazymy
//jakis plik do otwarcia

//pomyslec zeby dodac do Consumer wait zeby tyle nie sprawdzal wait i notifye






//List<Integer> hasla -> to musi byc synchronizowane, musi byc synchronizowane szukanie tej listy czy element nalezy do listy niestety
//List<String> odszyfrowaneHaslaDoKonsumenta+nazwaWatkui