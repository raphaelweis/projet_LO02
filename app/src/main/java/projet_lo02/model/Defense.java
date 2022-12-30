package projet_lo02.model;

public class Defense implements Strategie {

    public String getNomStrategie(){
            return "Défense";
    }

    public void executerStrategie(Etudiant actionneur){

        double x = Utility.getRandomDouble(0, 100, true, true);
        double y = Utility.getRandomDouble(0, 0.6, false, true);

        Etudiant cible = actionneur.getZone().etudiantLePlusFaibleAllie(actionneur);
        int gainCreditsNet = Utility.getPartieEntiere(y * (10 + cible.getConstitution()));
        int gainCreditsMax = 30 + cible.getConstitution();

        if(gainCreditsNet >= gainCreditsMax){gainCreditsNet = gainCreditsMax;}

        if(x <= 20 + 6 * actionneur.getDexterite() && gainCreditsNet != 0){ //soin réussi
            cible.setCredits(cible.getCredits() + gainCreditsNet);
            System.out.println("Le soin est réussi, "  + cible.getIdEtudiant()+ "de" + cible.getJoueur().getPseudo()+ " a été soigné de " + gainCreditsNet + " crédits");
        } else System.out.println("Le soin est raté, l'étudiant ciblé n'a pas été soigné.");
    }
}
