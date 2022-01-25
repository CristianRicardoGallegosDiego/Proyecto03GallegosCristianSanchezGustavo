package fciencias.edatos.proyecto03;

import java.util.Random;
import java.text.Normalizer;

/**
* Implementación básica de un HashMap.
* @author Emmanuel Cruz Hernández.
* @version 2.0 Enero 2022. Anterior 1.0 Enero 2021.
* @since Estructuras de Datos 2022-1.
*/
public class AbstractHashMap<K,V> implements Map<K,V>{

	/** Arreglo de elementos. */
	private V[] table;

	/** Capacidad de la tabla. */
	private int capacity;

	/** Factor primo para calcular longitudes. */
	private int prime;

	/** Cantidad del cambio y escala. */
	private long scale, shift;

	/**
	* Crea un nuevo AbstractHashMap. 
	* @param cap la capacidad de la tabla.
	* @param p el factor primo.
	*/
	public AbstractHashMap(int cap, int p){
		table = (V[]) new Object[cap];
		prime = p;
		capacity = cap;
		Random rn = new Random();
		scale = rn.nextInt(prime-1) + 1;
		shift = rn.nextInt(prime);
	}

	/**
	* Crea un nuevo AbstractHashMap.
	* @param cap la capacidad de la tabla.
	*/
	public AbstractHashMap(int cap){
		this(cap, 109345121);
	}

	/**
	* Crea un nuevo AbstractHashMap.
	*/
	public AbstractHashMap(){
		this(17);
	}

	@Override
	public int size(){
		// Tarea moral
        int size = 0;
		for (int i=0; i<table.length; i++) {
			if (table[i] != null) {
				size++;
			}
		}
		return size;
	}

	@Override
	public V get(K key){
		int pos = hashFuction(key);
		return table[pos];
	}

	@Override
	public V put(K key, V value){
		int pos = hashFuction(key);
		//System.out.println("Valor: "+value+"\nPosicion: "+pos);
		V oldValue = table[pos];
		if(oldValue != null){
			System.out.println("\nREPETIDO\n");
		}
		table[pos] = value;
		return oldValue;
	}

	@Override
	public V remove(K key){
		int pos = hashFuction(key);
		V oldValue = table[pos];
		table[pos] = null;
		return oldValue;
	}

	@Override
	public boolean isEmpty(){
		// Tarea moral
		for (int i=0; i<table.length; i++) {
			if (table[i] != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Funcion hash
	 * @param k la clave
	 * @return un entero asociado a la clave dentro de un rango válido
	 */
	private int hashFuction(K k){
		String hashCode = (String)k;
		hashCode = hashCode.substring(0, 1);
		hashCode = cleanString(hashCode);
		char nuevo = hashCode.charAt(0);
		//////////////////////////////////// PELIGROOOOOOOOO //////////////////////////////// FALTA
		return 10; 
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