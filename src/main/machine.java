package main;

import java.lang.Runnable;
import java.util.Arrays;

public class machine {

    /*Atributos.*/
    int[] tablero;
    int valor, pos, aux;
    boolean mejorJugada = true;/*** *****true=>mejor; false=>peor**********************/

    /*Mi ficha.*/
    public final int fichaMaquina = 2;
    public final int fichaHumano = 1;

    public int ganaPartida() {
        /*Filas*/
        if (tablero[0] != 0 && tablero[0] == tablero[1] && tablero[0] == tablero[2]) {
            return tablero[0];
        }
        if (tablero[3] != 0 && tablero[3] == tablero[4] && tablero[3] == tablero[5]) {
            return tablero[3];
        }
        if (tablero[6] != 0 && tablero[6] == tablero[7] && tablero[6] == tablero[8]) {
            return tablero[6];
        }

        /*Columnas*/
        if (tablero[0] != 0 && tablero[0] == tablero[3] && tablero[0] == tablero[6]) {
            return tablero[0];
        }
        if (tablero[1] != 0 && tablero[1] == tablero[4] && tablero[1] == tablero[7]) {
            return tablero[1];
        }
        if (tablero[2] != 0 && tablero[2] == tablero[5] && tablero[2] == tablero[8]) {
            return tablero[2];
        }
        /*Diagonales*/
        if (tablero[0] != 0 && tablero[0] == tablero[4] && tablero[0] == tablero[8]) {
            return tablero[0];
        }
        if (tablero[2] != 0 && tablero[2] == tablero[4] && tablero[2] == tablero[6]) {
            return tablero[2];
        }

        return 0;
    }

    public boolean tableroCompleto() {
        for (int i = 0; i < 9; i++) {
            if (tablero[i] == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean finPartida() {
        return tableroCompleto() || ganaPartida() != 0;
    }

    public int ponerFichaOrdenador(int[] tablero) {
        this.tablero = tablero;
//        int v = Integer.MIN_VALUE;/********Cambiar a max_value para la peor jugada*****************/
//        int p = 0;
//        int aux;
        iniciarVariablesBusqueda();
        for (int i = 0; i < 9; i++) {
            if (this.tablero[i] == 0) {
                this.tablero[i] = 2;
                buscarSiguientePosición(i);
//                aux = min();
//                /**
//                 * ******Cambiar a max2 para la peor jugada****************
//                 */
//                if (aux > v) {
//                    /**
//                     * ******Cambiar a (aux menor v) para la peor
//                     * jugada****************
//                     */
//                    v = aux;
//                    p = i;
//                }
                this.tablero[i] = 0;
            }
        }
        this.tablero[pos] = 2;
        return pos; // retorna la posición de mejor jugada
    }

    public void iniciarVariablesBusqueda() {
        valor = (mejorJugada) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        pos = 0;
        aux = 0;
    }

    public void buscarSiguientePosición(int p) {
        if (mejorJugada) {
            aux = min();
            if (aux > valor) {
                valor = aux;
                pos = p;
            }
        } else {
            aux = max();
            if (aux < valor) {
                valor = aux;
                pos = p;
            }
        }
    }

    public int max() {
        // Antes de hallar el máximo se evalúa si es fin en el min
        if (finPartida()) {
            if (ganaPartida() != 0) {
                return -1; // Gana cuando se busca el min
            }
            return 0;
        }

        int v = Integer.MIN_VALUE;
        int p = 0;
        int aux2;
        for (int i = 0; i < 9; i++) {
            if (tablero[i] == 0) {
                tablero[i] = 2;
                aux2 = min();
                if (aux2 > v) {
                    v = aux2;
                }
                tablero[i] = 0;
            }
        }
        return v;
    }

    public int min() {
        // Antes de hallar el mínimo se evalúa si es fin en el max
        if (finPartida()) {
            if (ganaPartida() != 0) {
                return 1; // gana cuando se busca el max
            }
            return 0;
        }

        int v = Integer.MAX_VALUE;
        int p = 0;
        int aux2;
        for (int i = 0; i < 9; i++) {
            if (tablero[i] == 0) {
                tablero[i] = 1;
                aux2 = max();
                if (aux2 < v) {
                    v = aux2;
                }
                tablero[i] = 0;
            }
        }
        return v;
    }
}
