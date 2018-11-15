/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;

import co.edu.uniandes.csw.restaurante.entities.PlatoEspecialEntity;
import java.io.Serializable;

/**
 *
 * @author iy.barbosa
 */
public class PlatoEspecialDTO  implements Serializable {
     /**
     * Identificador del plato
     */
    private Long id;

    /**
     * Nombre del platp
     */
    private String name;
    /**
     * precio del plato
     */
    private Double precio;
   
    
    private SucursalDTO sucursal; 

    /**
     * Construye un PlatoEspecialDTO a partir de un PlatoEspecialEntity
     *
     * @param entity PlatoEntity
     */
    public PlatoEspecialDTO(PlatoEspecialEntity entity) {
         
            this.id = entity.getId();
            this.name = entity.getNombre();
            this.precio = entity.getPrecio();
            this.sucursal = new SucursalDTO(entity.getSucursal());
        
    }

    /**
     * Retorna el Id del plato
     */
    public Long getId() {
        return id;
    }

    /**
     * Retorna el nombre del plato
     */
    public String getName() {
        return name;
    }

    /**
     * Retorna el precio del plato
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Modifica el Id del plato
     *
     * * @param pId - nuevo ID
     */
    public void setId(Long pId) {
        id = pId;
    }

    /**
     * Modifica el nombre del plato
     *
     * * @param pName - nuevo ID
     */
    public void setName(String pName) {
        name = pName;
    }

    /**
     * Modifica el precio del plato
     *
     * * @param pPrecio - nuevo ID
     */
    public void setPrecio(Double pPrecio) {
        precio = pPrecio;
    }
    
      public SucursalDTO getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalDTO sucursal) {
        this.sucursal = sucursal;
    }

 
    public PlatoEspecialDTO() {

    }

    /**
     * Convierte un DTO a Entity
     *
     * @return Entity con los valores del DTO
     */
    public PlatoEspecialEntity toEntity() {
        PlatoEspecialEntity platoEntity = new PlatoEspecialEntity();
        platoEntity.setId(this.id);
        platoEntity.setNombre(this.name);
       platoEntity.setPrecio(this.precio);
        if (this.sucursal != null) {
            platoEntity.setSucursal(this.sucursal.toEntity());
        }
        return platoEntity;
    }
    
}
