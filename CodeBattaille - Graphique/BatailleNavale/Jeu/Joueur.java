package BatailleNavale.Jeu;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static BatailleNavale.Jeu.Grille.GRID_SIZE;


public class Joueur extends JFrame implements ActionListener {

    private JButton shoot;
    private JToggleButton H;
    private JToggleButton V;
    private JToggleButton sub;
    private JToggleButton des;
    private JToggleButton battles;
    private JToggleButton carrierB;
    private JToggleButton cruiserB;

    private JToggleButton Playing;
    private JButton place;
    private String name;
    private boolean isPlaying= false;
    private int score;
    boolean DesExist;
    boolean CruExist;
    boolean SubExist;
    boolean BattExist;
    boolean CarrExist;

    boolean hasShooted;

    int destroyer=1;
    int cruiser=1;
    int submarine=1;
    int battleship=1;
    int carrier=1;
    boolean Init = false;

    private Grille grid;



    public boolean isDesExist() {
        return DesExist;
    }

    public void setDesExist() {
        DesExist = false;
    }

    public boolean isCruExist() {
        return CruExist;
    }

    public void setCruExist() {
        CruExist = false;
    }

    public boolean isSubExist() {
        return SubExist;
    }

    public void setSubExist() {
        SubExist = false;
    }

    public boolean isBattExist() {
        return BattExist;
    }

    public void setBattExist() {
        BattExist = false;
    }

    public boolean isCarrExist() {
        return CarrExist;
    }

    public void setCarrExist() {
        CarrExist = false;
    }

    ButtonGroup Boat;

    ButtonGroup groupe;

    public Joueur(String name) {
        super(name);
        this.name = name;
        isPlaying = false;
        score = 0;
        grid = new Grille();
        grid.setEtatInit();
        add(grid);

        DesExist=true;
        CruExist=true;
        SubExist=true;
        BattExist=true;
        CarrExist=true;
        hasShooted=false;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dim.width/2,dim.height);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        shoot =new JButton("Shoot");
        H = new JToggleButton("Horrizontal");
        V =new JToggleButton("Vertical");
        sub =new JToggleButton("submarine");
        des =new JToggleButton("destroyer");
        battles =new JToggleButton("battleship");
        carrierB =new JToggleButton("carrier");
        cruiserB =new JToggleButton("cruiser");
        place = new JButton("Place");
        Playing = new JToggleButton(" ");

        groupe = new ButtonGroup();
        groupe.add(H);
        groupe.add(V);
        Boat=new ButtonGroup();
        Boat.add(carrierB);
        Boat.add(cruiserB);
        Boat.add(battles);
        Boat.add(sub);
        Boat.add(des);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(shoot);
        menuBar.add(H);
        menuBar.add(V);
        menuBar.add(des);
        menuBar.add(cruiserB);
        menuBar.add(sub);
        menuBar.add(battles);
        menuBar.add(carrierB);
        menuBar.add(place);
        menuBar.add(Playing);

        Playing.setEnabled(false);

        shoot.addActionListener(this);
        H.addActionListener(this);
        V.addActionListener(this);
        des.addActionListener(this);
        cruiserB.addActionListener(this);
        sub.addActionListener(this);
        battles.addActionListener(this);
        carrierB.addActionListener(this);
        place.addActionListener(this);


        setJMenuBar(menuBar);
        setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);

        this.setVisible(true);
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(){
        isPlaying = !isPlaying;
    }

    public boolean getInit(){return Init;}

    public void setInit(){Init=true;}

    public boolean getHasShooted(){return hasShooted;}

    public void setHasShooted(boolean tir){hasShooted=tir;}
    public Grille getGrid() {
        return grid;
    }

    public void setNewGrid(Grille newGrid){
        grid = newGrid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int newscore){
        score=newscore;
    }

    public void setName(String newname){
        name= newname;
    }

    public String getName() {
        return name;
    }

    public String WhatBoatButtonSelected(){
        if (carrierB.isSelected()){
            return "carrier";
        }else if(cruiserB.isSelected()){
            return "cruiser";
        }else if(battles.isSelected()){
            return "battleship";
        }else if (sub.isSelected()){
            return "submarine";
        }else if(des.isSelected()){
            return "destroyer";
        }
        return "";
    }

    public boolean IsABoatButtonSelected(){
        if (carrierB.isSelected()){
            return true;
        }else if(cruiserB.isSelected()){
            return true;
        }else if(battles.isSelected()){
            return true;
        }else if (sub.isSelected()){
            return true;
        }else if(des.isSelected()){
            return true;
        }
        return false;
    }

    public boolean IsAnOrientationSelected(){
        if (V.isSelected()){
            return true;
        }else if (H.isSelected()){
            return true;
        }
        return false;
    }

    public String WhatOrientationSelected(){
        if (V.isSelected()){
            return "V";
        }else if (H.isSelected()){
            return "H";
        }
        return "";
    }

    public boolean IsABoatAvailable(String name){
        if (name == "destroyer" && destroyer==1){
            return true;
        }
        if (name == "submarine" && submarine == 1){
            return true;
        }
        if (name == "cruiser" && cruiser == 1){
            return true;
        }
        if (name == "battleship" && battleship == 1){
            return true;
        }
        if (name == "carrier" && carrier == 1){
            return true;
        }
        return false;
    }

    public void deleteaBoat(String name){
        if (name == "destroyer"){
            destroyer=0;
        }
        if (name == "submarine"){
            submarine = 0;
        }
        if (name == "cruiser"){
            cruiser= 0;
        }
        if (name == "battleship"){
            battleship = 0;
        }
        if (name == "carrier"){
            carrier = 0;
        }
        if(name == ""){
            System.out.println("Placer a un endroit disponible");
        }
    }

    public void UnselectButton(){
        place.setEnabled(false);
        place.setSelected(false);
        H.setEnabled(false);
        V.setEnabled(false);
        des.setEnabled(false);
        cruiserB.setEnabled(false);
        sub.setEnabled(false);
        battles.setEnabled(false);
        carrierB.setEnabled(false);
        H.setSelected(false);
        V.setSelected(false);
        des.setSelected(false);
        cruiserB.setSelected(false);
        sub.setSelected(false);
        battles.setSelected(false);
        carrierB.setSelected(false);
    }

    public boolean AreAllTheBoatPlaced(){
        if (cruiser==0 && carrier == 0 && submarine == 0 && battleship == 0 && destroyer == 0){
            return true;
        }
        return false;
    }

    public String IsABoatDead(){
        int DestroyerCompte=0;
        int CruiserCompte=0;
        int SubmarineCompte=0;
        int BattleshipCompte=0;
        int CarrierCompte=0;
        for(int x=0;x != GRID_SIZE;x++){
            for(int y=0;y!=GRID_SIZE;y++){
                if(!(Objects.equals(grid.getToggleButtonsAppartenance(x, y), "Mer"))){
                    if ((Objects.equals(grid.getToggleButtonsAppartenance(x, y), "destroyer"))){
                        DestroyerCompte++;
                    }
                    if ((Objects.equals(grid.getToggleButtonsAppartenance(x, y), "cruiser"))){
                        CruiserCompte++;
                    }
                    if ((Objects.equals(grid.getToggleButtonsAppartenance(x, y),"submarine" ))){
                        SubmarineCompte++;
                    }
                    if ((Objects.equals(grid.getToggleButtonsAppartenance(x, y),"battleship" ))){
                        BattleshipCompte++;
                    }
                    if ((Objects.equals(grid.getToggleButtonsAppartenance(x, y), "carrier"))){
                        CarrierCompte++;
                    }
                }

            }
        }
        if(BattleshipCompte==0 && BattExist){
            System.out.println("Battleship coulé ! +200");
            setBattExist();
            return "battleship";
        }
        if(CruiserCompte==0 && CruExist){
            System.out.println("Cruiser coulé ! +100");
            setCruExist();
            return "cruiser";
        }
        if (SubmarineCompte== 0 && SubExist){
            System.out.println("Submarine coulé ! +100");
            setSubExist();
            return "submarine";
        }
        if (DestroyerCompte==0 && DesExist){
            System.out.println("Destroyer coulé ! +50");
            setDesExist();
            return "destroyer";
        }
        if (CarrierCompte==0 && CarrExist){
            System.out.println("Carrier coulé ! +250");
            setCarrExist();
            return "carrier";
        }
        return "no";
    }

    private Timer Isplay=new Timer(50,this);

    private void setIcon(){
        if (isPlaying){
            Playing.setBackground(Color.GREEN);
        }
        else {
            Playing.setBackground(Color.RED);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Isplay){
            setIcon();
        }

        if (!Init)
            if (e.getSource() == place && !AreAllTheBoatPlaced()){
                if (IsABoatButtonSelected()){
                    if (IsAnOrientationSelected()){
                        if(grid.IsAButtonSelected()){
                            if (IsABoatAvailable(WhatBoatButtonSelected())){
                                String Delete = grid.addBoat(WhatBoatButtonSelected(),grid.getSelectedButtonX(),grid.getSelectedButtonY(),WhatOrientationSelected());
                                deleteaBoat(Delete);
                                grid.setEtatInit();
                            }
                        }
                    }
                }
            }else if (e.getSource() == place && AreAllTheBoatPlaced()){
                setInit();
                Isplay.start();
            }
        if (e.getSource()==shoot){
            if(isPlaying){
                    if (grid.IsAButtonSelected()){
                        Bateau.Shoot(grid,grid.getSelectedButtonX(),grid.getSelectedButtonY());
                        setHasShooted(true);
                    }
                }
            }
        }
    }