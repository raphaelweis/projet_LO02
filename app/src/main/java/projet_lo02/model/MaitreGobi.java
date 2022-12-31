package projet_lo02.model;

/**
 * Classe pour l'étudiant de type Maitre du Gobi
 * @author Julian Marques
 * @author Raphaël Weis
 */
public class MaitreGobi extends Etudiant{

    /**
     * Constructeur de la classe Maitre du Gobi
     * @param idEtudiant ID de l'étudiant
     * @param  joueur joueur de l'étudiant
     */
    public MaitreGobi(int idEtudiant, Joueur joueur) {
        super(idEtudiant, joueur);
        this.type = "MaîtreGobi";
    }
}
