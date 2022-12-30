package projet_lo02.model;

public class Elite extends Etudiant {

    public Elite(int idEtudiant, Joueur joueur) {
        super(idEtudiant, joueur);
        this.type = "Elite";
    }

    public static void main(String[] args) {
        Elite eliteTest = new Elite(1, null);
        System.out.println(eliteTest);
    }
}

