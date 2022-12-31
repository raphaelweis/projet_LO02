package projet_lo02.model;

/**
 * Interface pour lee design pattern stratégie
 * @author Julian Marques
 * @author Raphaël Weis
 */
public interface Strategie {

    /**
     * Méthode abstraite pour exécuter la stratégie d'un étudiant
     * @param actionneur étudiant exécutant sa stratégie
     */
    public void executerStrategie(Etudiant actionneur);
    /**
     * Méthode abstraite pour obtenir le nom de la stratégie
     * @return le nom de la stratégie
     */
    public String getNomStrategie();
}
