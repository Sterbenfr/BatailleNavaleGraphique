package BatailleNavale.Jeu;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.Map;
import BatailleNavale.Jeu.Case;

import static BatailleNavale.Jeu.Bateau.*;

public class Grille extends JPanel {
    static final int GRID_SIZE = 10;
    private Case[][] toggleButtons=new Case[GRID_SIZE][GRID_SIZE];


    public Grille(){
        ButtonGroup gridCase = new ButtonGroup();
        this.setLayout(new GridLayout(GRID_SIZE,GRID_SIZE));
        for (int x = 0; x != GRID_SIZE; x++) {
            for (int y = 0; y != GRID_SIZE; y++) {
                toggleButtons[x][y] = new Case("water",x,y,"Mer");
                toggleButtons[x][y].setEtatInit("water");
                gridCase.add(toggleButtons[x][y]);
                add(toggleButtons[x][y]);
            }
        }
        toggleButtons[0][0].setSelected(true);
    }

    public JToggleButton[][] getToggleButtons(){
            return toggleButtons;
    }



    public void setEtatGame() {
        for (int z = 0; z < GRID_SIZE; z++) {
            for (int y = 0;y<GRID_SIZE;y++){
                toggleButtons[z][y].setEtatGame(toggleButtons[z][y].getEtat());
            }
        }
    }

    public void ClickButton(){
        JToggleButton Temp = getSelectedButton();
        for (int z = 0; z < GRID_SIZE; z++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                toggleButtons[z][y].setSelected(true);
                toggleButtons[z][y].setSelected(false);
            }
        }
        Temp.setSelected(true);
    }
    public void setEtatInit() {
        for (int z = 0; z < GRID_SIZE; z++) {
            for (int y = 0;y<GRID_SIZE;y++){
                toggleButtons[z][y].setEtatInit(toggleButtons[z][y].getEtat());
            }
        }
    }
    public String getToggleButtonsEtat(int x,int y){
        return toggleButtons[x][y].getEtat();
    }

    public String getToggleButtonsAppartenance(int x,int y){
        return toggleButtons[x][y].getAppartenance();
    }

    public void setToggleButtons(int x, int y,String Etat,String Appart){
        toggleButtons[x][y].setEtatGame(Etat);
        toggleButtons[x][y].setAppartenance(Appart);
    }

    public void setToggleButtonsInit(int x, int y,String Etat,String Appart){
        toggleButtons[x][y].setEtatInit(Etat);
        toggleButtons[x][y].setAppartenance(Appart);
    }
    public JToggleButton getSelectedButton() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (toggleButtons[i][j].isSelected()) {
                    return toggleButtons[i][j];
                }
            }
        }
        return null;
    }
    public int getSelectedButtonX() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (toggleButtons[i][j].isSelected()) {
                    return i;
                }
            }
        }
        return 0;
    }
    public int getSelectedButtonY() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (toggleButtons[i][j].isSelected()) {
                    return j;
                }
            }
        }
        return 0;
    }

    public boolean IsAButtonSelected() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (toggleButtons[i][j].isSelected()) {
                    return true;
                }
            }
        }
        return false;
    }



    public String addBoat(String name, int x, int y, String Orientation) {
        int maxX=10;
        int maxY=10;
        int size = setSizeFromName(name);
        if(Objects.equals(Orientation, "H") || Objects.equals(Orientation, "h")){
            maxY=10-size;
        }
        if(Objects.equals(Orientation, "V") || Objects.equals(Orientation, "v")) {
            maxX=10-size;
        }
        if (x >maxX){
            return "";
        }
        if (y>maxY){
            return "";
        }
        if ((Objects.equals(Orientation, "V") || Objects.equals(Orientation, "v")) && !ThereIsAlreadyABoatV(this,x,y,size))  {
            for (int i = x; i < x + size; i++) {
                this.setToggleButtonsInit(i,y,"Bateau",name);
            }
        }
        else if ((Objects.equals(Orientation, "H") || Objects.equals(Orientation, "h")) && !ThereIsAlreadyABoatH(this,x,y,size)) {
            for (int i = y; i < y + size; i++) {
                this.setToggleButtonsInit(x,i,"Bateau",name);
            }
        }
        else {
            return "";
        }
        return name;
    }
}