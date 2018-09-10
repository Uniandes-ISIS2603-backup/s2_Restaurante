/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;

import co.edu.uniandes.csw.restaurante.entities.TarjetaEntity;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author jp.hidalgo
 */
public class TarjetaDTO implements Serializable{
    private Long sucuralID;
    private Long clientID;
    private ArrayList puntos;
    
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
            this.clientID = tarjetaEntity.getId();
        }
    }   

    /**
     *
     * @return
     */
    public long getClientID() {
        return clientID;
    }

    /**
     *
     * @return
     */
    public ArrayList getPuntos() {
        return puntos;
    }

    /**
     *
     * @return
     */
    public long getSucuralID() {
        return sucuralID;
    }

    /**
     *
     * @param clientID
     */
    public void setClientID(long clientID) {
        this.clientID = clientID;
    }

    /**
     *
     * @param puntos
     */
    public void setPuntos(ArrayList puntos) {
        this.puntos = puntos;
    }

    /**
     *
     * @param sucuralID
     */
    public void setSucuralID(long sucuralID) {
        this.sucuralID = sucuralID;
    }
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public TarjetaEntity toEntity() {
        TarjetaEntity tarjetaEntity = new TarjetaEntity();
        tarjetaEntity.setId(this.clientID);
        return tarjetaEntity;
    }
    
    
}
