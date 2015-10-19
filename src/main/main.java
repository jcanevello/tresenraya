package main;

import com.sun.awt.AWTUtilities;
import com.sun.xml.internal.ws.resources.SoapMessages;
import main.utilitarios.PanelEspecial;
import main.utilitarios.WindowDragger;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;
import javax.swing.*;

/**
 *
 * @author TAKESHI
 */
public class main extends JFrame implements MouseListener, ActionListener {

    /**
     * Variables  *
     */

    /*Maquina con IA*/
    machine computadora;

    /*Caracteristicas Humana*/
    Competidor humano, maquina;

    /*Array que contiene los casilleros del tablero*/
    JLabel casilleros[];

    /*Numero auxiliar que controla la casilla*/
    int numCasilla;

    /*Array que contiene los estados del tablera*/
    int tablero[];

    /*Variables Auxiliares*/
    int HUMANO = 1;
    int MAQUINA = 2;
    int turno;
    
    /******************************************************************/
    int inicioDeJuego = HUMANO; //Cambiar a HUMANO/MAQUINA
    /******************************************************************/
    
    boolean initAll = false; // verifica que se haya iniciado todas las variables para inicar el juego
    boolean juegoProceso = true;
    boolean juegoTerminado = false;

    /*Vector que guarda direcciones de la imagen de las fichas*/
    public ImageIcon[] fichas;

    /*Variables de Interfaz*/
    public PanelEspecial forma;
    public JLabel botonCerrar;
    public ImageIcon[] botones;
    public JLabel iniciar;
    public JLabel fondo;

    public JLabel c1;
    public JLabel c2;
    public JLabel c3;
    public JLabel c4;
    public JLabel c5;
    public JLabel c6;
    public JLabel c7;
    public JLabel c8;
    public JLabel c9;

    public JLabel jLabelMaquina;
    public JLabel jLabelHumano;

    public JLabel title;

    public JLabel nomHumano;
    public JLabel nomMaquina;

    public main() {
        super();
        setUndecorated(true);
        setVisible(true);
        setBounds(300, 20, 600, 700);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        forma = new PanelEspecial();
        forma.setBounds(0, 0, 500, 700);
        add(forma);

        AWTUtilities.setWindowShape(this, forma.getShape());
        AWTUtilities.setWindowOpacity(this, 0.90F);
        WindowDragger windowDragger = new WindowDragger(this, forma);

        botones = new ImageIcon[3];
        botones[0] = new ImageIcon(getClass().getResource("/main/imagenes/cerrar.png"));
        botones[1] = new ImageIcon(getClass().getResource("/main/imagenes/iniciar.png"));
        botones[2] = new ImageIcon(getClass().getResource("/main/imagenes/fondo1.png"));

        botonCerrar = new JLabel();
        botonCerrar.setBounds(445, 20, 36, 36);
        botonCerrar.setIcon(botones[0]);
        botonCerrar.addMouseListener(this);
        forma.add(botonCerrar);
        forma.repaint();

        iniciar = new JLabel();
        iniciar.setBounds(120, 570, 300, 128);
        iniciar.setIcon(botones[1]);
        iniciar.addMouseListener(this);
        forma.add(iniciar);
        forma.repaint();

        fondo = new JLabel();
        fondo.setBounds(50, 25, 400, 600);
        fondo.setIcon(botones[2]);
        forma.add(fondo);
        forma.repaint();

        c1 = new JLabel();
        c1.setBounds(25, 50, 120, 180);
        c1.addMouseListener(this);
        fondo.add(c1);

        c2 = new JLabel();
        c2.setBounds(155, 50, 120, 180);
        c2.addMouseListener(this);
        fondo.add(c2);

        c3 = new JLabel();
        c3.setBounds(280, 50, 120, 180);
        c3.addMouseListener(this);
        fondo.add(c3);

        c4 = new JLabel();
        c4.setBounds(25, 200, 120, 180);
        c4.addMouseListener(this);
        fondo.add(c4);

        c5 = new JLabel();
        c5.setBounds(155, 200, 120, 180);
        c5.addMouseListener(this);
        fondo.add(c5);

        c6 = new JLabel();
        c6.setBounds(280, 200, 120, 180);
        c6.addMouseListener(this);
        fondo.add(c6);

        c7 = new JLabel();
        c7.setBounds(25, 370, 120, 180);
        c7.addMouseListener(this);
        fondo.add(c7);

        c8 = new JLabel();
        c8.setBounds(155, 370, 120, 180);
        c8.addMouseListener(this);
        fondo.add(c8);

        c9 = new JLabel();
        c9.setBounds(280, 370, 120, 180);
        c9.addMouseListener(this);
        fondo.add(c9);

        fondo.repaint();

        jLabelMaquina = new JLabel();
        jLabelMaquina.setBounds(85, 20, 50, 50);
        jLabelMaquina.setFont(new Font("Comic Sans MS", 3, 20));
        jLabelMaquina.setText("" + 0);
        fondo.add(jLabelMaquina);
        fondo.repaint();

        jLabelHumano = new JLabel();
        jLabelHumano.setBounds(345, 20, 50, 50);
        jLabelHumano.setFont(new Font("Comic Sans MS", 3, 20));
        jLabelHumano.setText("" + 0);
        fondo.add(jLabelHumano);
        fondo.repaint();

        title = new JLabel("TRES EN RAYA");
        title.setBounds(115, 0, 300, 20);
        title.setFont(new Font("Comic Sans MS", 3, 25));
        fondo.add(title);
        fondo.repaint();

        nomMaquina = new JLabel();
        nomMaquina.setBounds(5, 35, 150, 20);
        nomMaquina.setText("Máquina:");
        nomMaquina.setFont(new Font("Comic Sans MS", 3, 15));
        fondo.add(nomMaquina);
        fondo.repaint();

        nomHumano = new JLabel();
        nomHumano.setBounds(310, 35, 100, 20);
        nomHumano.setFont(new Font("Comic Sans MS", 3, 15));
        nomHumano.setText("Tú:");
        fondo.add(nomHumano);
        fondo.repaint();

    }

    public static void main(String[] args) {
        new main();
    }

    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if (o == botonCerrar) {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            System.exit(0);
        }
        if (o == iniciar) {
            iniciarJuego();
        }
        if (initAll && juegoProceso) {// initAll=> Verifica que todas las variables estén iniciadas para evitar errores
            if (turno == MAQUINA) {
                ponerJugadaMaquina(computadora.ponerFichaOrdenador(this.tablero));
            } else {
                if (o == c1) {
                    numCasilla = 1;
                    movimiento(c1);
                } else if (o == c2) {
                    numCasilla = 2;
                    movimiento(c2);
                } else if (o == c3) {
                    numCasilla = 3;
                    movimiento(c3);
                } else if (o == c4) {
                    numCasilla = 4;
                    movimiento(c4);
                } else if (o == c5) {
                    numCasilla = 5;
                    movimiento(c5);
                } else if (o == c6) {
                    numCasilla = 6;
                    movimiento(c6);
                } else if (o == c7) {
                    numCasilla = 7;
                    movimiento(c7);
                } else if (o == c8) {
                    numCasilla = 8;
                    movimiento(c8);
                } else if (o == c9) {
                    numCasilla = 9;
                    movimiento(c9);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Haz clic en iniciar");
        }

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void actionPerformed(ActionEvent e) {

    }

    /*Metodos Auxiliares para algoritmo*/
    public void iniciarJuego() {
        if (!initAll) {
            initAll = true;
            /*Incializamos el tablero*/
            tablero = new int[9];

            /*Inicializamos el numero de casilla*/
            numCasilla = 0;

            /*Inicializamos a la computadora*/
            computadora = new machine();

            /*Llenamos el tablero de 0s*/
            Arrays.fill(tablero, 0);

            /*Llenamos el array con las posisicones del tablero*/
            casilleros = new JLabel[9];
            casilleros[0] = c1;
            casilleros[1] = c2;
            casilleros[2] = c3;
            casilleros[3] = c4;
            casilleros[4] = c5;
            casilleros[5] = c6;
            casilleros[6] = c7;
            casilleros[7] = c8;
            casilleros[8] = c9;

            /*Inicializar turno*/
            turno = inicioDeJuego; // Iniciamos con el turno que asignamos en la declaración de variables

            /*Inicializamos variables de fichas*/
            fichas = new ImageIcon[2];
            fichas[0] = new ImageIcon(getClass().getResource("/main/imagenes/circulo.png"));
            fichas[1] = new ImageIcon(getClass().getResource("/main/imagenes/cielo2.png"));

            /*Inicializar fichas de los jugadores*/
            humano = new Competidor("Humano", this.fichas[0]);
            maquina = new Competidor("Máquina", this.fichas[1]);
        } else {
            reiniciarJuego();
        }
    }

    public void movimiento(JLabel jugada) {
        if (juegoProceso) {
            if (turno == HUMANO) {
                ponerJugadaHumano(jugada);
            }

            if (turno == MAQUINA && (!lleno())) {// lleno verifica que la maquina no realice un juego cuando no hay espacio
                ponerJugadaMaquina(computadora.ponerFichaOrdenador(this.tablero));
            }
        }

        int aux = termino();
        if (aux != 0) { // Si hay ganador
            if (aux == 1) { // Si ganó el humano
                humano.gano();
            } else { // Si ganó la maquina
                humano.perdio();
            }
            juegoProceso = false;
            juegoTerminado = true;
        } else if (lleno()) {// Si hay empate===========falso => no está lleno; verdadero=> lleno
            maquina.empato();
            juegoProceso = false;
            juegoTerminado = true;
        }
        actualizarMarcador();
//        recorrer_tabla();
    }

    public void actualizarMarcador() {
        jLabelMaquina.setText(Integer.toString(maquina.GANADOS));
        jLabelHumano.setText(Integer.toString(humano.GANADOS));
    }

    public void ponerJugadaHumano(JLabel jugada) {
        int numTablero = this.numCasilla - 1;

        if (numTablero < 0) {
            System.out.println("Error de casilla");
            return;
        }

        //1=>Ocupado
        //0=> Desocupado
        if (estaOcupada(numTablero)) {
            System.out.println("Casilla Ocupada");
            return;
        }

        jugada.setIcon(humano.obtenFicha());
        tablero[numTablero] = turno; // turno contiene el valor del jugador
        turno = MAQUINA;
    }

    public void recorrer_tabla() {
        System.out.println("========Tabla=============");
        for (int i = 0; i < 9; i++) {
            System.out.print("|" + tablero[i] + "|");
            if (i == 2 || i == 5) {
                System.out.println("");
            }
        }
    }

    public void ponerJugadaMaquina(int mov) {
        if (mov == -1) {
            return;
        }
        switch (mov) {
            case 0:
                this.c1.setIcon(maquina.obtenFicha());
                break;
            case 1:
                this.c2.setIcon(maquina.obtenFicha());
                break;
            case 2:
                this.c3.setIcon(maquina.obtenFicha());
                break;
            case 3:
                this.c4.setIcon(maquina.obtenFicha());
                break;
            case 4:
                this.c5.setIcon(maquina.obtenFicha());
                break;
            case 5:
                this.c6.setIcon(maquina.obtenFicha());
                break;
            case 6:
                this.c7.setIcon(maquina.obtenFicha());
                break;
            case 7:
                this.c8.setIcon(maquina.obtenFicha());
                break;
            case 8:
                this.c9.setIcon(maquina.obtenFicha());
                break;
        }

        this.tablero[mov] = turno;
        turno = HUMANO;
    }

    public boolean estaOcupada(int casilla) {
        System.out.println(tablero[casilla]);
        return (tablero[casilla] != 0);
    }

    public void reiniciarJuego() {

        /*Incializamos el tablero*/
        tablero = new int[9];

        /*Inicializamos el numero de casilla*/
        numCasilla = 0;

        /*Inicializamos a la computadora*/
        computadora = new machine();

        //Llenamos el tablero con 0s*/
        Arrays.fill(tablero, 0);

        /*Borramos los iconos.*/
        for (int i = 0; i < 9; i++) {
            casilleros[i].setIcon(null);
        }

        /*Inicializar turno*/
        turno = inicioDeJuego;

        /*Iniciar juego*/
        juegoProceso = true;
        juegoTerminado = false;

    }

    public int termino() {
        /*Estados es en el que se gana o pierde*/
        /*Filas*/
        if (tablero[0] == tablero[1] && tablero[0] == tablero[2] && tablero[0] != 0) {
            return tablero[0];
        } else if (tablero[3] == tablero[4] && tablero[3] == tablero[5] && tablero[3] != 0) {
            return tablero[3];
        } else if (tablero[6] == tablero[7] && tablero[6] == tablero[8] && tablero[6] != 0) {
            return tablero[6];
        } /*Columnas*/ else if (tablero[0] == tablero[3] && tablero[0] == tablero[6] && tablero[0] != 0) {
            return tablero[0];
        } else if (tablero[1] == tablero[4] && tablero[1] == tablero[7] && tablero[1] != 0) {
            return tablero[1];
        } else if (tablero[2] == tablero[5] && tablero[2] == tablero[8] && tablero[2] != 0) {
            return tablero[2];
        } /*Diagonales*/ else if (tablero[0] == tablero[4] && tablero[0] == tablero[8] && tablero[0] != 0) {
            return tablero[0];
        } else if (tablero[2] == tablero[4] && tablero[2] == tablero[6] && tablero[2] != 0) {
            return tablero[2];
        }

        return 0;
//        return computadora.ganaPartida();

    }

    public boolean lleno() {
        boolean res = true;
        for (int i = 0; i < tablero.length; i++) {
            if (tablero[i] == 0) {
                res = false;
            }
        }
        return res;
//        return computadora.tableroCompleto();
    }
}
