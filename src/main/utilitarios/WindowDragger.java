/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.utilitarios;

/**
 *
 * @author Julio
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.SwingUtilities;

public class WindowDragger{

    public WindowDragger(Window window, Component component){
        fWindow = window;
        fComponent = component;
        fComponent.addMouseListener(createMouseListener());
        fComponent.addMouseMotionListener(createMouseMotionListener());
    }

    private MouseListener createMouseListener(){
        return new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                Point clickPoint = new Point(e.getPoint());
                SwingUtilities.convertPointToScreen(clickPoint, fComponent);
                dX = clickPoint.x - fWindow.getX();
                dY = clickPoint.y - fWindow.getY();
            }
        }
;
    }

    private MouseMotionAdapter createMouseMotionListener(){
        return new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e){
                Point dragPoint = new Point(e.getPoint());
                SwingUtilities.convertPointToScreen(dragPoint, fComponent);
                fWindow.setLocation(dragPoint.x - dX, dragPoint.y - dY);
            }

        }
;
    }

    private Window fWindow;
    private Component fComponent;
    private int dX;
    private int dY;
}

