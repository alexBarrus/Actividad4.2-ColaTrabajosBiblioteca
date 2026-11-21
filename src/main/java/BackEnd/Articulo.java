/*Barrutiea Nulutagua Juan Alexander
Alcala Bejarano Jennifer Paola
*/

package BackEnd;

/**
 * Subclase de Trabajo: agrega el nombre de la conferencia donde se presentó.
 *
 * @author Alexander
 */
public class Articulo extends Trabajo {

    private String nombreConferencia;

    public Articulo(String titulo, String autor, int anioPublicacion, String nombreConferencia) {
        super(titulo, autor, anioPublicacion);
        this.nombreConferencia = validarTexto(nombreConferencia, "nombre de la conferencia");
    }

    public String getNombreConferencia() {
        return nombreConferencia;
    }

    public void setNombreConferencia(String nombreConferencia) {
        this.nombreConferencia = validarTexto(nombreConferencia, "nombre de la conferencia");
    }

    @Override
    public String getTipo() {
        return "Articulo";
    }

    @Override
    public String toString() {
        return "[Articulo] " + super.toString() + " | Conferencia: " + nombreConferencia;
    }
}
