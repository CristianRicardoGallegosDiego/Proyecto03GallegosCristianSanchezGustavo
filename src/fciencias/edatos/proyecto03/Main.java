package fciencias.edatos.proyecto03;

import java.util.Timer;
import java.util.Scanner;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("BIENVENIDO AL JUEGO DEL MINUTO :D\n \nIngrese su nombre jugador 1: ");
        Scanner sc = new Scanner(System.in);
        String jugadorUno = sc.nextLine();
        System.out.println("Ingrese su nombre jugador 2: ");
        String jugadorDos = sc.nextLine();
        runGame(jugadorUno, jugadorDos);
        boolean terminaEjecucion=false;
        //while(!terminaEjecucion){
        //    System.out.println();
        //}
       
    }

    public static void runGame(String jugadorUno, String jugadorDos){
        System.out.println("Empezemos a jugar jugador "+ jugadorUno+ ": \n \n ");
        Scanner sc = new Scanner(System.in);
        Game juego = new Game(jugadorUno, jugadorUno);
        String[] playerOne = juego.getSecuenciaPlayerOne();
        String corridaUno = printSecuence(playerOne);
        System.out.print("DEBERAS FORMAR TODAS LAS POSIBLES PALABRAS CON LA SIGUIENTES LETRAS: "+ corridaUno+ 
                        "SOLO TIENES UN MINUTO, TU PUEDES!!!\n");
        System.out.print("EL JUEGO COMIENZA EN 5 SEGUNDOS PREPARATE :D");
        try {
            System.out.println();
            Thread.sleep(5*1000);
        } catch (Exception e) {
            System.out.println("ALGO OCURRIO :(( LO SENTIMOS");
        }
        System.out.println("INICIAAAAAAAA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;
        while (elapsedTime < 60*1000) { //60
            System.out.println("\nCADENA: "+corridaUno+"");
            System.out.println("INGRESA PALABRA: ");
            String palabra = sc.nextLine();
            String nueva = palabra.toLowerCase();
            String[] temporal = new String[nueva.length()];
            for (int i = 0; i < temporal.length; i++){
                temporal[i] = Character.toString(nueva.charAt(i));
            }
            if(verifica(temporal, playerOne)){
                if(juego.getDictionary().contains(nueva)){
                    System.out.println("VERDEROOOOOOOOOOOO");
               }
            }
            
            elapsedTime = (new Date()).getTime() - startTime;
            System.out.println("TIEMPO TRANSCURRIDO: "+elapsedTime/1000+ " seg.\n");
        }
        String[] playerTwo = juego.getSecuenciaPlayerTwo();
    }

    /**
     * METODO QUE REGRESA LA SECUENCIA DE LETRAS QUE HAY EN CADA JUGADOR
     * @param random es el arreglo al que se le suma todas sus letras
     * @return la secuencia que deberia formar 
     */
    public static String printSecuence(String[] random){
        String letras = "";
        for (int i = 0; i < random.length; i++) {
            letras=letras+random[i]+", ";
        }
        return letras;
    } 

    public static boolean verifica(String[] arreglo, String[] verdadero){
        boolean contiene = false;
        for (int i = 0; i < arreglo.length; i++) {
            for (int j = 0; j < verdadero.length; j++) {
                if(arreglo[i].equals(arreglo[j])){
                    contiene=true;
                    j=0;
                    if(i==arreglo.length-1){
                        break;
                    }
                    i++;
                }else{
                    contiene=false;
                }
            }
        }
        return contiene;
    }
}
