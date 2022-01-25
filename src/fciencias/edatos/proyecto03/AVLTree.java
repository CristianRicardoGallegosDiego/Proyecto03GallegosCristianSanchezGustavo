package fciencias.edatos.proyecto03;

import java.util.Scanner;
import java.lang.Math;

/**
 * @author Gallegos Diego Cristian Ricardo --> Numero de cuenta: 318114723
 * @author Castro Gustavo --> Numero de cuenta: 318213888
 */
public class AVLTree<K extends Comparable, T> implements TDABinarySearchTree <K,T>{

    public class AVLNode{

        /** Altura del nodo. */
        public int altura;

        /** Hijo izquierdo. */
        public AVLNode izquierdo;

        /** Hijo derecho. */
        public AVLNode derecho;

        /** Padre del nodo. */
        public AVLNode padre;

        /** Elemento almacenado en el nodo. */
        public T elemento;

        /** Clave del nodo. */
        public K clave;

        public int factorCri;

        /**
         * Crea un nuevo nodo AVL
         * @param element el elemento a almacenar.
         * @param key la clave del nodo.
         * @param padre el padre del nodo
         */
        public AVLNode(T element, K key, AVLNode padre){
            elemento = element;
            clave = key;
            this.padre = padre;
            altura = this.getAltura();
            factorCri=0;
        }

        /**
         * Calcula la altura del nodo.
         */
        public int getAltura(){
            // Si este nodo es hoja
            if(izquierdo == null && derecho==null){
                return 0;
            } else if(izquierdo != null && derecho != null){ // Dos hijos
                int max = izquierdo.getAltura() > derecho.getAltura() ? izquierdo.getAltura() : derecho.getAltura();
                return 1 + max;
            } else{ // Tiene solo un hijo
                boolean tieneIzquierdo = izquierdo!=null;
                return 1 + (tieneIzquierdo ? izquierdo.getAltura() : derecho.getAltura());
            }
        }

        /**
         * Actualiza la altura del nodo.
         */
        public void actualizaAltura(){
            this.altura = this.getAltura();
        }
    }


    /** Root */
    private AVLNode root;

    /**
     * Recupera el objeto con clave k.
     * @param k la clave a buscar.
     * @return el elemento con clave k o null si no existe.
     */
    @Override
    public T retrieve(K k) {
        AVLNode node = retrieve(root, k);
        if(node == null)
            return null;
        return node.elemento;
    }

    /**
     * Metodo auxiliar para retrive donde recibimos un Nodo
     * @param actual Nodo a recibir
     * @param k llave del elemento
     * @return Regresamos el metodo recursivo
     */
    private AVLNode retrieve(AVLNode actual, K k){
        // No se encuentra el elemento
        if(actual == null)
            return null;

        // Si encontramos el elemento
        if(actual.clave.compareTo(k) == 0)
            return actual;

        // Comparamos los elementos
        if(k.compareTo(actual.clave) < 0){ // Verificamos en la izquierda
            return retrieve(actual.izquierdo, k);
        } else { // Verificar en la derecha
            return retrieve(actual.derecho, k);
        }
    }
    
    /**
     * Inserta un nuevo elemento al árbol.
     * @param e el elemento a ingresar.
     * @param k la clave del elemento a ingresar.
     */
    @Override
    public void insert(T e, K k){
        if(root == null){ // Arbol vacío
            root = new AVLNode(e, k, null);
            return;
        }
        AVLNode v = insert(e, k, root);
        updateBalance(v);
    }

    /**
     * METODO QUE VERIFICA SI HAY REBALANCEO
     * @param actual
     */
    private void balance(AVLNode actual){
        if (actual.factorCri < -1 || actual.factorCri > 1) {
			rebalance(actual);
			return;
		}
        if (actual.padre != null) {
			if (actual == actual.padre.izquierdo) {
				actual.padre.factorCri -= 1;
			} 
			if (actual == actual.padre.derecho) {
				actual.padre.factorCri += 1;
			}
			if (actual.padre.factorCri != 0) {
				updateBalance(actual.padre);
			}
		}
    }

    /**
     * METODO QUE HACE UN REBALANCE ENTRE SUS HIJOS 
     * @param actual es el nodo al que se le va a hacer el rebalance 
     */
    private void rebalance(AVLNode actual){
        if(actual.factorCri > 0){
            if(actual.derecho.factorCri < 0){
                rightRotate(actual.derecho);
                leftRotate(actual);
            }else{
                leftRotate(actual);
            }
        }else if (actual.factorCri < 0){
            if(actual.izquierdo.factorCri>0){
                leftRotate(actual.izquierdo);
                rightRotate(actual);
            }else{
                rightRotate(actual);
            }
        }
    }

    /**
     * METODO AXULIZAR PARA REBALANZAR A LA IZQUIERDA
     * @param actual
     */
    private void leftRotate(AVLNode actual){
        AVLNode temp=actual.derecho;
        actual.derecho=temp.izquierdo;
        if(temp.izquierdo != null){
            temp.izquierdo.padre=actual;
        }
        temp.padre=actual.padre;
        if(actual.padre==null){
            this.root=temp;
        }else if(actual==actual.padre.izquierdo){
            actual.padre.izquierdo=temp;
        }else{
            actual.padre.derecho=temp;
        }
        temp.izquierdo=actual;
        actual.padre=temp;
        //ACTUALIZAMOS EL FACTOR CRITICO DE BALANCE
        actual.factorCri=actual.factorCri-1-Math.max(0, temp.factorCri);
        temp.factorCri=temp.factorCri-1+Math.min(0, actual.factorCri);
    }

    /**
     * METODO QUE HACE LA ROTACION A LA DERECHA
     * @param actual
     */
    private void rightRotate(AVLNode actual){
        AVLNode temp= actual.izquierdo;
        actual.izquierdo=temp.derecho;
        if(temp.derecho != null){
            temp.derecho.padre=actual;
        }
        temp.padre = actual.padre;
        if(actual.padre==null){
            this.root = temp;
        }else if(actual== actual.padre.derecho){
            actual.padre.derecho=temp;
        }else{
            actual.padre.izquierdo = temp;
        }
        temp.derecho=actual;
        actual.padre=temp;
        //ACTUALIZMAOS EL FACTOR CRITICO DE BALANCE
        actual.factorCri = actual.factorCri+1-Math.min(0, temp.factorCri);
        temp.factorCri=temp.factorCri+1+Math.max(0, actual.factorCri);
    }

    /**
     * ACTUALIZA EL FACTOR CRITICO DE BALANCE CON EL QUE REALIZAMOS LOS REBALANCEOS
     * @param actual
     */
    private void updateBalance(AVLNode actual){
        if(actual.factorCri<-1 || actual.factorCri>1){
            rebalance(actual);
            return;
        }
        if(actual.padre != null){
            if(actual == actual.padre.izquierdo){
                actual.padre.factorCri-=1;
            }
            if(actual==actual.padre.derecho){
                actual.padre.factorCri+=1;
            }
            if(actual.padre.factorCri != 0){
                updateBalance(actual.padre);
            }
        }
    }

    /**
     * Inserta un nodo de forma recursiva.
     * @param e el elemento a insertar
     * @param k es la clave del nodo a insertar
     * @param actual el nodo actual
     * @return
     */
    public AVLNode insert(T e, K k, AVLNode actual){
        if(k.compareTo(actual.clave)<0){ // Verificamos sobre el izquierdo
            if(actual.izquierdo == null){ // Insertamos en esa posición
                actual.izquierdo = new AVLNode(e, k, actual);
                return actual.izquierdo;
            } else { // Recursión sobre el izquierdo
                return insert(e, k, actual.izquierdo);
            }
        } else{ // Verificamos sobre la derecha
            if(actual.derecho == null){ // Insertamos en esa posición
                actual.derecho = new AVLNode(e, k, actual);
                return actual.derecho;
            } else { // Recursión sobre el derecho
                return insert(e, k, actual.derecho);
            }
        }
    }

    /**
     * Elimina el nodo con clave k del árbol.
     * @param k la clave perteneciente al nodo a eliminar.
     * @return el elemento almacenado en el nodo a eliminar.
     * null si el nodo con clave k no existe.
     */
    @Override
    public T delete(K k){
        AVLNode v = retrieve(root, k);

        // El elemento que queremos eliminar no está en el árbol
        if(v == null){
            return null;
        }

        T eliminado = v.elemento;

        // Eliminar con auxiliar
        AVLNode w = delete(v);
        // Rebalancear
        updateBalance(w.padre);
        return eliminado;
    }

    private AVLNode delete(AVLNode v){
        if(v.izquierdo!=null && v.derecho!=null){ // Tiene dos hijos
            AVLNode menor = findMin(v.derecho);
            swap(menor, v);
            return delete(menor);
        } else if(v.izquierdo==null && v.derecho==null){ // No tiene hijos
            boolean esIzquierdo = v.padre.izquierdo==v;
            if(esIzquierdo){
                v.padre.izquierdo = null;
            } else{
                v.padre.derecho = null;
            }
            return v.padre;
        } else{ // Sólo tiene un hijo
            boolean hijoIzquierdo = v.izquierdo!=null;
            if(hijoIzquierdo){
                swap(v, v.izquierdo);
                return delete(v.izquierdo);
            } else{
                swap(v, v.derecho);
                return delete(v.derecho);
            }
        }
    }

    private void swap(AVLNode v, AVLNode w){
        T value = v.elemento;
        K clave = v.clave;
        v.elemento = w.elemento;
        v.clave = w.clave;
        w.elemento = value;
        w.clave = clave;
    }

    private AVLNode findMin(AVLNode actual){
        if(actual == null)
            return null;
        AVLNode iterador = actual;
        while(iterador.izquierdo != null){
            iterador = actual.izquierdo;
        }
        return iterador;
    }

    /**
     * Encuentra la clave k con valor o peso mínimo del árbol.
     * @return el elemento con llave de peso mínimo.
     */
    @Override
    public T findMin() {
        AVLNode aux = root , last = null;
        while (aux != null){
            last =  aux;
            aux = aux.izquierdo;
        }
        return last.elemento;

    }

    /**
     * Encuentra la clave k con valor o peso máximo del árbol.
     * @return el elemento con llave de peso máximo.
     */
    @Override
    public T findMax() {
        AVLNode aux = root , last = null;
        while (aux != null){
            last =  aux;
            aux = aux.izquierdo;
        }
        return (T) last.derecho;
    }

    /**
     * Recorre el árbol en preorden.
     */
    @Override
    public void preorden(){
        if(root == null){
            System.out.println("LO SENTIMOS, PRIMERO INGRESA DATOS AL ARBOL");
        }else{
            preorden(root);
        }
    }

    private void preorden(AVLNode actual){
        System.out.println(actual.elemento.toString());
        if(actual.izquierdo != null){
            preorden(actual.izquierdo);
        }
        if(actual.derecho != null){
            preorden(actual.derecho);
        }
    }

    /**
     * Recorre el árbol en inorden.
     */
    @Override
    public void inorden(){
        if(root== null){
            System.out.println("LO SENTIMOS, PRIMERO INGRESA DATOS AL ARBOL");
        }else{
            inorden(root);
        }
    }

    private void inorden(AVLNode actual){
        if(actual != null){
            inorden(actual.izquierdo);
            System.out.println(actual.elemento.toString());
            inorden(actual.derecho);
        }
    }

    /**
     * Recorre el árbol en postorden.
     */
    @Override
    public void postorden(){
        if(root== null){
            System.out.println("LO SENTIMOS, PRIMERO INGRESA DATOS AL ARBOL");
        }else{
            postorden(root);
        }

    }
    private void postorden(AVLNode actual){
        if(actual != null){
            postorden(actual.izquierdo);
            postorden(actual.derecho);
            System.out.println(actual.elemento.toString());
        }
    }

    /**
     * Verifica si el árbol es vacío.
     * @return true si el árbol es vacío, false en otro caso.
     */
    @Override
    public boolean isEmpty(){
        return root==null;
    }

    /**
     * main en donde generamos nuestro arbol y realizamos las operaciones
     * usamos un switch para las diferentes opciones y un ciclo do while
     * para decidir cuando cerrar el programa
     * @param args .
     */
    public static void main (String[] args) {
        AVLTree tree = new AVLTree();
        try {
            Scanner read = new Scanner(System.in);
            boolean isRunning = true;
            do {
                menu();
                int option = read.nextInt();
                switch(option) {
                    case 1:
                        System.out.println("Ingrese la clave del elemento a recuperar");
                        Object element = read.next();
                        if (tree.isEmpty() || tree.retrieve((Comparable) element) == null )
                            System.out.println("El elemento no se encuentra en el arbol ");
                        else
                            System.out.println(tree.retrieve((Comparable) element));
                        break;
                    case 2:
                        System.out.println("Ingrese el nuevo elemento ");
                        Object elemento = read.next();
                        System.out.println("Ingrese la llave del elemento ");
                        Object key = read.next();
                        tree.insert(elemento , (Comparable) key);
                        break;
                    case 3:
                        System.out.println("Ingrese la clave del elemento a Eliminar ");
                        Object clave = read.next();
                        if (tree.isEmpty() || tree.retrieve((Comparable) clave)== null )
                            System.out.println("El elemento no se encuentra en el arbol");
                        else{

                            System.out.println("Se elimino el elemento " + " - " + tree.delete((Comparable) clave));
                        }
                        break;
                    case 4:
                        if(!tree.isEmpty()){
                            System.out.println("El peso minimo  es ");
                            System.out.println(tree.findMin());
                        }else
                            System.out.println("El arbol esta vacio");
                        break;
                    case 5:
                        if(!tree.isEmpty()){
                            System.out.println("El peso maximo es ");
                            System.out.println(tree.findMax());
                        }else
                            System.out.println("El arbol esta vacio");
                        break;
                    case 6:
                        if (!tree.isEmpty()){
                            System.out.println("El arbol en preorden queda de la siguiente manera: ");
                            tree.preorden();
                        }else
                            System.out.println("El arbol esta vacio");
                        break;
                    case 7:
                        if (!tree.isEmpty()){
                            System.out.println("El arbol en inorden queda de la siguiente manera: ");
                            tree.inorden();
                        }else
                            System.out.println("El arbol esta vacio");
                        break;
                    case 8:
                        if (!tree.isEmpty()){
                            System.out.println("El arbol en postorden queda de la siguiente manera: ");
                            tree.postorden();
                        }else
                            System.out.println("El arbol esta vacio");
                        break;
                    case 9:
                        if (tree.isEmpty())
                            System.out.println("El arbol esta vacio ");
                        else
                            System.out.println("El arbon no esta vacio");

                        break;
                    case 10:
                        isRunning = false;
                        System.out.println("Vuelve pronto!");
                        break;
                    default:
                        System.out.println("Opcion no valida. Vuelve a intentarlo.\n");
                }

            } while(isRunning);

        } catch (Exception e) {
            System.out.println("Operacion no soportada. ");
            main(args);//recursividad en caso de fallar
        }
    }

    public static void menu(){
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Bienvenido al Menu");
        System.out.println("Selecciona la opcion de tu preferencia.");
        System.out.println("1-Recupera el objeto con clave k.");
        System.out.println("2-Inserta un nuevo elemento al árbol.");
        System.out.println("3-Elimina el nodo con clave k del árbol.");
        System.out.println("4-Encuentra la clave k con valor o peso mínimo del árbol");
        System.out.println("5-Encuentra la clave k con valor o peso máximo del árbol.");
        System.out.println("6-Recorre el árbol en preorden.");
        System.out.println("7-Recorre el árbol en inorden.");
        System.out.println("8-Recorre el árbol en postorden.");
        System.out.println("9-Verifica si el árbol es vacío.");
        System.out.println("10-Exit");
        System.out.println("-------------------------------------------------------------------------");
    }
}