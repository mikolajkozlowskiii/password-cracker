package consumers;

import constants.Constants;
import lombok.AllArgsConstructor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class Consumer implements Runnable {
    private List<String> crackedPasswords;

    private final String outputPathname;
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
                    final String crackedPass = crackedPasswords.remove(0);
                    count++;
                    System.out.println(count+". Cracked password: " + crackedPass);
                    writeToFile(crackedPass);
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