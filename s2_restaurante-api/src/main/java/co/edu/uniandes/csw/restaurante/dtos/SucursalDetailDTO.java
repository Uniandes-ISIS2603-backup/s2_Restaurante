/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;

import co.edu.uniandes.csw.restaurante.entities.CalificacionEntity;
import co.edu.uniandes.csw.restaurante.entities.PlatoEntity;
import co.edu.uniandes.csw.restaurante.entities.SucursalEntity;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author jp.romero12
 */
public class SucursalDetailDTO extends SucursalDTO implements Serializable {

    
    
    private ArrayList<CalificacionDTO> calificaciones;

    private ArrayList<PlatoDTO> platos;

    public SucursalDetailDTO() {
        super();
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity, SucursalEntity a converit a DTO
     */
    public SucursalDetailDTO(SucursalEntity entity) {
        super(entity);
        //Crea un SucursalDTO con el entity que llega

        if (entity != null) {
            
            calificaciones = new ArrayList<>();
            for (CalificacionEntity entityCalificacion : entity.getCalificaciones()) {
                calificaciones.add(new CalificacionDTO(entityCalificacion));
            }
            
            platos = new ArrayList<>();
            for (PlatoEntity entityPlato : entity.getPlatos()) {
                platos.add(new PlatoDTO(entityPlato));
            }

        }
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return un SucursalEntity con base en el DTO actual
     */
    @Override
    public SucursalEntity toEntity() {
        SucursalEntity entity = super.toEntity();
        
        //Agrega al nuevo Entity la calificación del DTO
        if (calificaciones != null) {
            ArrayList<CalificacionEntity> calificacionesEntity = new ArrayList<>();
            for (CalificacionDTO calificacionDTO : calificaciones) {
                calificacionesEntity.add(calificacionDTO.toEntity());
            }
            entity.setCalificaciones(calificacionesEntity);
            
            if (platos != null) {
                ArrayList<PlatoEntity> platosEntity = new ArrayList<>();
                for (PlatoDTO platoDTO : platos) {
                    platosEntity.add(platoDTO.toEntity());
                }
                entity.setPlatos(platosEntity);
            }
        }
        return entity;
    }

    

    

    public ArrayList<PlatoDTO> getPlatos() {
        return platos;
    }

    public void setPlatos(ArrayList<PlatoDTO> platos) {
        this.platos = platos;
    }

    /**
     * Retorna la calificacion del sucursal
     *
     * @return - Calificación del sucursal
     */
    public ArrayList<CalificacionDTO> getCalificaciones() {
        return this.calificaciones;
    }

    /**
     * Modifica la calificación del sucursal
     *
     * @param pCalificaciones - Nueva calificación
     */
    public void setCalificaciones(ArrayList<CalificacionDTO> pCalificaciones) {
        this.calificaciones = pCalificaciones;
    }
}
