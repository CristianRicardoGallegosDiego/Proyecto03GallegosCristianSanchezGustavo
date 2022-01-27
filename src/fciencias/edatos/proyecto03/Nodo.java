package fciencias.edatos.proyecto03;

/**
 * @author  Gallegos Diego Cristian Ricardo 318114723
 * @author  Sánchez Castro Gustavo 318213888
 * @version 1.0 Octubre 2021.
 * @since Estructuras de datos 2022-1. Prática 2.
 */
public class Nodo<T>{
    private T elemento;
    private Nodo<T> siguiente;
    private Nodo<T> anterior;

    /**
     * Crea un nuevo nodo 
     * @param elemento el elemento que alamcena el nodo
     */
    public Nodo(T elemento){
        setElemento(elemento);
    }

    /**
     * Accede a la informacion del nodo
     * @return el elemento 
     */
    public T getElemento(){
        return elemento;
    }
    public void setElemento(T elemento){
        this.elemento=elemento;
    }

    /**
     * Permite cambiar el nodo siguiente
     * @param newNodo el nuevo nodo siguiente
     */
    public void setSiguiente(Nodo<T> newNodo){
        siguiente=newNodo;
    }

    /**
     * Accede al Nodo siguiente 
     * @return el Nodo siguiente
     */
    public Nodo<T> getSiguiente(){
        return siguiente;
    }

    /**
     * Permite cambiar el nodo anterior
     * @param newNodo el nuevo nodo anterior
     */
    public void setAnterior(Nodo<T> newNodo){
        anterior=newNodo;
    }

    /**
     * Accede al Nodo anterior
     * @return el nodo anterior
     */
    public Nodo<T> getAnterior(){
        return anterior;
    }
}