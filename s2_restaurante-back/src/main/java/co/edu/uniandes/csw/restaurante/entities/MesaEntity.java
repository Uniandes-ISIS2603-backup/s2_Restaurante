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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author jp.romero12
 */
@javax.persistence.Entity
public class MesaEntity extends BaseEntity implements Serializable{
    
    private int numero;
    
    
    @PodamExclude
    @OneToMany(mappedBy = "mesa",cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = javax.persistence.FetchType.LAZY)
    private List<ReservaEntity> reservas = new ArrayList<ReservaEntity>();
    
    @PodamExclude
    @ManyToOne
    private SucursalEntity sucursal;
    
    

    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }
    
    public int getNumero() {
        return this.numero;
    }
    
    public void setNumero(int pNumero) {
        this.numero = pNumero;
    }

    public SucursalEntity getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalEntity sucursal) {
        this.sucursal = sucursal;
    }
    
    
    
}
