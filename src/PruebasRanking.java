import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class PruebasRanking {

    private static int fallos = 0;

    public static void main(String[] args) {
        probarArregloVacio();
        probarUnSoloJugador();
        probarEmpatePorVictorias();
        probarEmpatePorNombre();
        probarAleatorios();
        verificar(fallos == 0, "Resumen de pruebas");
        System.out.println(fallos == 0 ? "Todas las pruebas pasaron" : "Pruebas fallidas: " + fallos);
    }

    private static void verificar(boolean condicion, String nombre) {
        if (!condicion) {
            fallos++;
            System.out.println("FALLO: " + nombre);
        } else {
            System.out.println("OK: " + nombre);
        }
    }

    private static void probarArregloVacio() {
        Jugador[] a = new Jugador[0];
        new QuickSortJugadores().ordenar(a);
        verificar(a.length == 0, "Arreglo vacio");
    }

    private static void probarUnSoloJugador() {
        Jugador[] a = { new Jugador("Ana", 5, 2, 30) };
        new QuickSortJugadores().ordenar(a);
        verificar(a[0].getNombre().equals("Ana"), "Un solo jugador");
    }

    private static void probarEmpatePorVictorias() {
        Jugador[] a = {
            new Jugador("A", 10, 3, 100),
            new Jugador("B", 10, 7, 100),
            new Jugador("C", 10, 5, 100)
        };
        new QuickSortJugadores().ordenar(a);
        verificar(a[0].getNombre().equals("B") && a[1].getNombre().equals("C")
                && a[2].getNombre().equals("A"), "Empate de puntos se resuelve por victorias");
    }

    private static void probarEmpatePorNombre() {
        Jugador[] a = {
            new Jugador("Zoe", 10, 5, 100),
            new Jugador("ana", 10, 5, 100),
            new Jugador("Mario", 10, 5, 100)
        };
        new QuickSortJugadores().ordenar(a);
        verificar(a[0].getNombre().equals("ana") && a[1].getNombre().equals("Mario")
                && a[2].getNombre().equals("Zoe"), "Empate total se resuelve por nombre");
    }

    private static void probarAleatorios() {
        Random azar = new Random(7);
        boolean todoBien = true;
        for (int ronda = 0; ronda < 200; ronda++) {
            int n = azar.nextInt(60);
            Jugador[] a = new Jugador[n];
            for (int i = 0; i < n; i++) {
                int partidas = azar.nextInt(30) + 1;
                a[i] = new Jugador("J" + azar.nextInt(15), partidas, azar.nextInt(partidas + 1), azar.nextInt(6) * 50);
            }
            Jugador[] esperado = a.clone();
            Arrays.sort(esperado, Comparator.comparingInt(Jugador::getPuntos).reversed()
                    .thenComparing(Comparator.comparingInt(Jugador::getVictorias).reversed())
                    .thenComparing(Jugador::getNombre, String.CASE_INSENSITIVE_ORDER));
            QuickSortJugadores q = new QuickSortJugadores();
            q.ordenar(a);
            for (int i = 0; i < n; i++) {
                if (a[i].getPuntos() != esperado[i].getPuntos()
                        || a[i].getVictorias() != esperado[i].getVictorias()
                        || !a[i].getNombre().equalsIgnoreCase(esperado[i].getNombre())) {
                    todoBien = false;
                }
            }
            if (!q.estaOrdenado(a)) {
                todoBien = false;
            }
        }
        verificar(todoBien, "200 arreglos aleatorios con valores repetidos");
    }
}