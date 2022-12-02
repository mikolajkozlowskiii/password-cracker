import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> listaHaselDoZlamania = new ArrayList<Integer>();
        listaHaselDoZlamania.add(5);
        listaHaselDoZlamania.add(50000);
        listaHaselDoZlamania.add(55000);
        listaHaselDoZlamania.add(100010);
        listaHaselDoZlamania.add(190000);

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