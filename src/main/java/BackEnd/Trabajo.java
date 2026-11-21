/*Barrutieta Nulutagua Juan Alexander
Alcala Bejarano Jennifer Paola
*/

package BackEnd;

import java.time.Year;

/**
 * Clase base de la jerarquía de la Biblioteca.
 * Contiene los datos comunes a cualquier publicación (Libro, Revista, Articulo).
 * Es abstracta: solo se instancian sus subclases, pero todas se manejan
 * de forma polimórfica como objetos de tipo Trabajo.
 *
 * @author Alexander
 */
public abstract class Trabajo {

    // ---------- Atributos privados (ocultación de información) ----------
    private String titulo;
    private String autor;
    private int anioPublicacion;

    // ---------- Constructor ----------

    /**
     * @param titulo título del trabajo (obligatorio)
     * @param autor autor del trabajo (obligatorio)
     * @param anioPublicacion año entre 1 y el año actual
     */
    public Trabajo(String titulo, String autor, int anioPublicacion) {
        this.titulo = validarTexto(titulo, "título");
        this.autor = validarTexto(autor, "autor");
        this.anioPublicacion = validarAnio(anioPublicacion);
    }

    // ---------- Validaciones (protegidas: las reutilizan las subclases) ----------

    protected static String validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + campo + " es obligatorio (no puede estar vacío).");
        }
        return valor.trim();
    }

    protected static int validarPositivo(int valor, String campo) {
        if (valor <= 0) {
            throw new IllegalArgumentException("El campo " + campo + " debe ser un entero mayor a cero (recibido: " + valor + ").");
        }
        return valor;
    }

    private static int validarAnio(int anio) {
        validarPositivo(anio, "año de publicación");
        int actual = Year.now().getValue();
        if (anio > actual) {
            throw new IllegalArgumentException("El año de publicación no puede ser mayor a " + actual + " (recibido: " + anio + ").");
        }
        return anio;
    }

    // ---------- Getters y setters ----------

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = validarTexto(titulo, "título");
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = validarTexto(autor, "autor");
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = validarAnio(anioPublicacion);
    }

    /**
     * Nombre del tipo de publicación. Cada subclase lo sobrescribe (polimorfismo).
     */
    public abstract String getTipo();

    @Override
    public String toString() {
        return "Título: " + titulo + " | Autor: " + autor + " | Año: " + anioPublicacion;
    }
}
