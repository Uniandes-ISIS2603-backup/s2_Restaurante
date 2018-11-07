/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;

import co.edu.uniandes.csw.restaurante.entities.CalificacionEntity;
import co.edu.uniandes.csw.restaurante.entities.ClienteEntity;
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

    private ArrayList<ClienteDTO> clientes;

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
            clientes = new ArrayList<>();
            for (ClienteEntity entityClientes : sucursalEntity.getClientes()) {
                clientes.add(new ClienteDTO(entityClientes));
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

    public ArrayList<ClienteDTO> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<ClienteDTO> clientes) {
        this.clientes = clientes;
    }

    

}
