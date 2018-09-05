/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;

import co.edu.uniandes.csw.restaurante.entities.DomicilioEntity;
import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class DomicilioDTO implements Serializable{
    /**
     * Precio total del domicilio
     */
    private Float precio;
    /**
     * id del domicilio
     */
    private Long id;
    /**
     * contructor vacio
     */
    public DomicilioDTO() {
    }
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param domicilioEntity: Es la entidad que se va a convertir a DTO
     */
    public DomicilioDTO(DomicilioEntity domicilioEntity) {
        if (domicilioEntity != null) {
            this.id = domicilioEntity.getId();
        }
    } 
    /**
     * da el id del domicilio
     * @return id del domicilio
     */
    public Long getId() {
        return id;
    }

    /**
     * da el precio del domicilio
     * @return precio del domicilio
     */
    public Float getPrecio() {
        return precio;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @param precio
     */
    public void setPrecio(Float precio) {
        this.precio = precio;
    }
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public DomicilioEntity toEntity() {
       DomicilioEntity domicilioEntity = new DomicilioEntity();
        domicilioEntity.setId(this.id);
        return domicilioEntity;
    }
    
}
