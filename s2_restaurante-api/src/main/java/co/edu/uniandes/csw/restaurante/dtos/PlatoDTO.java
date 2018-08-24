/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;

/**
 *
 * @author iy.barbosa
 */
public class PlatoDTO {
    private Long id;
    private String name;
    private Integer precio;
     
    public PlatoDTO (){
        
    }
     public Long getId(){
        return id ;
    }
    public Strig getName(){
        return name ;
    }
    public Integer getPrecio(){
       return precio ;
    }
    public void setId(Long pId){
        id = pId ;
    }
    public void setName(String pName){
         name=pName ;
    }
    public viod setPrecio (Integer pPrecio){
        precio = pPrecio ;
    }
    
}
