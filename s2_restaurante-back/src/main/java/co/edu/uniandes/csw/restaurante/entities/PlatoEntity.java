/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.entities;

/**
 *
 * @author estudiante
 */
public class PlatoEntity {
    
    /**
     * Identificador del plato
     */
    private Long id;
    
     /**
     * Nombre del platp
     */
    private String name;
     /**
     * precio del plato
     */
    private Integer precio;
    
     /**
     * Constructor vacio
     */
    public PlatoEntity(){
        
    }
    
    /**
     * Retorna el Id del plato
     */
     public Long getId(){
        return id ;
    }
     
   /**
     * Retorna el nombre del plato
     */
    public String getName(){
        return name ;
    }
    
    /**
     * Retorna el precio del plato
     */
    public Integer getPrecio(){
       return precio ;
    }
    
      /**
     * Modifica el Id del plato 
     * 
     * * @param pId - nuevo ID
     */
    public void setId(Long pId){
        id = pId ;
    }
    
     /**
     * Modifica el nombre del plato
     * 
     * * @param pName - nuevo ID
     */
    public void setName(String pName){
         name=pName ;
    }
      /**
     * Modifica el precio del plato 
     * 
     * * @param pPrecio - nuevo ID
     */
    public void setPrecio (Integer pPrecio){
        precio = pPrecio ;
    }
    
    
     
    
}
