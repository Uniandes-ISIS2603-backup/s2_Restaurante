/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;

import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class DomicilioDTO implements Serializable{
    private float precio;
    private long id;

    public DomicilioDTO() {
    }

    public long getId() {
        return id;
    }

    public float getPrecio() {
        return precio;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    
}
