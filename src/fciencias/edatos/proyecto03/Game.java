package fciencias.edatos.proyecto03;
 
import java.util.Random;
import java.util.Hashtable;

public class Game {

    private String jugadorUno; 
    private String[] jugador1;
    private int puntuacionUno;
    private Dictionary diccionario;

    public Game(String jugadorUno){
        this.jugadorUno = jugadorUno;
        this.jugador1 = lettersRandom();
        diccionario = new Dictionary();
    }

    /**
     * Metodo que crea las palabras al azar
     * @return
     */
    public String[] lettersRandom(){
        char[] vocales ={'a', 'e', 'i', 'o', 'u'};
        Random r = new Random();
        String array[] = new String[10];
        for(int i=0; i<10; i++) {
            int caseOne = r.nextInt(2);
            switch (caseOne) {
                case 0:
                    int valor = r.nextInt(5);
                    array[i]=String.valueOf(vocales[valor]);
                    break;
                case 1:
                    int valorLetra = (r.nextInt(25)+97);
                    char temporal = (char)valorLetra;
                    array[i]= String.valueOf(temporal);
                    break;
                default:
                    break;
            }
            
        }
        return array;
    }
    public Hashtable<String, String> getDictionary(){
        return diccionario.getDictionary();
    }

    public String[] getSecuenciaPlayerOne(){
        return jugador1;
    }
}
