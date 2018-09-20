/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;

import co.edu.uniandes.csw.restaurante.dtos.PuntoDTO;
import co.edu.uniandes.csw.restaurante.ejb.PuntoLogic;
import co.edu.uniandes.csw.restaurante.entities.PuntoEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
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
 * @author jp.hidalgo
 */
@Path("puntos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PuntoResource {
    
    @Inject
    private PuntoLogic PuntoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(PuntoResource.class.getName());

    /**
     * Crea un nuevo Punto con la información que se recibe en el cuerpo de la
     * petición y se regresa un objeto idéntico con un ID auto-generado por la
     * base de datos.
     *
     * @param Punto {@link PuntoDTO} - El Punto que se desea guardar.
     * @return JSON {@link PuntoDTO} - El autor guardado con el atributo ID
     * auto-generado.
     */
    @POST
    public PuntoDTO crearPunto(PuntoDTO Punto) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PuntoResource crearPunto: input: {0}", Punto.toString());
        PuntoDTO PuntoDTO = new PuntoDTO(PuntoLogic.createPunto(Punto.toEntity()));
        LOGGER.log(Level.INFO, "PuntoResource crearPunto: output: {0}", PuntoDTO.toString());
        return PuntoDTO;
    }


    /**
     * Borra el Punto con el id asociado recibido en la URL.
     *
     * @param PuntosId Identificador del Punto que se desea borrar. Este
     * debe ser una cadena de dígitos.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * si el autor tiene libros asociados
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el autor a borrar.
     */
    @DELETE
    @Path("{PuntosId: \\d+}")
    public void deletePunto(@PathParam("PuntosId") Long PuntosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PuntoResource deletePunto: input: {0}", PuntosId);
        if (PuntoLogic.getPunto(PuntosId) == null) {
            throw new WebApplicationException("El recurso /Puntos/" + PuntosId + " no existe.", 404);
        }
        PuntoLogic.deletePunto(PuntosId);
        LOGGER.info("PuntoResource deletePunto: output: void");
    }



}
