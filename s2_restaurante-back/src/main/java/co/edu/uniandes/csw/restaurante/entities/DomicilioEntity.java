/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.entities;

import java.io.Serializable;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jp.hidalgo
 */
@javax.persistence.Entity
public class DomicilioEntity extends BaseEntity implements Serializable {
    
    
    @PodamExclude
    @OneToOne
    private ClienteEntity cliente;
    
    /*@PodamExclude
    @OneToOne
    private SucursalEntity sucural;
    */
    /**
     * Precio domicilio
     */
    private Float precio;

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
    
}
