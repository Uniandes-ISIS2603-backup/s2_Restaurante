/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;

import co.edu.uniandes.csw.restaurante.entities.TarjetaEntity;
import java.io.Serializable;

/**
 *
 * @author jp.hidalgo
 */
public class TarjetaDTO implements Serializable{
    private Long id;
    
    /*
    * Relación a un cliente  
    * dado que esta tiene cardinalidad 1.
     */
    private ClienteDTO cliente;  
    
    /**
     *
     */
    public TarjetaDTO(){
        
    }
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param tarjetaEntity: Es la entidad que se va a convertir a DTO
     */
    public TarjetaDTO(TarjetaEntity tarjetaEntity) {
        if (tarjetaEntity != null) {
            this.id = tarjetaEntity.getId();
                   
            if (tarjetaEntity.getCliente()!= null) {
                this.cliente = new ClienteDTO(tarjetaEntity.getCliente());
            } else {
                this.cliente = null;
            }
        }
    }   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    
    public TarjetaEntity toEntity() {
        TarjetaEntity tarjetaEntity = new TarjetaEntity();
        tarjetaEntity.setId(this.id);
        if (this.cliente != null) {
            tarjetaEntity.setCliente(this.cliente.toEntity());
        }
        
        return tarjetaEntity;
    }
    
    
}
