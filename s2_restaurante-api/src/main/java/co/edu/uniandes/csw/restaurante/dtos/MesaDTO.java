/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;
import java.io.Serializable;
/**
 *
 * @author jp.romero12
 */
public class MesaDTO implements Serializable{
    
    private int numero;
    private boolean ocupada;
    
    public MesaDTO() {
    }

    public long getNumero() {
        return numero;
    }
    
    public void setNumero(int pNumero)
    {
        this.numero = pNumero;
    }
    
    public boolean getOcupada ()
    {
        return ocupada;
    }
    
    public void setOcupada (boolean pOcupada)
    {
        this.ocupada = pOcupada;
    }
    
}
