/*Barrutieta Nulutagua Juan Alexander
Alcala Bejarano Jennifer Paola
*/

package BackEnd;

/**
 * Subclase de Trabajo: agrega el número de edición.
 *
 * @author Alexander
 */
public class Revista extends Trabajo {

    private int numeroEdicion;

    public Revista(String titulo, String autor, int anioPublicacion, int numeroEdicion) {
        super(titulo, autor, anioPublicacion);
        this.numeroEdicion = validarPositivo(numeroEdicion, "número de edición");
    }

    public int getNumeroEdicion() {
        return numeroEdicion;
    }

    public void setNumeroEdicion(int numeroEdicion) {
        this.numeroEdicion = validarPositivo(numeroEdicion, "número de edición");
    }

    @Override
    public String getTipo() {
        return "Revista";
    }

    @Override
    public String toString() {
        return "[Revista] " + super.toString() + " | Edición: " + numeroEdicion;
    }
}
