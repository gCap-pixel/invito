import java.util.Random;

public class Auto extends Thread {
    private String colore;
    private int metriPercorsi = 0;
    private Random random = new Random();
    private boolean inGara = true;
    private boolean incidenteSegnalato = false;

    public Auto(String colore) {
        this.colore = colore;
    }

    public String getColore() {
        return colore;
    }

    public synchronized int getMetriPercorsi() {
        return metriPercorsi;
    }

    public synchronized void aggiungiMetri(int passo) {
        metriPercorsi += passo;
    }

    public synchronized boolean isInGara() {
        return inGara;
    }

    public synchronized void terminaGara() {
        inGara = false;
    }

    public synchronized boolean isIncidenteDaSegnalare() {
        if (!inGara && !incidenteSegnalato) {
            incidenteSegnalato = true;
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        while (inGara) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }

            synchronized (this) {
                if (!inGara) {
                    return;
                }

                int passo = random.nextInt(99) + 1;
                aggiungiMetri(passo);

                if (passo == 43 || getMetriPercorsi() == 79 || passo == 2 || passo == 19 || passo == 27) {
                    inGara = false;
                }
            }
        }
    }
}
