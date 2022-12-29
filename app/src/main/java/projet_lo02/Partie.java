package projet_lo02;

import java.util.*;


public class Partie {

    private Joueur joueur1;
    private Joueur joueur2;
    private List<Zone> listZones = new ArrayList<Zone>();
    private List<Zone> listZonesNonControlees = new ArrayList<Zone>();
    private  boolean zoneControlee;

    public Partie(){
        Utility.clearTerminal();
        System.out.println("Initialisation : Création des joueurs");
        Utility.jumpLines(1);
        System.out.print("Entrer Pseudo Joueur 1 : ");
        this.joueur1 = new Joueur(Utility.userInput.next(), this);
        System.out.print("Entrer Pseudo Joueur 2 : ");
        this.joueur2 = new Joueur(Utility.userInput.next(), this);
        this.initialiserZones();
    }

    public static void lancerJeu(){
        while(true){
            Utility.clearTerminal();
            System.out.println("Bienvenue !\n");
            System.out.println("Voulez-vous commencer une nouvelle Partie ?");
            System.out.println("(O) : OUI || (N) : NON");
            String input = Utility.promptString();
            switch(input){
                case "Y" :
                case "y" :
                case "O" :
                case "o" :
                    Partie partieEnCours = new Partie();
                    partieEnCours.parametrageDesEquipes();
                    break;
                case "N" :
                case "n" :
                    System.out.println("Fin de l'éxecution du Programme");
                    Utility.userInput.close();
                    System.exit(0);
                    break;
                default :
                    System.out.println("Erreur : valeur entrée non valide");
                    Utility.sleep(2500);
                    break;
            }
        }
    }

    public void parametrageDesEquipes(){
        while(true){
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

    public void configurationZones(){
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


    public boolean placerEtudiants(Joueur joueur){
        Iterator<Map.Entry<Integer, Etudiant>> iteratorEquipe = joueur.equipe.entrySet().iterator();
        while (iteratorEquipe.hasNext()) {
            Utility.clearTerminal();
            System.out.println("Attribuez une zone à votre étudiant : ");
            Utility.jumpLines(1);
            System.out.println("(1) : Administration || (2) : BDE || (3) : Bibliothèque || (4) : Halles industrielles || (5) : Halle sportive");
            Utility.jumpLines(1);
            Map.Entry<Integer, Etudiant> etudiantActuel;
            do{
                etudiantActuel = iteratorEquipe.next();
            }while(etudiantActuel.getValue().getReserviste() == true);
            System.out.println("Etudiant à placer : " + etudiantActuel.getValue().getType() + etudiantActuel.getValue().getIdEtudiant());
            Utility.jumpLines(1);
            System.out.println(etudiantActuel.getValue());
            String input = Utility.promptString();
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


    public void placerReservistes(Joueur joueur) {

        Iterator<Map.Entry<Integer, Etudiant>> iteratorEquipe = joueur.equipe.entrySet().iterator();
        while (iteratorEquipe.hasNext()) {
            Map.Entry<Integer, Etudiant> etudiantActuel = iteratorEquipe.next();

            if(etudiantActuel.getValue().getReserviste()){
                etudiantActuel.getValue().setReserviste(false);
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


                System.out.println("Etudiant à placer : " + etudiantActuel.getValue().getType() + etudiantActuel.getValue().getIdEtudiant());
                Utility.jumpLines(1);
                System.out.println(etudiantActuel.getValue());
                String input = Utility.promptString();
                if(Utility.isStringInt(input)){
                    int numericInput = Integer.parseInt(input);
                    //prednre la zone correspondant à cet indice
                    //prednre zone dans liste zone
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

    public void initialiserCombats(){

        Utility.clearTerminal();
        System.out.println("Configuration initiale terminée !");
        Utility.jumpLines(1);
        System.out.println("Entrez (go) pour lancer les combats !");
        String input = Utility.promptString();

        if(input.equals("go")){
            this.combattre();
            System.out.println("Fin des combats : repasser en mode paramétrage ?");
            Utility.jumpLines(1);
            System.out.println("(C) : Continuer");
            Utility.jumpLines(1);
            input = Utility.promptString();
            if(input.equals("c") || input.equals("C")){

            }
        }
    }

    public void combattre(){
        this.trierZones();
        Iterator<Zone> iteZones2 = this.listZones.iterator();
        while (iteZones2.hasNext()){
            Zone zone = iteZones2.next();
            zone.setLengthEquipe1(zone.getEquipe1().size());
            zone.setLengthEquipe2(zone.getEquipe2().size());

        }

        while(joueur1.getNombreZonesControlees() < 3 && joueur2.getNombreZonesControlees() < 3){
            Iterator<Zone> iteZones = this.listZones.iterator();
            while (iteZones.hasNext()){
                Zone zone = iteZones.next();
                if(!zone.getZoneControlee()){
                    System.out.println("\n---------- ZONE " + zone.getNomZone()+ " -----------");
                    zone.combattreZone();
                    if(zone.getZoneControlee()){
                        this.listZonesNonControlees.remove(zone);
                        // TREVE
                        Utility.jumpLines(1);
                        System.out.println("--------------------------- TREVE ---------------------------");
                        Utility.jumpLines(1);
                        treve();

                        //redéploiement des troupes
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

        if(joueur1.getNombreZonesControlees() >= 3){
            System.out.println("Joueur 1 a gagné la partie");
        } else {
            System.out.println("Joueur 2 a gagné la partie");
        }

    }

    public void treve(){

        treveJoueur(joueur1);
        treveJoueur(joueur2);

    }

    public void treveJoueur(Joueur joueur){
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

    public void trierZones(){
        Iterator<Zone> iteZones = this.listZones.iterator();
        while (iteZones.hasNext()){
            Zone zone = iteZones.next();
            zone.trierEtudiantsInitiative();
            zone.trierEtudiantsCredits(zone.getEquipe1());
            zone.trierEtudiantsCredits(zone.getEquipe2());

        }
    }

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

    public Joueur getJoueur1(){
        return joueur1;
    }

    public Joueur getJoueur2(){
        return joueur2;
    }

    public List<Zone> getListZones(){
        return this.listZones;
    }

    public static void main(String[] args) {
        lancerJeu();
    }
}