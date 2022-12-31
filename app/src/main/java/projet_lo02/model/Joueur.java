package projet_lo02.model;

import java.util.*;

/**
 * Classe du joueur
 * @author Julian Marques
 * @author Raphaël Weis
 */
public class Joueur {

    /**
     * Pseudo du joueur
     */
    private String pseudo;

    /**
     * Branche du joueur
     */
    private String branche;

    /**
     * partie du joueur
     */
    private Partie partieEnCours;

    /**
     * Solde de points du joueur
     */
    private int soldePoints;

    /**
     * Nb de zones contrôlées par le joueur
     */
    private int nombreZonesControlees;

    /**
     * Liste des troupes
     */
    HashMap<Integer, Etudiant> equipe;

    /**
     * Solde de points au départ
     */
    public final int TOTAL_SOLDE_POINTS = 400;

    /**
     * Constructeurs du joueur
     * @param nouveauPseudo pseudo du joueur
     * @param branche branche du joueur
     * @param partieDeCeJoueur partie du joueur
     */
    public Joueur(String nouveauPseudo, String branche, Partie partieDeCeJoueur){
        int compteur = 1;
        this.setPseudo(nouveauPseudo);
        this.setBranche(branche);
        this.partieEnCours = partieDeCeJoueur;
        this.soldePoints = TOTAL_SOLDE_POINTS;
        this.nombreZonesControlees = 0;
        equipe = new HashMap<Integer, Etudiant>(20);
        for(int i = 0; i < 15; i++){
            equipe.put(compteur, new Etudiant(compteur, this));
            compteur += 1;
        }
        for(int i = 0; i < 4; i++){
            equipe.put(compteur, new Elite(compteur, this));
            compteur += 1;
        }
        equipe.put(compteur, new MaitreGobi(compteur, this));
    }

    public Joueur(Partie partieDeCeJoueur){
        int compteur = 1;
        this.setPseudo(null);
        this.setBranche(null);
        this.partieEnCours = partieDeCeJoueur;
        this.soldePoints = TOTAL_SOLDE_POINTS;
        this.nombreZonesControlees = 0;
        equipe = new HashMap<Integer, Etudiant>(20);
        for(int i = 0; i < 15; i++){
            equipe.put(compteur, new Etudiant(compteur, this));
            compteur += 1;
        }
        for(int i = 0; i < 4; i++){
            equipe.put(compteur, new Elite(compteur, this));
            compteur += 1;
        }
        equipe.put(compteur, new MaitreGobi(compteur, this));
    }

    public void parametrerEquipe(){
        while(true){
            Utility.clearTerminal();
            System.out.println("Mode Paramétrage d'équipe : Equipe de " + this.pseudo);
            Utility.jumpLines(1);
            System.out.println("Solde de points : " + this.soldePoints + "\n");
            this.afficherEquipe();
            Utility.jumpLines(1);
            System.out.println("Quel étudiant souhaitez-vous modifier ? (Entrez l'id correspondant)");
            Utility.jumpLines(1);
            System.out.println("(q) : revenir au choix de l'équipe");
            String idInput = Utility.promptString();
            if(idInput.equals("q") || idInput.equals("Q")){
                break;
            }
            if(Utility.isStringInt(idInput)){
                int numericIdInput = Integer.parseInt(idInput);
                if(numericIdInput >= 1 && numericIdInput <= this.equipe.size()){
                    Etudiant etudiantChoisi = this.equipe.get(numericIdInput);
                    this.parametrerEtudiant(etudiantChoisi, etudiantChoisi.idEtudiant);
                } else{
                    System.out.println("Erreur : valeur donnée non valide");
                    Utility.sleep(2500);
                }
            } else {
                System.out.println("Erreur : type de donnée non valide");
                Utility.sleep(2500);
            }
        }
    }

    /**
     * Méthode pour afficher l'équipe du joueur
     */
    public void afficherEquipe(){
        System.out.print(Etudiant.etudiantColumnHeaders());
        Iterator<Map.Entry<Integer, Etudiant>> iteratorEtudiants = equipe.entrySet().iterator();
        while(iteratorEtudiants.hasNext()){
            Map.Entry<Integer, Etudiant> etudiantActuel = iteratorEtudiants.next();
            System.out.print(etudiantActuel.getValue().etudiantOneLiner());
        }
    }

    /**
     * Méthode pour afficher les réservistes de l'équipe du joueur
     */
    public void afficherReservisteEquipe(){
        System.out.print(Etudiant.etudiantColumnHeaders());
        Iterator<Map.Entry<Integer, Etudiant>> iteratorEtudiants = equipe.entrySet().iterator();
        while(iteratorEtudiants.hasNext()){
            Map.Entry<Integer, Etudiant> etudiantActuel = iteratorEtudiants.next();
            if(etudiantActuel.getValue().isReserviste()){
                System.out.print(etudiantActuel.getValue().etudiantOneLiner());
            }
        }
    }

    /**
     * Méthode pour paramétrer un étudiant du joueur
     * @param  etudiant étudiant à paramétrer
     * @param idEtudiant ID de l'étudiant
     */
    public void parametrerEtudiant(Etudiant etudiant, int idEtudiant){
        boolean escape = false;
        while(escape == false){
            Utility.clearTerminal();
            System.out.println("Mode Paramétrage d'Etudiant : " + etudiant.type + idEtudiant);
            Utility.jumpLines(1);
            System.out.println("Solde de points : " + this.soldePoints);
            Utility.jumpLines(2);
            System.out.println(etudiant);
            System.out.println("Choissisez un mode d'édition : ");
            Utility.jumpLines(1);
            System.out.println("(1) : Editer tous les attributs");
            System.out.println("(2) : Editer un seul attribut");
            System.out.println("(q) : Revenir au paramétrage de l'équipe");
            String input = Utility.promptString();
            if(input.equals("q") || input.equals("Q")){
                break;
            }
            if(Utility.isStringInt(input)){
                int numericInput = Integer.parseInt(input);
                switch(numericInput){
                    case 1 :
                        etudiant.editerAttributs();
                        escape = true;
                        break;
                    case 2 :
                        etudiant.editerAttribut();
                        break;
                    default :
                        System.out.println("Erreur : valeur entrée non valide");
                        Utility.sleep(2500);
                        break;
                }
            } else{
                System.out.println("Erreur : type de donnée non valide");
                Utility.sleep(2500);
            }
        }
    }

    /**
     * Méthode pour calculer le nb de réservistes de l'équipe du joueur
     * @return nb de réservistes
     */
    public int getNbReserviste(){
        int nbreserviste = 0;

        Iterator<Map.Entry<Integer, Etudiant>> iteratorEtudiants = equipe.entrySet().iterator();
        while(iteratorEtudiants.hasNext()){
            if(iteratorEtudiants.next().getValue().getReserviste()){
                nbreserviste += 1;
            }
        }
        return nbreserviste;
    }

    /**
     * Méthode pour afficher le joueur
     * @return la liste des étudiants
     */
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("-------- " + this.pseudo + " --------\n");
        sb.append("Solde de points : " + this.soldePoints + "\n");
        sb.append("Nombre de zones controlees : " + this.nombreZonesControlees + "\n");
        return sb.toString();
    }

    //getters et setters

    /**
     * Getter pseudo
     * @return le pseudo du joueur
     */
    public String getPseudo() {
        return this.pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Getter branche
     * @return la branche du joueur
     */
    public String getBranche() {
        return this.branche;
    }

    public void setBranche(String branche) {
        this.branche = branche;
    }

    /**
     * Getter solde points
     * @return entier solde points du joueur
     */
    public int getSoldePoints() {
        return this.soldePoints;
    }

    public void setSoldePoints(int nouveauSoldePoints) {
        this.soldePoints = nouveauSoldePoints;
    }

    /**
     * Getter nb zones controlées
     * @return entier nb zones controlées
     */
    public int getNombreZonesControlees() {
        return this.nombreZonesControlees;
    }

    public void setNombreZonesControlees(int nbZonesControlees) {
        this.nombreZonesControlees = nbZonesControlees;
    }

    /**
     * Getter equipe du joueur
     * @return liste d'étudiants
     */
    public HashMap<Integer, Etudiant> getEquipe() {
        return this.equipe;
    }

    public void setEquipe(HashMap<Integer, Etudiant> listeEtu) {
        this.equipe = listeEtu;
    }

    /**
     * Getter partie du joueur
     * @return partie
     */
    public Partie getPartie(){
        return this.partieEnCours;
    }
}
