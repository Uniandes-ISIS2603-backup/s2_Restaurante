/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;

import co.edu.uniandes.csw.restaurante.entities.ReservaEntity;
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

    /*
    * Relación a un cliente  
    * dado que esta tiene cardinalidad 1.
     */
    private ClienteDTO cliente;   
    
    
     /*
    * Relación a una sucursal  
    * dado que esta tiene cardinalidad 1.
     */
    private SucursalDTO sucursal;
    
    //METODOS
    
    /**
     * Constructor vacio
     */
    public ReservaDTO() {
    }
    /**
     * Construye una ReservaDTO a partir de una ReservaEntity
     *
     * @param entity ReservaEntity
     */
    public ReservaDTO(ReservaEntity entity) {
        
        if(entity!=null)
        { this.id = entity.getId();
          this.hora = entity.getHora();
          this.cantidadPersonas = entity.getCantidadPersonas();
            
          if (entity.getCliente()!= null) {
                this.cliente = new ClienteDTO(entity.getCliente());
            } else {
                this.cliente = null;
            }
           if (entity.getSucursal() != null) {
                this.sucursal = new SucursalDTO(entity.getSucursal());
            } else {
                this.sucursal = null;
            }
        }
    }
    
       public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public SucursalDTO getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalDTO sucursal) {
        this.sucursal = sucursal;
    }
    
    
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
    
    
        /**
     * Convierte un DTO a Entity
     *
     * @return Entity con los valores del DTO
     */
    public ReservaEntity toEntity() {
        ReservaEntity entity = new ReservaEntity();
        entity.setId(this.id);
        entity.setHora(this.hora);
        entity.setCantidadPersonas(this.cantidadPersonas);
        
        if (this.cliente != null) {
            entity.setCliente(this.cliente.toEntity());
        }
        
        if (this.sucursal != null) {
            entity.setSucursal(this.sucursal.toEntity());
        }
        
        return entity;
    }
   
}
