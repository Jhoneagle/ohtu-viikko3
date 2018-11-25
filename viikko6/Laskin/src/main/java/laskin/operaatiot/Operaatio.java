/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin.operaatiot;

import java.util.Stack;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import laskin.Sovelluslogiikka;
import laskin.interfaces.Komento;

/**
 *
 * @author Johneagle
 */
public abstract class Operaatio implements Komento {
    protected TextField tuloskentta;
    protected TextField syotekentta;
    protected Button nollaa;
    protected Button undo;
    protected Sovelluslogiikka sovellus;
    protected static Stack<Integer> edellinenArvo;

    public Operaatio(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
        initStack();
    }
    
    private static void initStack() {
        if (edellinenArvo == null) {
            edellinenArvo = new Stack<>();
        }
    }
    
    @Override
    public void peru() {
        if (!edellinenArvo.empty()) {
            int vanha = edellinenArvo.pop();
            sovellus.palaa(vanha);
            tuloskentta.setText("" + sovellus.tulos());
        } else {
            undo.disableProperty().set(true);
        }
    }
}
