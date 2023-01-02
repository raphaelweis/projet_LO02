package projet_lo02;

import projet_lo02.model.Utility;
import projet_lo02.model.Partie;
import projet_lo02.view.GUI;
import projet_lo02.controller.ControllerGUI;

public class App {

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
                    partieEnCours.initialiserJoueurs();
                    partieEnCours.parametrageDesEquipes();
                    break;
                case "G" :
                case "g" :
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
                    break;
                default :
                    System.out.println("Erreur : valeur entrée non valide");
                    Utility.sleep(2500);
            }
        }
    }

}
