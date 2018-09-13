/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;

import java.util.ArrayList;

/**
 *
 * @author jp.romero12
 */
public class SucursalDetailDTO {
    
    
    
    private ArrayList<ReservaDTO> reservas;
    
    private ArrayList<DomicilioDTO> domicilios;
    
    private ArrayList<CalificacionDTO> calificacion;
    
    private ArrayList<PlatoDTO> platos;
    
    private ArrayList<MesaDTO> mesas;
    
    
    public ArrayList<ReservaDTO> getReservas() {
        return this.reservas;
    }

    public void setReservas(ArrayList<ReservaDTO> pReservas) {
        this.reservas = pReservas;
    }
    
    public ArrayList<DomicilioDTO> getDomicilios() {
        return this.domicilios;
    }

    
    public void setDomicilios(ArrayList<DomicilioDTO> pDomicilios) {
        this.domicilios = pDomicilios;
    }
    
    public ArrayList<CalificacionDTO> getCalificacion() {
        return this.calificacion;
    }

    public void setCalificacion(ArrayList<CalificacionDTO> pCalificacion) {
        this.calificacion = pCalificacion;
    }
    
    public ArrayList<PlatoDTO> getPlatos() {
        return this.platos;
    }
    
    public void setPlatos(ArrayList<PlatoDTO> pPlatos) {
        this.platos = pPlatos;
    }
    
    public ArrayList<MesaDTO> getMesas() {
        return this.mesas;
    }

    public void setMesas(ArrayList<MesaDTO> pMesas) {
        this.mesas = pMesas;
    }
    
    
    
}
