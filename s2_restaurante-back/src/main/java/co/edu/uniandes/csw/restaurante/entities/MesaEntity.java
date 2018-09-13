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
public class MesaEntity extends BaseEntity implements Serializable{
    
    private String numero;
    private Boolean ocupada;
    
    
    @PodamExclude
    @OneToMany(mappedBy = "mesa",cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = javax.persistence.FetchType.LAZY)
    private List<ReservaEntity> reservas = new ArrayList<ReservaEntity>();
    
    

    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }
    
    
    
    public String getNumero() {
        return this.numero;
    }
    
    public void setNumero(String pNumero) {
        this.numero = pNumero;
    }
    
    public Boolean getOcupada() {
        return this.ocupada;
    }
    
    public void setOcupada(Boolean pOcupada) {
        this.ocupada = pOcupada;
    }
    
}
