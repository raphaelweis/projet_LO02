package projet_lo02.model;

import java.util.*;

/**
 * Classe Utility
 * @author Julian Marques
 * @author Raphaël Weis
 */
public class Zone {
    /**
     * Propriétaire de la zone
     */
    private Statut propriete;

    /**
     * Nom de la zone
     */
    private NomZone nomZone;

    /**
     * Partie de la zone
     */
    private Partie partie;

    /**
     * Liste d'étudiants de la zone
     */
    private ArrayList<Etudiant> listEtudiantZone = new ArrayList<Etudiant>();

    /**
     * Liste d'étudiants de l'équipe 1 de la zone
     */
    private ArrayList<Etudiant> listEquipe1 = new ArrayList<Etudiant>();

    /**
     * Liste d'étudiants de l'équipe 2 de la zone
     */
    private ArrayList<Etudiant> listEquipe2 = new ArrayList<Etudiant>();

    /**
     * Liste d'étudiants morts de l'équipe 1
     */
    private int nbMortsEquipe1 = 0;

    /**
     * Liste d'étudiants morts de l'équipe 2
     */
    private int nbMortsEquipe2 = 0;

    /**
     * Nb d'étudiants de l'équipe 1
     */
    private int lengthEquipe1;

    /**
     * Nb d'étudiants de l'équipe 2
     */
    private int lengthEquipe2;

    /**
     * Statut de la zone (controlée ou non)
     */
    private boolean zoneControlee;

    /**
     * Constructeur de la zone
     * @param zone nom de la zone
     * @param partieEnCours partie de la zone
     */
    public Zone(NomZone zone, Partie partieEnCours){
        this.nomZone = zone;
        this.propriete = Statut.UNDEFINED;
        this.partie = partieEnCours;
        this.zoneControlee = false;
    }

    /**
     * Méthode pour attribuer un étudiant à la zone
     * @param etudiant étudiant à attribuer
     */
    public void attribuerEtudiant(Etudiant etudiant){
        listEtudiantZone.add(etudiant);
        if(etudiant.getJoueur().equals(etudiant.getJoueur().getPartie().getJoueur1())){
            listEquipe1.add(etudiant);
        } else {
            listEquipe2.add(etudiant);
        }
    }

    /**
     * Méthode pour trier les étudiants par initiative
     */
    public void trierEtudiantsInitiative(){
        Collections.sort(listEtudiantZone, new Comparator<Etudiant>(){
            @Override
            public int compare(Etudiant etudiant1, Etudiant etudiant2){
                return etudiant2.initiative - etudiant1.initiative;
            }
        });
    }

    /**
     * Méthode pour trier les étudiants d'une équipe par crédits
     * @param equipe équipe à trier
     */
    public void trierEtudiantsCredits(ArrayList<Etudiant> equipe){
        Collections.sort(equipe, new Comparator<Etudiant>(){
            @Override
            public int compare(Etudiant etudiant1, Etudiant etudiant2){
                return etudiant2.getCredits() - etudiant1.getCredits();
            }
        });
    }

    /**
     * Méthode pour rechercher l'étudiant adverse le + faible
     * @param etudiant équipe attaquant
     */
    public Etudiant etudiantLePlusFaibleAdverse(Etudiant etudiant){
        //chercher dans la zone de l'etu, le joueur le + faible de l'equipe adverse
        //si etudiant est du joueur 1, on prend le dernier etudiant du joueur 2 de la zone
        if(etudiant.getJoueur().equals(etudiant.getJoueur().getPartie().getJoueur1())){
            int index = etudiant.getZone().getEquipe2().size() - 1;
            return etudiant.getZone().getEquipe2().get(index);
        } else {
            int index = etudiant.getZone().getEquipe1().size() - 1;
            return etudiant.getZone().getEquipe1().get(index);
        }
    }

    /**
     * Méthode pour rechercher l'étudiant allié le + faible
     * @param etudiant équipe défenseur
     */
    public Etudiant etudiantLePlusFaibleAllie(Etudiant etudiant){
        //chercher dans la zone de l'etu, le joueur le + faible de l'equipe adverse
        //si etudiant est du joueur 1, on prend le dernier etudiant du joueur 2 de la zone
        if(etudiant.getJoueur().equals(etudiant.getJoueur().getPartie().getJoueur1())){
            int index = etudiant.getZone().getEquipe1().size() - 1;
            return etudiant.getZone().getEquipe1().get(index);
        } else {
            int index = etudiant.getZone().getEquipe2().size() - 1;
            return etudiant.getZone().getEquipe2().get(index);
        }
    }

    /**
     * Méthode pour effectuer 1 tour de combat = le premier de la liste effectue sa stratégie
     */
    public void combattreZone(){

        //1er étudiant joue
        Utility.jumpLines(1);
        Etudiant premierListe = listEtudiantZone.get(0);
        System.out.println("L'etudiant  " + premierListe.getIdEtudiant() + " du joueur "+ listEtudiantZone.get(0).getJoueur().getPseudo() + " execute sa stratégie");
        premierListe.getStrategie().executerStrategie(listEtudiantZone.get(0));
        // on le déplace en dernière position du tableau
        listEtudiantZone.remove(0);
        listEtudiantZone.add(premierListe);

        enleverMort();

        annoncerGagnant();
    }

    /**
     * Méthode pour enlever les étudiants morts de la zone
     */
    public void enleverMort(){
        Iterator<Etudiant> iteEtudiant = listEtudiantZone.iterator();
        while(iteEtudiant.hasNext()){
            Etudiant etudiant = iteEtudiant.next();
            if(etudiant.getCredits() <= 0){
                if(etudiant.getJoueur().equals(etudiant.getJoueur().getPartie().getJoueur1())){
                    nbMortsEquipe1 += 1;
                    System.out.println("L'etudiant " + etudiant.getIdEtudiant() + " du joueur " + etudiant.getJoueur().getPseudo() + " est mort");
                    //enlever l'etudiant mort
                    listEtudiantZone.remove(etudiant);
                    listEquipe1.remove(etudiant);
                    etudiant.getJoueur().getEquipe().remove(etudiant.getIdEtudiant());
                    break;
                }else {
                    nbMortsEquipe2 += 1;
                    System.out.println("L'etudiant " + etudiant.getIdEtudiant() + " du joueur " + etudiant.getJoueur().getPseudo() + " est mort");
                    //enlever l'etudiant mort
                    listEtudiantZone.remove(etudiant);
                    listEquipe2.remove(etudiant);
                    etudiant.getJoueur().getEquipe().remove(etudiant.getIdEtudiant());
                    break;
                }
            }
        }
    }

    /**
     * Méthode pour calculer la sommes des crédits de l'équipe 1
     * @return entier somme des crédits
     */
    public int sommeCreditsEquipe1(){
        Iterator<Etudiant> iteEtudiant = listEquipe1.iterator();
        int sommeCredits = 0;
        while(iteEtudiant.hasNext()){
            sommeCredits += iteEtudiant.next().getCredits();
        }
        return sommeCredits;
    }

    /**
     * Méthode pour calculer la sommes des crédits de l'équipe 2
     * @return entier somme des crédits
     */
    public int sommeCreditsEquipe2(){
        Iterator<Etudiant> iteEtudiant = listEquipe2.iterator();
        int sommeCredits = 0;
        while(iteEtudiant.hasNext()){
            sommeCredits += iteEtudiant.next().getCredits();
        }
        return sommeCredits;
    }

    public void annoncerGagnant(){
        //annonce gagnant
        if(this.nbMortsEquipe1 == this.lengthEquipe1){
            System.out.println("Le joueur 2 a gagné la zone " + this.nomZone);
            this.propriete = Statut.JOUEUR2;
            partie.getJoueur2().setNombreZonesControlees(partie.getJoueur2().getNombreZonesControlees()+1);
            this.zoneControlee = true;

        } else if(this.nbMortsEquipe2 == this.lengthEquipe2){
            System.out.println("Le joueur 1 a gagné la zone " + this.nomZone);
            this.propriete = Statut.JOUEUR1;
            partie.getJoueur1().setNombreZonesControlees(partie.getJoueur1().getNombreZonesControlees()+1);
            this.zoneControlee = true;
        }
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        switch(this.getNomZone()){
            case "ADMINISTRATION" :
                sb.append("Administration");
                break;
            case "BDE" :
                sb.append("BDE");
                break;
            case "BIBLIOTHEQUE" :
                sb.append("Bibliothèque");
                break;
            case "HALLE_INDUSTRIELLE" :
                sb.append("Halle industrielle");
                break;
            case "HALLE_SPORTIVE" :
                sb.append("Halle sportive");
                break;
        }
        return sb.toString();
    }

    public ArrayList<Etudiant> listEtudiantsJoueur(Joueur j){
        if(j.equals(partie.getJoueur1())){
            return this.listEquipe1;
        } else{
            return this.listEquipe2;
        }
    }

    public void afficherListeEtudiants(Joueur joueur){
        System.out.print(Etudiant.etudiantColumnHeaders());
        Iterator<Etudiant> iteratorEtudiants = this.listEtudiantsJoueur(joueur).iterator();
        while(iteratorEtudiants.hasNext()){
            Etudiant etudiantActuel = iteratorEtudiants.next();
            System.out.print(etudiantActuel.etudiantOneLiner());
        }
    }

    //GETTERS ET SETTERS

    /**
     * Getter nom de la zone
     * @return nom de la zone
     */
    public String getNomZone(){
        return this.nomZone.name();
    }

    /**
     * Getter statut de la zone
     * @return propriétaire de la zone
     */
    public Statut getStatus(){
        return this.propriete;
    }

    /**
     * Getter équipe du joueur 1
     * @return liste d'étudiants du joueur 1
     */
    public ArrayList<Etudiant> getEquipe1(){
        return this.listEquipe1;
    }

    /**
     * Getter équipe du joueur 2
     * @return liste d'étudiants du joueur 2
     */
    public ArrayList<Etudiant> getEquipe2(){
        return this.listEquipe2;
    }

    /**
     * Getter nb d'étudiants du joueur 1
     * @return entier nb d'étudiants du joueur 1
     */
    public int getLengthEquipe1() {
        return lengthEquipe1;
    }

    /**
     * Getter nb d'étudiants du joueur 2
     * @return entier nb d'étudiants du joueur 2
     */
    public int getLengthEquipe2() {
        return lengthEquipe2;
    }

    /**
     * Setter nb d'étudiants du joueur 1
     * @param lengthEquipe1 nb d'étudiants du joueur 1
     */
    public void setLengthEquipe1(int lengthEquipe1) {
        this.lengthEquipe1 = lengthEquipe1;
    }

    /**
     * Setter nb d'étudiants du joueur 2
     * @param lengthEquipe2 nb d'étudiants du joueur 2
     */
    public void setLengthEquipe2(int lengthEquipe2) {
        this.lengthEquipe2 = lengthEquipe2;
    }

    /**
     * Getter statut de la zone
     * @return vrai si zone controlée, faux sinon
     */
    public boolean getZoneControlee() {
        return zoneControlee;
    }

    /**
     * Setter  statut de la zone
     * @param status statut de la zone
     */
    public void setZoneControlee(boolean status) {
        this.zoneControlee = status;
    }

    /**
     * Getter liste d'étudiants de la zone
     * @return liste d'étudiants de la zone
     */
    public ArrayList<Etudiant> getListEtudiantZone() {
        return listEtudiantZone;
    }

    /**
     * Setter liste d'étudiants de la zone
     * @param listEtudiantZone liste d'étudiants de la zone
     */
    public void setListEtudiantZone(ArrayList<Etudiant> listEtudiantZone) {
        this.listEtudiantZone = listEtudiantZone;
    }

}
