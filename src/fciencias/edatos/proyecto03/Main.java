package fciencias.edatos.proyecto03;

import java.util.Timer;
import java.util.Scanner;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("BIENVENIDO AL JUEGO DEL MINUTO :D\n \nINGRESE SU NOMBRE : ");
        Scanner sc = new Scanner(System.in);
        String jugadorUno = sc.nextLine();
        boolean terminaEjecucion=false;
        System.out.println("¿QUE DESEA HACER?\n 1.JUGAR 1 MINUTO\n 2.VER LAS MEJORES ESTADISTICAS\n 3.SALIR");
        while(!terminaEjecucion){
            String decision= sc.nextLine();
            decision=decision.replaceAll("\\s+","");
            if(decision.equals("1")){
                runGame(jugadorUno);
                System.out.println("¿QUE MAS DESEA HACER?\n 1.JUGAR 1 MINUTO\n 2.VER LAS MEJORES ESTADISTICAS\n 3.SALIR");
            }else if(decision.equals("2")){
                System.out.println("¿QUE MAS DESEA HACER?\n 1.JUGAR 1 MINUTO\n 2.VER LAS MEJORES ESTADISTICAS\n 3.SALIR");
                //FALTAAA
            }else if(decision.equals("3")){
                System.out.println("NOS VEMOS :D, CUIDATE!!!!");
                terminaEjecucion=true;
            }else{
                System.out.println("ESCIRBE SOLO UN NUMERO :))), LAS OPCIONES SON:\n1.JUGAR 1 MINUTO\n 2.VER LAS MEJORES ESTADISTICAS\n 3.SALIR");
            }
        }
       
    }

    public static void runGame(String jugadorUno){
        System.out.println("Empezemos a jugar jugador "+ jugadorUno+ ": \n \n ");
        Scanner sc = new Scanner(System.in);
        Game juego = new Game(jugadorUno);
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
        List<String> play = new List<String>(); 
        int indice=0;
        int puntuacion1=0;
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
                //System.out.println("PUEBAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                if(juego.getDictionary().contains(nueva)){
                    //System.out.println("VERDEROOOOOOOOOOOO");
                    if(play!=null){
                        if(!play.contains(nueva)){
                            play.add(indice,nueva);
                            int punt = nueva.length()*nueva.length();
                            puntuacion1=puntuacion1+(punt);
                            System.out.println("PUNTUACIÓN: "+punt);
                            indice++;
                        }
                    }else{
                        play.add(indice,nueva);
                        int punt = nueva.length()*nueva.length();
                        puntuacion1=puntuacion1+(punt);
                        System.out.println("PUNTUACIÓN: "+punt);
                        indice++;
                    }
               }
            }
            elapsedTime = (new Date()).getTime() - startTime;
            System.out.println("TIEMPO TRANSCURRIDO: "+elapsedTime/1000+ " seg.\n");
        }
        System.out.println("\nTU TIEMPO TERMINO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("LA PUNTUACION TOTAL ES: "+ puntuacion1);
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

    /**
     * METODO AUXILIAR QUE NOS AYUDA A VERIFICAR SI LAS PALABRAS INGRESADAS SON IGUALES A LAS YA DADAS
     * @param arreglo es el arreglo con las letras que metio el usuario
     * @param verdadero es el arreglo con las letras ya dadas
     * @return
     */
    public static boolean verifica(String[] arreglo, String[] verdadero){
        boolean contiene = false;
        for (int i = 0; i < arreglo.length; i++) {
            for (int j = 0; j < verdadero.length; j++) {
                if(arreglo[i].equals(verdadero[j])){
                    contiene=true;
                    j=0;
                    if(i==arreglo.length-1){
                        break;
                    }
                    i++;
                }else{
                    contiene=false;
                    if(j==verdadero.length-1){
                        break;
                    }
                }
            }
        }
        return contiene;
    }
}
