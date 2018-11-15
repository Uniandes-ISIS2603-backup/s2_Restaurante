/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;

import co.edu.uniandes.csw.restaurante.entities.CalificacionEntity;
import java.io.Serializable;


/**
 *
 * @author iy.barbosa
 */
public class CalificacionDTO implements Serializable{
    
    /**
     * Identificador de la calificacion
     */
    private Long id;
    /**
     * puntaje que da el cliente en la calificacion
     */
    private Double puntaje;
    /**
     * sucursal la cual se esta calificando
     */
    private SucursalDTO sucursal;
    
    
    /**
     * Retorna el puntaje de la calificacion
     */
    public Double getPuntaje() {
        return puntaje;
    }
    
    /**
     * Modifica el puntaje de la calificacion
     *
     * @param pPuntaje - nuevo ID
     */
    public void setPuntaje(Double pPuntaje) {
        this.puntaje = pPuntaje;
    }
    
    /**
     * Retorna la sucuarsal que se esta calificando
     */
    public SucursalDTO getSucursal() {
        return sucursal;
    }

    /**
     * Modifica el puntaje de la calificacion
     *
     * @param pSucursal - nuevo ID
     */
    public void setSucursal(SucursalDTO pSucursal) {
        this.sucursal = pSucursal;
    }


    /**
     * Retorna el Id de la calificacion
     */
    public Long getId(){
        return id ;
    }
    
    /**
     * Modifica el Id  de la calificacion
     *
     * @param pId - nuevo ID
     */
    public void setId(Long pId){
        id = pId ;
    }
    public CalificacionDTO()
    {
        
    }
    /**
     * Construye un PlatoDTO a partir de un PlatoEntity
     *
     * @param entity PlatoEntity
     */
    public CalificacionDTO(CalificacionEntity entity) {
        this.id = entity.getId();
        this.puntaje = entity.getPuntaje();
          if (entity.getSucursal() != null) {
                this.sucursal = new SucursalDTO(entity.getSucursal());
            } else {
                this.sucursal = null;
            }
       
    }
    
     /**
     * Convierte un DTO a Entity
     *
     * @return Entity con los valores del DTO
     */
    public CalificacionEntity toEntity() {
       CalificacionEntity entity = new CalificacionEntity();
       entity.setId(this.id);
       entity.setPuntaje(this.puntaje);
       entity.setSucursal(this.sucursal.toEntity());
       return entity;
    }

   
    
}
