/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;

import co.edu.uniandes.csw.restaurante.dtos.PuntoDTO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jp.hidalgo
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TarjetaPuntoResource {
    private static final Logger LOGGER = Logger.getLogger(TarjetaPuntoResource.class.getName());
    /*@Inject
    private PuntoLogic puntologic;
    */
     /**
     * Asocia un libro existente con un autor existente
     *
     * @param tarjetaId El ID de la tarjeta a la cual se le va a asociar el punto
     * @param puntoId El ID del l que se asocia
     * @return JSON {@link BookDetailDTO} - El libro asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{booksId: \\d+}")
    public PuntoDTO addPunto(@PathParam("tarjetaId") Long tarjetaId, @PathParam("puntoId") Long puntoId) {
        /*LOGGER.log(Level.INFO, "TarjetaPuntoResource addPunto: input: tarjetaId {0} , puntoId {1}", new Object[]{tarjetaId, puntoId});
        if (puntoLogic.getPunto(puntoId) == null) {
            throw new WebApplicationException("El recurso /punto/" + puntoId + " no existe.", 404);
        }
        PuntoDTO detailDTO = new PuntoDTO(tarjetaPuntoLogic.addBook(tarjetaId, puntoId));
        LOGGER.log(Level.INFO, "AuthorBooksResource addBook: output: {0}", detailDTO);*/
        return null;
    }
    /**
     * Busca y devuelve todos los libros que existen en un autor.
     *
     * @param tarjetaId El ID del autor del cual se buscan los libros
     * @return JSONArray {@link BookDetailDTO} - Los libros encontrados en el
     * autor. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<PuntoDTO> getBooks(@PathParam("tarjetaId") Long tarjetaId) {
        /*LOGGER.log(Level.INFO, "TarjetaPuntoResource getPuntos: input: {0}", tarjetaId);
        List<PuntoDTO> lista = booksListEntity2DTO(puntoLogic.getPuntos(tarjetaId));
        LOGGER.log(Level.INFO, "TarjetaPuntoResource getPuntos: output: {0}", lista.toString());

        return lista;
    */  
        return null;
    }
    
}
