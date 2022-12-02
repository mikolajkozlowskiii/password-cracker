import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> listOfPasswordsToCrack = new ArrayList<>();
        listOfPasswordsToCrack.add("575c6b368bdc5915a846d28070e03908");
        listOfPasswordsToCrack.add("77dfb4d8d7d248f1439c99bbb727d49e");
        listOfPasswordsToCrack.add("4d775f10721c63af33dc9ce3ce89fae4");
        listOfPasswordsToCrack.add("7bc62a30570999711c310a6607ee9b75");
        listOfPasswordsToCrack.add("f74632a8ad34822025de08ff372bdd8f");
        listOfPasswordsToCrack.add("7bb8724f2fa47613857d17f63c4aaec1");
        List<String> listOfCrackedPasswords = new ArrayList<String>();
        List<String> listOfDictionary =
                new FileReader("md5test.txt","english3.txt").readFileForDictionary();

        Thread producerThread1 = new Thread(new ProducerLowerCase(listOfDictionary,listOfPasswordsToCrack,listOfCrackedPasswords));
        Thread producerThread2 = new Thread(new ProducerUpperCase(listOfDictionary,listOfPasswordsToCrack,listOfCrackedPasswords));
        Thread producerThread3 = new Thread(new ProducerFirstLetterUp(listOfDictionary,listOfPasswordsToCrack,listOfCrackedPasswords));
        Thread consumerThread = new Thread(new Consumer(listOfCrackedPasswords));
        producerThread1.start();
        producerThread2.start();
        producerThread3.start();
        consumerThread.start();
    }
}



//pomyslec zeby dodac do Consumer wait zeby tyle nie sprawdzal wait i notify






//List<Integer> hasla -> to musi byc synchronizowane, musi byc synchronizowane szukanie tej listy czy element nalezy do listy niestety
//List<String> odszyfrowaneHaslaDoKonsumenta+nazwaWatkui