/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;

import co.edu.uniandes.csw.restaurante.entities.ClienteEntity;
import java.io.Serializable;

/**
 *
 * @author j.prieto
 */
public class ClienteDTO implements Serializable{

    /**
     * Identificador del cliente
     */
    private Long id;

    /**
     * Nombre del cliente
     */
    private String nombre;

    /**
     * Método de pago del cliente
     */
    private String metodoPago;
    
    /**
     * Identificacion del cliente
     */
    private Long identificacion;

    /**
     * Constructor vacio
     */
    public ClienteDTO() {
    }
    

    /**
     * Construye un ClienteDTO a partir de un ClienteEntity
     *
     * @param entity ClienteEntity
     */
    public ClienteDTO(ClienteEntity entity) {
        if(entity !=null)
        {
        this.id = entity.getId();
        this.nombre = entity.getNombre();
        this.metodoPago = entity.getMetodoPago();
        this.identificacion = entity.getIdentificacion();
        }
    }

    /**
     * Retorna el ID del cliente
     * @return long
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Modifica el ID del cliente
     *
     * @param pId - nuevo ID
     */
    public void setId(Long pId) {
        this.id = pId;
    }

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
     * Obtiene la identificación del cliente
     * @return - retorna la identificación del cliente
     */
    public Long getIdentificacion(){
        return this.identificacion;
    }
    
    /**
     * Modifica la identificación del cliente
     * @param pIdentificacion - nueva identificación del cliente
     */
    public void setIdentificacion(Long pIdentificacion){
        this.identificacion = pIdentificacion;
    } 

    /**
     * Convierte un DTO a Entity
     *
     * @return Entity con los valores del DTO
     */
    public ClienteEntity toEntity() {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(this.getId());
        clienteEntity.setNombre(this.getNombre());
        clienteEntity.setMetodoPago(this.getMetodoPago());
        clienteEntity.setIdentificacion(this.getIdentificacion());
        return clienteEntity;
    }
}
