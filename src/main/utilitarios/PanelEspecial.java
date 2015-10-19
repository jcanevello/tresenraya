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
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class PanelEspecial extends JPanel{

    public PanelEspecial(){
        borderColor = new Color(131, 142, 128);
        anchoDeBorde = 9.0F;
        setLayout(null);
        setOpaque(false);
        setBackground(new Color(131, 142, 128));
    }

    protected void paintBorder(Graphics g){
        Graphics2D graphics2d = (Graphics2D)g.create();
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint paint = new GradientPaint(0.0F, 0.0F, borderColor, 0.0F, getHeight(), borderColor);
        java.awt.Stroke oldStroke = graphics2d.getStroke();
        graphics2d.setStroke(new BasicStroke(anchoDeBorde));
        graphics2d.setPaint(paint);
        graphics2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
        graphics2d.setStroke(oldStroke);
        graphics2d.dispose();
    }

    protected void paintComponent(Graphics g){
        Graphics2D graphics2d = (Graphics2D)g.create();
        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2d.setComposite(AlphaComposite.Src);
        graphics2d.setColor(getBackground());
        graphics2d.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
        graphics2d.dispose();
    }

    public Shape getShape(){
        return new java.awt.geom.RoundRectangle2D.Float(0.0F, 0.0F, getWidth(), getHeight(), 16F, 16F);
    }

    public float getAnchoDeBorde(){
        return anchoDeBorde;
    }

    public void setAnchoDeBorde(float anchoDeBorde){
        this.anchoDeBorde = anchoDeBorde;
    }

    public Color getBorderColor(){
        return borderColor;
    }

    public void setBorderColor(Color borderColor){
        this.borderColor = borderColor;
    }

    private Color borderColor;
    private float anchoDeBorde;
}

