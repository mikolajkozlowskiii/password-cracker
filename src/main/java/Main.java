import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> listaHaselDoZlamania = new ArrayList<>();
        listaHaselDoZlamania.add("575c6b368bdc5915a846d28070e03908");
        listaHaselDoZlamania.add("77dfb4d8d7d248f1439c99bbb727d49e");
        listaHaselDoZlamania.add("4d775f10721c63af33dc9ce3ce89fae4");
        listaHaselDoZlamania.add("7bc62a30570999711c310a6607ee9b75");
        listaHaselDoZlamania.add("f74632a8ad34822025de08ff372bdd8f");
        List<String> listaHaselZlamanych = new ArrayList<String>();

        Thread producerThread1 = new Thread(new Producer(listaHaselDoZlamania,listaHaselZlamanych));
        Thread producerThread2 = new Thread(new Producer2(listaHaselDoZlamania,listaHaselZlamanych));
        Thread consumerThread = new Thread(new Consumer(listaHaselZlamanych));
        producerThread1.start();
        producerThread2.start();
        consumerThread.start();
    }
}



//pomyslec zeby dodac do Consumer wait zeby tyle nie sprawdzal wait i notify






//List<Integer> hasla -> to musi byc synchronizowane, musi byc synchronizowane szukanie tej listy czy element nalezy do listy niestety
//List<String> odszyfrowaneHaslaDoKonsumenta+nazwaWatkui