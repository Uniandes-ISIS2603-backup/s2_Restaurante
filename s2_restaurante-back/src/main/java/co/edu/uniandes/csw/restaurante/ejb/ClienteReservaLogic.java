/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.ejb;

import co.edu.uniandes.csw.restaurante.entities.ClienteEntity;
import co.edu.uniandes.csw.restaurante.entities.ReservaEntity;
import co.edu.uniandes.csw.restaurante.persistence.ClientePersistence;
import co.edu.uniandes.csw.restaurante.persistence.ReservaPersistence;
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
     * Asocia un Book existente a un Author
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
}
