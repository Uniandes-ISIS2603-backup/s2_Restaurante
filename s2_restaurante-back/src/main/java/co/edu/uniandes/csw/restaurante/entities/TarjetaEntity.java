/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
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
    @OneToMany(mappedBy = "tarjeta", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<PuntoEntity> puntos = new ArrayList<PuntoEntity>();
    
    @PodamExclude
    @OneToOne
    private ClienteEntity cliente;
    
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

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity clienteID) {
        this.cliente = clienteID;
    }        
}
