import java.util.ArrayList;

public class Gara {
    public static void main(String[] args) {
        // Avviso iniziale obbligatorio
        System.out.println("==================================================================");
        System.out.println("⚠️ ATTENZIONE: Finché la macchina 'Chiuchetti' non vince la gara,");
        System.out.println("non verrà mostrato nulla e la data dell'evento rimarrà segreta!");
        System.out.println("==================================================================\n");

        Auto a = new Auto("Rossa");
        Auto a1 = new Auto("Blu");
        Auto a2 = new Auto("Verde");
        
        // AGGIUNTA: Creazione della macchina Chiuchetti richiesta
        Auto chiuchetti = new Auto("Chiuchetti"); 

        ArrayList<Auto> macchine = new ArrayList<Auto>();
        macchine.add(a1);
        macchine.add(a2);
        macchine.add(a);
        
        // AGGIUNTA: Inserimento di Chiuchetti nella lista della gara
        macchine.add(chiuchetti); 

        Gestione gara = new Gestione(macchine, 700); // distanza vittoria
        gara.start();

        // AGGIUNTA: Attendiamo il termine della gara prima di verificare la data
        try {
            gara.join(); 
        } catch (InterruptedException e) {
            System.out.println("Errore nell'attesa del thread della gara.");
        }

        // AGGIUNTA: Controllo divertente sulla vittoria di Chiuchetti
        if ("Chiuchetti".equals(gara.getColoreVincitore())) {
            System.out.println("\n🎉 REQUISITO SODDISFATTO! La macchina Chiuchetti ha vinto!");
            System.out.println("📅 DATA DELL'EVENTO RILEVATA: Sabato 13 Giugno");
        } else {
            System.out.println("\n❌ REQUISITO FALLITO: Chiuchetti NON ha vinto.");
            System.out.println("🔒 La data dell'evento rimarrà nascosta per sempre (o finché non riavvii e vince lei).");
        }
    }
}
