/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;

import co.edu.uniandes.csw.restaurante.entities.CalificacionEntity;
import co.edu.uniandes.csw.restaurante.entities.SucursalEntity;
import co.edu.uniandes.csw.restaurante.entities.DomicilioEntity;
import co.edu.uniandes.csw.restaurante.entities.MesaEntity;
import co.edu.uniandes.csw.restaurante.entities.PlatoEntity;
import co.edu.uniandes.csw.restaurante.entities.ReservaEntity;
import co.edu.uniandes.csw.restaurante.entities.SucursalEntity;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author jp.romero12
 */
public class SucursalDetailDTO extends SucursalDTO implements Serializable {

    private ArrayList<ReservaDTO> reservas;

    private ArrayList<DomicilioDTO> domicilios;

    private ArrayList<CalificacionDTO> calificaciones;

 
    private ArrayList<MesaDTO> mesas;

    public SucursalDetailDTO(SucursalEntity sucursalEntity) {
        super(sucursalEntity);
        if (sucursalEntity != null) {
            reservas = new ArrayList<>();
            for (ReservaEntity entityReservas : sucursalEntity.getReservas()) {
                reservas.add(new ReservaDTO(entityReservas));
            }
            domicilios = new ArrayList<>();
            for (DomicilioEntity entityDomicilios : sucursalEntity.getDomicilios()) {
                domicilios.add(new DomicilioDTO(entityDomicilios));
            }
            calificaciones = new ArrayList<>();
            for (CalificacionEntity entityCalificaciones : sucursalEntity.getCalificaciones()) {
                calificaciones.add(new CalificacionDTO(entityCalificaciones));
            }
           
            mesas = new ArrayList<>();
            for (MesaEntity entityMesas : sucursalEntity.getMesas()) {
                mesas.add(new MesaDTO(entityMesas));
            }
        }
    }

    public ArrayList<ReservaDTO> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<ReservaDTO> reservas) {
        this.reservas = reservas;
    }

    public ArrayList<DomicilioDTO> getDomicilios() {
        return domicilios;
    }

    public void setDomicilios(ArrayList<DomicilioDTO> domicilios) {
        this.domicilios = domicilios;
    }

    public ArrayList<CalificacionDTO> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(ArrayList<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public ArrayList<MesaDTO> getMesas() {
        return mesas;
    }

    public void setMesas(ArrayList<MesaDTO> mesas) {
        this.mesas = mesas;
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return un SucursalEntity con base en el DTO actual
     */
    @Override
    public SucursalEntity toEntity() {
        SucursalEntity entity = super.toEntity();
        //Agrega al nuevo Entity la tarjeta del DTO
        if (calificaciones != null) {
            ArrayList<CalificacionEntity> calificacionesEntity = new ArrayList<>();
            for (CalificacionDTO calificacionDTO : calificaciones) {
                calificacionesEntity.add(calificacionDTO.toEntity());
            }
            entity.setCalificaciones(calificacionesEntity);
            if (domicilios != null) {
                ArrayList<DomicilioEntity> domiciliosEntity = new ArrayList<>();
                for (DomicilioDTO domicilioDTO : domicilios) {
                    domiciliosEntity.add(domicilioDTO.toEntity());
                }
                entity.setDomiclios(domiciliosEntity);
            }
            if (mesas != null) {
                ArrayList<MesaEntity> mesaEntity = new ArrayList<>();
                for (MesaDTO mesaDTO : mesas) {
                    mesaEntity.add(mesaDTO.toEntity());
                }
                entity.setMesas(mesaEntity);
            }
            if (reservas != null) {
                ArrayList<ReservaEntity> reservaEntity = new ArrayList<>();
                for (ReservaDTO reservaDTO : reservas) {
                    reservaEntity.add(reservaDTO.toEntity());
                }
                entity.setReservas(reservaEntity);
            }
        }
        return entity;
    }

}
