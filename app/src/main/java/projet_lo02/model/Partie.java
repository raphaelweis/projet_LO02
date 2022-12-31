package projet_lo02.model;

import java.util.*;

/**
 * Classe Partie
 * @author Julian Marques
 * @author Raphaël Weis
 */
public class Partie {

    /**
     * Joueur 1
     */
    private Joueur joueur1;

    /**
     * Joueur 2
     */
    private Joueur joueur2;

    /**
     * Liste des zones
     */
    private List<Zone> listZones = new ArrayList<Zone>();

    /**
     * Liste des zones non controlées
     */
    private List<Zone> listZonesNonControlees = new ArrayList<Zone>();

    /**
     * Constructeur de la partie
     */
    public Partie(){
            this.joueur1 = new Joueur(this);
            this.joueur2 = new Joueur(this);
            this.initialiserZones();
    }

    public void initialiserJoueurs(){
            Utility.clearTerminal();
            System.out.println("Initialisation : Création des joueurs");
            Utility.jumpLines(1);

            System.out.print("Entrer Pseudo Joueur 1 : ");
            String pseudoJ1 = Utility.userInput.next();
            Utility.jumpLines(0);
            System.out.print("Entrer Branche Joueur 1 : ");
            String brancheJ1 = Utility.userInput.next();
            this.joueur1.setPseudo(pseudoJ1);
            this.joueur1.setBranche(brancheJ1);

            Utility.jumpLines(1);

            System.out.print("Entrer Pseudo Joueur 2 : ");
            String pseudoJ2 = Utility.userInput.next();
            Utility.jumpLines(0);
            System.out.print("Entrer Branche Joueur 2 : ");
            String brancheJ2 = Utility.userInput.next();
            this.joueur2.setPseudo(pseudoJ2);
            this.joueur2.setBranche(brancheJ2);
    }

    /**
     * Méthode pour paramétrer les équipes
     */
    public void parametrageDesEquipes(){
        while(true){
            //Affichage
            Utility.clearTerminal();
            System.out.println("Paramétrage des équipes");
            Utility.jumpLines(1);
            System.out.println("Quelle équipe voulez-vous paramétrer ?");
            System.out.println("(1) : Paramétrer l'équipe de " + this.joueur1.getPseudo());
            System.out.println("(2) : Paramétrer l'équipe de " + this.joueur2.getPseudo());
            Utility.jumpLines(1);
            for(int i = 0; i < 137; i++){
                System.out.print("-");
            }
            Utility.jumpLines(2);
            System.out.println("(3) : Passer en phase assignation des zones (Action irréversible !)");
            String input = Utility.promptString();
            //paramétrage d'une des équipes ou zone en fonction de la réponse
            if(Utility.isStringInt(input)){
                int numericInput = Integer.parseInt(input);
                switch(numericInput){
                    case 1 :
                        this.joueur1.parametrerEquipe();
                        break;
                    case 2 :
                        this.joueur2.parametrerEquipe();
                        break;
                    case 3 :
                        this.configurationZones();
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
     * Méthode pour configurer les zones
     */
    public void configurationZones(){
        //paramétrage équipe 1
        boolean exit = false;
        Utility.clearTerminal();
        System.out.println("Assignation des zones");
        Utility.jumpLines(1);
        System.out.println("C'est au tour de " + this.joueur1.getPseudo() + " d'affecter des zones à son équipe");
        Utility.sleep(2500);
        Utility.jumpLines(2);
        while(exit == false){
            exit = this.placerEtudiants(this.joueur1);
        }

        //paramétrage équipe 2
        exit = false;
        Utility.clearTerminal();
        System.out.println("Assignation des zones");
        Utility.jumpLines(1);
        System.out.println("C'est au tour de " + this.joueur2.getPseudo() + " d'affecter des zones à son équipe");
        Utility.sleep(2500);
        Utility.jumpLines(2);
        while(exit == false){
            exit = this.placerEtudiants(this.joueur2);
        }
        this.initialiserCombats();
    }

    /**
     * Méthode pour placer les étudiants dans les zones
     * @param  joueur joueur pour lequel on veut placer les étudiants
     */
    public boolean placerEtudiants(Joueur joueur){
        Iterator<Map.Entry<Integer, Etudiant>> iteratorEquipe = joueur.equipe.entrySet().iterator();
        while (iteratorEquipe.hasNext()) {
            //Afffichage
            Utility.clearTerminal();
            System.out.println("Attribuez une zone à votre étudiant : ");
            Utility.jumpLines(1);
            System.out.println("(1) : Administration || (2) : BDE || (3) : Bibliothèque || (4) : Halles industrielles || (5) : Halle sportive");
            Utility.jumpLines(1);

            Map.Entry<Integer, Etudiant> etudiantActuel;
            do{
                etudiantActuel = iteratorEquipe.next();
            }while(etudiantActuel.getValue().getReserviste());
            System.out.println("Etudiant à placer : " + etudiantActuel.getValue().getType() + etudiantActuel.getValue().getIdEtudiant());
            Utility.jumpLines(1);
            System.out.println(etudiantActuel.getValue());
            String input = Utility.promptString();
            //si id et zone corrects, on peut le placer
            if(Utility.isStringInt(input)){
                int numericInput = Integer.parseInt(input);
                if(numericInput >= 1 && numericInput <= this.listZones.size()){
                    etudiantActuel.getValue().setZone(this.listZones.get(numericInput - 1));//attribuer la zone à l'étudiant
                    this.listZones.get(numericInput - 1).attribuerEtudiant(etudiantActuel.getValue()); //attribuer etudiant à la zone
                } else {
                    System.out.println("Erreur : valeur entrée non valide");
                    Utility.sleep(2500);
                }
            } else{
                System.out.println("Erreur : type de donnée non valide");
                Utility.sleep(2500);
            }
        }
        while(true){
            //affichage récapitulatif
            Utility.clearTerminal();
            System.out.println("Récapitulation des affectations : équipe de " + joueur.getPseudo());
            Utility.jumpLines(1);
            joueur.afficherEquipe();
            Utility.jumpLines(1);
            System.out.println("Continuer : (C) || Recommencer la configuration : (R)");
            String input = Utility.promptString();
            if(input.equals("c") || input.equals("C")){
                return true;
            } else if(input.equals("r") || input.equals("R")){
                return false;
            } else{
                System.out.println("Erreur : valeur entrée non valide");
                Utility.sleep(2500);
            }
        }
    }

    /**
     * Méthode pour placer les réservistes dans les zones
     * @param  joueur joueur pour lequel on veut placer les réservistes
     */
    public void placerReservistes(Joueur joueur) {

        Iterator<Map.Entry<Integer, Etudiant>> iteratorEquipe = joueur.equipe.entrySet().iterator();
        while (iteratorEquipe.hasNext()) {
            Map.Entry<Integer, Etudiant> etudiantActuel = iteratorEquipe.next();
            //si l'étudiant est réserviste, on le place
            if(etudiantActuel.getValue().getReserviste()){
                //affichage
                etudiantActuel.getValue().setReserviste(false);
                Utility.clearTerminal();
                System.out.println("------------- PLacement des réservistes du joueur " + joueur.getPseudo() + " -------------");
                System.out.println("Attribuez une zone à votre étudiant : ");
                Utility.jumpLines(1);
                System.out.println("Zones possibles : ");
                Utility.jumpLines(1);

                for(int i = 0 ; i < this.listZones.size() ; i++) {
                    if (!this.listZones.get(i).getZoneControlee()) {
                        System.out.println("(" + (i+1) + ") " + this.listZones.get(i).getNomZone());
                    }
                }

                System.out.println("Etudiant à placer : " + etudiantActuel.getValue().getType() + etudiantActuel.getValue().getIdEtudiant());
                Utility.jumpLines(1);
                System.out.println(etudiantActuel.getValue());
                String input = Utility.promptString();
                if(Utility.isStringInt(input)){
                    //si id et zone corrects, on peut le placer
                    int numericInput = Integer.parseInt(input);

                    if(numericInput >= 1 && numericInput <= this.listZones.size() && !this.listZones.get(numericInput - 1).getZoneControlee()){
                        etudiantActuel.getValue().setZone(this.listZones.get(numericInput - 1));//attribuer la zone à l'étudiant
                        this.listZones.get(numericInput - 1).attribuerEtudiant(etudiantActuel.getValue()); //attribuer etudiant à la zone
                    } else {
                        System.out.println("Erreur : vous ne pouvez mettre l'étudiant que dans la liste de zones indiquées !");
                        Utility.sleep(2500);
                    }
                } else{
                    System.out.println("Erreur : type de donnée non valide");
                    Utility.sleep(2500);
                }
            }
        }
    }

    /**
     * Méthode pour initialiser les combats
     */
    public void initialiserCombats(){
        while(true){ 
            Utility.clearTerminal();
            System.out.println("Configuration initiale terminée !");
            Utility.jumpLines(1);
            System.out.println("Entrez (go) pour lancer les combats !");
            String input = Utility.promptString();

            if(input.equals("go")){
                this.combattre();
                break;
            } else {
                System.out.println("Erreur : Entrez exactement go pour confirmer le début des combats");
                Utility.sleep(2500);
            }
        }
    }

    /**
     * Méthode pour les combats
     */
    public void combattre(){

        //tri des zones
        this.trierZones();
        Iterator<Zone> iteZones2 = this.listZones.iterator();
        while (iteZones2.hasNext()){
            Zone zone = iteZones2.next();
            zone.setLengthEquipe1(zone.getEquipe1().size());
            zone.setLengthEquipe2(zone.getEquipe2().size());

        }

        //tant que partie pas gagnée
        while(joueur1.getNombreZonesControlees() < 3 && joueur2.getNombreZonesControlees() < 3){
            Iterator<Zone> iteZones = this.listZones.iterator();
            while (iteZones.hasNext()){
                Zone zone = iteZones.next();
                if(!zone.getZoneControlee()){
                    System.out.println("\n------------- ZONE " + zone.getNomZone()+ " --------------");
                    zone.combattreZone();
                    if(zone.getZoneControlee()){
                        this.listZonesNonControlees.remove(zone);
                        // TREVE
                        Utility.jumpLines(1);
                        System.out.println("--------------------------- TREVE ---------------------------");
                        Utility.jumpLines(1);
                        treve();

                        //redéploiement des troupes du joueur ayant controlé la zone
                        if(zone.getStatus().equals(Statut.JOUEUR1) && joueur1.getNombreZonesControlees() < 3){
                            if(zone.getEquipe1().size() > 1 ){
                                redeploiement(joueur1, zone);
                            } else {
                                System.out.println("Il n'y a qu'un joueur, il ne peut pas être redéployé ! ");
                            }
                        } else if(zone.getStatus().equals(Statut.JOUEUR2) && joueur2.getNombreZonesControlees() < 3){
                            if(zone.getEquipe2().size() > 1 ){
                                redeploiement(joueur2, zone);
                            } else {
                                System.out.println("Il n'y a qu'un joueur, il ne peut pas être redéployé ! ");
                            }
                        }
                    }
                }
            }
        }

        //annonce gagnant zone
        if(joueur1.getNombreZonesControlees() >= 3){
            System.out.println("Joueur 1 a gagné la partie");
        } else {
            System.out.println("Joueur 2 a gagné la partie");
        }

    }

    /**
     * Méthode pour la trêve
     */
    public void treve(){

        treveJoueur(joueur1);
        treveJoueur(joueur2);

    }

    /**
     * Méthode pour la trêve d'un joueur
     * @param  joueur joueur pour lequel on fait la trêve
     */
    public void treveJoueur(Joueur joueur){
        //si il y a des réservistes
        if(ReservistesPresents(joueur)) {
            System.out.println("Réservistes du joueur " + joueur.getPseudo()+ " :");
            joueur.afficherReservisteEquipe();
            afficherRecapZones(joueur);
            System.out.println("Souhaitez-vous déployer vos réservistes (O/N)?");
            String input = Utility.promptString();
            if(input.equals("O") || input.equals("o")){
                placerReservistes(joueur);
            }
        }
    }

    /**
     * Méthode pour la trêve d'un joueur
     * @param  joueur joueur pour lequel on redéploie les étudiants
     * @param zone zone qui vient d'être controlée
     */
    public void redeploiement(Joueur joueur, Zone zone){

        if(joueur.equals(joueur.getPartie().getJoueur1())){
            //afficher etudiants zone + demander lequel reste

            System.out.println("--------- Redéploiement du joueur " + joueur.getPseudo() + " ---------");
            System.out.println(zone.getEquipe1());
            System.out.println("Quel étudiant souhaitez-vous laisser dans la zone (donner son id) ?");

            String input = Utility.promptString();

            // placer tous les etudiants sauf celui qui reste
            Iterator<Etudiant> iteEtudiant = zone.getEquipe1().iterator();
            while(iteEtudiant.hasNext()){
                Etudiant etudiantActuel = iteEtudiant.next();
                int numericInput = Integer.parseInt(input);
                if(etudiantActuel.getIdEtudiant() != numericInput){
                    //placer l'etudiant
                    deployerEtudiant(etudiantActuel, zone);
                }
            }
        } else {
            //afficher etudiants zone + demander lequel reste
            System.out.println(zone.getEquipe2());
            System.out.println("Quel étudiant souhaitez-vous laisser dans la zone (donner son id) ?");
            String input = Utility.promptString();

            // placer tous les etudiants sauf celui qui reste
            Iterator<Etudiant> iteEtudiant = zone.getEquipe2().iterator();
            while(iteEtudiant.hasNext()){
                int numericInput = Integer.parseInt(input);
                Etudiant etudiant = iteEtudiant.next();
                if(etudiant.getIdEtudiant() != numericInput){
                    //placer l'etudiant
                    deployerEtudiant(etudiant, zone);
                }
            }
        }
    }

    /**
     * Méthode pour redéployer un étudiant
     * @param  etudiant étudiant à redéployer
     * @param zoneActuelle zone où était l'étudiant
     */
    public void deployerEtudiant(Etudiant etudiant, Zone zoneActuelle){

        Utility.clearTerminal();
        System.out.println("Attribuez une zone à votre étudiant : ");
        Utility.jumpLines(1);
        System.out.println("Zones possibles : ");
        Utility.jumpLines(1);

        for(int i = 0 ; i < this.listZones.size() ; i++) {
            if (!this.listZones.get(i).getZoneControlee()) {
                System.out.println("(" + (i+1) + ") " + this.listZones.get(i).getNomZone());
            }
        }

        System.out.println("Etudiant à placer : " + etudiant.getType() + etudiant.getIdEtudiant());
        Utility.jumpLines(1);
        System.out.println(etudiant);
        String input = Utility.promptString();
        if(Utility.isStringInt(input)){
            int numericInput = Integer.parseInt(input);
            if(numericInput >= 1 && numericInput <= this.listZones.size() && !this.listZones.get(numericInput - 1).getZoneControlee()){
                etudiant.setZone(this.listZones.get(numericInput - 1));//attribuer la zone à l'étudiant
                this.listZones.get(numericInput - 1).attribuerEtudiant(etudiant); //attribuer etudiant à la zone
                // retirer etudiant à la zone
                this.listZones.get(numericInput - 1).getListEtudiantZone().remove(etudiant);
                if(etudiant.getJoueur().equals(etudiant.getJoueur().getPartie().getJoueur1())){
                    this.listZones.get(numericInput - 1).getEquipe1().remove(etudiant);
                } else {
                    this.listZones.get(numericInput - 1).getEquipe2().remove(etudiant);
                }

            } else {
                System.out.println("Erreur : vous ne pouvez mettre l'étudiant que dans la liste de zones indiquées !");
                Utility.sleep(2500);
            }
        } else{
            System.out.println("Erreur : type de donnée non valide");
            Utility.sleep(2500);
        }
    }

    /**
     * Méthode pour afficher le récapitulatif des zones
     * @param  joueur joueur pour lequel on fait le récap
     */
    public void afficherRecapZones(Joueur joueur){
        System.out.println("Souhaite-vous voir le nombre de crédits par zone (O/N)?");
        String input = Utility.promptString();
        if(input.equals("O") || input.equals("o")){
            Utility.jumpLines(1);
            System.out.println("------------------------- Recap des zones -------------------------");
            Utility.jumpLines(1);
            sommeECTSZones(joueur);
            Utility.jumpLines(1);
            System.out.println("-------------------------------------------------------------------");
            Utility.jumpLines(1);

        }
    }

    /**
     * Méthode pour afficher la somme des crédits des zones
     * @param  joueur joueur pour lequel on fait le récap
     */
    public void sommeECTSZones(Joueur joueur){
        Iterator<Zone> iteZones = this.listZones.iterator();
        while (iteZones.hasNext()){
            Zone zone = iteZones.next();
            if(joueur.equals(joueur.getPartie().getJoueur1())){
                System.out.println(" Zone : " + zone.getNomZone() + " - " + zone.sommeCreditsEquipe1() + " crédits");
            } else {
                System.out.println(" Zone : " + zone.getNomZone() + " - credits : " + zone.sommeCreditsEquipe2());
            }
        }
    }

    /**
     * Méthode pour savoir si il reste des réservistes dans l'équipe
     * @param  joueur joueur pour lequel on checke
     */
    public boolean ReservistesPresents(Joueur joueur){
        Iterator<Map.Entry<Integer, Etudiant>> iteEquipe = joueur.equipe.entrySet().iterator();
        int reserviste = 0;
        while(iteEquipe.hasNext()){
            if(iteEquipe.next().getValue().getReserviste()){
                reserviste += 1;
            }
        }
        return reserviste > 0;
    }

    /**
     * Méthode pour trier les zones
     */
    public void trierZones(){
        Iterator<Zone> iteZones = this.listZones.iterator();
        while (iteZones.hasNext()){
            Zone zone = iteZones.next();
            zone.trierEtudiantsInitiative();
            zone.trierEtudiantsCredits(zone.getEquipe1());
            zone.trierEtudiantsCredits(zone.getEquipe2());
        }
    }

    /**
     * Méthode pour initialiser les zones
     */
    public void initialiserZones(){
        this.listZones = new ArrayList<Zone>();
        this.listZones.add(new Zone(NomZone.ADMINISTRATION, this));
        this.listZones.add(new Zone(NomZone.BDE, this));
        this.listZones.add(new Zone(NomZone.BIBLIOTHEQUE, this));
        this.listZones.add(new Zone(NomZone.HALLE_INDUSTRIELLE, this));
        this.listZones.add(new Zone(NomZone.HALLE_SPORTIVE, this));

        this.listZonesNonControlees = new ArrayList<Zone>();
        this.listZonesNonControlees.add(new Zone(NomZone.ADMINISTRATION, this));
        this.listZonesNonControlees.add(new Zone(NomZone.BDE, this));
        this.listZonesNonControlees.add(new Zone(NomZone.BIBLIOTHEQUE, this));
        this.listZonesNonControlees.add(new Zone(NomZone.HALLE_INDUSTRIELLE, this));
        this.listZonesNonControlees.add(new Zone(NomZone.HALLE_SPORTIVE, this));
    }

    /**
     * Getter joueur 1
     * @return joueur 1
     */
    public Joueur getJoueur1(){
        return joueur1;
    }

    /**
     * Getter joueur 2
     * @return joueur 2
     */
    public Joueur getJoueur2(){
        return joueur2;
    }

    /**
     * Getter liste des zones
     * @return liste de zones
     */
    public List<Zone> getListZones(){
        return this.listZones;
    }

    /**
     * Getter liste des zones non controlées
     * @return liste de zones non controlées
     */
    public List<Zone> getListZonesNonControlees() {
        return listZonesNonControlees;
    }

    /**
     * Setter joueur 1
     * @param joueur1 joueur1 de la partie
     */
    public void setJoueur1(Joueur joueur1) {
        this.joueur1 = joueur1;
    }

    /**
     * Setter joueur 2
     * @param joueur2 joueur2 de la partie
     */
    public void setJoueur2(Joueur joueur2) {
        this.joueur2 = joueur2;
    }

    /**
     * Setter liste de zones
     * @param listZones liste de zones de la partie
     */
    public void setListZones(List<Zone> listZones) {
        this.listZones = listZones;
    }

    /**
     * Setter liste de zones non controlées
     * @param listZonesNonControlees liste de zones non controlées de la partie
     */
    public void setListZonesNonControlees(List<Zone> listZonesNonControlees) {
        this.listZonesNonControlees = listZonesNonControlees;
    }
    
}
