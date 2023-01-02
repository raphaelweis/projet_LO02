package projet_lo02.model;

import java.util.*;

public class Joueur {

    private String pseudo;
    private String branche;
    private Partie partieEnCours;
    private int soldePoints;
    private int nombreZonesControlees;
    HashMap<Integer, Etudiant> equipe;
    public final int TOTAL_SOLDE_POINTS = 400;

    //Constructeurs
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

    public void afficherEquipe(){
        System.out.print(Etudiant.etudiantColumnHeaders());
        Iterator<Map.Entry<Integer, Etudiant>> iteratorEtudiants = equipe.entrySet().iterator();
        while(iteratorEtudiants.hasNext()){
            Map.Entry<Integer, Etudiant> etudiantActuel = iteratorEtudiants.next();
            System.out.print(etudiantActuel.getValue().etudiantOneLiner());
        }
    }

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

    public void randomizeEquipe(){
        Utility.clearTerminal();
        HashMap<Integer, Etudiant> localEquipe = this.getEquipe();
        List<Integer> unmodifiableLocalKeySet;
        List<Integer> localKeySet;
        Etudiant randomEtu;
        Etudiant nextEtu;
        int randomNumber;
        Integer randomKey;
        unmodifiableLocalKeySet = Arrays.asList(localEquipe.keySet().toArray(new Integer[0]));
        localKeySet = new ArrayList<>(unmodifiableLocalKeySet);
        for(int i = 0; i < localKeySet.size() + 1; i++){
            randomKey = Utility.getRandomInt(1, localKeySet.size());
            randomEtu = localEquipe.get(randomKey);
            randomNumber = Utility.getRandomInt(randomEtu.getMinimumForce(), randomEtu.getMaximumForce());
            if(randomNumber <= this.soldePoints){
                randomEtu.setForce(randomNumber);
            }
            randomNumber = Utility.getRandomInt(randomEtu.getMinimumDexterite(), randomEtu.getMaximumDexterite());
            if(randomNumber <= this.soldePoints){
                randomEtu.setDexterite(randomNumber);
            }
            randomNumber = Utility.getRandomInt(randomEtu.getMinimumResistance(), randomEtu.getMaximumResistance());
            if(randomNumber <= this.soldePoints){
                randomEtu.setResistance(randomNumber);
            }
            randomNumber = Utility.getRandomInt(randomEtu.getMinimumConstitution(), randomEtu.getMaximumConstitution());
            if(randomNumber <= this.soldePoints){
                randomEtu.setConstitution(randomNumber);
            }
            randomNumber = Utility.getRandomInt(randomEtu.getMinimumInitiative(), randomEtu.getMaximumInitiative());
            if(randomNumber <= this.soldePoints){
                randomEtu.setInitiative(randomNumber);
            }
            localKeySet.remove(randomKey);
        }

        for(int key : localEquipe.keySet()){
            randomNumber = Utility.getRandomInt(0, 4);
            nextEtu = localEquipe.get(key);
            nextEtu.setZone(this.partieEnCours.getListZones().get(randomNumber));
        }
        Etudiant etu;

        // pour s'assurer qu'il y a au moins un étudiant par zone
        etu = localEquipe.get(1);
        etu.setZone(this.partieEnCours.getListZones().get(0));
        etu = localEquipe.get(2);
        etu.setZone(this.partieEnCours.getListZones().get(1));
        etu = localEquipe.get(3);
        etu.setZone(this.partieEnCours.getListZones().get(2));
        etu = localEquipe.get(4);
        etu.setZone(this.partieEnCours.getListZones().get(3));
        etu = localEquipe.get(5);
        etu.setZone(this.partieEnCours.getListZones().get(4));
        
        Utility.clearTerminal();
    }

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

    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("-------- " + this.pseudo + " --------\n");
        sb.append("Solde de points : " + this.soldePoints + "\n");
        sb.append("Nombre de zones controlees : " + this.nombreZonesControlees + "\n");
        return sb.toString();
    }

    //getters et setters

    public String getPseudo() {
        return this.pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getBranche() {
        return this.branche;
    }

    public void setBranche(String branche) {
        this.branche = branche;
    }

    public int getSoldePoints() {
        return this.soldePoints;
    }

    public void setSoldePoints(int nouveauSoldePoints) {
        this.soldePoints = nouveauSoldePoints;
    }

    public int getNombreZonesControlees() {
        return this.nombreZonesControlees;
    }

    public void setNombreZonesControlees(int nbZonesControlees) {
        this.nombreZonesControlees = nbZonesControlees;
    }

    public HashMap<Integer, Etudiant> getEquipe() {
        return this.equipe;
    }

    public void setEquipe(HashMap<Integer, Etudiant> listeEtu) {
        this.equipe = listeEtu;
    }

    public Partie getPartie(){
        return this.partieEnCours;
    }

    /*public int getTotalPoints() {
        return this.totalPoints;
    }*/

    public static void main(String[] args) {
        // Joueur joueurTest = new Joueur("test");
        // joueurTest.afficherEquipe();
        System.out.println(Integer.parseInt("23") + Integer.parseInt("2"));
    }
}
