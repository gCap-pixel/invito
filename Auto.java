import java.util.Random;

public class Auto extends Thread {
    private String colore;
    private int metriPercorsi = 0;
    private Random random = new Random();
    private boolean inGara = true;
    private Gestione g;

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

    public void terminaGara() {
        inGara = false;
    }

    @Override
    public void run() {
        while (inGara != false) {
            try {
                Thread.sleep(1000); // ogni secondo
            } catch (InterruptedException e) {
                System.out.println("Errore nel thread della macchina " + colore);
            }

            int passo = random.nextInt(99) + 1; // passo casuale
            aggiungiMetri(passo);

            if (passo == 43 || getMetriPercorsi()==79 || passo == 2 || passo == 19 || passo == 27){
                try {
                    throw new RuntimeException();
                }
                catch (RuntimeException e) {
                    System.err.println("la macchina " + colore + " ha fatto un incidente");
                    inGara = false;
                }
            }

            System.out.println("la macchina " + colore + " ha percorso " + getMetriPercorsi() + " metri.");
            }
        }
    }