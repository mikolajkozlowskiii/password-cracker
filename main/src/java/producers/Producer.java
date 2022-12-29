package producers;

import constants.PoisonPill;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
@Builder
public class Producer implements Runnable{
    private final ProducerManager producerManager;
    private final List<String> crackedPasswordsBuffer;

    @Override
    public void run() {
        if (producerManager.checkValidateInputs()){
            notifyEndConsumer();
            return;
        }
        AtomicInteger countMainLoop = new AtomicInteger(0);
        while(true) {
            final List<String> crackedPasswords = producerManager.getCrackedPasswords(countMainLoop.get());
            if (!crackedPasswords.isEmpty()){
                addWordsToConsumer(crackedPasswords);
            }
            if (producerManager.checkIfAllPasswordsCracked()) {
                notifyEndConsumer();
                return;
            }
            countMainLoop.incrementAndGet();
        }
    }
    private void notifyEndConsumer() {
        synchronized (crackedPasswordsBuffer){
            crackedPasswordsBuffer.add(PoisonPill.allPasswordsCracked);
            crackedPasswordsBuffer.notifyAll();
        }
    }

    private void addWordsToConsumer(List<String> crackedPasswords) {
        synchronized (crackedPasswordsBuffer) {
            crackedPasswordsBuffer.addAll(crackedPasswords);
            crackedPasswordsBuffer.notifyAll();
        }
    }
}
