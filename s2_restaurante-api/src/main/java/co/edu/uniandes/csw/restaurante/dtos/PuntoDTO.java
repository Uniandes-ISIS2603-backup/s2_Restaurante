/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author estudiante
 */
public class PuntoDTO implements Serializable{
    private Date fechaCreacion;
    private long id;

    public PuntoDTO() {
    }

    
    public long getId() {
        return id;
    }
    
    
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setId(long id) {
        this.id = id;
    }

    
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    
    
}
