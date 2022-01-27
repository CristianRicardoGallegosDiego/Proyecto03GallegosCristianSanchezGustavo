package fciencias.edatos.proyecto03;

import java.io.File;
import java.util.Hashtable;
import java.util.Scanner;

public class Dictionary {

    private Hashtable<String, String> diccionario = new Hashtable<String, String>(646618);

    public Dictionary(){
        readTxt("src/fciencias/edatos/proyecto03/Diccionario.txt");
    }

    /**
     * Metodo que nos regresa el diccionario con todas las posibles palabras que hay.
     * @return un Hastable con todas las posibles palabras que hay en español.
     */
    public Hashtable<String, String> getDictionary(){
        return diccionario;
    }

    /**
     * METODO QUE HACE LA LECTURA DEL DICCIONARIO.
     * @param path es la ruta donde se encuentra el diccionario
     */
    public void readTxt(String path){
        File  archivo = new File(path);
        Scanner sc = null;
        try {
            sc = new Scanner(archivo);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                diccionario.put(line, line);
            }                    
        } catch (Exception e) {
            System.out.println("Chin, ocurrio un error, por favor reinicia el programa. Si el problema persiste favor de contactarnos");
        }finally{
            //CERRAMOS EL FICHERO
            try {
                if (sc != null){sc.close();}
            } catch (Exception e) {
                System.out.println("Chin, ocurrio un error, por favor reinicia el programa. Si el problema persiste favor de contactarnos");
            }
        }
    }
}