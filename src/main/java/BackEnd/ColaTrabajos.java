/*Barrutieta Nulutagua Juan Alexander
Alcala Bejarano Jennifer Paola
*/

package BackEnd;

import java.util.ArrayList;
import java.util.List;

/**
 * Cola dinámica de trabajos (FIFO: el primero en entrar es el primero en salir).
 * Implementada con nodos enlazados: crece y decrece en tiempo de ejecución
 * sin un tamaño máximo fijo.
 *
 *   frente -> [T1] -> [T2] -> [T3] <- fin
 *   offer() agrega por el fin, poll() atiende por el frente.
 *
 * Guarda referencias de tipo Trabajo, por lo que acepta Libro, Revista y
 * Articulo indistintamente (polimorfismo).
 *
 * @author Alexander
 */
public class ColaTrabajos {

    /** Nodo de la lista enlazada: guarda un trabajo y la referencia al siguiente. */
    private static class Nodo {

        private final Trabajo dato;
        private Nodo siguiente;

        Nodo(Trabajo dato) {
            this.dato = dato;
        }
    }

    private Nodo frente;   // primer elemento (el que se atiende)
    private Nodo fin;      // último elemento (donde se encola)
    private int tamanio;

    public ColaTrabajos() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

    /**
     * Encola un trabajo al final de la cola.
     *
     * @throws IllegalArgumentException si el trabajo es null
     */
    public void offer(Trabajo trabajo) {
        if (trabajo == null) {
            throw new IllegalArgumentException("No se puede encolar un trabajo nulo.");
        }
        Nodo nuevo = new Nodo(trabajo);
        if (isEmpty()) {
            frente = nuevo;
        } else {
            fin.siguiente = nuevo;
        }
        fin = nuevo;
        tamanio++;
    }

    /**
     * Atiende (remueve y devuelve) el trabajo del frente.
     *
     * @throws IllegalStateException si la cola está vacía
     */
    public Trabajo poll() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía: no hay trabajos para atender.");
        }
        Trabajo atendido = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {      // se atendió el último elemento
            fin = null;
        }
        tamanio--;
        return atendido;
    }

    /**
     * Consulta el trabajo del frente sin removerlo.
     *
     * @throws IllegalStateException si la cola está vacía
     */
    public Trabajo peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía: no hay trabajo en el frente.");
        }
        return frente.dato;
    }

    /** Consulta el trabajo del final sin removerlo (null si está vacía). */
    public Trabajo verFinal() {
        return isEmpty() ? null : fin.dato;
    }

    /** Sinónimo de offer. */
    public void enqueue(Trabajo trabajo) {
        offer(trabajo);
    }

    /** Sinónimo de poll. */
    public Trabajo dequeue() {
        return poll();
    }

    public boolean isEmpty() {
        return frente == null;
    }

    /** Elimina todos los trabajos de la cola. */
    public void vaciarCola() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

    public int size() {
        return tamanio;
    }

    /**
     * Devuelve una copia de los elementos en orden, del frente al final.
     * Modificar la lista devuelta no altera la cola.
     */
    public List<Trabajo> listar() {
        List<Trabajo> elementos = new ArrayList<>(tamanio);
        for (Nodo actual = frente; actual != null; actual = actual.siguiente) {
            elementos.add(actual.dato);
        }
        return elementos;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Cola vacía";
        }
        StringBuilder sb = new StringBuilder("FRENTE -> ");
        for (Nodo actual = frente; actual != null; actual = actual.siguiente) {
            sb.append(actual.dato.getTipo()).append("(\"").append(actual.dato.getTitulo()).append("\")");
            if (actual.siguiente != null) {
                sb.append(" -> ");
            }
        }
        return sb.append(" <- FINAL").toString();
    }
}
