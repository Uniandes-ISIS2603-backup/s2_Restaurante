/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    
    @PodamExclude
    @OneToOne
    private ClienteEntity clienteID;
    
    @PodamExclude
    @OneToOne
    private SucursalEntity sucursal;

    public SucursalEntity getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalEntity sucursal) {
        this.sucursal = sucursal;
    }

    public List<PuntoEntity> getPuntos() {
        return puntos;
    }

    public void setPuntos(List<PuntoEntity> puntos) {
        this.puntos = puntos;
    }

    public ClienteEntity getClienteID() {
        return clienteID;
    }

    public void setClienteID(ClienteEntity clienteID) {
        this.clienteID = clienteID;
    }        
}
