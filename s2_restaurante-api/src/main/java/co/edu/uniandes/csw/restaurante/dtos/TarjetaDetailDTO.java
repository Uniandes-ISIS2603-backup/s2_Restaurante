/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;

import co.edu.uniandes.csw.restaurante.entities.PuntoEntity;
import co.edu.uniandes.csw.restaurante.entities.TarjetaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jp.hidalgo
 */
public class TarjetaDetailDTO extends TarjetaDTO implements Serializable{
    /**
     * lista de puntos
     */
    private List<PuntoDTO> puntos;
    /**
     * constructor vacio
     */
    public TarjetaDetailDTO()
    {
        
    }
    /**
     * contructor de entity
     * @param tarjetaEntity
     */
    public TarjetaDetailDTO(TarjetaEntity tarjetaEntity)
    {
        super(tarjetaEntity);
        if (tarjetaEntity !=null &&tarjetaEntity.getPuntos()!= null) {
            puntos = new ArrayList<>();
            for (PuntoEntity entityBook : tarjetaEntity.getPuntos()) {
                puntos.add(new PuntoDTO(entityBook));
            }
        }
    }
     /**
     * Transformar un DTO a un Entity
     *
     * @return El DTO de la editorial para transformar a Entity
     */
    @Override
    public TarjetaEntity toEntity() {
        TarjetaEntity tarjetaEntity = super.toEntity();
        if (puntos != null) {
            List<PuntoEntity> booksEntity = new ArrayList<>();
            for (PuntoDTO dtoBook : puntos) {
                booksEntity.add(dtoBook.toEntity());
            }
            tarjetaEntity.setPuntos(booksEntity);
        }
        return tarjetaEntity;
    }

    public List<PuntoDTO> getPuntos() {
        return puntos;
    }

    public void setPuntos(List<PuntoDTO> puntos) {
        this.puntos = puntos;
    }
    
}
