package fciencias.edatos.proyecto03;

/**
 * @author Gallegos Diego Cristian Ricardo 318114723
 * @author Sanchez Castro Gustavo 318213888
 */
public class List<T> implements TDAList<T>{

    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private int tam;

    /**
     * Inserta un nuevo elemento <i>e</i> en la posición <i>i</i>.
     * @param i la posición donde agregar el elemento.
     * @param e el elemento a insertar.
     * @throws IndexOutOfBoundException si el índice está fuera de rango.
     */
    @Override
    public void add(int i, T e) throws IndexOutOfBoundsException{
        if(i>size()||i<0){throw new IndexOutOfBoundsException();}
        Nodo<T> nuevo= new Nodo(e);
        if(cabeza==null){		//Si todavia no hay elementos en la lista
            cabeza=nuevo;
            tam++;
        }else if(i==0){			//Si se va a agregar en la cabeza o indice 0
            cola=cabeza;
            nuevo.setSiguiente(cabeza);
            cabeza=nuevo;
            tam++;
        }else{					//Si se va a agregar en un rango dentro de la lista
            Nodo<T> iterador=cabeza;
            for(int j=0; j<i-1; j++){
                iterador=iterador.getSiguiente();
            }
            nuevo.setSiguiente(iterador.getSiguiente());
            iterador.setSiguiente(nuevo);
            tam++;
        }
    }

    /**
     * Limpia la lista. Elimina todos los elementos.
     */
    @Override
    public void clear(){
        cabeza=null;
        cola=null;
        tam=0;
    }

    /**
     * Verifica si un elemento está contenido en la lista.
     * @param e el elemento a verificar si está contenido.
     * @return true si el elemento está contenid, false en otro caso.
     */
    @Override
    public boolean contains(T e){
        boolean contiene=false;
        Nodo<T> iterador= cabeza;
        for(int i=0; i<size(); i++){				//Recorre la lista
            if(iterador.getElemento().equals(e)){	//Hace la comparacion si el objeto T se parece a uno agregado
                contiene=true;
                break;
            }
            iterador=iterador.getSiguiente();
        }
        return contiene;
    }

    /**
     * Obtiene el elemento en la posición <i>i</i>.
     * @param i el índice a obtener elemento.
     * @throws IndexOutOfBoundException si el índice está fuera de rango.
     */
    @Override
    public T get(int i) throws IndexOutOfBoundsException{
        if(i>size()||i<0){throw new IndexOutOfBoundsException();}
        Nodo<T> iterador=cabeza;
        for(int j=0; j<i; j++){					//Recorre la lista hasta obtener en el indice
            iterador=iterador.getSiguiente();
        }
        return (T) iterador.getElemento();
    }

    /**
     * Verifica si la lista está vacía.
     * @return true si la lista no contiene elementos, false en otro caso.
     */
    @Override
    public boolean isEmpty(){
        return !(this.tam>0);
    }

    /**
     * Elimina el elemento en la posición <i>i</i>.
     * @param i el índice del elemento a eliminar.
     * @return el elemento eliminado.
     * @throws IndexOutOfBoundException si el índice está fuera de rango.
     */
    @Override
    public T remove(int i) throws IndexOutOfBoundsException{
        if(i>size()||i<0){throw new IndexOutOfBoundsException();}
        tam--;
        if(i==0){												//Remueve en la posicion 0 de la lista
            Nodo aux=cabeza;
            T objeto= (T) cabeza.getElemento();
            cabeza=cabeza.getSiguiente();
            return objeto;
        }else{
            Nodo<T> iterador=cabeza;
            int j;
            for(j=0; j<i-1; j++){
                iterador=iterador.getSiguiente();
            }
            T valor= (T) iterador.getSiguiente().getElemento();
            if(j==i-2){											//Remueve en el ultimo indice
                iterador.setSiguiente(null);
            }else{												//Remueve en cualquier otro indice
                Nodo<T> borrado=iterador;
                borrado=iterador.getSiguiente().getSiguiente();
                iterador.setSiguiente(borrado);
            }
            return valor;
        }
    }

    /**
     * Regresa la cantidad de elementos contenidos en la lista.
     * @return la cantidad de elementos contenidos.
     */
    @Override
    public int size(){
        return tam;
    }

    /**
     * Da una cadena con los elementos contenidos en la lista.
     * @return una representación de la lista.
     */
    @Override
    public String toString(){
        String texto="";
        Nodo<T> iterador=cabeza;
        while(iterador!=null){
            texto+=(String) iterador.getElemento()+"\n";
            iterador=iterador.getSiguiente();
        }
        return texto;
    }

    /**
     * METODO QUE REGRESA EN QUE INDICE SE ENCUENTRA EL ELEMENTO
     * @param e
     * @return
     */
    public int indexElement(T e){
        int counter=0;
        Nodo<T> iterador= cabeza;
        for(int i=0; i<size(); i++){
            if(iterador.getElemento().equals(e)){
                break;
            }
            iterador=iterador.getSiguiente();
            counter++;
        }
        return counter;
    }
}