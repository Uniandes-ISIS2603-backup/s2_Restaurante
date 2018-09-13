/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author jp.romero12
 */
@javax.persistence.Entity
public class SucursalEntity extends BaseEntity implements Serializable{
    

    @PodamExclude
    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ReservaEntity> reservas = new ArrayList<ReservaEntity>();
    
    private String direccion;
    private String ciudad;
    
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String pDireccion) {
        this.direccion = pDireccion;
    }
    
    public String getCiudad() {
        return this.ciudad;
    }
    
    public void setCiudad(String pCiudad) {
        this.ciudad = pCiudad;
    }
    
    
    
    
}
