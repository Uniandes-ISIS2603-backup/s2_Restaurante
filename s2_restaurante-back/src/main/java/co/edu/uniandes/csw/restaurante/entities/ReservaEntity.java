/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@javax.persistence.Entity
/**
 *
 * @author Juan Ortega. 
 */
public class ReservaEntity extends BaseEntity implements Serializable{
    
    private static final long serialVersionUID =1L;
    private Date hora;
    private Integer cantidadPersonas;

    @javax.persistence.ManyToOne()
    ClienteEntity cliente;
    
//    @javax.persistence.ManyToOne()
//    SucursalEntity sucursal;
    
    
    
    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Integer getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(Integer cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }
    
    
//    @javax.persistence.OneToMany(
//            mappedBy ="reserva",
//            fetch = javax.persistence.FetchType.LAZY
//    )
//    Collection<ClienteEntity> clientes;
    
    
    
    
    
}
