package projet_lo02;

import java.util.Random;

public class StrategieAleatoire implements Strategie{
    
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
