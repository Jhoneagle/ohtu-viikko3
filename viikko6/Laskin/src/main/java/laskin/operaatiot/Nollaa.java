/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin.operaatiot;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import laskin.Sovelluslogiikka;

/**
 *
 * @author Johneagle
 */
public class Nollaa extends Operaatio {
    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        edellinenArvo.push(sovellus.tulos());
        
        sovellus.nollaa();
        syotekentta.setText("");
        tuloskentta.setText("0");

        nollaa.disableProperty().set(true);
        undo.disableProperty().set(false);
    }
}
