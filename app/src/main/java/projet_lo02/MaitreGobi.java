package projet_lo02;

public class MaitreGobi extends Etudiant{

    public MaitreGobi(int idEtudiant, Joueur joueur) {
        super(idEtudiant, joueur);
        this.type = "MaîtreGobi";
    }

    public static void main(String[] args) {
        MaitreGobi maitreGobiTest = new MaitreGobi(1, null);
        Elite eliteTest = new Elite(1, null);
        //System.out.println(maitreGobiTest);
        System.out.println(maitreGobiTest.getMinimumConstitution());
        System.out.println(eliteTest.getMinimumConstitution());
        System.out.println(maitreGobiTest);
        System.out.println(eliteTest);
    }
}