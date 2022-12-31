package projet_lo02.model;

import java.util.Random;
import java.util.Scanner;

/**
 * Classe Utility
 * @author Julian Marques
 * @author Raphaël Weis
 */
public final class Utility {

    /**
     * Attribut pour le scanner (entrée sur le terminal)
     */
    public static Scanner userInput = new Scanner(System.in);

    /**
     * Constructeur Utility
     */
    private Utility(){
        throw new java.lang.UnsupportedOperationException("Cette classe est une classe d'utilité est ne peut pas être instanciée.");
    }

    /**
     * Méthode pour obtenir un réel aléatoire dans un intervalle donné
     * @param  min minimum de l'intervalle
     * @param  max maximum de l'intervalle
     * @param  inclusiviteInf savoir si on prend la borne inf
     * @param  inclusiviteSup savoir si on prend la borne sup
     * @return un réel inclus dans l'intervalle
     */
    public static double getRandomDouble(double min, double max, Boolean inclusiviteInf, Boolean inclusiviteSup){
        double randomNumber;
        Random rnd = new Random();
        if(inclusiviteInf == true && inclusiviteSup == true){
            randomNumber = (rnd.nextDouble() * (max - min)) + min; //generation d'un nombre aleatoire entre les 2 bornes inclusives
            return randomNumber;
        }else if(inclusiviteInf == false && inclusiviteSup == false){//si il faut changer l'inclusivite on lance une nouvelle fois le calcul tant que on tombe sur une borne exclue.
            do{
                randomNumber = (rnd.nextDouble() * (max - min)) + min;
            }while(randomNumber == min && randomNumber == max);
            return randomNumber;
        }else if(inclusiviteInf == false && inclusiviteSup == true){
            do{
            randomNumber = (rnd.nextDouble() * (max - min)) + min;
            }while(randomNumber == min);
            return randomNumber;
        }else{ //if(inclusiviteInf == true && inclusivite Sup == false)
            do{
                randomNumber = (rnd.nextDouble() * (max - min)) + min;
            }while(randomNumber == max);
            return randomNumber;
        }
    }

    /**
     * Obtenir la partie entière d'un réel
     * @param n un réel
     * @return un entier
     */
    public static int getPartieEntiere(double n){
        double partieDecimale = n % 1;
        return (int)(n - partieDecimale);
    }

    /**
     * Méthode pour clear le terminal
     */
    public static void clearTerminal(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        for(int i = 0; i < 60; i++){
            System.out.print("=");
        }
        System.out.print(" C'est du brutal ! ");
        for(int i = 0; i < 60; i++){
            System.out.print("=");
        }
        jumpLines(2);
    }

    /**
     * Méthode pour sauter des lignes
     * @param  nombreLignes nb de lignes à sauter
     */
    public static void jumpLines(int nombreLignes){
        for(int i = 0; i < nombreLignes; i++){
            System.out.println();
        }
    }

    /**
     * Méthode pour temporiser une action
     */
    public static void sleep(int time){
        try{
            Thread.sleep(time);
        }catch(Exception e){}
    }

    /**
     * Méthode pour afficher une flèche sur le prompt
     * @return "->"
     */
    public static String promptString(){
        jumpLines(1);
        System.out.print("-> ");
        return userInput.next();
    }

    /**
     * Méthode pour checker un string
     * @param stringToCheck string à vérifier
     * @return vrai si vérifié, faux sinon
     */
    public static boolean isStringInt(String stringToCheck){
        if(stringToCheck.matches("-?\\d+")){
            return true;
        } else{
            return false;
        }
    }
}
