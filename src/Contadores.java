public class Contadores {
    private long comparaciones;
    private long intercambios;
    private long llamadasRecursivas;

    public void sumarComparacion() {
        comparaciones++;
    }

    public void sumarIntercambio() {
        intercambios++;
    }

    public void sumarLlamada() {
        llamadasRecursivas++;
    }

    public long getComparaciones() {
        return comparaciones;
    }

    public long getIntercambios() {
        return intercambios;
    }

    public long getLlamadasRecursivas() {
        return llamadasRecursivas;
    }

    public void reiniciar() {
        comparaciones = 0;
        intercambios = 0;
        llamadasRecursivas = 0;
    }
}
