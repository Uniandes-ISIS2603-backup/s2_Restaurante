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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Cliente y Domicilio.
 * @author Juan Ortega
 */

 @Stateless
public class ClienteDomicilioLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ClienteDomicilioLogic.class.getName());

    @Inject
    private DomicilioPersistence domicilioPersistence;

    @Inject
    private ClientePersistence clientePersistence;

    
       /**
     * Asocia una Domicilio existente a un Cliente
     *
     * @param clientesId Identificador de la instancia de Clietne
     * @param domiciliosId Identificador de la instancia de Domicilio
     * @return Instancia de DomicilioEntity que fue asociada a un Cliente
     */
    public DomicilioEntity addDomicilio(Long clientesId, Long domiciliosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle una Domicilio al cliente con id = {0}", clientesId);
        ClienteEntity clienteEntity = clientePersistence.find(clientesId);
        DomicilioEntity domicilioEntity = domicilioPersistence.find(domiciliosId);
        domicilioEntity.setCliente(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle una Domicilio al cliente con id = {0}", clientesId);
        return domicilioPersistence.find(domiciliosId);
    }
    
       /**
     * Retorna una Domicilio asociada a un cliente
     *
     * @param clientesId El id del cliente a buscar.
     * @param domiciliosId El id de la Domicilio a buscar
     * @return La Domicilio encontrada del cliente dado.
     * @throws BusinessLogicException Si la Domicilio no se encuentra asociada al cliente 
     */
    public DomicilioEntity getDomicilio(Long clientesId, Long domiciliosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la Domicilio con id = {0} del cliente con id = " + clientesId, domiciliosId);
        DomicilioEntity domicilioEntity = domicilioPersistence.find(domiciliosId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la Domicilio con id = {0} del cliente con id = " + clientesId, domiciliosId);
        if(domicilioEntity ==null)
        {
            return domicilioEntity;
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
        DomicilioEntity domicilioEntity = domicilioPersistence.find(domiciliosId);
        clienteEntity.getDomicilios().remove(domicilioEntity);
        domicilioPersistence.delete(domiciliosId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar una Domicilio del cliente con id = {0}", clientesId);
    }
    
    
}
