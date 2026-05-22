import java.util.ArrayList;

public class Gestione extends Thread {
    private ArrayList<Auto> macchine;
    private int distanzaVittoria;
    private boolean garaInCorso = true;
    
    // AGGIUNTA: Variabile per salvare il colore di chi vince
    private String coloreVincitore = ""; 

    public Gestione(ArrayList<Auto> macchine, int distanzaVittoria) {
        this.macchine = macchine;
        this.distanzaVittoria = distanzaVittoria;
    }

    // AGGIUNTA: Metodo getter per permettere al Main di sapere chi ha vinto
    public String getColoreVincitore() {
        return coloreVincitore;
    }

    @Override
    public void run() {
        System.out.println("--- LA GARA HA INIZIO! TRAGUARDO A " + distanzaVittoria + " METRI ---");
        
        for (Auto auto : macchine) {
            auto.start();
        }

        while (garaInCorso) {
            try {
                Thread.sleep(500); 
            } catch (InterruptedException e) {
                System.out.println("Errore nella gestione della gara.");
            }

            int autoAttive = 0;

            for (Auto auto : macchine) {
                if (auto.isAlive()) {
                    autoAttive++;
                    
                    if (auto.getMetriPercorsi() >= distanzaVittoria) {
                        // AGGIUNTA: Registriamo il vincitore prima di chiudere la gara
                        coloreVincitore = auto.getColore(); 
                        System.out.println("\n*** HA VINTO LA MACCHINA " + coloreVincitore.toUpperCase() + "! ***");
                        terminaTuttiIThread();
                        break;
                    }
                }
            }

            if (autoAttive == 0 && garaInCorso) {
                System.out.println("\n--- GARA TERMINATA: Tutte le macchine si sono scontrate! ---");
                coloreVincitore = "Nessuno";
                garaInCorso = false;
            }
        }
    }

    private void terminaTuttiIThread() {
        garaInCorso = false;
        for (Auto auto : macchine) {
            auto.terminaGara();
            auto.interrupt(); 
        }
    }
}
