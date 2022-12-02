import java.util.List;

public class Producer2 implements Runnable{
    private List<Integer> listHaselDoZlamania;
    private List<String> listaZlamanychHasel;

    public Producer2(List<Integer> listaHasel, List<String> listaZlamanychHasel) {
        this.listHaselDoZlamania = listaHasel;
        this.listaZlamanychHasel = listaZlamanychHasel;
    }



    @Override
    public void run() {
        //while(!listaHaselDoZlamania.isEmpty())
        int i = 100000;
        while(true) {

            i++;

                synchronized (listHaselDoZlamania) {
                    if (listHaselDoZlamania.isEmpty()) {
                        synchronized (listaZlamanychHasel){
                            System.out.println("koniec pord2");
                            listaZlamanychHasel.add("-1");
                        }
                        break;
                    }
                    for(int j = 0; j<listHaselDoZlamania.size(); j++){
                        if(listHaselDoZlamania.get(j) == i){
                            System.out.println("Usuwamy haslo z listy, aktualna lista: " + listHaselDoZlamania+"\n usuwamy:" +
                                    i);
                            synchronized (listaZlamanychHasel){
                                listaZlamanychHasel.add(String.valueOf(listHaselDoZlamania.remove(j)));
                                listaZlamanychHasel.notifyAll();
                            }
                        }
                    }
                }

        }
    }
}