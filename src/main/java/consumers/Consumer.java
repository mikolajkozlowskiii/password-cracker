package consumers;

import constants.Constants;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class Consumer implements Runnable {
    private List<String> crackedPasswords;

    @Override
    public void run() {
        while (true) {
            synchronized (crackedPasswords) {
                if (crackedPasswords.isEmpty()) {
                    try {
                        crackedPasswords.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (Objects.equals(crackedPasswords.get(0), Constants.allPasswordsCracked)) {
                    System.out.println(Thread.currentThread().getName() + " exiting.");
                    return;
                } else {
                    System.out.println("Cracked password: " + crackedPasswords.remove(0));
                }
            }
        }
    }
}