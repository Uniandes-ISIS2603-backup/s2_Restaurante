/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.ejb;

import co.edu.uniandes.csw.restaurante.entities.ClienteEntity;
import co.edu.uniandes.csw.restaurante.entities.ReservaEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.restaurante.persistence.ClientePersistence;
import co.edu.uniandes.csw.restaurante.persistence.ReservaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Cliente y Reserva.
 * @author Juan Ortega
 */

/**
 *
 *
 * @author ISIS2603
 */
public class ClienteReservaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ClienteReservaLogic.class.getName());

    @Inject
    private ReservaPersistence reservaPersistence;

    @Inject
    private ClientePersistence clientePersistence;

    
       /**
     * Asocia una Reserva existente a un Cliente
     *
     * @param clientesId Identificador de la instancia de Clietne
     * @param reservasId Identificador de la instancia de Reserva
     * @return Instancia de ReservaEntity que fue asociada a un Cliente
     */
    public ReservaEntity addReserva(Long clientesId, Long reservasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle una reserva al cliente con id = {0}", clientesId);
        ClienteEntity clienteEntity = clientePersistence.find(clientesId);
        ReservaEntity reservaEntity = reservaPersistence.find(reservasId);
        reservaEntity.setCliente(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle una reserva al cliente con id = {0}", clientesId);
        return reservaPersistence.find(reservasId);
    }
    
     /**
     * Retorna todas las reservas asociadas a un cliente
     *
     * @param clientesId El ID del cliente buscado
     * @return La lista de reservas del cliente
     */
    public List<ReservaEntity> getReservas(Long clientesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar las reservas asociadas a el cliente con id = {0}", clientesId);
        return clientePersistence.find(clientesId).getReservas();
    }
    
       /**
     * Retorna una reerva asociada a un cliente
     *
     * @param clientesId El id del cliente a buscar.
     * @param reservasId El id de la reserva a buscar
     * @return La reserva encontrada del cliente dado.
     * @throws BusinessLogicException Si la reserva no se encuentra asociada al cliente 
     */
    public ReservaEntity getReserva(Long clientesId, Long reservasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la reserva con id = {0} del cliente con id = " + clientesId, reservasId);
        List<ReservaEntity> reservas = clientePersistence.find(clientesId).getReservas();
        ReservaEntity reservaEntity = reservaPersistence.find(reservasId);
        int index = reservas.indexOf(reservaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la reserva con id = {0} del cliente con id = " + clientesId, reservasId);
        if (index >= 0) {
            return reservas.get(index);
        }
        throw new BusinessLogicException("La reserva no está asociada a el cliente");
    }
    
     /**
     * Desasocia una reserva existente de un cliente existente
     *
     * @param clientesId Identificador de la instancia de cliente
     * @param reservasId Identificador de la instancia de la reserva
     */
    public void removeReserva(Long clientesId, Long reservasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una reserva con id = {0}", clientesId);
        ClienteEntity clienteEntity = clientePersistence.find(clientesId);
        ReservaEntity reservaEntity = reservaPersistence.find(reservasId);
        clienteEntity.getReservas().remove(reservaEntity);
        reservaPersistence.delete(reservasId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar una reserva del cliente con id = {0}", clientesId);
    }
    
    
}
