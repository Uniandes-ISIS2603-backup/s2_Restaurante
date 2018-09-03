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
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juliana Prieto Arcila
 */
@javax.persistence.Entity
public class ClienteEntity extends BaseEntity implements Serializable {

//    @PodamExclude
//    @OneToOne
//    private CalificacionEntity calificacion;
    
//    @PodamExclude
//    @OneToOne
//    private TarjetaEntity tarjeta;
    
    @PodamExclude
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ReservaEntity> reservas = new ArrayList<ReservaEntity>();
    
    
//    @PodamExclude
//    @OneToMany(mappedBy = "domicilio", cascade = CascadeType.PERSIST, orphanRemoval = true)
//    private List<DomicilioEntity> domicilios = new ArrayList<DomicilioEntity>();
    
    /**
     * Nombre del cliente
     */
    private String nombre;

    /**
     * Método de pago del cliente
     */
    private String metodoPago;

    /**
     * Construye un ClienteDTO a partir de un ClienteEntity
     *
     * @param entity ClienteEntity
     */
//    public ClienteDTO(ClienteEntity entity) {
//        this.id = entity.getId();
//        this.nombre = entity.getNombre();
//        this.metodoPago = entity.getMetodoPago();
//    }
    
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
     * Convierte un DTO a Entity
     *
     * @return Entity con los valores del DTO
     */
//    public ClienteEntity toEntity() {
//        ClienteEntity entity = new ClienteEntity();
//        cliente.setId(this.id);
//        cliente.setNombre(this.nombre);
//        cliente.setMetodoPago(this.metodoPago);
//        return entity;
//    }
}
