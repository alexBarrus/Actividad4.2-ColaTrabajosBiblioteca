/*Barrutieta Nulutagua Juan Alexander
Alcala Bejarano Jennifer Paola
*/

package BackEnd;

/**
 * Subclase de Trabajo: agrega el número de páginas.
 *
 * @author Alexander
 */
public class Libro extends Trabajo {

    private int numeroPaginas;

    public Libro(String titulo, String autor, int anioPublicacion, int numeroPaginas) {
        super(titulo, autor, anioPublicacion);
        this.numeroPaginas = validarPositivo(numeroPaginas, "número de páginas");
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = validarPositivo(numeroPaginas, "número de páginas");
    }

    @Override
    public String getTipo() {
        return "Libro";
    }

    @Override
    public String toString() {
        return "[Libro] " + super.toString() + " | Páginas: " + numeroPaginas;
    }
}
