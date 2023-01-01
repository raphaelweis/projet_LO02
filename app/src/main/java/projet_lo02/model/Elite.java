package projet_lo02.model;

public class Elite extends Etudiant {

    public Elite(int idEtudiant, Joueur joueur) {
        super(idEtudiant, joueur);
        this.type = "Elite";
    }

}

