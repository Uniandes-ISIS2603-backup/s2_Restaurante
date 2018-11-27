/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;

import co.edu.uniandes.csw.restaurante.entities.PlatoEntity;
import java.io.Serializable;

/**
 *
 * @author iy.barbosa
 */
public class PlatoDTO implements Serializable{

    /**
     * Identificador del plato
     */
    private Long id;

    /**
     * Nombre del platp
     */
    private String name;
    /**
     * imagen del platp
     */
    private String imagen;
    /**
     * precio del plato
     */
    private Double precio;
    
    private SucursalDTO sucursal;

    /**
     * Construye un PlatoDTO a partir de un PlatoEntity
     *
     * @param entity PlatoEntity
     */
    public PlatoDTO(PlatoEntity entity) {
        this.id = entity.getId();
        this.name = entity.getNombre();
        this.precio = entity.getPrecio();
        this.imagen= entity.getImagen();
        if (entity.getSucursal() != null) {
                this.sucursal = new SucursalDTO(entity.getSucursal());
            } else {
                this.sucursal = null;
            }
       
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
     * Retorna el nombre del plato
     */
    public String getImagen() {
        return imagen ;
    }

    /**
     * Retorna el precio del plato
     */
    public Double getPrecio() {
        return precio;
    }

    public SucursalDTO getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalDTO sucursal) {
        this.sucursal = sucursal;
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
     * Modifica el nombre del plato
     *
     * * @param pName - nuevo ID
     */
    public void setImagen(String pImaen) {
        imagen = pImaen;
    }

    /**
     * Modifica el precio del plato
     *
     * * @param pPrecio - nuevo ID
     */
    public void setPrecio(Double pPrecio) {
        precio = pPrecio;
    }

 
    public PlatoDTO() {

    }

    /**
     * Convierte un DTO a Entity
     *
     * @return Entity con los valores del DTO
     */
    public PlatoEntity toEntity() {
        PlatoEntity platoEntity = new PlatoEntity();
        platoEntity.setId(this.id);
        platoEntity.setNombre(this.name);
        platoEntity.setPrecio(this.precio);
        platoEntity.setImagen(imagen);
        if (this.sucursal != null) {
            platoEntity.setSucursal(this.sucursal.toEntity());
        }
        return platoEntity;
    }
    
    
    @Override
    public String toString() {
        return "PlatoDTO{" + "id=" + id + ", nombre=" + name + ", precio=" + precio + ", sucursal=" + sucursal + ", imagen=" + imagen +  '}';
    }
}
