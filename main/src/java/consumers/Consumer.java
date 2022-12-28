package consumers;

import constants.Constants;
import lombok.AllArgsConstructor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@AllArgsConstructor
public class Consumer implements Runnable {
    private final BlockingQueue<String> crackedPasswords;
    private final String outputPathname;
    private static AtomicLong counter = new AtomicLong(0);
    private final String poisonPill = Constants.allPasswordsCracked;

    @Override
    public void run() {
        try {
            while (true) {
                final String crackedPassword = crackedPasswords.take();
                if (crackedPassword.equals(poisonPill)) {
                    System.out.println(Thread.currentThread().getName() + " exiting.");
                    return;
                }
                counter.getAndIncrement();
                writeToFile(crackedPassword);
                System.out.printf("%d%s%s%n",counter.get(),
                        ". Cracked password: ", crackedPassword);

            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
    }




           /* synchronized (crackedPasswords) {
                if (crackedPasswords.isEmpty()) {
                    try {
                        crackedPasswords.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (!crackedPasswords.isEmpty() &&
                        Objects.equals(crackedPasswords.get(0), Constants.allPasswordsCracked)) {
                    System.out.println(Thread.currentThread().getName() + " exiting.");
                    return;
                }
                else {
                    while(!crackedPasswords.isEmpty()){
                        final String crackedPass = crackedPasswords.remove(0);
                        numOfCrackedPasswords++;
                        writeToFile(crackedPass);
                        System.out.printf("%d%s%s%n",numOfCrackedPasswords,
                                ". Cracked password: ", crackedPass);
                    }
                }
            }*/
    }

    private synchronized void writeToFile(String crackedPass) {
        try(FileWriter fileWriter = new FileWriter(outputPathname,true);
            PrintWriter writer = new PrintWriter(fileWriter)){
            writer.write(crackedPass +"\n");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}