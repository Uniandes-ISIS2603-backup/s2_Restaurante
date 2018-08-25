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
public class CalificacionDTO {
    private Long id;
    private String name;
    //private SucursalDTO sucursal;
    
    public CalificacionDTO (){
    
    }
    
    public Long getId(){
        return id ;
    }
    public String getName(){
        return name ;
    }
    //public SucursalDTO getSucursar(){
      //  return sucursal ;
    //}
    
    public void setId(Long pId){
        id = pId ;
    }
    public void setName(String pName){
         name=pName ;
    }
    //public viod setSucursar(SucursalDTO pSucur){
      //   sucursal=pSucur ;
    //}
    
}
