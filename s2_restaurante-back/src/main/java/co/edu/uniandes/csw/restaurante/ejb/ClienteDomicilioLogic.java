/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.ejb;

import co.edu.uniandes.csw.restaurante.entities.ClienteEntity;
import co.edu.uniandes.csw.restaurante.entities.DomicilioEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.restaurante.persistence.ClientePersistence;
import co.edu.uniandes.csw.restaurante.persistence.DomicilioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Cliente y Domicilio.
 * @author Juan Ortega
 */

/**
 *
 *
 * @author ISIS2603
 */
public class ClienteDomicilioLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ClienteDomicilioLogic.class.getName());

    @Inject
    private DomicilioPersistence DomicilioPersistence;

    @Inject
    private ClientePersistence clientePersistence;

    
       /**
     * Asocia una Domicilio existente a un Cliente
     *
     * @param clientesId Identificador de la instancia de Clietne
     * @param DomiciliosId Identificador de la instancia de Domicilio
     * @return Instancia de DomicilioEntity que fue asociada a un Cliente
     */
    public DomicilioEntity addDomicilio(Long clientesId, Long DomiciliosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle una Domicilio al cliente con id = {0}", clientesId);
        ClienteEntity clienteEntity = clientePersistence.find(clientesId);
        DomicilioEntity DomicilioEntity = DomicilioPersistence.find(DomiciliosId);
        DomicilioEntity.setCliente(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle una Domicilio al cliente con id = {0}", clientesId);
        return DomicilioPersistence.find(DomiciliosId);
    }
    
       /**
     * Retorna una Domicilio asociada a un cliente
     *
     * @param clientesId El id del cliente a buscar.
     * @param DomiciliosId El id de la Domicilio a buscar
     * @return La Domicilio encontrada del cliente dado.
     * @throws BusinessLogicException Si la Domicilio no se encuentra asociada al cliente 
     */
    public DomicilioEntity getDomicilio(Long clientesId, Long DomiciliosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la Domicilio con id = {0} del cliente con id = " + clientesId, DomiciliosId);
        DomicilioEntity DomicilioEntity = DomicilioPersistence.find(DomiciliosId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la Domicilio con id = {0} del cliente con id = " + clientesId, DomiciliosId);
        if(DomicilioEntity ==null)
        {
            return DomicilioEntity;
        }
        throw new BusinessLogicException("La Domicilio no está asociada a el cliente");
    }
    /**
     * Desasocia una Domicilio existente de un cliente existente
     *
     * @param clientesId Identificador de la instancia de cliente
     * @param domiciliosId Identificador de la instancia de la Domicilio
     */
    public void removeDomicilio(Long clientesId, Long domiciliosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una Domicilio con id = {0}", clientesId);
        ClienteEntity clienteEntity = clientePersistence.find(clientesId);
        DomicilioEntity DomicilioEntity = DomicilioPersistence.find(domiciliosId);
        clienteEntity.getDomicilios().remove(DomicilioEntity);
        DomicilioPersistence.delete(domiciliosId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar una Domicilio del cliente con id = {0}", clientesId);
    }
    
    
}
