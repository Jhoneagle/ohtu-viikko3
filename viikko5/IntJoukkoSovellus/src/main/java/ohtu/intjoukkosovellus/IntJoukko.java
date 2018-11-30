
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        init(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        
        init(kapasiteetti, OLETUSKASVATUS);
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain :D
        }
        
        init(kapasiteetti, kasvatuskoko);
    }

    private void init(int kapasiteetti, int kasvatuskoko) {
        ljono = new int[kapasiteetti];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    } 
    
    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            ljono[alkioidenLkm] = luku;
            alkioidenLkm++;
            
            if (alkioidenLkm % ljono.length == 0) {
                int[] taulukkoOld = ljono;
                ljono = new int[alkioidenLkm + kasvatuskoko];
                kopioiTaulukko(taulukkoOld, ljono);
            }
            
            return true;
        }
        
        return false;
    }
    
    private int hae(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return i;
            }
        }
        
        return -1;
    }
    
    public boolean kuuluu(int luku) {
        return hae(luku) >= 0;
    }

    public boolean poista(int luku) {
        int kohta = hae(luku);
        
        if (kohta != -1) {
            for (int j = kohta; j < alkioidenLkm - 1; j++) {
                int apu = ljono[j];
                ljono[j] = ljono[j + 1];
                ljono[j + 1] = apu;
            }
            
            alkioidenLkm--;
            return true;
        }

        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else {
            String tuotos = "{";
            
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += ljono[i] + ", ";
            }
            
            return tuotos + ljono[alkioidenLkm - 1] + "}";
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        
        return taulu;
    }
   
    public static IntJoukko joukkoOperaatiot(IntJoukko a, IntJoukko b, String operaatio) {
        int[] bTaulu = b.toIntArray();
        int[] aTaulu = a.toIntArray();
        
        if (operaatio.contains("yhdiste")) {
            return yhdiste(aTaulu, bTaulu, new IntJoukko());
        } else if (operaatio.contains("leikkaus")) {
            return leikkaus(aTaulu, bTaulu, new IntJoukko());
        } else if (operaatio.contains("erotus")) {
            return erotus(aTaulu, bTaulu, new IntJoukko());
        } else {
            return null;
        }
    }
    
    public static IntJoukko yhdiste(int[] aTaulu, int[] bTaulu, IntJoukko x) {
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        
        return x;
    }

    public static IntJoukko leikkaus(int[] aTaulu, int[] bTaulu, IntJoukko x) {
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    x.lisaa(bTaulu[j]);
                }
            }
        }
        
        return x;
    }
    
    public static IntJoukko erotus (int[] aTaulu, int[] bTaulu, IntJoukko x) {
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        
        for (int i = 0; i < bTaulu.length; i++) {
            x.poista(i);
        }
 
        return x;
    }
}