package projet_lo02.model;

public class Attaque implements Strategie {

    private final int degatDeReference = 10;

    public void executerStrategie(Etudiant actionneur){

        //System.out.println("La stratégie Attaque vient d'être utilisée !");

        double x = Utility.getRandomDouble(0, 100, true, true);
        double y = Utility.getRandomDouble(0, 1, false, true);

        Etudiant cible;
        //si l'attaquant est le joueur 1, cible = joueur 2, sinon cible = joueur 1
        if(actionneur.getJoueur().equals(actionneur.getJoueur().getPartie().getJoueur1())){
            cible = actionneur.getZone().etudiantLePlusFaibleAdverse(actionneur);
        } else {
            cible = actionneur.getZone().etudiantLePlusFaibleAdverse(actionneur);
        }

        //System.out.println("cible : " + cible.getIdEtudiant() + " - " + cible.getJoueur().getPseudo());

        int coefficientDegat = Math.max(0, Math.min(100, 10 * actionneur.getForce() - 5 * cible.getResistance()));
        int perteCreditsNette = Utility.getPartieEntiere(y * (1 + coefficientDegat) * degatDeReference);

        if(x <= 40 + 3 * actionneur.getDexterite() && perteCreditsNette != 0){ //attaque réussie
            if(cible.getCredits() - perteCreditsNette < 0 ){
                cible.setCredits(0);
            } else{
                cible.setCredits(cible.getCredits() - perteCreditsNette);
            }
            System.out.println("L'attaque est réussie, " + cible.getIdEtudiant()+ "de" + cible.getJoueur().getPseudo()+ "a perdu " + perteCreditsNette + " crédits.");
        } else System.out.println("L'attaque est ratée, l'étudiant cible n'a pas perdu de crédits.");
    }

}
