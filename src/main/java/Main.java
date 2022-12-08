import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<String> listOfPasswordsToCrack = new ArrayList<>();
        listOfPasswordsToCrack.add("575c6b368bdc5915a846d28070e03908");
        listOfPasswordsToCrack.add("77dfb4d8d7d248f1439c99bbb727d49e");
        listOfPasswordsToCrack.add("4d775f10721c63af33dc9ce3ce89fae4");
        listOfPasswordsToCrack.add("7bc62a30570999711c310a6607ee9b75");
        listOfPasswordsToCrack.add("f74632a8ad34822025de08ff372bdd8f");
        listOfPasswordsToCrack.add("7bb8724f2fa47613857d17f63c4aaec1");
        listOfPasswordsToCrack.add("73162e55e459ea072481fd373fce9af1");
        listOfPasswordsToCrack.add("8fcd19e250bec54f452b4b4b8c2313dd");

        listOfPasswordsToCrack.add("93c8d51f065f37c3ee500e8b90be7ea4");
        listOfPasswordsToCrack.add("aa87c48637503af00f34b0cc6af47764");
        listOfPasswordsToCrack.add("272a2b074d1dab5e4f5d12b7d8fa9828");
        listOfPasswordsToCrack.add("16465ef68d5cc085851de7c38a17052a");
        listOfPasswordsToCrack.add("3ac15e09bb65fbee753fea2344fbc584");
        List<String> listOfCrackedPasswords = new ArrayList<String>();
        List<String> listOfDictionary =
                new FileReader("md5test.txt","english3.txt").readFileForDictionary();

        List<Thread> threads = List.of(
                new Thread(new ProducerSingleWord(listOfDictionary,listOfPasswordsToCrack
                        ,listOfCrackedPasswords, WordStrategy.WORD_FIRST_LETTER_UP)),
                new Thread(new ProducerSingleWord(listOfDictionary,listOfPasswordsToCrack,
                        listOfCrackedPasswords, WordStrategy.WORD_TO_UPPER_CASE)),
                new Thread(new ProducerSingleWord(listOfDictionary,listOfPasswordsToCrack
                        ,listOfCrackedPasswords, WordStrategy.WORD_TO_lOWER_CASE)),
                new Thread(new ProducerDoubleWord(listOfDictionary,listOfPasswordsToCrack
                        ,listOfCrackedPasswords, WordStrategy.WORD_FIRST_LETTER_UP)),
                new Thread(new ProducerDoubleWord(listOfDictionary,listOfPasswordsToCrack
                        ,listOfCrackedPasswords, WordStrategy.WORD_TO_lOWER_CASE)),
                new Thread(new ProducerDoubleWord(listOfDictionary,listOfPasswordsToCrack
                        ,listOfCrackedPasswords, WordStrategy.WORD_TO_UPPER_CASE)));

        Thread consumerThread1 = new Thread(new Consumer(listOfCrackedPasswords));
        for(Thread thread : threads){
            thread.start();
        }
        consumerThread1.start();
        while(true){
            String msg = scan.nextLine();
            System.out.println(msg.toUpperCase());
            if(msg.equals("quit")){

            }
        }
    }
}

//stworzyc funkcje do tworzenia watkow, zawsze taka sama
//ona sie bedzie wywolywac i jakby stworzyc nowe watki jesli przekazymy
//jakis plik do otwarcia

//pomyslec zeby dodac do Consumer wait zeby tyle nie sprawdzal wait i notify






//List<Integer> hasla -> to musi byc synchronizowane, musi byc synchronizowane szukanie tej listy czy element nalezy do listy niestety
//List<String> odszyfrowaneHaslaDoKonsumenta+nazwaWatkui