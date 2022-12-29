package consumers;

import constants.PoisonPill;
import lombok.AllArgsConstructor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class Consumer implements Runnable {
    private final List<String> crackedPasswords;
    private final String outputPathname;
    private static volatile int numOfCrackedPasswords = 0;

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
                if (!crackedPasswords.isEmpty() &&
                        Objects.equals(crackedPasswords.get(0), PoisonPill.allPasswordsCracked)) {
                    System.out.println(Thread.currentThread().getName() + " exiting.");
                    return;
                }
                if(!crackedPasswords.isEmpty()) {
                    final String crackedPass = crackedPasswords.remove(0);
                    numOfCrackedPasswords++;
                    writeToFile(crackedPass);
                    System.out.printf("%d%s%s%n",numOfCrackedPasswords,
                            ". Cracked password: ", crackedPass);
                }
            }
        }
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