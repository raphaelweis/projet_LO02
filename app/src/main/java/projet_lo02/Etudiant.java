package projet_lo02;

public class Etudiant {
    protected int idEtudiant;
    protected String type;
    protected int credits;
    protected int dexterite;
    protected int force;
    protected int resistance;
    protected int constitution;
    protected int initiative;
    private Strategie strategie;
    private Joueur joueur;
    private boolean reserviste;
    private Zone zoneEtudiant;

    //constructeur
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
        this.zoneEtudiant = null;
        this.credits = 30 + this.constitution;
        this.joueur = joueur;
        this.zoneEtudiant = null;
    }

    public void editerAttribut(){
        while(true){
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

    public void editerDexterite(){
        while(true){
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
                if(this.setDexterite(numbericInput) == true){
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
    
    public void attribuerZone(){

    }

    public void positionnerReserviste(){

    }

    public void redeployerTroupe(){

    }

    //a revoir
    // public void executerStrategie(Etudiant actionneur){
    //     this.strategie.executerStrategie(actionneur);
    // }

    //affichage toString

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

    //TODO : TOSTRING POUR LES STRATEGIES
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append(etudiantColumnHeaders());
        sb.append(this.etudiantOneLiner());
        return sb.toString();
    }

    //getters and setters
    
    public int getCredits() {
        return this.credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getIdEtudiant(){
        return this.idEtudiant;
    }

    public int getDexterite() {
        return this.dexterite;
    }

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

    public int getForce() {
        return this.force;
    }

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

    public int getResistance() {
        return this.resistance;
    }

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

    public int getConstitution() {
        return this.constitution;
    }

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

    public int getInitiative() {
        return this.initiative;
    }

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

    public Strategie getStrategie() {
        return this.strategie;
    }

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

    public Joueur getJoueur() {
        return this.joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public boolean isReserviste() {
        return this.reserviste;
    }

    public boolean getReserviste() {
        return this.reserviste;
    }

    public void setReserviste(boolean nouveauStatut) {
        if(nouveauStatut == true && this.reserviste == false){
            this.reserviste = true;
        }else if(nouveauStatut == false && this.reserviste == true){
            this.reserviste = false;
        }
    }

    public Zone getZone() {
        return this.zoneEtudiant;
    }

    public void setZone(Zone zone) {
        this.zoneEtudiant = zone;
    }

    public String getType(){
        return this.type;
    }

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
    
    public static void main(String[] args) {
        Etudiant etuTest = new Etudiant(1, null);
        etuTest.constitution = 10;
        System.out.println(etuTest);
    }
}
