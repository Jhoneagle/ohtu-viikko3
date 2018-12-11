package ohtu.kivipaperisakset.modes;

import java.util.Scanner;
import ohtu.kivipaperisakset.Tuomari;

public abstract class KPS {
    protected final Scanner scanner = new Scanner(System.in);
    
    public static void kpsPelaajaVsPelaajaGo() {
        print();
        new KPSPelaajaVsPelaaja().pelaa();
    }
    
    public static void kpsTekoalyGo() {
        print();
        new KPSTekoaly().pelaa();
    }
    
    public static void kpsParempiTekoalyGo() {
        print();
        new KPSParempiTekoaly().pelaa();
    }
    
    public void pelaa() {
        Tuomari tuomari = new Tuomari();
        
        System.out.print("Ensimmäisen pelaajan siirto: ");
        String ekanSiirto = scanner.nextLine();
        String tokanSiirto = tokanPelaajanSiirto();
        
        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();

            System.out.print("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = scanner.nextLine();

            tokanPelaajanSiirto();
            annaSiirto(ekanSiirto);
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }
    
    protected abstract String tokanPelaajanSiirto();
    
    protected abstract void annaSiirto(String siirto);
    
    private boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
    
    private static void print() {
        System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
    }
}
