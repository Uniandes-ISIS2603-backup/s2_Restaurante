/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;

import co.edu.uniandes.csw.restaurante.entities.CalificacionEntity;

/**
 *
 * @author iy.barbosa
 */
public class CalificacionDTO {
    
    /**
     * Identificador de la calificacion
     */
    private Long id;
    /**
     * puntaje que da el cliente en la calificacion
     */
    private Integer puntaje;
    /**
     * sucursal la cual se esta calificando
     */
    private SucursalDTO sucursal;
    /**
     * cliente que califica la sucursal 
     */
    private ClienteDTO cliente ;
    
    
    /**
     * constructor vacio 
     */
    public CalificacionDTO (){
    
    }
    
    
    /**
     * Retorna el puntaje de la calificacion
     */
    public Integer getPuntaje() {
        return puntaje;
    }
    
    /**
     * Modifica el puntaje de la calificacion
     *
     * @param pPuntaje - nuevo ID
     */
    public void setPuntaje(Integer pPuntaje) {
        this.puntaje = pPuntaje;
    }
    
    /**
     * Retorna la sucuarsal que se esta calificando
     */
    public SucursalDTO getSucursal() {
        return sucursal;
    }

    /**
     * Modifica el puntaje de la calificacion
     *
     * @param pSucursal - nuevo ID
     */
    public void setSucursal(SucursalDTO pSucursal) {
        this.sucursal = pSucursal;
    }
    /**
     * Retorna el cliente que califica
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * Modifica el puntaje de la calificacion
     *
     * @param pCliente - nuevo ID
     */
    public void setCliente(ClienteDTO pCliente) {
        this.cliente = pCliente;
    }
    /**
     * Retorna el Id de la calificacion
     */
    public Long getId(){
        return id ;
    }
    
    /**
     * Modifica el Id  de la calificacion
     *
     * @param pId - nuevo ID
     */
    public void setId(Long pId){
        id = pId ;
    }
    
    /**
     * Construye un CalificacionDTO a partir de un CalificacionEntity
     *
     * @param entity CalificacionEntity
     */
    public CalificacionDTO(CalificacionEntity entity) {
       // this.id = entity.getId();
     
    }
    
     /**
     * Convierte un DTO a Entity
     *
     * @return Entity con los valores del DTO
     */
    public CalificacionEntity toEntity() {
       CalificacionEntity entity = new CalificacionEntity();
       //calificacion.setId(this.id);
      // cliente.setNombre(this.nombre);
       //cliente.setMetodoPago(this.metodoPago);
       return entity;
   }
   
    
}
