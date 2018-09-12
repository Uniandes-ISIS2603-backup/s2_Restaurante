/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;
import java.io.Serializable;
/**
 *
 * @author jp.romero12
 */
public class SucursalDTO implements Serializable{
    
    private Long id;
    private String direccion;
    private String ciudad;
    
    public SucursalDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getDireccion()
    {
        return direccion;
    }
    
    public void setDireccion(String pDireccion) {
        this.direccion = pDireccion;
    }

    public void setId(long pId) {
        this.id = pId;
    }

    public String getCiudad()
    {
        return ciudad;
    }
    
    public void setCiudad(String pCiudad) {
        this.ciudad = pCiudad;
    }
    
    
}
