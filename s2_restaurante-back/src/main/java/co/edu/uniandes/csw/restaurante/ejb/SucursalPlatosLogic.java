/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.ejb;

import co.edu.uniandes.csw.restaurante.entities.SucursalEntity;
import co.edu.uniandes.csw.restaurante.entities.PlatoEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.restaurante.persistence.SucursalPersistence;
import co.edu.uniandes.csw.restaurante.persistence.PlatoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;



/**
 *
 *
 * @author jp.romero12
 */
@Stateless
public class SucursalPlatosLogic {
    
    private static final Logger LOGGER = Logger.getLogger(SucursalPlatosLogic.class.getName());

    @Inject
    private PlatoPersistence platoPersistence;

    @Inject
    private SucursalPersistence sucursalPersistence;

    
     
    public PlatoEntity addPlato(Long sucursalesId, Long platosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un nuevo plato a la sucursal con id = {0}", sucursalesId);
        SucursalEntity sucursalEntity = sucursalPersistence.find(sucursalesId);
        PlatoEntity platoEntity = platoPersistence.find(platosId);
        platoEntity.setSucursal(sucursalEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un plato a la sucursal con id = {0}", sucursalesId);
        return platoPersistence.find(platosId);
    }
    
    
    public List<PlatoEntity> getPlatos(Long sucursalesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los platos asociadas a la sucursal con id = {0}", sucursalesId);
        return sucursalPersistence.find(sucursalesId).getPlatos();
    }
    
    
    public PlatoEntity getPlato(Long sucursalesId, Long platosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el plato con id = {0} de la sucursal con id = " + sucursalesId, platosId);
        List<PlatoEntity> platos = sucursalPersistence.find(sucursalesId).getPlatos();
        PlatoEntity platoEntity = platoPersistence.find(platosId);
        int index = platos.indexOf(platoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el plato con id = {0} de la sucursal con id = " + sucursalesId, platosId);
        if (index >= 0) {
            return platos.get(index);
        }
        throw new BusinessLogicException("El plato no está asociado a la sucursal");
    }
    
     
    public void removePlato(Long sucursalesId, Long platosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un plato con id = {0}", sucursalesId);
        SucursalEntity sucursalEntity = sucursalPersistence.find(sucursalesId);
        PlatoEntity platoEntity = platoPersistence.find(platosId);
        sucursalEntity.getPlatos().remove(platoEntity);
        platoPersistence.delete(platosId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un plato de la sucursal con id = {0}", sucursalesId);
    }
    
    
}