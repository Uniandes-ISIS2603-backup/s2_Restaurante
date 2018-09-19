/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.ejb;

import co.edu.uniandes.csw.restaurante.entities.ClienteEntity;
import co.edu.uniandes.csw.restaurante.entities.TarjetaEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.restaurante.persistence.ClientePersistence;
import co.edu.uniandes.csw.restaurante.persistence.TarjetaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Cliente y Tarjeta.
 * @author Juan Ortega
 */

/**
 *
 *
 * @author ISIS2603
 */
public class ClienteTarjetaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ClienteTarjetaLogic.class.getName());

    @Inject
    private TarjetaPersistence TarjetaPersistence;

    @Inject
    private ClientePersistence clientePersistence;

    
       /**
     * Asocia una Tarjeta existente a un Cliente
     *
     * @param clientesId Identificador de la instancia de Clietne
     * @param TarjetasId Identificador de la instancia de Tarjeta
     * @return Instancia de TarjetaEntity que fue asociada a un Cliente
     */
    public TarjetaEntity addTarjeta(Long clientesId, Long TarjetasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle una Tarjeta al cliente con id = {0}", clientesId);
        ClienteEntity clienteEntity = clientePersistence.find(clientesId);
        TarjetaEntity TarjetaEntity = TarjetaPersistence.find(TarjetasId);
        TarjetaEntity.setClienteID(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle una Tarjeta al cliente con id = {0}", clientesId);
        return TarjetaPersistence.find(TarjetasId);
    }
    
       /**
     * Retorna una tarjeta asociada a un cliente
     *
     * @param clientesId El id del cliente a buscar.
     * @param TarjetasId El id de la Tarjeta a buscar
     * @return La Tarjeta encontrada del cliente dado.
     * @throws BusinessLogicException Si la Tarjeta no se encuentra asociada al cliente 
     */
    public TarjetaEntity getTarjeta(Long clientesId, Long TarjetasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la Tarjeta con id = {0} del cliente con id = " + clientesId, TarjetasId);
        TarjetaEntity tarjetaEntity = TarjetaPersistence.find(TarjetasId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la Tarjeta con id = {0} del cliente con id = " + clientesId, TarjetasId);
        if(tarjetaEntity ==null)
        {
            return tarjetaEntity;
        }
        throw new BusinessLogicException("La Tarjeta no está asociada a el cliente");
    }
    
     /**
     * Desasocia una Tarjeta existente de un cliente existente
     *
     * @param clientesId Identificador de la instancia de cliente
     * @param TarjetasId Identificador de la instancia de la Tarjeta
     */
    public void removeTarjeta(Long clientesId, Long TarjetasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una Tarjeta con id = {0}", clientesId);
        TarjetaPersistence.delete(TarjetasId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar una Tarjeta del cliente con id = {0}", clientesId);
    }
    
    
}
