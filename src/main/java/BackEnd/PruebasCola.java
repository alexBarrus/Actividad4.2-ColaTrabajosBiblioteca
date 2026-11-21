/*Barrutiea Nulutagua Juan Alexander
Alcala Bejarano Jennifer Paola
*/

package BackEnd;

/**
 * Pruebas de funcionamiento por consola (punto 6 de la actividad).
 * Ejecutar con: Run File sobre esta clase en NetBeans.
 *
 * @author Alexander
 */
public class PruebasCola {

    private static int aprobadas = 0;
    private static int falladas = 0;

    public static void main(String[] args) {
        ColaTrabajos cola = new ColaTrabajos();

        System.out.println("=== Prueba 1: insertar distintos tipos (herencia y polimorfismo) ===");
        Trabajo t1 = new Libro("Cien años de soledad", "Gabriel García Márquez", 1967, 471);
        Trabajo t2 = new Revista("National Geographic", "National Geographic Society", 2024, 312);
        Trabajo t3 = new Articulo("Attention Is All You Need", "Vaswani et al.", 2017, "NeurIPS");
        cola.offer(t1);
        cola.offer(t2);
        cola.offer(t3);
        for (Trabajo t : cola.listar()) {
            // La misma llamada toString() ejecuta la versión de cada subclase.
            System.out.println("  " + t.getTipo() + " -> " + t);
        }
        verificar("tamaño 3 después de encolar", cola.size() == 3);
        System.out.println("  " + cola);

        System.out.println("\n=== Prueba 3: peek no modifica la estructura ===");
        Trabajo frente1 = cola.peek();
        Trabajo frente2 = cola.peek();
        System.out.println("  peek() -> " + frente1);
        verificar("peek devuelve el primero encolado", frente1 == t1);
        verificar("dos peek seguidos devuelven lo mismo", frente1 == frente2);
        verificar("el tamaño sigue siendo 3", cola.size() == 3);

        System.out.println("\n=== Prueba 2: extraer consecutivamente respeta FIFO ===");
        Trabajo[] esperado = {t1, t2, t3};
        for (int i = 0; i < esperado.length; i++) {
            Trabajo atendido = cola.poll();
            System.out.println("  poll() #" + (i + 1) + " -> " + atendido);
            verificar("poll #" + (i + 1) + " sale en orden de llegada", atendido == esperado[i]);
        }
        verificar("isEmpty() es true tras atender todo", cola.isEmpty());

        System.out.println("\n=== Protección ante cola vacía ===");
        try {
            cola.poll();
            verificar("poll sobre cola vacía lanza excepción", false);
        } catch (IllegalStateException e) {
            System.out.println("  poll() -> " + e.getMessage());
            verificar("poll sobre cola vacía lanza excepción", true);
        }
        try {
            cola.peek();
            verificar("peek sobre cola vacía lanza excepción", false);
        } catch (IllegalStateException e) {
            System.out.println("  peek() -> " + e.getMessage());
            verificar("peek sobre cola vacía lanza excepción", true);
        }

        System.out.println("\n=== Prueba 4: datos inválidos o incompletos ===");
        probarInvalido("título vacío", () -> new Libro("  ", "Autor", 2000, 100));
        probarInvalido("autor vacío", () -> new Revista("Revista", "", 2000, 5));
        probarInvalido("conferencia vacía", () -> new Articulo("Art", "Autor", 2000, ""));
        probarInvalido("año cero", () -> new Libro("Libro", "Autor", 0, 100));
        probarInvalido("año negativo", () -> new Libro("Libro", "Autor", -5, 100));
        probarInvalido("año futuro", () -> new Libro("Libro", "Autor", 9999, 100));
        probarInvalido("páginas en cero", () -> new Libro("Libro", "Autor", 2000, 0));
        probarInvalido("edición negativa", () -> new Revista("Revista", "Autor", 2000, -1));
        probarInvalido("encolar null", () -> cola.offer(null));
        verificar("la cola sigue vacía tras los intentos inválidos", cola.isEmpty());

        System.out.println("\n=== Prueba 5: vaciar la estructura ===");
        cola.offer(new Libro("El Principito", "Antoine de Saint-Exupéry", 1943, 96));
        cola.offer(new Revista("Muy Interesante", "Editorial Televisa", 2023, 450));
        verificar("isEmpty() es false con 2 elementos", !cola.isEmpty());
        cola.vaciarCola();
        verificar("isEmpty() es true después de vaciarCola()", cola.isEmpty());
        verificar("size() es 0 después de vaciarCola()", cola.size() == 0);
        cola.offer(t3);
        verificar("la cola se puede reutilizar después de vaciarla", cola.peek() == t3 && cola.verFinal() == t3);

        System.out.println("\nResultado: " + aprobadas + " aprobadas, " + falladas + " falladas.");
        if (falladas > 0) {
            System.exit(1);
        }
    }

    private static void verificar(String descripcion, boolean condicion) {
        if (condicion) {
            aprobadas++;
            System.out.println("  [OK]    " + descripcion);
        } else {
            falladas++;
            System.out.println("  [FALLA] " + descripcion);
        }
    }

    private static void probarInvalido(String caso, Runnable accion) {
        try {
            accion.run();
            verificar("rechaza " + caso, false);
        } catch (IllegalArgumentException e) {
            verificar("rechaza " + caso + " -> " + e.getMessage(), true);
        }
    }
}
