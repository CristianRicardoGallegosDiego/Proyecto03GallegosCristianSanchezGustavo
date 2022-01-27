package fciencias.edatos.proyecto03;

import java.util.Timer;
import java.util.Scanner;
import java.util.Date;
import java.text.Normalizer;

public class Main {
    public static void main(String[] args) {
        System.out.println("BIENVENIDO AL JUEGO DEL MINUTO :D\n \nINGRESE SU NOMBRE : ");
        Scanner sc = new Scanner(System.in);
        String jugadorUno = sc.nextLine();
        boolean terminaEjecucion=false;
        System.out.println("¿QUE DESEA HACER?\n1. JUGAR 1 MINUTO\n2. VER LAS MEJORES ESTADISTICAS\n3. SALIR");
        while(!terminaEjecucion){
            String decision= sc.nextLine();
            decision=decision.replaceAll("\\s+","");
            if(decision.equals("1")){
                System.out.println("¿Desea que la maquina genere las letras random, o quiere ingresar 9 LETRAS SIN SIGNOS DE PUNTUACION?"
                                    +"\n1. MAQUINA GENERE RANDOM\n2. INGRESAR SOLO 9 LETRAS");
                boolean asegurate=true;
                while(asegurate){
                    String recibe = sc.nextLine();
                    recibe=recibe.replaceAll("\\s+","");
                    if(recibe.equals("1")){
                        runGame(jugadorUno, true, null);
                        asegurate=false;
                    }else if(recibe.equals("2")){
                        System.out.println("INGRESA SOLO 9 LETRAS, LAS QUE TU QUIERAS");
                        boolean reinicia = true;
                        while(reinicia){
                            String personal = sc.nextLine();
                            personal=personal.replaceAll("\\s+","");
                            personal=personal.toLowerCase();
                            personal=cleanString(personal);
                            if(personal.length()!=9 || !contieneSoloLetras(personal)){
                                System.out.println("INGRESA SOLO 9 LETRAS, LAS QUE TU QUIERAS");
                            }else{
                                String[] texto= new String[9];
                                for (int i = 0; i < texto.length; i++) {
                                    texto[i]=Character.toString(personal.charAt(i));
                                }
                                runGame(jugadorUno, false, texto);
                                reinicia=false;
                            }
                        }
                        asegurate=false;
                    }else{
                        System.out.println("INGRESA SOLO EL NUMERO :D, LAS OPCIONES SON: \n1. MAQUINA GENERE RANDOM\n2. INGRESAR SOLO 9 LETRAS");
                    }
                }
                System.out.println("¿QUE MAS DESEA HACER?\n1. JUGAR 1 MINUTO\n2. VER LAS MEJORES ESTADISTICAS\n3. SALIR");
            }else if(decision.equals("2")){
                //FALTAAAAAA
                //--------
                System.out.println("¿QUE MAS DESEA HACER?\n1. JUGAR 1 MINUTO\n2. VER LAS MEJORES ESTADISTICAS\n3. SALIR");
            }else if(decision.equals("3")){
                System.out.println("NOS VEMOS :D, CUIDATE!!!!");
                terminaEjecucion=true;
            }else{
                System.out.println("ESCIRBE SOLO UN NUMERO :))), LAS OPCIONES SON:\n1.JUGAR 1 MINUTO\n2. VER LAS MEJORES ESTADISTICAS\n3. SALIR");
            }
        }
       
    }

    /**
     * Metodo que nos dice sin contien solo cadenas
     * @param cadena es el texto al que se le quiere sacar 
     * @return truw si solo contien letras, false en otro caso
     */
    public static boolean contieneSoloLetras(String cadena) {
        for (int x = 0; x < cadena.length(); x++) {
            char c = cadena.charAt(x);
            // Si no está entre a y z, ni entre A y Z, ni es un espacio
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                return false;
            }
        }
        return true;
    }

    /**
     * METODO QUE QUITA LOS ACENTOS 
     * @param texto texto al que se le quitan los acentos 
     * @return
     */
    public static String cleanString(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return texto;
    }

    /**
     * METODO QUE NOS DICE QUE HACER 
     * @param jugadorUno nombre del jugador
     * @param conCualJugar, true si la maquina genera las letras, false si el jugador lo hace
     * @param arreglo es el arreglo si se decide que va a jugar el jugador
     */
    public static void runGame(String jugadorUno, boolean conCualJugar, String[] arreglo){
        System.out.println("Empezemos a jugar jugador "+ jugadorUno+ ": \n \n ");
        Scanner sc = new Scanner(System.in);
        Game juego = new Game(jugadorUno);
        String[] playerOne;
        if(conCualJugar){
            playerOne = juego.getSecuenciaPlayerOne();
        }else{
            playerOne = arreglo;
        }

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
