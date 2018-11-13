/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.entities;

import java.io.Serializable;
import uk.co.jemos.podam.common.PodamExclude;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 * Clase que representa un autor en la persistencia y permite su serialización
 *
 * @author iy.barbosa
 */
@javax.persistence.Entity
public class PlatoEspecialEntity extends BaseEntity implements Serializable {


    
  @PodamExclude
    @ManyToOne
    private SucursalEntity sucursal;
    
    /**
     * Nombre del plato
     */
    private String name;

    /**
     * Precio del plato
     */
    private Double precio;

    public String getNombre() {
        return name;
    }

    public void setNombre(String nombre) {
        this.name = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    
   public SucursalEntity getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalEntity sucursal) {
        this.sucursal = sucursal;
    }
    

}