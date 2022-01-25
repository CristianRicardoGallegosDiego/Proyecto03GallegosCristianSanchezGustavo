package fciencias.edatos.proyecto03;

import java.io.File;
import java.util.Scanner;

public class Dictionary {

    private final Map<String, AVLTree> map = new AbstractHashMap<>(27); 

    public Dictionary(){
        readTxt("src/fciencias/edatos/proyecto03/Diccionario.txt");
    }
    
    /**
     * METODO DE REGRESO PARA PODER OBTENER EL MAPA DONDE ESTA EL DICCIONARIO
     * @return un mapa con los diccionarios
     */
    public Map<String, AVLTree> getMap(){
        return this.map;
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
                /**String line = sc.nextLine();
                String[] split = line.split(",");
                Element temporal = new Element(split[0], split[1], Double.parseDouble(split[2]));
                actual.put(split[1], temporal);*/
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