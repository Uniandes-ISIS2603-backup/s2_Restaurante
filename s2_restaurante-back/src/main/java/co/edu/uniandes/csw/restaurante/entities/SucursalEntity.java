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
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jp.romero12
 */
@javax.persistence.Entity
public class SucursalEntity extends BaseEntity implements Serializable {

    @PodamExclude
    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = javax.persistence.FetchType.LAZY)
    private List<ReservaEntity> reservas = new ArrayList<ReservaEntity>();

    @PodamExclude
    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = javax.persistence.FetchType.LAZY)
    private List<DomicilioEntity> domicilios;

    @PodamExclude
    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = javax.persistence.FetchType.LAZY)
    private List<PlatoEntity> platos;

    @PodamExclude
    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = javax.persistence.FetchType.LAZY)
    private List<CalificacionEntity> calificaciones;

    @PodamExclude
    @OneToMany(mappedBy = "sucursal", fetch=FetchType.LAZY)
    private List<MesaEntity> mesas;

    public List<ClienteEntity> getClientes() {
        return clientes;
    }
    
    @PodamExclude
    @ManyToMany
    private List<ClienteEntity> clientes = new ArrayList<>();

    public void setClientes(List<ClienteEntity> clientes) {
        this.clientes = clientes;
    }

    public List<MesaEntity> getMesas() {
        return mesas;
    }

    public void setMesas(List<MesaEntity> mesas) {
        this.mesas = mesas;
    }

    public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public List<PlatoEntity> getPlatos() {
        return platos;
    }

    public void setPlatos(List<PlatoEntity> platos) {
        this.platos = platos;
    }

    private String direccion;

    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }

    public List<DomicilioEntity> getDomicilios() {
        return domicilios;
    }

    public void setDomiclios(List<DomicilioEntity> domiclios) {
        this.domicilios = domiclios;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String pDireccion) {
        this.direccion = pDireccion;
    }

}
