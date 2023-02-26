package BatailleNavale.Jeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static BatailleNavale.Jeu.Grille.*;
import static BatailleNavale.Jeu.Bateau.*;
public class Jeu extends JFrame implements ActionListener {

    private JTextField textFieldP1;
    private JTextField textFieldP2;

    JButton placer;

    public Jeu() {
        super("Launch Game");
        setSize(300, 200);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));


        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 200));

        JLabel TextP1 = new JLabel("Nom Joueur 1");
        JLabel TextP2 = new JLabel("Nom Joueur 2");
        textFieldP1 = new JTextField();
        panel.add(textFieldP1, BorderLayout.CENTER);
        panel.add(TextP1,BorderLayout.NORTH);
        textFieldP2 = new JTextField();
        panel2.add(textFieldP2, BorderLayout.CENTER);
        panel2.add(TextP2,BorderLayout.NORTH);

        placer = new JButton("Placer");
        placer.addActionListener(this);
        container.add(placer);

        container.add(panel);
        container.add(panel2);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(container);
        setVisible(true);
    }


    Joueur Player1;
    Joueur Player2;
    JButton play;
    public Timer timer = new Timer(500, this);

    public Timer timerGame = new Timer(500,this);

    public Timer timerAffichage = new Timer(10000,this);

    public JFrame Play;

    public JFrame EndGame;

    public void Game(String P1, String P2) {
        Player1 = new Joueur(P1);
        Player2 = new Joueur(P2);


        Play = new JFrame();
        EndGame = new JFrame();
        EndGame.setSize(200,200);
        EndGame.setResizable(false);
        EndGame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        play = new JButton("Play");
        play.addActionListener(this);
        Play.add(play);
        Play.setSize(300, 200);
        Play.setVisible(true);
        Play.setDefaultCloseOperation(EXIT_ON_CLOSE);
        timer.start();
        timerGame.start();
        timerAffichage.start();

    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            if (Player1.getInit() && Player2.getInit()){
                Player1.getGrid().setEtatGame();
                Player2.getGrid().setEtatGame();
            }else {
            Player1.getGrid().setEtatInit();
            Player2.getGrid().setEtatInit();
        }
        }


        if (e.getSource() == placer) {
            String P1 = textFieldP1.getText();
            String P2 = textFieldP2.getText();
            Game(P1, P2);
            setVisible(false);
        }
        if (e.getSource() == play) {
            if (Player1.getInit() && Player2.getInit()) {
                Random rand = new Random();
                int coin = rand.nextInt(1000) % 2;

                if (coin == 1) {
                    Player1.setPlaying();
                } else {
                    Player2.setPlaying();
                }

                Player1.getGrid().setEtatGame();
                Player2.getGrid().setEtatGame();

                Grille Joueur1 = Player1.getGrid();
                Grille Joueur2 = Player2.getGrid();

                Player1.setNewGrid(Joueur2);

                Player2.setNewGrid(Joueur1);

                Player1.UnselectButton();
                Player2.UnselectButton();

                Play.dispose();

            }
        }

        if(e.getSource() == timerAffichage){
            Player1.getGrid().ClickButton();
            Player2.getGrid().ClickButton();
        }

        if (e.getSource() == timerGame){
            if (Player1.getInit() && Player2.getInit()){
                if (Player1.getScore() < 700 && Player2.getScore() < 700) {
                    if (Player1.isPlaying()) {
                        if (Player1.getHasShooted()) {
                            String Dead = Player1.IsABoatDead();
                            switch (Dead) {
                                case "destroyer":
                                    Player1.setDesExist();
                                    Player1.setScore(Player1.getScore() + 50);
                                    break;
                                case "submarine":
                                    Player1.setSubExist();
                                    Player1.setScore(Player1.getScore() + 100);
                                    break;
                                case "cruiser":
                                    Player1.setCruExist();
                                    Player1.setScore(Player1.getScore() + 100);
                                    break;
                                case "battleship":
                                    Player1.setBattExist();
                                    Player1.setScore(Player1.getScore() + 200);
                                    break;
                                case "carrier":
                                    Player1.setCarrExist();
                                    Player1.setScore(Player1.getScore() + 250);
                                    break;
                                case "no":
                                    break;
                            }
                            Player1.setPlaying();
                            Player2.setPlaying();
                            Player1.setHasShooted(false);
                        }
                    }

                    if (Player2.isPlaying()) {
                        if (Player2.getHasShooted()) {
                            String Dead = Player2.IsABoatDead();
                            switch (Dead) {
                                case "destroyer":
                                    Player2.setDesExist();
                                    Player2.setScore(Player2.getScore() + 50);
                                    break;
                                case "submarine":
                                    Player2.setSubExist();
                                    Player2.setScore(Player2.getScore() + 100);
                                    break;
                                case "cruiser":
                                    Player2.setCruExist();
                                    Player2.setScore(Player2.getScore() + 100);
                                    break;
                                case "battleship":
                                    Player2.setBattExist();
                                    Player2.setScore(Player2.getScore() + 200);
                                    break;
                                case "carrier":
                                    Player2.setCarrExist();
                                    Player2.setScore(Player2.getScore() + 250);
                                    break;
                                case "no":
                                    break;
                            }
                            Player1.setPlaying();
                            Player2.setPlaying();
                            Player2.setHasShooted(false);
                        }
                    }
                }else{
                    if (Player1.getScore() == 700) {
                        JLabel Fin = new JLabel("Bravo "+Player1.getName()+". Bg !");
                        Fin.setSize(150,150);
                        Fin.setHorizontalAlignment(SwingConstants.CENTER);
                        Player1.dispose();
                        Player2.dispose();

                        EndGame.add(Fin);
                        EndGame.setVisible(true);
                    }
                    if (Player2.getScore() == 700) {
                        JLabel Fin = new JLabel("Bravo "+Player2.getName()+". Bg !");
                        Fin.setSize(150,150);
                        Fin.setHorizontalAlignment(SwingConstants.CENTER);
                        Player1.dispose();
                        Player2.dispose();

                        EndGame.add(Fin);

                        EndGame.setVisible(true);
                    }
                }
            }
        }
    }
}