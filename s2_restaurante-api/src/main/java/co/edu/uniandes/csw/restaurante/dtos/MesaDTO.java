/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;


import co.edu.uniandes.csw.restaurante.entities.MesaEntity;
import co.edu.uniandes.csw.restaurante.resources.ReservaResource;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jp.romero12
 */
public class MesaDTO implements Serializable {

    private Long id;
    private Integer numero;
    private static final Logger LOGGER = Logger.getLogger(MesaDTO.class.getName());

    /*
    * Relación a un cliente  
    * dado que esta tiene cardinalidad 1.
     */
    private SucursalDTO sucursal; 
    
    public MesaDTO() {
    }

    public MesaDTO(MesaEntity mesaEntity) {
        if (mesaEntity != null) {
            this.id = mesaEntity.getId();
            this.numero = mesaEntity.getNumero();
            
            if (mesaEntity.getSucursal() != null) {
                this.sucursal = new SucursalDTO(mesaEntity.getSucursal());
            } else {
                this.sucursal = null;
            }
        }
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int pNumero) {
        this.numero = pNumero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long pId) {
        this.id= pId;
    }

    public SucursalDTO getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalDTO sucursal) {
        this.sucursal = sucursal;
    }
    
    public MesaEntity toEntity() {
        MesaEntity entity = new MesaEntity();
        LOGGER.log(Level.INFO, "Mesa createReserva: input: {0}", this.id);
        LOGGER.log(Level.INFO, "Mesa createReserva: input: {0}", this.numero);
        if(this.numero != null)  entity.setNumero(this.numero);
      
        entity.setId(this.id);
     
        if (this.sucursal != null) {
            entity.setSucursal(this.sucursal.toEntity());
        }
        return entity;
        
    }
}
