/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author estudiante
 */
public class TarjetaDTO implements Serializable{
    private long sucuralID;
    private long clientID;
    //private long id;
    private ArrayList puntos;
    
    public TarjetaDTO(){
        
    }

    public long getClientID() {
        return clientID;
    }

    public ArrayList getPuntos() {
        return puntos;
    }

    public long getSucuralID() {
        return sucuralID;
    }

    public void setClientID(long clientID) {
        this.clientID = clientID;
    }

    public void setPuntos(ArrayList puntos) {
        this.puntos = puntos;
    }

    public void setSucuralID(long sucuralID) {
        this.sucuralID = sucuralID;
    }
    
    
    
    
}
