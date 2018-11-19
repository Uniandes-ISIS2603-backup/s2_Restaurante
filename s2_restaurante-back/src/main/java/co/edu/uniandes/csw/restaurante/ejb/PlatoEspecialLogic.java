/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.ejb;
import co.edu.uniandes.csw.restaurante.entities.PlatoEspecialEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.restaurante.persistence.PlatoEspecialPersistence;
import co.edu.uniandes.csw.restaurante.persistence.SucursalPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class PlatoEspecialLogic {
    private static final Logger LOGGER = Logger.getLogger(PlatoEspecialLogic.class.getName());

    @Inject
    private PlatoEspecialPersistence persistence;
    
    @Inject
    private SucursalPersistence sucursalPersistence;
    
    public PlatoEspecialEntity createPlatoEspecial(PlatoEspecialEntity platoEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del plato especial");
      
        
        
        if (platoEntity.getSucursal()== null || sucursalPersistence.find(platoEntity.getSucursal().getId()) == null) {
          throw new BusinessLogicException("La sucursal es inválida");
        }
        else{
            PlatoEspecialEntity newPlatoEspecialEntity = persistence.create(platoEntity);
            LOGGER.log(Level.INFO, "Termina proceso de creación del plato especial ");
            return newPlatoEspecialEntity;
        }   
        
              
    }
    
public List <PlatoEspecialEntity> getPlatosEspeciales() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los platos especiales");
        List<PlatoEspecialEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los platos ");
        return lista;
    }
    
    public PlatoEspecialEntity getPlatoEspecial(Long platoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el plato especial con id = {0}", platoId);
        PlatoEspecialEntity platoEntity = persistence.find(platoId);
        if (platoEntity == null) {
            LOGGER.log(Level.SEVERE, "el plato especial con el id = {0} no existe", platoId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el plato especial con id = {0}", platoId);
        return platoEntity;
    }

public PlatoEspecialEntity updatePlatoEspecial(Long platoId, PlatoEspecialEntity platoEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el palto especial con id = {0}", platoId);
        PlatoEspecialEntity newEntity = persistence.update(platoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el plato especial con id = {0}", platoId);
        return newEntity;
    }
    
    public void deletePlatoEspecial(Long platoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el plato especial con id = {0}", platoId);
      
        persistence.delete(platoId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el palto especial con id = {0}", platoId);
    }
}
