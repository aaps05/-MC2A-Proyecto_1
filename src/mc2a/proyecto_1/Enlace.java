/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mc2a.proyecto_1;

/**
 *
 * @author andre
 */
public class Enlace {
    
private String destino;
private double peso;

public Enlace(String desti, double peso1){
    destino = desti;
    peso = peso1;
}

public Enlace(String desti){
    destino = desti;
    peso = -1;
}

public void modificar(double peso1){
    peso = peso1;
}

public String getDestino(){
    return destino;
}

public double getPeso(){
    return peso;
}

}
