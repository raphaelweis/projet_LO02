package projet_lo02.model;

/**
 * Classe pour l'étudiant de type Elite
 * @author Julian Marques
 * @author Raphaël Weis
 */
public class Elite extends Etudiant {

    /**
     * Constructeur Elite
     * @param idEtudiant ID de l'étudiant
     * @param joueur joueur de l'étudiant
     */
    public Elite(int idEtudiant, Joueur joueur) {
        super(idEtudiant, joueur);
        this.type = "Elite";
    }
}

