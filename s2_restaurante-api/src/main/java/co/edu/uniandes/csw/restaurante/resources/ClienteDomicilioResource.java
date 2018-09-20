/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;

import co.edu.uniandes.csw.restaurante.dtos.DomicilioDTO;
import co.edu.uniandes.csw.restaurante.ejb.ClienteDomicilioLogic;
import co.edu.uniandes.csw.restaurante.ejb.DomicilioLogic;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Cliente y Domicilio.
 * 
 * @author ja.ortega
 */

@Stateless
public class ClienteDomicilioResource {
    
    private static final Logger LOGGER = Logger.getLogger(ClienteDomicilioResource.class.getName());

    @Inject
    private ClienteDomicilioLogic clienteDomicilioLogic;

    @Inject
    private DomicilioLogic DomicilioLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    
    /**
     * Asocia un Domicilio existente con un cliente existente
     *
     * @param clientesId El ID del cliente a el cual se le va a asociar la Domicilio
     * @param DomiciliosId El ID de la Domicilio que se asocia
     * @return JSON {@link DomicilioDTO} - La Domicilio asociada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la Domicilio.
     */
    @POST
    @Path("{DomicilioId: \\d+}")
    public DomicilioDTO addDomicilio(@PathParam("clientesId") Long clientesId, @PathParam("DomiciliosId") Long DomiciliosId) {
        LOGGER.log(Level.INFO, "ClienteDomicilioResource addDomicilio: input: clientesId {0} , DomiciliosId {1}", new Object[]{clientesId, DomiciliosId});
        if (DomicilioLogic.getDomicilio(DomiciliosId) == null) {
            throw new WebApplicationException("El recurso /Domicilios/" + DomiciliosId + " no existe.", 404);
        }
        DomicilioDTO dto = new DomicilioDTO(clienteDomicilioLogic.addDomicilio(clientesId, DomiciliosId));
        LOGGER.log(Level.INFO, "ClienteDomicilioResource addDomicilio: output: {0}", dto);
        return dto;
    }
   
    
     /**
     * Elimina la conexión entre la Domicilio y el cliente recibidos en la URL.
     *
     * @param clientesId El ID del cliente al cual se le va a desasociar la Domicilio
     * @param DomiciliosId El ID de la Domicilio que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la Domicilio.
     */
    @DELETE
    @Path("{DomiciliosId: \\d+}")
    public void removeBook(@PathParam("clientesId") Long clientesId, @PathParam("DomiciliosId") Long DomiciliosId) {
        LOGGER.log(Level.INFO, "AuthorBooksResource deleteDomicilio: input: clientesId {0} , DomiciliosId {1}", new Object[]{clientesId, DomiciliosId});
        if (DomicilioLogic.getDomicilio(DomiciliosId) == null) {
            throw new WebApplicationException("El recurso /Domicilios/" + DomiciliosId + " no existe.", 404);
        }
        clienteDomicilioLogic.removeDomicilio(clientesId, DomiciliosId);
        LOGGER.info("ClienteDomicilioResource deleteBook: output: void");
    }
    
  

}
