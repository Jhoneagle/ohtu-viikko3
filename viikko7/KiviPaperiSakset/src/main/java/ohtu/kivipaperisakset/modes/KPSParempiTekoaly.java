package ohtu.kivipaperisakset.modes;

import ohtu.kivipaperisakset.TekoalyParannettu;

public class KPSParempiTekoaly extends KPS {
    private final TekoalyParannettu tekoaly;

    public KPSParempiTekoaly() {
        tekoaly = new TekoalyParannettu(20);
    }
    
    @Override
    protected String tokanPelaajanSiirto() {
        String tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        return tokanSiirto;
    }

    @Override
    protected void annaSiirto(String siirto) {
        tekoaly.asetaSiirto(siirto);
    }
}
