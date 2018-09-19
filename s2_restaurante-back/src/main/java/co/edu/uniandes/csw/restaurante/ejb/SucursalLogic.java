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
 * @author jp.romero12
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
    
    public SucursalEntity getSucursal(Long sucursalId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la sucursal con id = {0}", sucursalId);
        SucursalEntity sucursalEntity = persistence.find(sucursalId);
        if (sucursalEntity == null) {
            LOGGER.log(Level.SEVERE, "La sucursal con el id = {0} no existe", sucursalId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la sucursal con id = {0}", sucursalId);
        return sucursalEntity;
    }
    
    public List<SucursalEntity> getSucursales() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las sucursales");
        List<SucursalEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las sucursales");
        return lista;
    }
    
    public SucursalEntity updateSucursal(Long sucursalId, SucursalEntity sucursalEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la sucursal id = {0}", sucursalId);
        SucursalEntity newSucursalEntity = persistence.update(sucursalEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la sucursal con id = {0}", sucursalId);
        return newSucursalEntity;
    }
    
    public void deleteSucursal(Long sucursalesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la sucursal con id = {0}", sucursalesId);
      
        persistence.delete(sucursalesId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la sucursales con id = {0}", sucursalesId);
    }
    
}
