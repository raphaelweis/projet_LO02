package projet_lo02.model;

public class MaitreGobi extends Etudiant{

    public MaitreGobi(int idEtudiant, Joueur joueur) {
        super(idEtudiant, joueur);
        this.type = "MaîtreGobi";
    }

}
