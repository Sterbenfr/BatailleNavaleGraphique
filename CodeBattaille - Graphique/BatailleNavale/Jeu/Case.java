package BatailleNavale.Jeu;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Case extends JToggleButton {
    private String Etat;
    private final int x;
    private final int y;

    private String appartenance;

    public Case(String Etat, int x, int y,String appartien) {
        this.x = x;
        this.y = y;
        this.appartenance=appartien;
        setBackground(Color.cyan);
    }

    public String toString() {
        if (Objects.equals(Etat, "water")) {
            return "| M ";
        }
        if (Objects.equals(Etat, "Bateau")) {
            return "| B ";
        }
        if (Objects.equals(Etat, "TirMort")) {
            return "| ° ";
        }
        if (Objects.equals(Etat, "SunkenShip")) {
            return "| * ";
        } else {
            return "| ! ";
        }
    }

    public void setEtatInit(String etat) {
        Etat = etat;
        setBackground(Color.cyan);
        if (Etat == "water"){
            setBackground(Color.cyan);
        }
        if (Etat == "Bateau"){
            setBackground(Color.black);
        }
        if (Etat == "TirMort"){
            setBackground(Color.darkGray);
        }
        if (Etat == "Sunkenship"){
            setBackground(Color.RED);
        }
    }

    public void setEtatGame(String etat) {
        Etat = etat;
        setBackground(Color.cyan);
        if (Etat == "water"){
            setBackground(Color.cyan);
        }
        if (Etat == "Bateau"){
            setBackground(Color.cyan);
        }
        if (Etat == "TirMort"){
            setBackground(Color.darkGray);
        }
        if (Etat == "SunkenShip"){
            setBackground(Color.RED);
        }
    }

    public String getEtat() {
        return Etat;
    }

    public int getX() {
        return x;
    }

    public void setAppartenance(String appartenance1){
        appartenance=appartenance1;
    }
    public String getAppartenance(){
        return appartenance;
    }

    public int getY() {
        return y;
    }
}