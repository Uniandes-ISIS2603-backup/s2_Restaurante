/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;

import co.edu.uniandes.csw.restaurante.entities.SucursalEntity;
import java.io.Serializable;

/**
 *
 * @author jp.romero12
 */
public class SucursalDTO implements Serializable {

    private Long id;
  
    private String direccion;
    
    private Double promedio;
    
    private Long numMesas;

    public SucursalDTO() {
    }

    public SucursalDTO(SucursalEntity sucursalEntity) {
        if (sucursalEntity != null) {
            this.id = sucursalEntity.getId();
            this.direccion = sucursalEntity.getDireccion();
            this.promedio = sucursalEntity.getPromedio();
            this.numMesas = sucursalEntity.getNumMesas();
        }
    }

    public Long getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String pDireccion) {
        this.direccion = pDireccion;
    }

    public void setId(Long pId) {
        this.id = pId;
    }
   
    public Double getPromedio()
    {
        return promedio;
    }
    
    public void setPromedio(Double pPromedio)
    {
        this.promedio = pPromedio;
    }
    
    public void setNumMesas(Long pMesas)
    {
        this.numMesas = pMesas;
    }
    
    public Long getNumMesas()
    {
        return numMesas;
    }
            
    public SucursalEntity toEntity() {
        SucursalEntity entity = new SucursalEntity();
        entity.setId(this.id);
        entity.setDireccion(this.direccion);
        entity.setPromedio(this.promedio);
        entity.setNumMesas(this.numMesas);
        return entity;
    }
    
}
