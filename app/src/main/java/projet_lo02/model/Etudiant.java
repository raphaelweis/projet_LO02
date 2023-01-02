package projet_lo02.model;

/**
 * Classe pour l'étudiant de type Elite
 * @author Julian Marques
 * @author Raphaël Weis
 */
public class Etudiant {

    /**
     * ID étudiant
     */
    protected int idEtudiant;

    /**
     * Type étudiant
     */
    protected String type;

    /**
     * Nb de crédits
     */
    protected int credits;

    /**
     * Points de dextérité
     */
    protected int dexterite;

    /**
     * Points de force
     */
    protected int force;

    /**
     * Points de resistance
     */
    protected int resistance;

    /**
     * Points de constitution
     */
    protected int constitution;

    /**
     * Points de initiative
     */
    protected int initiative;

    /**
     * Stratégie de l'étudiant
     */
    private Strategie strategie;

    /**
     * Joueur de l'étudiant
     */
    private Joueur joueur;

    /**
     * Statut (réserviste ou non)
     */
    private boolean reserviste;

    /**
     * Zone où il est situé
     */
    private Zone zoneEtudiant;

    /**
     * Constructeur Etudiant
     * @param idEtudiant ID de l'étudiant
     * @param joueur joueur de l'étudiant
     */
    public Etudiant(int idEtudiant, Joueur joueur){
        this.idEtudiant = idEtudiant;
        this.type = "Etudiant";
        this.dexterite = getMinimumDexterite();
        this.force = getMinimumForce();
        this.resistance = getMinimumResistance();
        this.constitution = getMinimumConstitution();
        this.initiative = getMinimumInitiative();
        this.strategie = new StrategieAleatoire();
        this.reserviste = false;
        this.credits = 30 + this.constitution;
        this.joueur = joueur;
        this.zoneEtudiant = null;
    }

    /**
     * Constructeur Etudiant
     */
    public Etudiant(){
        this.type = "Etudiant";
        this.dexterite = getMinimumDexterite();
        this.force = getMinimumForce();
        this.resistance = getMinimumResistance();
        this.constitution = getMinimumConstitution();
        this.initiative = getMinimumInitiative();
        this.strategie = new StrategieAleatoire();
        this.reserviste = false;
        this.credits = 30 + this.constitution;
        this.zoneEtudiant = null;
    }

    /**
     * Méthode pour éditer un attribut
     */
    public void editerAttribut(){
        while(true){
            //Affichage
            Utility.clearTerminal();
            System.out.println("Mode Paramétrage d'Etudiant (édition d'un attribut) : " + this.type + this.idEtudiant);
            Utility.jumpLines(1);
            System.out.println("Solde de points : " + this.joueur.getSoldePoints());
            Utility.jumpLines(2);
            System.out.println(this);
            System.out.println("Sélectionnez l'attribut que vous souhaitez modifier :");
            System.out.println("(1) : Dextérité");
            System.out.println("(2) : Force");
            System.out.println("(3) : Résistance");
            System.out.println("(4) : Constitution");
            System.out.println("(5) : Initiative");
            System.out.println("(6) : Stratégie");
            System.out.println("(7) : Statut de Réserviste");
            System.out.println("(q) : Revenir au paramétrage de l'étudiant");
            String input = Utility.promptString();
            if(input.equals("q") || input.equals("Q")){
                break;
            }
            //choisir l'attribut en fonction du choix
            if(Utility.isStringInt(input)){
                int numericInput = Integer.parseInt(input);
                switch(numericInput){
                    case 1 :
                        this.editerDexterite();
                        break;
                    case 2 :
                        this.editerForce();
                        break;
                    case 3 :
                        this.editerResistance();
                        break;
                    case 4 :
                        this.editerConstitution();
                        break;
                    case 5 :
                        this.editerInitiative();
                        break;
                    case 6 :
                        this.editerStrategie();
                        break;
                    case 7 :
                        this.editerReserviste();
                        break;
                    default :
                        System.out.println("Erreur : valeur entrée non valide");
                        Utility.sleep(2500);
                        break;
                }
            }else{
                System.out.println("Erreur : type de donnée non valide");
                Utility.sleep(2500);
            }
        }
    }

    /**
     * Méthode pour éditer tous les attributs
     */
    public void editerAttributs(){
        Utility.clearTerminal();
        System.out.println("Vous avez choisi de paramétrer tous les attributs de " + this.type + this.idEtudiant);
        Utility.jumpLines(1);
        System.out.println("Solde de points : " + this.joueur.getSoldePoints());
        Utility.jumpLines(2);
        Utility.sleep(2500);
        this.editerDexterite();
        this.editerForce();
        this.editerResistance();
        this.editerConstitution();
        this.editerInitiative();
        this.editerStrategie();
        this.editerReserviste();
        Utility.clearTerminal();
        System.out.println("Fin du paramétrage de " + this.type + this.idEtudiant);
        Utility.jumpLines(2);
        Utility.sleep(2500);
    }

    /**
     * Méthode pour éditer la dextérité
     */
    public void editerDexterite(){
        while(true){
            //affichage
            Utility.clearTerminal();
            System.out.println("Mode Paramétrage d'Etudiant (édition d'un attribut : Dextérité) : " + this.type + this.idEtudiant);
            Utility.jumpLines(1);
            System.out.println("Solde de points : " + this.joueur.getSoldePoints());
            Utility.jumpLines(2);
            System.out.println(this);
            System.out.printf("Entrez la valeur désirée : (MIN = %s, MAX = %s)\n", this.getMinimumDexterite(), this.getMaximumDexterite());
            System.out.println("## note : la valeur entrée écrasera la valeur précédente, au lieu de s'y ajouter ##");
            String input = Utility.promptString();
            if(Utility.isStringInt(input)){
                int numbericInput = Integer.parseInt(input);
                if(this.setDexterite(numbericInput)){
                    break;
                }else{
                    Utility.sleep(2500);
                }
            } else{
                System.out.println("Erreur : type de donnée non valide");
                Utility.sleep(2500);
            }
        }
    }

    /**
     * Méthode pour éditer la force
     */
    public void editerForce(){
        while(true){
            Utility.clearTerminal();
            System.out.println("Mode Paramétrage d'Etudiant (édition d'un attribut : Force) : " + this.type + this.idEtudiant);
            Utility.jumpLines(1);
            System.out.println("Solde de points : " + this.joueur.getSoldePoints());
            Utility.jumpLines(2);
            System.out.println(this);
            System.out.printf("Entrez la valeur désirée : (MIN = %s, MAX = %s)\n", this.getMinimumForce(), this.getMaximumForce());
            System.out.println("## note : la valeur entrée écrasera la valeur précédente, au lieu de s'y ajouter ##");
            String input = Utility.promptString();
            if(Utility.isStringInt(input)){
                int numbericInput = Integer.parseInt(input);
                if(this.setForce(numbericInput) == true){
                    break;
                }else{
                    Utility.sleep(2500);
                }
            } else{
                System.out.println("Erreur : type de donnée non valide");
                Utility.sleep(2500);
            }
        }
    }

    /**
     * Méthode pour éditer la resistance
     */
    public void editerResistance(){
        while(true){
            Utility.clearTerminal();
            System.out.println("Mode Paramétrage d'Etudiant (édition d'un attribut : Résistance) : " + this.type + this.idEtudiant);
            Utility.jumpLines(1);
            System.out.println("Solde de points : " + this.joueur.getSoldePoints());
            Utility.jumpLines(2);
            System.out.println(this);
            System.out.printf("Entrez la valeur désirée : (MIN = %s, MAX = %s)\n", this.getMinimumResistance(), this.getMaximumResistance());
            System.out.println("## note : la valeur entrée écrasera la valeur précédente, au lieu de s'y ajouter ##");
            String input = Utility.promptString();
            if(Utility.isStringInt(input)){
                int numbericInput = Integer.parseInt(input);
                if(this.setResistance(numbericInput) == true){
                    break;
                }else{
                    Utility.sleep(2500);
                }
            } else{
                System.out.println("Erreur : type de donnée non valide");
                Utility.sleep(2500);
            }
        }
    }

    /**
     * Méthode pour éditer la constitution
     */
    public void editerConstitution(){
        while(true){
            Utility.clearTerminal();
            System.out.println("Mode Paramétrage d'Etudiant (édition d'un attribut : Constitution) : " + this.type + this.idEtudiant);
            Utility.jumpLines(1);
            System.out.println("Solde de points : " + this.joueur.getSoldePoints());
            Utility.jumpLines(2);
            System.out.println(this);
            System.out.printf("Entrez la valeur désirée : (MIN = %s, MAX = %s)\n", this.getMinimumConstitution(), this.getMaximumConstitution());
            System.out.println("## note : la valeur entrée écrasera la valeur précédente, au lieu de s'y ajouter ##");
            String input = Utility.promptString();
            if(Utility.isStringInt(input)){
                int numbericInput = Integer.parseInt(input);
                if(this.setConstitution(numbericInput) == true){
                    break;
                }else{
                    Utility.sleep(2500);
                }
            } else{
                System.out.println("Erreur : type de donnée non valide");
                Utility.sleep(2500);
            }
        }
    }

    /**
     * Méthode pour éditer la l'initiative
     */
    public void editerInitiative(){
        while(true){
            Utility.clearTerminal();
            System.out.println("Mode Paramétrage d'Etudiant (édition d'un attribut : Initiative) : " + this.type + this.idEtudiant);
            Utility.jumpLines(1);
            System.out.println("Solde de points : " + this.joueur.getSoldePoints());
            Utility.jumpLines(2);
            System.out.println(this);
            System.out.printf("Entrez la valeur désirée : (MIN = %s, MAX = %s)\n", this.getMinimumInitiative(), this.getMaximumInitiative());
            System.out.println("## note : la valeur entrée écrasera la valeur précédente, au lieu de s'y ajouter ##");
            String input = Utility.promptString();
            if(Utility.isStringInt(input)){
                int numbericInput = Integer.parseInt(input);
                if(this.setInitiative(numbericInput) == true){
                    break;
                }else{
                    Utility.sleep(2500);
                }
            } else{
                System.out.println("Erreur : type de donnée non valide");
                Utility.sleep(2500);
            }
        }
    }

    /**
     * Méthode pour éditer la stratégie
     */
    public void editerStrategie(){
        while(true){
            Utility.clearTerminal();
            System.out.println("Mode Paramétrage d'Etudiant (édition d'un attribut : Stratégie) : " + this.type + this.idEtudiant);
            Utility.jumpLines(1);
            System.out.println(this);
            System.out.println("Choisissez la stratégie que vous voulez attribuer :");
            System.out.println("(1) : Attaque");
            System.out.println("(2) : Défense");
            System.out.println("(3) : Aléatoire");
            String input = Utility.promptString();
            if(Utility.isStringInt(input)){
                int numericInput = Integer.parseInt(input);
                if(this.setStrategie(numericInput) == true){
                    break;
                }
            } else{
                Utility.sleep(2500);
            }
        }
    }

    /**
     * Méthode pour éditer le statut de réserviste
     */
    public void editerReserviste(){
        boolean editSuccessful = false;
        while(editSuccessful == false){
            Utility.clearTerminal();
            System.out.println("Mode Paramétrage d'Etudiant (édition d'un attribut : statut de réserviste) : " + this.type + this.idEtudiant);
            Utility.jumpLines(4);
            System.out.println(this);
            System.out.println("Modifier le statut de réserviste de ce pion ?");
            System.out.println("(O) : OUI");
            System.out.println("(N) : NON");
            String input = Utility.promptString();
            switch(input){
                case "O" :
                case "o" :
                case "Y" :
                case "y" :
                    this.setReserviste(true);
                    editSuccessful = true;
                    break;
                case "N" :
                case "n" :
                    this.setReserviste(false);
                    editSuccessful = true;
                    break;
                default :
                    System.out.println("Erreur : valeur entrée non valide");
                    Utility.sleep(2500);
                    break;
            }
        }
    }

    /**
     * Méthode pour afficher le nom des caractéristiques
     * @return affichage des caractéristiques
     */
    public static String etudiantColumnHeaders(){
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("%2s", "id"));
        sb.append(String.format("%13s", "Type"));
        sb.append(String.format("%13s", "Crédits"));
        sb.append(String.format("%13s", "Dextérité"));
        sb.append(String.format("%13s", "Force"));
        sb.append(String.format("%13s", "Résistance"));
        sb.append(String.format("%13s", "Constitution"));
        sb.append(String.format("%13s", "Initiative"));
        sb.append(String.format("%13s", "Stratégie"));
        sb.append(String.format("%13s", "Réserviste"));
        sb.append(String.format("%20s", "Zone"));
        sb.append("\n");
        for(int i = 0; i < 139; i++){
            sb.append("-");
        }
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Méthode pour afficher les caractéristiques d'un étudiant
     * @return affichage des caractéristiques de l'étudiant
     */
    public String etudiantOneLiner(){
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("%2s", this.idEtudiant));
        sb.append(String.format("%13s", this.type));
        sb.append(String.format("%13s", this.credits));
        sb.append(String.format("%13s", this.dexterite));
        sb.append(String.format("%13s", this.force));
        sb.append(String.format("%13s", this.resistance));
        sb.append(String.format("%13s", this.constitution));
        sb.append(String.format("%13s", this.initiative));
        if(this.strategie instanceof Attaque){
            sb.append(String.format("%13s", "Attaque"));
        }else if(this.strategie instanceof Defense){
            sb.append(String.format("%13s", "Defense"));
        }else if(this.strategie instanceof StrategieAleatoire){
            sb.append(String.format("%13s", "Aléatoire"));
        }else{
            sb.append(String.format("%13s", "NULL"));
        }
        if(this.reserviste == true){
            sb.append(String.format("%13s", "OUI"));
        }else if(this.reserviste == false){
            sb.append(String.format("%13s", "NON"));
        }
        if(this.zoneEtudiant == null){
            sb.append(String.format("%20s", "NULL"));
        } else{
            sb.append(String.format("%20s", this.zoneEtudiant.getNomZone()));
        }
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Méthode pour afficher un étudiant et ses caractéristiques
     * @return affichage de l'étudiant
     */
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append(etudiantColumnHeaders());
        sb.append(this.etudiantOneLiner());
        return sb.toString();
    }

    //getters and setters

    /**
     * Getter crédits
     * @return nb crédits de l'étudiant
     */
    public int getCredits() {
        return this.credits;
    }

    /**
     * Setter crédits
     * @param credits nb credits de l'étudiant
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * Getter ID étudiant
     * @return ID de l'étudiant
     */
    public int getIdEtudiant(){
        return this.idEtudiant;
    }

    /**
     * Getter dextérité
     * @return nb pts de dextérité de l'étudiant
     */
    public int getDexterite() {
        return this.dexterite;
    }

    /**
     * Setter dexterite
     * @param nouvelleDexterite nb dexterite de l'étudiant
     */
    public boolean setDexterite(int nouvelleDexterite) {
        int ancienneDexterite = this.dexterite;
        if(nouvelleDexterite >= getMinimumDexterite() && nouvelleDexterite <= getMaximumDexterite()){
            if(this.joueur.getSoldePoints() > nouvelleDexterite){
                this.dexterite = nouvelleDexterite;
                this.joueur.setSoldePoints(this.joueur.getSoldePoints() + (ancienneDexterite - nouvelleDexterite));
                return true;
            } else {
                System.out.println("Erreur : votre solde de points est insuffisant");
                return false;
            }
        } else {
            System.out.println("Erreur : rentrez une valeur entre " + getMinimumDexterite() + " et " + getMaximumDexterite());
            return false;
        }
    }

    /**
     * Getter force
     * @return nb pts de force de l'étudiant
     */
    public int getForce() {
        return this.force;
    }

    /**
     * Setter force
     * @param nouvelleForce nb force de l'étudiant
     */
    public boolean setForce(int nouvelleForce) {
        int ancienneForce = this.force;
        if(nouvelleForce >= getMinimumForce() && nouvelleForce <= getMaximumForce()){
            if(this.joueur.getSoldePoints() > nouvelleForce){
                this.force = nouvelleForce;
                this.joueur.setSoldePoints(this.joueur.getSoldePoints() + (ancienneForce - nouvelleForce));
                return true;
            } else {
                System.out.println("Erreur : votre solde de points est insuffisant");
                return false;
            }
        } else {
            System.out.println("Erreur : rentrez une valeur entre " + getMinimumForce() + " et " + getMaximumForce());
            return false;
        }
    }

    /**
     * Getter resistance
     * @return nb pts de resistance de l'étudiant
     */
    public int getResistance() {
        return this.resistance;
    }

    /**
     * Setter resistance
     * @param nouvelleResistance nb resistance de l'étudiant
     */
    public boolean setResistance(int nouvelleResistance) {
        int ancienneResistance = this.resistance;
        if(nouvelleResistance >= getMinimumResistance() && nouvelleResistance <= getMaximumResistance()){
            if(this.joueur.getSoldePoints() > nouvelleResistance){
                this.resistance = nouvelleResistance;
                this.joueur.setSoldePoints(this.joueur.getSoldePoints() + (ancienneResistance - nouvelleResistance));
                return true;
            } else {
                System.out.println("Erreur : votre solde de points est insuffisant");
                return false;
            }
        } else {
            System.out.println("Erreur : rentrez une valeur entre " + getMinimumResistance() + " et " + getMaximumResistance());
            return false;
        }
    }

    /**
     * Getter constitution
     * @return nb pts de constitution de l'étudiant
     */
    public int getConstitution() {
        return this.constitution;
    }

    /**
     * Setter constitution
     * @param nouvelleConstitution nb constitution de l'étudiant
     */
    public boolean setConstitution(int nouvelleConstitution) {
        int ancienneConstitution = this.constitution;
        if(nouvelleConstitution >= getMinimumConstitution() && nouvelleConstitution <= getMaximumConstitution()){
            if(this.joueur.getSoldePoints() > nouvelleConstitution - ancienneConstitution){
                this.constitution = nouvelleConstitution;
                this.joueur.setSoldePoints(this.joueur.getSoldePoints() + (ancienneConstitution - nouvelleConstitution));
                return true;
            } else {
                System.out.println("Erreur : votre solde de points est insuffisant");
                return false;
            }
        } else {
            System.out.println("Erreur : rentrez une valeur entre " + getMinimumConstitution() + " et " + getMaximumConstitution());
            return false;
        }
    }

    /**
     * Getter intitiative
     * @return nb pts de intitiative de l'étudiant
     */
    public int getInitiative() {
        return this.initiative;
    }

    /**
     * Setter Initiative
     * @param nouvelleInitiative nb Initiative de l'étudiant
     */
    public boolean setInitiative(int nouvelleInitiative) {
        int ancienneInitiative = this.initiative;
        if(nouvelleInitiative >= getMinimumInitiative() && nouvelleInitiative <= getMaximumInitiative()){
            if(this.joueur.getSoldePoints() > nouvelleInitiative - ancienneInitiative){
                this.initiative = nouvelleInitiative;
                this.joueur.setSoldePoints(this.joueur.getSoldePoints() + (ancienneInitiative - nouvelleInitiative));
                return true;
            } else {
                System.out.println("Erreur : votre solde de points est insuffisant");
                return false;
            }
        } else {
            System.out.println("Erreur : rentrez une valeur entre " + getMinimumInitiative() + " et " + getMaximumInitiative());
            return false;
        }
    }

    /**
     * Getter stratégie
     * @return statut stratégie de l'étudiant
     */
    public Strategie getStrategie() {
        return this.strategie;
    }

    /**
     * Getter nom stratégie
     * @return nom stratégie de l'étudiant
     */
    public String getNomStrategie(){
        return this.strategie.getNomStrategie();
    }

    /**
     * Setter Strategie
     * @param nouvelleStrategie Strategie de l'étudiant
     */
    public boolean setStrategie(int nouvelleStrategie) {
        switch(nouvelleStrategie){
            case 1 :
                this.strategie = new Attaque();
                return true;
            case 2 :
                this.strategie = new Defense();
                return true;
            case 3 :
                this.strategie = new StrategieAleatoire();
                return true;
            default :
                System.out.println("Erreur : valeur entrée non valide");
                return false;
        }
    }

    /**
     * Getter joueur
     * @return joueur de l'étudiant
     */
    public Joueur getJoueur() {
        return this.joueur;
    }

    /**
     * Setter joueur
     * @param joueur joueur de l'étudiant
     */
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    /**
     * Méthode pour connaitre statut reserviste
     * @return vrai si reserviste, faux sinon
     */
    public boolean isReserviste() {
        return this.reserviste;
    }

    /**
     * Getter statut reserviste
     * @return statut reserviste de l'étudiant
     */
    public boolean getReserviste() {
        return this.reserviste;
    }

    public String getStringReserviste() {
        if(this.reserviste == true){
            return "OUI";
        } else{
            return "NON"; 
        }
    }

    /**
     * Setter statut de réserviste
     * @param nouveauStatut statut de réserviste de l'étudiant
     */
    public void setReserviste(boolean nouveauStatut) {

        if(nouveauStatut == true && this.reserviste == false){
            if(this.getJoueur().getNbReserviste() < 5){
                this.reserviste = true;
            } else {
                System.out.println(" Vous avez atteint le nombre maximal de réservistes autorisés !");
            }
        }else if(nouveauStatut == false && this.reserviste == true){
            this.reserviste = false;
        }
    }

    public void setReserviste(int nouveauStatut){
        switch(nouveauStatut){
            case 0 :
                this.reserviste = true;
                break;
            case 1 : 
                this.reserviste = false;
                break;
        }   
    }


    /**
     * Getter zone étudiant
     * @return zone
     */
    public Zone getZone() {
        return this.zoneEtudiant;
    }

    /**
     * Getter nom zone de l'étudiant
     * @return nom de la zone
     */
    public String getNomZone(){
        return this.zoneEtudiant.getNomZone();
    }

    /**
     * Setter zone de l'étudiant
     * @param zone zone de l'étudiant
     */
    public void setZone(Zone zone) {
        this.zoneEtudiant = zone;
        Zone objetZone = this.getJoueur().getPartie().getListZones().get(getJoueur().getPartie().getListZones().indexOf(zone));
        objetZone.attribuerEtudiant(this);
    }

    /**
     * Getter type de l'étudiant
     * @return ntype de l'étudiant
     */
    public String getType(){
        return this.type;
    }

    /**
     * Getter minimum de dextérité
     * @return entier minimum de dextérité
     */
    public int getMinimumDexterite(){
        int minimumDexterite = 0;
        if(this instanceof Elite){
            minimumDexterite += 1;
        }
        if(this instanceof MaitreGobi){
            minimumDexterite += 2;
        }
        return minimumDexterite;
    }

    /**
     * Getter minimum de force
     * @return entier minimum de force
     */
    public int getMinimumForce(){
        int minimumForce = 0;
        if(this instanceof Elite){
            minimumForce += 1;
        }
        if(this instanceof MaitreGobi){
            minimumForce += 2;
        }
        return minimumForce;
    }

    /**
     * Getter minimum de resistance
     * @return entier minimum de resistance
     */
    public int getMinimumResistance(){
        int minimumResistance = 0;
        if(this instanceof Elite){
            minimumResistance += 1;
        }
        if(this instanceof MaitreGobi){
            minimumResistance += 2;
        }
        return minimumResistance;
    }

    /**
     * Getter minimum de constitution
     * @return entier minimum de constitution
     */
    public int getMinimumConstitution(){
        int minimumConstitution = 0;
        if(this instanceof Elite){
            minimumConstitution += 5;
        }
        if(this instanceof MaitreGobi){
            minimumConstitution += 10;
        }
        return minimumConstitution;
    }

    /**
     * Getter minimum de intitiative
     * @return entier minimum de intitiative
     */
    public int getMinimumInitiative(){
        int minimumInitiative = 0;
        if(this instanceof Elite){
            minimumInitiative += 1;
        }
        if(this instanceof MaitreGobi){
            minimumInitiative += 2;
        }
        return minimumInitiative;
    }

    /**
     * Getter max de dexterite
     * @return entier max de dexterite
     */
    public int getMaximumDexterite(){
        int maximumDexterite = 10;
        if(this instanceof Elite){
            maximumDexterite += 1;
        }
        if(this instanceof MaitreGobi){
            maximumDexterite += 2;
        }
        return maximumDexterite;
    }

    /**
     * Getter max de force
     * @return entier max de force
     */
    public int getMaximumForce(){
        int maximumForce = 10;
        if(this instanceof Elite){
            maximumForce += 1;
        }
        if(this instanceof MaitreGobi){
            maximumForce += 2;
        }
        return maximumForce;
    }

    /**
     * Getter max de resistance
     * @return entier max de resistance
     */
    public int getMaximumResistance(){
        int maximumResistance = 10;
        if(this instanceof Elite){
            maximumResistance += 1;
        }
        if(this instanceof MaitreGobi){
            maximumResistance += 2;
        }
        return maximumResistance;
    }

    /**
     * Getter max de constitution
     * @return entier max de constitution
     */
    public int getMaximumConstitution(){
        int maximumConstitution = 30;
        if(this instanceof Elite){
            maximumConstitution += 5;
        }
        if(this instanceof MaitreGobi){
            maximumConstitution += 10;
        }
        return maximumConstitution;
    }

    /**
     * Getter max de initiative
     * @return entier max de initiative
     */
    public int getMaximumInitiative(){
        int maximumInitiative = 10;
        if(this instanceof Elite){
            maximumInitiative += 1;
        }
        if(this instanceof MaitreGobi){
            maximumInitiative += 2;
        }
        return maximumInitiative;
    }
}
