package projet_lo02;

import java.util.Random;
import java.util.Scanner;

public final class Utility {

    public static Scanner userInput = new Scanner(System.in);

    private Utility(){
        throw new java.lang.UnsupportedOperationException("Cette classe est une classe d'utilité est ne peut pas être instanciée.");
    }

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

    public static int getPartieEntiere(double n){
        double partieDecimale = n % 1;
        return (int)(n - partieDecimale);
    }

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

    public static void jumpLines(int nombreLignes){
        for(int i = 0; i < nombreLignes; i++){
            System.out.println();
        }
    }

    public static void sleep(int time){
        try{
            Thread.sleep(time);
        }catch(Exception e){}
    }

    public static String promptString(){
        jumpLines(1);
        System.out.print("-> ");
        return userInput.next();
    }

    public static boolean isStringInt(String stringToCheck){
        if(stringToCheck.matches("-?\\d+")){
            return true;
        } else{
            return false;
        }
    }

    public static void main(String[] args){
        promptString();
    }
}
