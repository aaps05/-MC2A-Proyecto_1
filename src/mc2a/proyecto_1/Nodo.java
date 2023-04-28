/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mc2a.proyecto_1;

import java.util.ArrayList;

/**
 *
 * @author andre
 */
public class Nodo {
    
private String nombre;
private ArrayList<Enlace> enlaces;
private int enlacesExistentes;
 
public ArrayList<Enlace> getEnlaces(){
    return enlaces;
}

public Nodo(String newName){
    nombre = newName;
    enlacesExistentes = -1;
    enlaces = new ArrayList<Enlace>();
}
 
public int getEnlacesExistentes(){
    return enlacesExistentes;
}

public String getNombre(){
    return nombre;
}

public void agregarEnlace(String enlazar, double peso){
    if (enlacesExistentes == -1){
        enlaces.add(new Enlace(enlazar,peso));
        enlacesExistentes++;
    }else{
        int posicion;
        posicion = existeEnlace(enlazar);
        if (posicion == -1){
            enlaces.add(new Enlace(enlazar,peso));
            enlacesExistentes++;
        }
    }
}

public int existeEnlace(String enlazar){
    for (int i = 0; i < enlaces.size(); i++){
        Enlace miEnlace;
        miEnlace = enlaces.get(i);
        if (miEnlace.getDestino().equals(enlazar))
            return i;
    }
    return -1;
}

public double enlacePosicion(int posi){
    Enlace miEnlace;
    miEnlace = enlaces.get(posi);
    return miEnlace.getPeso();
}

public String NodoPosicion(int posi){
    Enlace miEnlace;
    miEnlace = enlaces.get(posi);
    return miEnlace.getDestino();
}

}
