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
    private Long sucuralID;
    private Long clientID;
    private Long id;
    
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
            this.sucuralID = tarjetaEntity.getSucursalID();
            this.clientID = tarjetaEntity.getClienteID();
                   
        }
    }   

    public Long getSucuralID() {
        return sucuralID;
    }

    public void setSucuralID(Long sucuralID) {
        this.sucuralID = sucuralID;
    }

    public Long getClientID() {
        return clientID;
    }

    public void setClientID(Long clientID) {
        this.clientID = clientID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public TarjetaEntity toEntity() {
        TarjetaEntity tarjetaEntity = new TarjetaEntity();
        tarjetaEntity.setId(this.id);
        tarjetaEntity.setClienteID(this.clientID);
        tarjetaEntity.setSucursalID(this.sucuralID);
        return tarjetaEntity;
    }
    
    
}
