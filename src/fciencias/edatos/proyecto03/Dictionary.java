package fciencias.edatos.proyecto03;

import java.io.File;
import java.util.Scanner;

public class Dictionary {

    private MapTree diccionario = new MapTree();

    public Dictionary(){
        readTxt("src/fciencias/edatos/proyecto03/Diccionario.txt");
    }

    public MapTree getDictionary(){
        return diccionario;
    }
    
    /**
     * METODO DE REGRESO PARA PODER OBTENER EL MAPA DONDE ESTA EL DICCIONARIO
     * @return un mapa con los diccionarios
     */

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