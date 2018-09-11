/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author jp.hidalgo
 */
@javax.persistence.Entity
public class TarjetaEntity extends BaseEntity implements Serializable {
    
    @PodamExclude
    @OneToMany
    private List<PuntoEntity> puntos;
    
    private Long clienteID;
    private Long sucursalID;

    public List<PuntoEntity> getPuntos() {
        return puntos;
    }

    public void setPuntos(List<PuntoEntity> puntos) {
        this.puntos = puntos;
    }

    public Long getClienteID() {
        return clienteID;
    }

    public void setClienteID(Long clienteID) {
        this.clienteID = clienteID;
    }

    public Long getSucursalID() {
        return sucursalID;
    }

    public void setSucursalID(Long sucursalID) {
        this.sucursalID = sucursalID;
    }
    
            
}
