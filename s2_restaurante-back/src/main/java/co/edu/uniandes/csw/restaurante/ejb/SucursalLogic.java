/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.ejb;
import co.edu.uniandes.csw.restaurante.entities.SucursalEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
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
public class SucursalLogic {
    
    private static final Logger LOGGER = Logger.getLogger(SucursalLogic.class.getName());
    
    @Inject
    private SucursalPersistence persistence;
    public SucursalEntity createSucursal(SucursalEntity sucursalEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la sucursal");
        
        
        SucursalEntity newSucursalEntity = persistence.create(sucursalEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la sucursal");
        return newSucursalEntity;
    }
    
}
