import java.util.ArrayList;
import java.util.Scanner;

public class Gara {
    public static void main(String[] args) {
        System.out.println("==================================================================");
        System.out.println("⚠️ ATTENZIONE: Finché la macchina 'Chiuchetti' non vince la gara,");
        System.out.println("non verrà mostrato nulla e la data dell'evento rimarrà segreta!");
        System.out.println("==================================================================\n");

        Auto a = new Auto("Mancino");
        Auto a1 = new Auto("Batocchi");
        Auto a2 = new Auto("Giappichini");
        Auto chiuchetti = new Auto("Chiuchetti");

        ArrayList<Auto> macchine = new ArrayList<Auto>();
        macchine.add(a1);
        macchine.add(a2);
        macchine.add(a);
        macchine.add(chiuchetti);

        Gestione gara = new Gestione(macchine, 700);
        gara.start();

        try {
            gara.join();
        } catch (InterruptedException e) {
            System.out.println("Errore nell'attesa del thread della gara.");
        }

        if ("Chiuchetti".equalsIgnoreCase(gara.getColoreVincitore())) {
            System.out.println("\n🎉 REQUISITO SODDISFATTO! PROF HA VINTO!!!");
            System.out.println("📅 DATA DELL'EVENTO RILEVATA: Sabato 13 Giugno");
        } else {
            System.out.println("\n❌ REQUISITO FALLITO: prof mi dispiace ma NON ha vinto.");
            System.out.println("🔒 La data dell'evento rimarrà nascosta per sempre (o finché non riavvia il gioco e vince lei).");
        }

        System.out.println("\nPremere INVIO per uscire...");
        Scanner input = new Scanner(System.in);
        input.nextLine();
        input.close();
    }
}
