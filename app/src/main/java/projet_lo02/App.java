package projet_lo02;

import projet_lo02.model.Utility;
import projet_lo02.model.Partie;
import projet_lo02.view.GUI;

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
                    new GUI(nouvellePartie.getJoueur1());
                    break;
                default :
                    System.out.println("Erreur : valeur entrée non valide");
                    Utility.sleep(2500);
            }
        }
    }

}
