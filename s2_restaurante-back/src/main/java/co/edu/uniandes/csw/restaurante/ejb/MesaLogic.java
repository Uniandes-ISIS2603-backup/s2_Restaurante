/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.ejb;

import co.edu.uniandes.csw.restaurante.entities.MesaEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.restaurante.persistence.MesaPersistence;
import co.edu.uniandes.csw.restaurante.persistence.SucursalPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jp.romero12
 */
@Stateless
public class MesaLogic {
    private static final Logger LOGGER = Logger.getLogger(MesaLogic.class.getName());

    @Inject
    private MesaPersistence persistence;
    
    @Inject
    private SucursalPersistence sucursalPersistence;
    
    public MesaEntity createMesa(MesaEntity mesaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la mesa");
      
        
        
        if (mesaEntity.getSucursal()== null || sucursalPersistence.find(mesaEntity.getSucursal().getId()) == null) {
          throw new BusinessLogicException("La sucursal es inválida");
        }
        else{
            MesaEntity newMesaEntity = persistence.create(mesaEntity);
            LOGGER.log(Level.INFO, "Termina proceso de creación de la mesa");
            return newMesaEntity;
        }   
              
    }
    
    public List<MesaEntity> getMesas() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las mesas");
        List<MesaEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las mesas");
        return lista;
    }
    
    public MesaEntity getMesa(Long mesaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la mesa con id = {0}", mesaId);
        MesaEntity mesaEntity = persistence.find(mesaId);
        if (mesaEntity == null) {
            LOGGER.log(Level.SEVERE, "La mesa con el id = {0} no existe", mesaId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la mesa con id = {0}", mesaId);
        return mesaEntity;
    }
    
        
    public MesaEntity updateMesa(Long mesaId, MesaEntity mesaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la mesa id = {0}", mesaId);
        MesaEntity newMesaEntity = persistence.update(mesaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la mesa con id = {0}", mesaId);
        return newMesaEntity;
    }
    
    public void deleteMesa(Long mesasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la mesa con id = {0}", mesasId);
      
        persistence.delete(mesasId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la mesa con id = {0}", mesasId);
    }
}
