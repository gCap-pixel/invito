import java.util.ArrayList;

public class Gestione extends Thread {
    private ArrayList<Auto> macchine;
    private int distanzaVittoria;
    private boolean garaInCorso = true;
    private String coloreVincitore = "";

    public Gestione(ArrayList<Auto> macchine, int distanzaVittoria) {
        this.macchine = macchine;
        this.distanzaVittoria = distanzaVittoria;
    }

    public String getColoreVincitore() {
        return coloreVincitore;
    }

    @Override
    public void run() {
        System.out.println("--- LA GARA HA INIZIO! TRAGUARDO A " + distanzaVittoria + " METRI ---");

        for (Auto auto : macchine) {
            auto.start();
        }

        try {
            Thread.sleep(1005);
        } catch (InterruptedException e) {
            return;
        }

        while (garaInCorso) {
            int autoAttive = 0;
            boolean qualcunoHaVinto = false;

            // Cicla direttamente sull'ArrayList dinamico passato dal Main
            for (Auto auto : macchine) {
                if (auto.isInGara()) {
                    autoAttive++;
                    System.out.println("la macchina " + auto.getColore() + " ha percorso " + auto.getMetriPercorsi() + " metri.");

                    if (auto.getMetriPercorsi() >= distanzaVittoria && !qualcunoHaVinto) {
                        coloreVincitore = auto.getColore();
                        qualcunoHaVinto = true;
                    }
                } else if (auto.isIncidenteDaSegnalare()) {
                    System.err.println("la macchina " + auto.getColore() + " ha fatto un incidente");
                } else if (auto.isAlive()) {
                    autoAttive++;
                }
            }

            System.out.println();

            if (qualcunoHaVinto) {
                System.out.println("*** HA VINTO LA MACCHINA " + coloreVincitore.toUpperCase() + "! ***");
                terminaTuttiIThread();
                break;
            }

            if (autoAttive == 0 && garaInCorso) {
                System.out.println("--- GARA TERMINATA: Tutte le macchine si sono scontrate! ---");
                coloreVincitore = "Nessuno";
                garaInCorso = false;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
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
