/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.ejb;

import co.edu.uniandes.csw.restaurante.entities.ReservaEntity;
import co.edu.uniandes.csw.restaurante.entities.SucursalEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.restaurante.persistence.ReservaPersistence;
import co.edu.uniandes.csw.restaurante.persistence.SucursalPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author ja.ortega
 */
public class SucursalReservasLogic {
    
    
    private static final Logger LOGGER = Logger.getLogger(SucursalReservasLogic.class.getName());

    @Inject
    private ReservaPersistence reservaPersistence;

    @Inject
    private SucursalPersistence sucursalPersistence;
    
     /**
     * Asocia una Reserva existente a una sucursal
     *
     * @param sucursalesId Identificador de la instancia de la sucursal
     * @param reservasId Identificador de la instancia de Reserva
     * @return Instancia de ReservaEntity que fue asociada a una sucursal
     */
    public ReservaEntity addReserva(Long sucursalesId, Long reservasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle una reserva a la sucursal con id = {0}", sucursalesId);
        SucursalEntity sucursalEntity = sucursalPersistence.find(sucursalesId);
        ReservaEntity reservaEntity = reservaPersistence.find(reservasId);
        reservaEntity.setSucursal(sucursalEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle una reserva a la sucursal con id = {0}", sucursalesId);
        return reservaPersistence.find(reservasId);
    }
    
    /**
     * Retorna todas las reservas asociadas a una sucursal
     *
     * @param sucursalesId El ID de la sucursal buscada
     * @return La lista de reservas de la sucursal
     */
    public List<ReservaEntity> getReservas(Long sucursalesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar las reservas asociadas a la sucursal con id = {0}", sucursalesId);
        return sucursalPersistence.find(sucursalesId).getReservas();
    }
    
    /**
     * Retorna una reerva asociada a un cliente
     *
     * @param sucursalesId El id de la sucursal a buscar.
     * @param reservasId El id de la reserva a buscar
     * @return La reserva encontrada de la sucursal dada.
     * @throws BusinessLogicException Si la reserva no se encuentra asociada a la sucursal 
     */
    public ReservaEntity getReserva(Long sucursalesId, Long reservasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la reserva con id = {0} de la sucursal con id = " + sucursalesId, reservasId);
        List<ReservaEntity> reservas = sucursalPersistence.find(sucursalesId).getReservas();
        ReservaEntity reservaEntity = reservaPersistence.find(reservasId);
        int index = reservas.indexOf(reservaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la reserva con id = {0} de la sucursal con id = " + sucursalesId, reservasId);
        if (index >= 0) {
            return reservas.get(index);
        }
        throw new BusinessLogicException("La reserva no está asociada a la sucursal");
    }
    
    
   /**
     * Desasocia una reserva existente de una sucursal existente
     *
     * @param sucursalesId Identificador de la instancia de la sucursal
     * @param reservasId Identificador de la instancia de la reserva
     */
    public void removeReserva(Long sucursalesId, Long reservasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una reserva con id = {0}", sucursalesId);
        SucursalEntity sucursalEntity = sucursalPersistence.find(sucursalesId);
        ReservaEntity reservaEntity = reservaPersistence.find(reservasId);
        sucursalEntity.getReservas().remove(reservaEntity);
        reservaPersistence.delete(reservasId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar una reserva de la reserva con id = {0}", sucursalesId);
    }
    
}
