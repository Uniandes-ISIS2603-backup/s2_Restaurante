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
    private TarjetaEntity tarjeta;
    
    @PodamExclude
    @OneToOne
    private ClienteEntity cliente;
    
    /**
     * Precio domicilio
     */
    private Float precio;

    public TarjetaEntity getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(TarjetaEntity tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

}
