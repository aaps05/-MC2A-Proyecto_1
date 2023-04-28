/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mc2a.proyecto_1;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author andre
 */
 
public class LienzodeGrafo extends JComponent{
    
    private ArrayList<DibujarPunto> puntos;
    private ArrayList<DibujarArista> aristas;
    private ArrayList<DibujarArista> neo;
    private Point a, b;
    public boolean estado = false;
    public boolean punto = false;
 
    public LienzodeGrafo(){
        aristas = new ArrayList<DibujarArista>();
        puntos = new ArrayList<DibujarPunto>();
        neo = new ArrayList<DibujarArista>();
    }
 
public void paintComponent(Graphics g){
    if (punto){
        g.setColor(Color.BLUE);
        g.drawLine((int) a.getX() + 5, (int) a.getY() + 5, (int) b.getX() + 5, (int) b.getY() + 5);
    }
    for (int i = 0; i < aristas.size(); i++){
        final DibujarArista arista = (DibujarArista) aristas.get(i);
        arista.pintarRecta(g);
    }
    if (estado)
        for (int i = 0; i < neo.size(); i++){
            final DibujarArista arista = (DibujarArista) neo.get(i);
            arista.setColor(Color.RED);
            arista.pintarRecta(g);
        }
    for (int i = 0; i < puntos.size(); i++){
        final DibujarPunto punto = (DibujarPunto) puntos.get(i);
        punto.pintarPunto(g);
    }
}
 
public void cambiarGrafo(Grafo nuevo){
    Arco aux;
    for (int i = 0; i < aristas.size(); i++){
        aux = aristas.get(i).getArista();

        if (nuevo.busarArista(aux) == true)
            neo.add(aristas.get(i));
    }

    for (int i = 0; i < aristas.size(); i++){
        final Arco n = aristas.get(i).getArista();
        nuevo.getAristas().add(n);
    }

estado = true;
repaint();
}
 
public ArrayList<DibujarPunto> getPuntos(){
        return puntos;
}

public void setPuntos(final ArrayList<DibujarPunto> puntos){
        this.puntos = puntos;
}

public ArrayList<DibujarArista> getAristas(){
        return aristas;
}

public void setAristas(final ArrayList<DibujarArista> aristas){
        this.aristas = aristas;
}

public ArrayList<DibujarArista> getNeo(){
        return neo;
}

public void setNeo(final ArrayList<DibujarArista> neo){
        this.neo = neo;
}

public void setA(Point a){
        this.a = a;
}

public void setB(Point b){
        this.b = b;
}

}
