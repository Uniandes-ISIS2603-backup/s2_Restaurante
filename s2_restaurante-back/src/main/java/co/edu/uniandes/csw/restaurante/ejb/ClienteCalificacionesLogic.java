/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.ejb;

import co.edu.uniandes.csw.restaurante.entities.ClienteEntity;
import co.edu.uniandes.csw.restaurante.entities.CalificacionEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.restaurante.persistence.ClientePersistence;
import co.edu.uniandes.csw.restaurante.persistence.CalificacionPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Cliente y Calificacion.
 * @author Juan Hidalgo
 */

@Stateless
public class ClienteCalificacionesLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ClienteCalificacionesLogic.class.getName());

    @Inject
    private CalificacionPersistence CalificacionPersistence;

    @Inject
    private ClientePersistence clientePersistence;

    
     /**
     * Asocia una Calificacion existente a un Cliente
     *
     * @param clientesId Identificador de la instancia de Clietne
     * @param CalificacionesId Identificador de la instancia de Calificacion
     * @return Instancia de CalificacionEntity que fue asociada a un Cliente
     */
    public CalificacionEntity addCalificacion(Long clientesId, Long CalificacionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle una Calificacion al cliente con id = {0}", clientesId);
        ClienteEntity clienteEntity = clientePersistence.find(clientesId);
        CalificacionEntity CalificacionEntity = CalificacionPersistence.find(CalificacionesId);
        CalificacionEntity.setCliente(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle una Calificacion al cliente con id = {0}", clientesId);
        return CalificacionPersistence.find(CalificacionesId);
    }
    
     /**
     * Retorna todas las Calificaciones asociadas a un cliente
     *
     * @param clientesId El ID del cliente buscado
     * @return La lista de Calificaciones del cliente
     */
    public List<CalificacionEntity> getCalificaciones(Long clientesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar las Calificaciones asociadas a el cliente con id = {0}", clientesId);
        return clientePersistence.find(clientesId).getCalificaciones();
    }
    
     /**
     * Retorna una reerva asociada a un cliente
     *
     * @param clientesId El id del cliente a buscar.
     * @param CalificacionesId El id de la Calificacion a buscar
     * @return La Calificacion encontrada del cliente dado.
     * @throws BusinessLogicException Si la Calificacion no se encuentra asociada al cliente 
     */
    public CalificacionEntity getCalificacion(Long clientesId, Long CalificacionesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la Calificacion con id = {0} del cliente con id = " + clientesId, CalificacionesId);
        List<CalificacionEntity> Calificaciones = clientePersistence.find(clientesId).getCalificaciones();
        CalificacionEntity CalificacionEntity = CalificacionPersistence.find(CalificacionesId);
        int index = Calificaciones.indexOf(CalificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la Calificacion con id = {0} del cliente con id = " + clientesId, CalificacionesId);
        if (index >= 0) {
            return Calificaciones.get(index);
        }
        throw new BusinessLogicException("La Calificacion no está asociada a el cliente");
    }
    
     /**
     * Desasocia una Calificacion existente de un cliente existente
     *
     * @param clientesId Identificador de la instancia de cliente
     * @param CalificacionesId Identificador de la instancia de la Calificacion
     */
    public void removeCalificacion(Long clientesId, Long CalificacionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una Calificacion con id = {0}", clientesId);
        ClienteEntity clienteEntity = clientePersistence.find(clientesId);
        CalificacionEntity CalificacionEntity = CalificacionPersistence.find(CalificacionesId);
        clienteEntity.getCalificaciones().remove(CalificacionEntity);
        CalificacionPersistence.delete(CalificacionesId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar una Calificacion del cliente con id = {0}", clientesId);
    }
    
    
}