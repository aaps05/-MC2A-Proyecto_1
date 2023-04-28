/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mc2a.proyecto_1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.QuadCurve2D;

/**
 *
 * @author andre
 */
public class Arista {
    
private Arco arista;
private Punto a, b;
private Point inicial;
private Point terminal, ubicacionExt;
private Color color = new Color(0, 128, 128), aux = Color.RED;
private int tipo;
private float peso;

public Arista(Punto puntoA, Punto puntoB, int tipo, float peso){
    arista = new Arco(puntoA.getNombre(), puntoB.getNombre(), peso);

    a = puntoA;
    b = puntoB;
    this.tipo = tipo;
    this.peso = peso;
}

public void pintarRecta(Graphics ga){
    inicial = a.getUbicacion();
    terminal = b.getUbicacion();

    Graphics2D g = (Graphics2D) ga;
    double a = (inicial.y - terminal.y);
    double b = (inicial.x - terminal.x);
    double m = a / b;
    double grado = Math.atan(m);

    switch (tipo){
        case 0:
            g.setColor(color);
            g.drawLine(inicial.x + 5, inicial.y + 5, terminal.x + 5, terminal.y + 5);
            g.setColor(aux);
            g.drawString(peso + "", (inicial.x + terminal.x) / 2, (inicial.y + terminal.y) / 2);
            break;
        }
}

public Point getUbicacion(){
    return ubicacionExt;
}

public int getTipo(){
    return tipo;
}

public Arco getArista(){
    return arista;
}

public void setArista(Arco arista){
    this.arista = arista;
}

public void getColor(){
    color = new Color(0, 128, 128);
    aux = Color.RED;
}

public void setColor(Color color){
    if (color.equals(new Color(0, 128, 128)))
        aux = Color.RED;
    else
        aux = Color.BLUE;
        this.color = color;
}

}
