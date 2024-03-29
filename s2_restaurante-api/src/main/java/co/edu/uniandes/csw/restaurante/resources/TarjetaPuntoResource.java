/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;

import co.edu.uniandes.csw.restaurante.dtos.PuntoDTO;
import co.edu.uniandes.csw.restaurante.dtos.TarjetaDetailDTO;
import co.edu.uniandes.csw.restaurante.ejb.PuntoLogic;
import co.edu.uniandes.csw.restaurante.ejb.TarjetaPuntoLogic;
import co.edu.uniandes.csw.restaurante.entities.PuntoEntity;
import co.edu.uniandes.csw.restaurante.entities.TarjetaEntity;
import java.util.ArrayList;
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
    @Inject
    private PuntoLogic puntoLogic;
    @Inject
    private TarjetaPuntoLogic tarjetaPuntoLogic;
    
     /**
     * Asocia un libro existente con un autor existente
     *
     * @param tarjetaId El ID de la tarjeta a la cual se le va a asociar el punto
     * @param puntoId El ID del l que se asocia
     * @return JSON {@link PuntoDetailDTO} - El libro asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{PuntosId: \\d+}")
    public PuntoDTO addPunto(@PathParam("tarjetaId") Long tarjetaId, @PathParam("puntoId") Long puntoId) {
        LOGGER.log(Level.INFO, "TarjetaPuntoResource addPunto: input: tarjetaId {0} , puntoId {1}", new Object[]{tarjetaId, puntoId});
        if (puntoLogic.getPunto(puntoId) == null) {
            throw new WebApplicationException("El recurso /punto/" + puntoId + " no existe.", 404);
        }
        PuntoDTO detailDTO = new PuntoDTO(tarjetaPuntoLogic.addPunto(tarjetaId, puntoId));
        LOGGER.log(Level.INFO, "AuthorPuntosResource addPunto: output: {0}", detailDTO);
        return detailDTO;
    }
    /**
     * Busca y devuelve todos los libros que existen en un autor.
     *
     * @param tarjetaId El ID del autor del cual se buscan los libros
     * @return JSONArray {@link PuntoDetailDTO} - Los libros encontrados en el
     * autor. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<PuntoDTO> getPuntos(@PathParam("tarjetaId") Long tarjetaId) {
        LOGGER.log(Level.INFO, "TarjetaPuntoResource getPuntos: input: {0}", tarjetaId);
        List<PuntoDTO> lista = PuntosListEntity2DetailDTO(tarjetaPuntoLogic.getPuntos(tarjetaId));
        LOGGER.log(Level.INFO, "TarjetaPuntoResource getPuntos: output: {0}", lista.toString());

        return lista;
    }
     private List<PuntoDTO> PuntosListEntity2DetailDTO(List<PuntoEntity> entityList) {
        List<PuntoDTO> list = new ArrayList<>();
        for (PuntoEntity entity : entityList) {
            list.add(new PuntoDTO(entity));
        }
        return list;
    }
}
