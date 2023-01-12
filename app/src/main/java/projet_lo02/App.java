package projet_lo02;

import projet_lo02.model.Utility;
import projet_lo02.model.Partie;
import projet_lo02.view.GUI;
import projet_lo02.controller.ControllerGUI;

/**
 * Classe App
 * @author Julian Marques
 * @author Raphaël Weis
 */
public class App {

    private static int mode;

    /**
     * Méthode main du jeu qui lance la partie
     */
    public static void main(String[] args) {
        while(true){
            Utility.clearTerminal();
            System.out.println("Bienvenue !\n");
            System.out.println("Comment voulez-vous interagir avec le programme ?");
            Utility.jumpLines(1);
            System.out.println("(C) : Via l'invite de commandes uniquement");
            System.out.println("(G) : Via une interface graphique (GUI)");
            System.out.println("(Q) : Quitter le programme");
            String input = Utility.promptString();
            Partie partieEnCours = new Partie();
            switch(input){
                case "C" :
                case "c" :
                    mode = 0;
                    break;
                case "G" :
                case "g" :
                    mode = 1;
                    break;
                default :
                    System.out.println("Erreur : valeur entrée non valide");
                    Utility.sleep(2500);
            }
            if(mode == 0){
                partieEnCours.initialiserJoueurs();
                partieEnCours.parametrageDesEquipes();
            } else{
                GUI guiJoueur1 = new GUI(partieEnCours.getJoueur1());
                new ControllerGUI(guiJoueur1, partieEnCours);
                //wait for the first gui to be closed before opening the second one
                while(guiJoueur1.getMainFrame().isVisible() == true){
                    Utility.sleep(1000);
                }
                GUI guiJoueur2 = new GUI(partieEnCours.getJoueur2());
                new ControllerGUI(guiJoueur2, partieEnCours);
                //wait for the second gui to be closed before exiting configuration phase
                while(guiJoueur2.getMainFrame().isVisible() == true){
                    Utility.sleep(1000);
                }
                partieEnCours.initialiserCombats();
                boolean exit = false;
                while(exit == false){
                    Utility.clearTerminal();
                    Utility.jumpLines(1);
                    if(partieEnCours.getJoueur1().getNombreZonesControlees() >= 3){
                        System.out.println(partieEnCours.getJoueur1().getPseudo() + " a gagné la partie !");
                    } else {
                        System.out.println(partieEnCours.getJoueur1().getPseudo() + " a gagné la partie !");
                    }
                    Utility.jumpLines(1);
                    System.out.println("Voulez-vous recommencer une partie ? (O) : oui || (N) : non");
                    String input2 = Utility.promptString();
                    switch(input2){
                        case "O" :
                        case "o" :
                        case "Y" :
                        case "y" :
                            exit = true;
                            break;
                        case "N" :
                        case "n" :
                            System.out.println("Fin du programme...");
                            System.exit(0);
                        default :
                            System.out.println("Erreur : valeur entrée non valide");
                            Utility.sleep(2500);
                            break;
                    }
                }
            }
        }
    }

}
