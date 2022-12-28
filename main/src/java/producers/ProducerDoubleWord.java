package producers;

import constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
@Builder
public class ProducerDoubleWord implements Runnable{
    private final ProducerManager producerManager;
    private final BlockingQueue<String> crackedPasswords;

    @Override
    public void run() {
        if (producerManager.checkValidateInputs()){
            notifyEndConsumer();
            return;
        }
        AtomicInteger countMainLoop = new AtomicInteger(0);
        while(true) {
            try{
                for (String s : producerManager.getCrackedDoubleWordPasswords(countMainLoop.get())) {
                    crackedPasswords.put(s);
                }
            }catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (producerManager.checkIfAllPasswordsCracked()) {
                return;
            }
            countMainLoop.incrementAndGet();
        }
    }
    private void notifyEndConsumer() {
        synchronized (producerManager.getListOfCrackedPasswords()){
            crackedPasswords.put(Constants.allPasswordsCracked));
        }
    }

    private void addWordsToConsumer(List<String> crackedPasswords) {
        synchronized (producerManager.getListOfCrackedPasswords()) {
            producerManager.getListOfCrackedPasswords().addAll(crackedPasswords);
            producerManager.getListOfCrackedPasswords().notifyAll();
        }
    }
}
