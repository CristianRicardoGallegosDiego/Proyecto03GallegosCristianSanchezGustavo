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
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;
        while (elapsedTime < 10*1000) { //60
            System.out.println("CADENA: "+corridaUno+"");
            System.out.print("INGRESA PALABRA: ");
            String palabra = sc.nextLine();
            elapsedTime = (new Date()).getTime() - startTime;
            System.out.println("TIEMPO TRANSCURRIDO: "+elapsedTime/1000+ " seg.\n");
        }
        String[] playerTwo = juego.getSecuenciaPlayerTwo();
    }

    public static String printSecuence(String[] random){
        String letras = "";
        for (int i = 0; i < random.length; i++) {
            letras=letras+random[i]+", ";
        }
        return letras;
    } 
}
