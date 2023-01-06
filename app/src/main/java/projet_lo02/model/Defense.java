package projet_lo02.model;

/**
 * Classe pour la stratégie défense
 * @author Julian Marques
 * @author Raphaël Weis
 */
public class Defense implements Strategie {

    /**
     * Méthode pour obtenir nom stratégie
     * @return string pour le nom
     */
    public String getNomStrategie(){
            return "Défense";
    }

    /**
     * Méthode pour exécuter la stratégie
     * @param actionneur étudiant exécutant stratégie
     */
    public void executerStrategie(Etudiant actionneur){

        double x = Utility.getRandomDouble(0, 100, true, true);
        double y = Utility.getRandomDouble(0, 0.6, false, true);

        //calcul gain crédits

        Etudiant cible = actionneur.getZone().etudiantLePlusFaibleAllie(actionneur);
        int gainCreditsNet = Utility.getPartieEntiere(y * 10);

        //mettre à jour crédits si défense réussie
        if(x <= 20 + 6 * actionneur.getDexterite() && gainCreditsNet != 0 && cible.getCredits() > 30 + cible.getConstitution()){ //soin réussi
            cible.setCredits(cible.getCredits() + gainCreditsNet);
            System.out.println("Le soin est réussi, "  + cible.getIdEtudiant()+ " de " + cible.getJoueur().getPseudo()+ "a un nouveau solde de crédits de " + cible.getCredits() + " crédits");
        } else System.out.println("Le soin est raté, l'étudiant ciblé n'a pas été soigné.");
    }
}
