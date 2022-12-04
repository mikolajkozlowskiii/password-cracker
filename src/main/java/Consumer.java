import java.util.List;

class Consumer implements Runnable {
    private List<String> listHaselZlamanych;

    public Consumer(List<String> listaHaselZlamanych) {
        this.listHaselZlamanych = listaHaselZlamanych;
    }


    @Override
    public void run() {

        while (true) {
            synchronized (listHaselZlamanych) {
                if (listHaselZlamanych.isEmpty()) {
                    try {
                        listHaselZlamanych.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); //ustawia status przerwania
                        throw new RuntimeException(e);
                    }
                }
                if (listHaselZlamanych.get(0).equals("-1")) {
                    System.out.println(Thread.currentThread().getName() + " exiting.");
                    break;
                } else {
                    System.out.println("Cracked password: " + listHaselZlamanych.remove(0));
                }
            }
        }
    }
}