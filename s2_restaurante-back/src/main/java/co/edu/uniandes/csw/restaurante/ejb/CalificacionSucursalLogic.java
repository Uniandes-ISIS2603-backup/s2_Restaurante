/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.ejb;

import co.edu.uniandes.csw.restaurante.entities.CalificacionEntity;
import co.edu.uniandes.csw.restaurante.entities.SucursalEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.restaurante.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.restaurante.persistence.SucursalPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author iy.barbosa
 */
public class CalificacionSucursalLogic {
    
    private static final Logger LOGGER = Logger.getLogger(CalificacionSucursalLogic.class.getName());

    @Inject
    private CalificacionPersistence caliPersistence;

    @Inject
    private SucursalPersistence sucursalPersistence;
    
     /**
     * Asocia una Reserva existente a una sucursal
     *
     * @param sucursalesId Identificador de la instancia de la sucursal
     * @param calificacionId Identificador de la instancia de calificaicon
     * @return Instancia de calificacionEntity que fue asociada a una sucursal
     */
    public CalificacionEntity addReserva(Long sucursalesId, Long calificacionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle una calificacion a la sucursal con id = {0}", sucursalesId);
        SucursalEntity sucursalEntity = sucursalPersistence.find(sucursalesId);
        CalificacionEntity calificacionEntity = caliPersistence.find(calificacionesId);
        calificacionEntity.setSucursal(sucursalEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle una calificacion a la sucursal con id = {0}", sucursalesId);
        return caliPersistence.find(calificacionesId);
    }
    
    /**
     * Retorna todas las calificaciones asociadas a una sucursal
     *
     * @param sucursalesId El ID de la sucursal buscada
     * @return La lista de calificaciones de la sucursal
     */
      public List<CalificacionEntity> getCalificaciones (Long sucursalesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar las calificacion asociadas a la sucursal con id = {0}", sucursalesId);
        return sucursalPersistence.find(sucursalesId).getCalificaciones();
    }
    /**
     * Retorna una calificacion asociada a un 
     *
     * @param sucursalesId El id de la sucursal a buscar.
     * @param calificacionId El id de la reserva a buscar
     * @return La reserva encontrada de la sucursal dada.
     * @throws BusinessLogicException Si la reserva no se encuentra asociada a la sucursal 
     */
    public CalificacionEntity getReserva(Long sucursalesId, Long calificacionesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la calificacion con id = {0} de la sucursal con id = " + sucursalesId, calificacionesId);
        List<CalificacionEntity> califi = sucursalPersistence.find(sucursalesId).getCalificaciones();
        CalificacionEntity caliEntity = caliPersistence.find(calificacionesId);
        int index = califi.indexOf(caliEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la calificacion con id = {0} de la sucursal con id = " + sucursalesId, calificacionesId);
        if (index >= 0) {
            return califi.get(index);
        }
        throw new BusinessLogicException("La calificacion no está asociada a la sucursal");
    }
    
    
   
    
}
