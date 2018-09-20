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
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author j.prieto
 */
@javax.persistence.Entity
public class ClienteEntity extends BaseEntity implements Serializable {

        
    private static final long serialVersionUID =1L;

    @PodamExclude
    @OneToOne
    private TarjetaEntity tarjeta;

    @PodamExclude
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = javax.persistence.FetchType.LAZY)
    private List<ReservaEntity> reservas = new ArrayList<ReservaEntity>();

    @PodamExclude
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<DomicilioEntity> domicilios = new ArrayList<DomicilioEntity>();

    @PodamExclude
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CalificacionEntity> calificaciones = new ArrayList<CalificacionEntity>();

    @PodamExclude
    @ManyToMany
    private List<SucursalEntity> sucursales = new ArrayList<>();
    
    /**
     * Nombre del cliente
     */
    private String nombre;

    /**
     * Método de pago del cliente
     */
    private String metodoPago;

    /**
     * Retorna el nombre del cliente
     *
     * @return - nombre del cliente
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Modifica el nombre del cliente
     */
    public void setNombre(String pNombre) {
        this.nombre = pNombre;
    }

    /**
     * Retorna el método de pago del cliente
     *
     * @return
     */
    public String getMetodoPago() {
        return this.metodoPago;
    }

    /**
     * Modifica el método de pago del cliente
     */
    public void setMetodoPago(String pMetodoPago) {
        this.metodoPago = pMetodoPago;
    }

    /**
     * Retorna la tarjeta del cliente
     *
     * @return - tarjeta del cliente
     */
    public TarjetaEntity getTarjeta() {
        return tarjeta;
    }

    /**
     * Modifica la tarjeta del cliente
     *
     * @param tarjeta - nueva tarjeta del cliente
     */
    public void setTarjeta(TarjetaEntity tarjeta) {
        this.tarjeta = tarjeta;
    }

    /**
     * Retorna una lista con las reservas hechas por el cliente
     *
     * @return - lista con las reservas hechas por el cliente
     */
    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    /**
     * Modifica las reservas hechas por el cliente
     *
     * @param reservas - nueva lista con las reservas hechas por el cliente
     */
    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }

    /**
     * Retorna una lista con los domicilios hechos por el cliente
     *
     * @return - lista con los domicilios hechos por el cliente
     */
    public List<DomicilioEntity> getDomicilios() {
        return domicilios;
    }

    /**
     * Modifica la lista de domicilios hechos por el cliente
     *
     * @param domicilios - nueva lista de domicilios hechos por el cliente
     */
    public void setDomicilios(List<DomicilioEntity> domicilios) {
        this.domicilios = domicilios;
    }

    /**
     * Retorna la lista con las calificaciones hechas por el cliente
     *
     * @return - lista de calificaciones hechas por el cliente
     */
    public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

    /**
     * Modifica las calificaciones hechas por el cliente
     *
     * @param calificaciones - nueva lista de calificaciones
     */
    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public List<SucursalEntity> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<SucursalEntity> sucursales) {
        this.sucursales = sucursales;
    }
}
