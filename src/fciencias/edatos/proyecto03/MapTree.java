package fciencias.edatos.proyecto03;

import java.text.Normalizer;

/**
* Implementación básica de un HashMap.
* @author Emmanuel Cruz Hernández.
* @version 2.0 Enero 2022. Anterior 1.0 Enero 2021.
* @since Estructuras de Datos 2022-1.
*/
public class MapTree{
	
	/** Arreglo de elementos. */
	private AVLTree[] table;

	public MapTree(){
		table = new AVLTree[27];
		for (int i = 0; i < table.length; i++) {
			AVLTree recorre = new AVLTree();
			table[i]=recorre;
		}
	}
	
	public String get(String key){
		int pos = hashFuction(key);
		AVLTree temporal = table[pos];
		return (String)temporal.retrieve(key);
	}

	public void put(String key, String value){
		int pos = hashFuction(key);
		table[pos].insert(value, value);
	}

	public void imprime(){
		for (int i = 0; i < table.length; i++) {
			table[i].preorden();
			System.out.println("\n \n \n");
		}
	}

	/**
	 * Funcion hash
	 * @param k la clave
	 * @return un entero asociado a la clave dentro de un rango válido
	 */
	private int hashFuction(String k){
		k = k.substring(0, 1);
		if (k.equals("ñ")){
			return 26;
		}else{
			k = cleanString(k);
			char nuevo = k.charAt(0);
			return (Character.getNumericValue(nuevo)-10);
		}
	}

	/**
	 * METODO PARA PODER BORRAR ACENTOS
	 * @param texto es la cadena a la que se le quiere quitar acentos
	 * @return
	 */
	private static String cleanString(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return texto;
    }
}