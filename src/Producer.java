import java.util.List;

public class Producer implements Runnable{
    private List<Integer> listHaselDoZlamania;
    private List<String> listaZlamanychHasel;

    public Producer(List<Integer> listaHasel, List<String> listaZlamanychHasel) {
        this.listHaselDoZlamania = listaHasel;
        this.listaZlamanychHasel = listaZlamanychHasel;
    }


    @Override
    public void run() {
        //while(!listaHaselDoZlamania.isEmpty())
        int i = 0;
        while(true) {

            i++;
                synchronized (listHaselDoZlamania) {
                    if (listHaselDoZlamania.isEmpty()) {
                        synchronized (listaZlamanychHasel){
                            System.out.println("koniec pord1");
                            listaZlamanychHasel.add("-1");
                            listaZlamanychHasel.notifyAll();
                        }
                        break;
                    }
                    for(int j = 0; j<listHaselDoZlamania.size(); j++){
                        if(listHaselDoZlamania.get(j) == i){
                            System.out.println("Usuwamy haslo z listy, aktualna lista: " + listHaselDoZlamania+"\n usuwamy:" +
                                    i);
                            synchronized (listaZlamanychHasel){
                                listaZlamanychHasel.add(String.valueOf(listHaselDoZlamania.remove(j)));
                                System.out.println(listaZlamanychHasel);
                                listaZlamanychHasel.notifyAll();

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
