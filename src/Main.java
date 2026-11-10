import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        QuickSortJugadores ordenador = new QuickSortJugadores();
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero(entrada, "Opcion: ");
            switch (opcion) {
                case 1:
                    ejecutar("Ejemplo con empates", datosEjemplo(), ordenador);
                    break;
                case 2:
                    ejecutar("Todos con los mismos puntos y victorias", datosEmpateTotal(), ordenador);
                    break;
                case 3:
                    ejecutar("Ya ordenado", datosOrdenados(), ordenador);
                    break;
                case 4:
                    ejecutar("Orden inverso", datosInversos(), ordenador);
                    break;
                case 5:
                    ejecutar("Ingresados por el usuario", capturarJugadores(entrada), ordenador);
                    break;
                case 0:
                    System.out.println("Fin del programa");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("=== RANKING DE JUGADORES ===");
        System.out.println("1. Ejemplo con empates");
        System.out.println("2. Empate total en puntos y victorias");
        System.out.println("3. Datos ya ordenados");
        System.out.println("4. Datos en orden inverso");
        System.out.println("5. Ingresar jugadores");
        System.out.println("0. Salir");
    }

    private static void ejecutar(String titulo, Jugador[] jugadores, QuickSortJugadores ordenador) {
        if (jugadores.length == 0) {
            System.out.println("No hay jugadores para ordenar");
            return;
        }
        System.out.println();
        System.out.println("--- " + titulo + " ---");
        System.out.println("Antes del ordenamiento:");
        imprimirLista(jugadores);
        ordenador.ordenar(jugadores);
        System.out.println();
        System.out.println("Clasificacion final:");
        imprimirClasificacion(jugadores);
        Contadores c = ordenador.getContadores();
        System.out.println();
        System.out.println("Comparaciones: " + c.getComparaciones());
        System.out.println("Intercambios: " + c.getIntercambios());
        System.out.println("Llamadas recursivas: " + c.getLlamadasRecursivas());
        System.out.println("Correctamente ordenado: " + ordenador.estaOrdenado(jugadores));
    }

    private static void imprimirLista(Jugador[] jugadores) {
        for (Jugador j : jugadores) {
            System.out.println("   " + j);
        }
    }

    private static void imprimirClasificacion(Jugador[] jugadores) {
        for (int i = 0; i < jugadores.length; i++) {
            System.out.printf("%2d. %s%n", i + 1, jugadores[i]);
        }
    }

    private static int leerEntero(Scanner entrada, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = entrada.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido");
            }
        }
    }

    private static Jugador[] capturarJugadores(Scanner entrada) {
        int cantidad = leerEntero(entrada, "Cantidad de jugadores: ");
        while (cantidad < 1) {
            cantidad = leerEntero(entrada, "Debe ser al menos 1: ");
        }
        Jugador[] jugadores = new Jugador[cantidad];
        int i = 0;
        while (i < cantidad) {
            System.out.println("Jugador " + (i + 1));
            System.out.print("Nombre: ");
            String nombre = entrada.nextLine().trim();
            int partidas = leerEntero(entrada, "Partidas jugadas: ");
            int victorias = leerEntero(entrada, "Victorias: ");
            int puntos = leerEntero(entrada, "Puntos: ");
            try {
                jugadores[i] = new Jugador(nombre, partidas, victorias, puntos);
                i++;
            } catch (IllegalArgumentException e) {
                System.out.println("Datos invalidos: " + e.getMessage());
            }
        }
        return jugadores;
    }

    private static Jugador[] datosEjemplo() {
        return new Jugador[] {
            new Jugador("Carlos", 20, 12, 340),
            new Jugador("Ana", 18, 14, 410),
            new Jugador("Luis", 25, 12, 340),
            new Jugador("bruno", 15, 9, 280),
            new Jugador("Maria", 22, 14, 410),
            new Jugador("Daniela", 30, 12, 340),
            new Jugador("Andres", 10, 3, 120),
            new Jugador("Sofia", 19, 14, 410),
            new Jugador("Pedro", 12, 5, 150)
        };
    }

    private static Jugador[] datosEmpateTotal() {
        return new Jugador[] {
            new Jugador("Zoe", 10, 5, 100),
            new Jugador("Mateo", 12, 5, 100),
            new Jugador("alberto", 8, 5, 100),
            new Jugador("Camila", 9, 5, 100),
            new Jugador("Bernardo", 11, 5, 100)
        };
    }

    private static Jugador[] datosOrdenados() {
        return new Jugador[] {
            new Jugador("Ana", 10, 9, 500),
            new Jugador("Beto", 10, 8, 400),
            new Jugador("Cata", 10, 7, 300),
            new Jugador("Dani", 10, 6, 200),
            new Jugador("Eli", 10, 5, 100)
        };
    }

    private static Jugador[] datosInversos() {
        return new Jugador[] {
            new Jugador("Eli", 10, 5, 100),
            new Jugador("Dani", 10, 6, 200),
            new Jugador("Cata", 10, 7, 300),
            new Jugador("Beto", 10, 8, 400),
            new Jugador("Ana", 10, 9, 500)
        };
    }
}