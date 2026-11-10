public class Jugador {
    private final String nombre;
    private final int partidasJugadas;
    private final int victorias;
    private final int puntos;

    public Jugador(String nombre, int partidasJugadas, int victorias, int puntos) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        if (partidasJugadas < 0 || victorias < 0 || puntos < 0) {
            throw new IllegalArgumentException("Los valores no pueden ser negativos");
        }
        if (victorias > partidasJugadas) {
            throw new IllegalArgumentException("Las victorias no pueden superar las partidas jugadas");
        }
        this.nombre = nombre;
        this.partidasJugadas = partidasJugadas;
        this.victorias = victorias;
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public int getVictorias() {
        return victorias;
    }

    public int getPuntos() {
        return puntos;
    }

    @Override
    public String toString() {
        return String.format("%-12s | partidas: %3d | victorias: %3d | puntos: %4d",
                nombre, partidasJugadas, victorias, puntos);
    }
}
