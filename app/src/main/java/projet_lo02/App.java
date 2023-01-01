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
            Partie nouvellePartie = new Partie();
            switch(input){
                case "C" :
                case "c" :
                    nouvellePartie.initialiserJoueurs();
                    nouvellePartie.parametrageDesEquipes();
                    break;
                case "G" :
                case "g" :
                    GUI guiJoueur1 = new GUI(nouvellePartie.getJoueur1());
                    new ControllerGUI(guiJoueur1, nouvellePartie);
                    break;
                default :
                    System.out.println("Erreur : valeur entrée non valide");
                    Utility.sleep(2500);
            }
        }
    }

}
