/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;

import co.edu.uniandes.csw.restaurante.entities.PuntoEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jp.hidalgo
 */
public class PuntoDTO implements Serializable{
    /**
     * fecha de creacion del punto
     */
    private Date fechaCreacion;
    /**
     * id del punto
     */
    private Long id;

    /**
     * constructor vacio
     */
    public PuntoDTO() {
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param puntoEntity: Es la entidad que se va a convertir a DTO
     */
    public PuntoDTO(PuntoEntity puntoEntity) {
        if (puntoEntity != null) {
            this.id = puntoEntity.getId();
            this.fechaCreacion = puntoEntity.getFecha();
        }
    }  

    /**
     *  retorna el id del punto
     * @return
     */
    public long getId() {
        return id;
    }
    
    /**
     * retorna la fecha de creacion del punto
     * @return
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * cambia el id del punto
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * cambia la fecha de creacion del punto
     * @param fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public PuntoEntity toEntity() {
        PuntoEntity puntoEntity = new PuntoEntity();
        puntoEntity.setId(this.id);
        puntoEntity.setFecha(this.fechaCreacion);
        return puntoEntity;
    }
    
}
