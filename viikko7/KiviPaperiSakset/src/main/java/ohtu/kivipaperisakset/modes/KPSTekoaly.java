package ohtu.kivipaperisakset.modes;

import ohtu.kivipaperisakset.Tekoaly;

public class KPSTekoaly extends KPS {
    private final Tekoaly tekoaly;

    public KPSTekoaly() {
        tekoaly = new Tekoaly();
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