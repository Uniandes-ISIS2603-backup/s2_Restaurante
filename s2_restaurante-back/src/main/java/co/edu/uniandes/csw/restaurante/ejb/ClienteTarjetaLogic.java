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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Cliente y Tarjeta.
 * @author Juan Ortega
 */
@Stateless
public class ClienteTarjetaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ClienteTarjetaLogic.class.getName());

    @Inject
    private TarjetaPersistence tarjetaPersistence;

    @Inject
    private ClientePersistence clientePersistence;

    
       /**
     * Asocia una Tarjeta existente a un Cliente
     *
     * @param clientesId Identificador de la instancia de Clietne
     * @param tarjetasId Identificador de la instancia de Tarjeta
     * @return Instancia de TarjetaEntity que fue asociada a un Cliente
     */
    public TarjetaEntity addTarjeta(Long clientesId, Long tarjetasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle una Tarjeta al cliente con id = {0}", clientesId);
        ClienteEntity clienteEntity = clientePersistence.find(clientesId);
        TarjetaEntity tarjetaEntity = tarjetaPersistence.find(tarjetasId);
        tarjetaEntity.setCliente(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle una Tarjeta al cliente con id = {0}", clientesId);
        return tarjetaPersistence.find(tarjetasId);
    }
    
       /**
     * Retorna una tarjeta asociada a un cliente
     *
     * @param clientesId El id del cliente a buscar.
     * @param tarjetasId El id de la Tarjeta a buscar
     * @return La Tarjeta encontrada del cliente dado.
     * @throws BusinessLogicException Si la Tarjeta no se encuentra asociada al cliente 
     */
    public TarjetaEntity getTarjeta(Long clientesId, Long tarjetasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la Tarjeta con id = {0} del cliente con id = " + clientesId, tarjetasId);
        TarjetaEntity tarjetaEntity = tarjetaPersistence.find(tarjetasId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la Tarjeta con id = {0} del cliente con id = " + clientesId, tarjetasId);
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
     * @param tarjetasId Identificador de la instancia de la Tarjeta
     */
    public void removeTarjeta(Long clientesId, Long tarjetasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una Tarjeta con id = {0}", clientesId);
        tarjetaPersistence.delete(tarjetasId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar una Tarjeta del cliente con id = {0}", clientesId);
    }
    
    
}
