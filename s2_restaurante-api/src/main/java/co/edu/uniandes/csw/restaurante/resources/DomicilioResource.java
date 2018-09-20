/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;
import co.edu.uniandes.csw.restaurante.dtos.DomicilioDTO;
import co.edu.uniandes.csw.restaurante.dtos.DomicilioDTO;
import co.edu.uniandes.csw.restaurante.dtos.DomicilioDTO;
import co.edu.uniandes.csw.restaurante.ejb.DomicilioLogic;
import co.edu.uniandes.csw.restaurante.entities.DomicilioEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
/**
 *
 * @author estudiante
 */
@Path("domicilios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class DomicilioResource {
   @Inject
    private DomicilioLogic DomicilioLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(DomicilioResource.class.getName());


    /**
     * Busca el Domicilio con el ID asociado recibido en la URL y lo devuelve.
     *
     * @param DomiciliosId Identificador del Domicilio que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link DomicilioDTO} - El Domicilio buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el Domicilio.
     */
    @GET
    //Como recibimos dígitos, ponemos d+
    @Path("{DomiciliosId: \\d+}")
    public DomicilioDTO getDomicilio(@PathParam("DomiciliosId") Long DomiciliosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "DomicilioResource getDomicilio: input: {0}", DomiciliosId);
        DomicilioEntity DomicilioEntity = DomicilioLogic.getDomicilio(DomiciliosId);
        if (DomicilioEntity == null) {
            throw new WebApplicationException("El recurso /Domicilios/" + DomiciliosId + " no existe.", 404);
        }
        DomicilioDTO detailDTO = new DomicilioDTO(DomicilioEntity);
        LOGGER.log(Level.INFO, "DomicilioResource getDomicilio: output: {0}", detailDTO.toString());
        return detailDTO;
    }

    

    /**
     * Borra el Domicilio con el id asociado recibido en la URL.
     *
     * @param DomiciliosId Identificador del Domicilio que se desea borrar. Este
     * debe ser una cadena de dígitos.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * si el autor tiene libros asociados
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el autor a borrar.
     */
    @DELETE
    @Path("{DomiciliosId: \\d+}")
    public void deleteDomicilio(@PathParam("DomiciliosId") Long DomiciliosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "DomicilioResource deleteDomicilio: input: {0}", DomiciliosId);
        if (DomicilioLogic.getDomicilio(DomiciliosId) == null) {
            throw new WebApplicationException("El recurso /Domicilios/" + DomiciliosId + " no existe.", 404);
        }
        DomicilioLogic.deleteDomicilio(DomiciliosId);
        LOGGER.info("DomicilioResource deleteDomicilio: output: void");
    }


    
}
