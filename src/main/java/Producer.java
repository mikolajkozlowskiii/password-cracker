import java.util.List;

public class Producer implements Runnable{
    private List<String> listOfPasswords;
    private List<String> listOfCrackedPasswords;


    public Producer(List<String> listOfPasswords, List<String> listOfCrackedPasswords) {
        this.listOfPasswords = listOfPasswords;
        this.listOfCrackedPasswords = listOfCrackedPasswords;
    }


    @Override
    public void run() {
        //while(!listaHaselDoZlamania.isEmpty())
        int i = 0;
        while(true) {

            i++;
                synchronized (listOfPasswords) {
                    if (listOfPasswords.isEmpty()) {
                        synchronized (listOfCrackedPasswords){
                            System.out.println("koniec pord1");
                            listOfCrackedPasswords.add("-1");
                            listOfCrackedPasswords.notifyAll();
                        }
                        break;
                    }
                    for(int j = 0; j<listOfPasswords.size(); j++){
                        if(listOfPasswords.get(j) == i){
                            System.out.println("Usuwamy haslo z listy, aktualna lista: " + listOfPasswords+"\n usuwamy:" +
                                    i);
                            synchronized (listOfCrackedPasswords){
                                listOfCrackedPasswords.add(String.valueOf(listOfPasswords.remove(j)));
                                System.out.println(listOfCrackedPasswords);
                                listOfCrackedPasswords.notifyAll();

                            }
                        }
                    }
                }
               /* try{
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }*/

        }
    }
}
