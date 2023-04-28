/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mc2a.proyecto_1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

/**
 *
 * @author andre
 */

public class Ventana implements MouseListener, ActionListener, MouseMotionListener{
    int cantidad = 0;

    ArrayList<DibujarPunto> puntos;
    ArrayList<DibujarArista> aristas;
    static JFrame frame;
    JButton aplicar,nuevo;
    Container contenedor;
    JRadioButton CrearNodo, CrearArista;
    JLabel titulo;
    String larista = "0";
    ButtonGroup grupo;
    public JTextArea area;
    DibujarPunto pun[];
    public LienzodeGrafo lienzo;
    Grafo grafo;
    int j,i;
    int x,y;
        
private int contador = 0;

public Ventana(){
    frame = new JFrame();
    contenedor=frame.getContentPane();

    Font font=new Font("Tahoma",Font.BOLD,11);
    Font font1=new Font("Tahoma",Font.PLAIN,12);

    grafo=new Grafo();

    lienzo = new LienzodeGrafo();
    lienzo.setBounds(0, 0, 600, 600);
    lienzo.setBorder(BorderFactory.createBevelBorder(1));
    lienzo.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

    pun = new DibujarPunto[2];
    pun[0]=null;
    pun[1]=null;

    puntos = new ArrayList<DibujarPunto>();
    aristas = new ArrayList<DibujarArista>();

    area=new JTextArea();
    area.setFont(font1);

    titulo=new JLabel("Dibujar Grafo:");
    titulo.setFont(font);
    titulo.setBounds(610, 20, 130, 20);

    CrearNodo = new JRadioButton("Crear Nodo");
    CrearArista = new JRadioButton("Crear Arista");

    CrearArista.setFont(font);
    CrearNodo.setFont(font);

    grupo = new ButtonGroup();
    grupo.add(CrearNodo);
    grupo.add(CrearArista);
    CrearNodo.setSelected(true);

    CrearNodo.setBounds(680, 60, 120, 20);
    CrearArista.setBounds(680, 110, 130, 20);

    aplicar=new JButton("Crear árbol minimal");
    aplicar.setBounds(670,160, 150, 20);
    aplicar.setFont(font);
    nuevo=new JButton("Nuevo");
    nuevo.setBounds(670,200, 80, 20);
    nuevo.setFont(font);
    contenedor.setLayout(null);

    contenedor.add(CrearArista);
    contenedor.add(CrearNodo);
    contenedor.add(lienzo);
    contenedor.add(aplicar);
    contenedor.add(nuevo);
    contenedor.add(titulo);


    frame.setFont(font);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(null);
    frame.setSize(1000, 600);
    frame.setLocation(10, 20);
    frame.setTitle("Árbol Minimal_[MC2]Proyecto1");
    frame.setVisible(true);


    lienzo.addMouseListener(this);
    lienzo.addMouseMotionListener(this);
    aplicar.addActionListener(this);
    nuevo.addActionListener(this);
               
    }

public void mouseClicked(MouseEvent evento){
    if (evento.getClickCount() == 2&&CrearNodo.isSelected()==true){
        String nombre = "existen";

        do{
            try{
                nombre = JOptionPane.showInputDialog(null,"Ingrese nombre del Nodo");
                nombre.length();
            }
            catch(NullPointerException e){
                return;
            }if(grafo.getNombres().contains(nombre)||nombre==null){
                JOptionPane.showMessageDialog(null,"Los nombres de los nodos no pueden ser iguales.","Ingrese nuevamente el nodo",JOptionPane.ERROR_MESSAGE );
                nombre="existen";
            }
        }
        while(nombre=="existen");
        
        DibujarPunto punto = new DibujarPunto((int) evento.getPoint().getX() - 5, (int) evento.getPoint().getY() - 5, nombre);
        grafo.ingresarNodo(nombre);
        punto.pintarPunto(lienzo.getGraphics());
        puntos.add(punto);
        lienzo.setPuntos(puntos);
    }

}

public void actionPerformed(ActionEvent e){

    if(e.getSource()==aplicar){
        AlgoritmoKruskal nuevo=new AlgoritmoKruskal();

        Grafo kruskal= new Grafo();
        kruskal=nuevo.aplicarKruskal(grafo);
        lienzo.cambiarGrafo(kruskal);
        
    }if(e.getSource()==nuevo){
        grafo=new Grafo();
        lienzo.getAristas().clear();
        lienzo.getPuntos().clear();
        lienzo.getNeo().clear();
        aristas.clear();
        lienzo.punto=false;
        lienzo.repaint();
    }
}


public void mousePressed(MouseEvent arg0){
    contador=0;
    if(CrearArista.isSelected()){
        for (int i = 0; i < puntos.size(); i++){
            if (puntos.get(i).ecuacionDeCirculo(arg0.getPoint())){
                    puntos.get(i).setColorPunto(Color.RED);
                    x=puntos.get(i).getUbicacion().x;
                    y=puntos.get(i).getUbicacion().y;

                    if(larista.equals("1")){
                            pun[contador] = puntos.get(i);
                            contador++;
                    }
                    pun[contador] = puntos.get(i);
                    break;
                        }
                }
        }
        lienzo.repaint();
}

public void mouseReleased(MouseEvent arg0){
    if(CrearArista.isSelected()){
        if(pun[1]==null||pun[1].ecuacionDeCirculo(arg0.getPoint())==false||pun[0].getUbicacion().equals(pun[1].getUbicacion()))
                        contador=0;

            if(contador==2||larista.equals("1")){
                float peso=-1;
                do{
                    try{
                            peso = Float.parseFloat(JOptionPane.showInputDialog(null,"Ingrese el peso de la Arista"));
                    }catch(NumberFormatException ex){
                            JOptionPane.showMessageDialog(null,"El peso de la Arista debe ser un número","Ingrese nuevamente el Peso",JOptionPane.ERROR_MESSAGE );
                            peso=-1;
                    }catch(NullPointerException e){
                            pun[0].setColorPunto(Color.BLUE);
                            if(pun[1]!=null)
                                    pun[1].setColorPunto(Color.BLUE);
                            lienzo.punto=false;
                            lienzo.repaint();
                            return;
                            }
                    
                }while(peso==-1);
                DibujarArista arista=new DibujarArista(pun[0], pun[1],Integer.parseInt(larista),peso);

                aristas.add(arista);
                lienzo.setAristas(aristas);

                arista.pintarRecta(lienzo.getGraphics());
                grafo.adicionarEnlace(pun[0].getNombre(),pun[1].getNombre(),peso);

                contador = 0;
                pun[0].setColorPunto(Color.BLUE);
                pun[1].setColorPunto(Color.BLUE);

                lienzo.punto=false;
                lienzo.repaint();
        }
    }

    if(pun[0]!=null)
        pun[0].setColorPunto(Color.BLUE);
        lienzo.repaint();
        lienzo.punto=false;
        contador=0;
        pun[0]=null;
        pun[1]=null;
}

public void mouseEntered(MouseEvent arg0){
}

public void mouseExited(MouseEvent arg0){
}
public void mouseMoved(MouseEvent arg0) {
}

public void mouseDragged(MouseEvent e) {

if(CrearArista.isSelected()){
    for (int i = 0; i < puntos.size(); i++){
        if (puntos.get(i).ecuacionDeCirculo(e.getPoint())){
            pun[1] = puntos.get(i);
            pun[1].setColorPunto( Color.RED);
            break;
        }else
            if(pun[1]!=null&&pun[1]!=pun[0])
                    pun[1].setColorPunto(Color.BLUE);
    }

        if(pun[0]!=null){
                lienzo.setA(new Point(x,y));
                lienzo.setB(e.getPoint());

                lienzo.punto=true;
                lienzo.repaint();
        }
    contador=2;
    }
}




public Grafo getGrafo(){
    return grafo;
    }

}



