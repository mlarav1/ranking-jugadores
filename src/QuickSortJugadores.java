public class QuickSortJugadores {
    private final Contadores contadores = new Contadores();

    public Contadores getContadores() {
        return contadores;
    }

    public void ordenar(Jugador[] jugadores) {
        contadores.reiniciar();
        if (jugadores == null || jugadores.length < 2) {
            return;
        }
        quickSort(jugadores, 0, jugadores.length - 1);
    }

    private void quickSort(Jugador[] a, int bajo, int alto) {
        contadores.sumarLlamada();
        if (bajo >= alto) {
            return;
        }
        int posicionPivote = particionar(a, bajo, alto);
        quickSort(a, bajo, posicionPivote - 1);
        quickSort(a, posicionPivote + 1, alto);
    }

    private int particionar(Jugador[] a, int bajo, int alto) {
        int medio = bajo + (alto - bajo) / 2;
        intercambiar(a, medio, alto);
        Jugador pivote = a[alto];
        int i = bajo;
        for (int j = bajo; j < alto; j++) {
            if (vaAntes(a[j], pivote)) {
                intercambiar(a, i, j);
                i++;
            }
        }
        intercambiar(a, i, alto);
        return i;
    }

    private boolean vaAntes(Jugador x, Jugador y) {
        return comparar(x, y) < 0;
    }

    private int comparar(Jugador x, Jugador y) {
        contadores.sumarComparacion();
        if (x.getPuntos() != y.getPuntos()) {
            return x.getPuntos() > y.getPuntos() ? -1 : 1;
        }
        if (x.getVictorias() != y.getVictorias()) {
            return x.getVictorias() > y.getVictorias() ? -1 : 1;
        }
        return x.getNombre().compareToIgnoreCase(y.getNombre());
    }

    private void intercambiar(Jugador[] a, int i, int j) {
        if (i == j) {
            return;
        }
        Jugador tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
        contadores.sumarIntercambio();
    }

    public boolean estaOrdenado(Jugador[] a) {
        for (int i = 1; i < a.length; i++) {
            if (comparar(a[i - 1], a[i]) > 0) {
                return false;
            }
        }
        return true;
    }
}

