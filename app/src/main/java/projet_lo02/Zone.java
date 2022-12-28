package projet_lo02;

import java.util.*;

public class Zone {
    private Statut propriete;
    private NomZone nomZone;
    private Partie partie;



    private ArrayList<Etudiant> listEtudiantZone = new ArrayList<Etudiant>();
    private ArrayList<Etudiant> listEquipe1 = new ArrayList<Etudiant>();
    private ArrayList<Etudiant> listEquipe2 = new ArrayList<Etudiant>();

    private int nbMortsEquipe1 = 0;
    private int nbMortsEquipe2 = 0;

    private int lengthEquipe1;
    private int lengthEquipe2;

    private boolean zoneControlee;


    public Zone(NomZone zone, Partie partieEnCours){
        this.nomZone = zone;
        this.propriete = Statut.UNDEFINED;
        this.partie = partieEnCours;
        this.zoneControlee = false;
    }

    public String getNomZone(){
        return this.nomZone.name();
    }

    public Statut getStatus(){
        return this.propriete;
    }

    public ArrayList<Etudiant> getEquipe1(){
        return this.listEquipe1;
    }

    public ArrayList<Etudiant> getEquipe2(){
        return this.listEquipe2;
    }

    public void attribuerEtudiant(Etudiant etudiant){
        listEtudiantZone.add(etudiant);
        if(etudiant.getJoueur().equals(etudiant.getJoueur().getPartie().getJoueur1())){
            listEquipe1.add(etudiant);
        } else {
            listEquipe2.add(etudiant);
        }
    }

    public void trierEtudiantsInitiative(){
        Collections.sort(listEtudiantZone, new Comparator<Etudiant>(){
            @Override
            public int compare(Etudiant etudiant1, Etudiant etudiant2){
                return etudiant2.initiative - etudiant1.initiative;
            }
        });
    }

    public void trierEtudiantsCredits(ArrayList<Etudiant> equipe){
        Collections.sort(equipe, new Comparator<Etudiant>(){
            @Override
            public int compare(Etudiant etudiant1, Etudiant etudiant2){
                return etudiant2.getCredits() - etudiant1.getCredits();
            }
        });
    }

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

    public int sommeCreditsEquipe1(){
        Iterator<Etudiant> iteEtudiant = listEquipe1.iterator();
        int sommeCredits = 0;
        while(iteEtudiant.hasNext()){
            sommeCredits += iteEtudiant.next().getCredits();
        }
        return sommeCredits;
    }

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




    //GETTERS ET SETTERS
    public int getLengthEquipe1() {
        return lengthEquipe1;
    }

    public int getLengthEquipe2() {
        return lengthEquipe2;
    }

    public void setLengthEquipe1(int lengthEquipe1) {
        this.lengthEquipe1 = lengthEquipe1;
    }

    public void setLengthEquipe2(int lengthEquipe2) {
        this.lengthEquipe2 = lengthEquipe2;
    }
    public boolean getZoneControlee() {
        return zoneControlee;
    }
    public void setZoneControlee(boolean status) {
        this.zoneControlee = status;
    }

    public ArrayList<Etudiant> getListEtudiantZone() {
        return listEtudiantZone;
    }

    public void setListEtudiantZone(ArrayList<Etudiant> listEtudiantZone) {
        this.listEtudiantZone = listEtudiantZone;
    }

    public static void main(String[] args) {
        //Scanner userInput = new Scanner(System.in);
        Utility.userInput = new Scanner(System.in);
        Partie partie = new Partie();
        Zone zone = new Zone(NomZone.ADMINISTRATION, partie);
        zone.attribuerEtudiant(partie.getJoueur1().getEquipe().get(1));
        zone.listEtudiantZone.get(0).setInitiative(1);
        zone.listEtudiantZone.get(0).setStrategie(1);
        zone.listEtudiantZone.get(0).setZone(zone);
        //System.out.println(zone.listEtudiantZone.get(0));

        zone.attribuerEtudiant(partie.getJoueur1().getEquipe().get(2));
        zone.listEtudiantZone.get(1).setInitiative(2);
        zone.listEtudiantZone.get(1).setStrategie(1);
        zone.listEtudiantZone.get(1).setZone(zone);
        //System.out.println(zone.listEtudiantZone.get(1));

        zone.attribuerEtudiant(partie.getJoueur2().getEquipe().get(1));
        zone.listEtudiantZone.get(2).setInitiative(4);
        zone.listEtudiantZone.get(2).setStrategie(1);
        zone.listEtudiantZone.get(2).setZone(zone);
        //System.out.println(zone.listEtudiantZone.get(2));

        zone.attribuerEtudiant(partie.getJoueur2().getEquipe().get(2));
        zone.listEtudiantZone.get(3).setInitiative(5);
        zone.listEtudiantZone.get(3).setStrategie(1);
        zone.listEtudiantZone.get(3).setZone(zone);
        // //System.out.println(zone.listEtudiantZone.get(3));

        zone.trierEtudiantsInitiative();

        Iterator<Etudiant> iteEtu = zone.listEtudiantZone.iterator();
        while(iteEtu.hasNext()){
            Etudiant etudiant = iteEtu.next();
            System.out.println(etudiant.toString());
        }
        zone.combattreZone();

    }
}