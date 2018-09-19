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

    public SucursalDTO() {
    }

    public SucursalDTO(SucursalEntity sucursalEntity) {
        if (sucursalEntity != null) {
            this.id = sucursalEntity.getId();
            this.direccion = sucursalEntity.getDireccion();
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

    public void setId(long pId) {
        this.id = pId;
    }

}
