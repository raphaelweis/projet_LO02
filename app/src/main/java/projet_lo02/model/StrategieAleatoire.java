package projet_lo02.model;

import java.util.Random;

/**
 * Classe stratégie aléatoire
 * @author Julian Marques
 * @author Raphaël Weis
 */
public class StrategieAleatoire implements Strategie{

    /**
     * Méthode pour obtenir le nom de la stratégie
     * @return "aléatoire"
     */
    public String getNomStrategie(){
            return "Aléatoire";
    }

    /**
     * Méthode pour exécuter la stratégie d'un étudiant
     * @param actionneur étudiant exécutant sa stratégie
     */
    public void executerStrategie(Etudiant actionneur){

        Random rnd = new Random();
        int valeurAleatoire = rnd.nextInt(2);
        if(valeurAleatoire == 0){
            Attaque atk = new Attaque();
            atk.executerStrategie(actionneur);
        } else if(valeurAleatoire == 1){
            Defense def = new Defense();
            def.executerStrategie(actionneur);
        }
    }
}
