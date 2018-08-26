/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;

import java.util.Date;


/**
 *
 * @author ja.ortega
 */
public class ReservaDTO{

    private static final long serialVersionUID = 1L;
  
     /**
     * ID de la reserva
     */
    private Long id;
    /**
     * Fecha de la reserva
     */
    private Date hora;
    /**
     * Cantidad de personas para la reserva
     */
    private int cantidadPersonas;

    
    //METODOS
    
    /**
     * Metodo que retorna el id unico de una reserva
     *
     * @return Id unico de una reserva
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Metodo que retorna la hora de la reserva
     *
     * @return La hora en la cual ser hara la reserva
     */
    public Date getHora(){
        return hora;
    }
    
    /**
     * Metodo que retorna la cantidad de personas que estan inscritas en la reserva
     *
     * @return Cantidad de personas inscritas en la reserva
     */
    public int getCantidadPersonas(){
        return cantidadPersonas;
    }
    
     /**
     * Modifica la hora de la reserva
     *
     * @param hora - nueva hora
     */
    public void setHora(Date hora){
        this.hora = hora;
    }
    
     /**
     * Modifica la la cantidad de personas inscritas para la reserva 
     *
     * @param cantidadPersonas - nueva cantidad de personas
     */
    public void setCantidadPersonas(int cantidadPersonas){
        this.cantidadPersonas = cantidadPersonas;
    }
    
    /**
     * Modifica el id de la reserva
     *
     * @param id - Nuevo id
     */
    public void setId(Long id) {
        this.id = id;
    }
   
}
