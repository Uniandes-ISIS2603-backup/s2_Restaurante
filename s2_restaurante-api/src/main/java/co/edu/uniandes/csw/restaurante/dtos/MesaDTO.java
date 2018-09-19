/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;


import co.edu.uniandes.csw.restaurante.entities.MesaEntity;
import java.io.Serializable;

/**
 *
 * @author jp.romero12
 */
public class MesaDTO implements Serializable {

    private Long id;
    private int numero;

    public MesaDTO() {
    }

    public MesaDTO(MesaEntity mesaEntity) {
        if (mesaEntity != null) {
            this.id = mesaEntity.getId();
            this.numero = mesaEntity.getNumero();
        }
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int pNumero) {
        this.numero = pNumero;
    }

}
