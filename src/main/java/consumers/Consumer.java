package consumers;

import constants.Constants;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class Consumer implements Runnable {
    private List<String> crackedPasswords;
    private static int count = 0;

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
                    count++;
                    System.out.println(count+". Cracked password: " + crackedPasswords.remove(0));
                }
            }
        }
    }
}