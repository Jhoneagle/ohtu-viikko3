package ohtu.kivipaperisakset.modes;

public class KPSPelaajaVsPelaaja extends KPS {
    @Override
    protected String tokanPelaajanSiirto() {
        System.out.print("Toisen pelaajan siirto: ");
        String tokanSiirto = scanner.nextLine();
        return tokanSiirto;
    }

    @Override
    protected void annaSiirto(String siirto) {
        // ei tehdä mitään 
    }
}