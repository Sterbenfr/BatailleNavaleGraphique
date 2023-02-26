package BatailleNavale.Jeu;

import java.util.Objects;

public class Bateau {
    private static int x;
    private static int y;
    private static int size = 0;
    private final String name;
    private static String orientation = null;

    public Bateau(String name, int x, int y, String orientation) {
        this.name = name;
        Bateau.x = x;
        Bateau.y = y;
        Bateau.orientation = orientation;
        size = setSizeFromName(name);
    }


    public int getY() {
        return y;
    }

    public String getOrientation() {
        return orientation;
    }

    public static boolean ThereIsAlreadyABoatH(Grille grid, int posx, int posy, int size1){
        for (int i = posy; i < posy + size1; i++){
            if (grid.getToggleButtonsEtat(posx,i)=="Bateau"){
                return true;
            }
        }
        return false;
    }
    public static boolean ThereIsAlreadyABoatV(Grille grid, int posx, int posy, int size2){
        for (int i = posx; i < posx + size2; i++){
            if (grid.getToggleButtonsEtat(i,posy)=="Bateau"){
                return true;
            }
        }
        return false;
    }
    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    static int setSizeFromName(String name){
        return switch (name) {
            case "destroyer" -> 2;
            case "submarine" -> 3;
            case "cruiser" -> 3;
            case "battleship" -> 4;
            case "carrier" -> 5;
            default -> 0;
        };
    }

    private static boolean Unusable(String name,int des,int sub, int cruis,int ship, int carrier){
        if(Objects.equals(name, "destroyer") && des==1){
            return false;
        }else{
            if(Objects.equals(name, "submarine") && sub == 1){
                return false;
            }else {
                if(Objects.equals(name, "cruiser") && cruis==1){
                    return false;
                }else{
                    if(Objects.equals(name, "battleship") && ship==1){
                        return false;
                    }else{
                        if(Objects.equals(name, "carrier") && carrier==1){
                            return false;
                        }else{
                            return true;}
                    }
                }
            }
        }
    }

    public  static boolean IsOnNameList(String name){
        return Objects.equals(name, "destroyer") || Objects.equals(name, "submarine") ||
                Objects.equals(name, "cruiser") || Objects.equals(name, "battleship") || Objects.equals(name, "carrier");
    }
    public static void Shoot(Grille tabCase, int posX, int posY){
        String etat =tabCase.getToggleButtonsEtat(posX,posY);
        if (Objects.equals(etat, "water")){
            tabCase.setToggleButtons(posX,posY,"TirMort","Mer");
        }else if(Objects.equals(etat, "Bateau")){
            System.out.println("Touché !");
            tabCase.setToggleButtons(posX,posY,"SunkenShip","Mer");
        }
    }
}

