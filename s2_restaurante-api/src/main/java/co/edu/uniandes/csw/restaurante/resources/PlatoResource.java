/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;

import co.edu.uniandes.csw.restaurante.dtos.PlatoDTO;
import co.edu.uniandes.csw.restaurante.ejb.PlatoLogic;
import co.edu.uniandes.csw.restaurante.entities.PlatoEntity;
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
 * @author iy.barbosa
 */
@Path("platos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PlatoResource {

    //    
    @Inject
    private PlatoLogic platoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(PlatoResource.class.getName());
    /**
     * Busca y devuelve todos los libros que existen en la aplicacion.
     *
     * @return JSONArray {@link BookDetailDTO} - Los libros encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<PlatoDTO> getPlatos() {
        LOGGER.info("PlatoResource getPlatos: input: void");
        List<PlatoDTO> listaPlatos = listEntity2DetailDTO(platoLogic.getPlatos());
        LOGGER.log(Level.INFO, "PlatoResource getPlatos: output: {0}", listaPlatos.toString());
        return listaPlatos;
    }

    /**
     * Busca el plato con el ID asociado recibido en la URL y lo devuelve.
     *
     * @param platosId Identificador del plato que se esta buscando.
     */
    @GET
    @Path("{platosId: \\d+}")
    public PlatoDTO getPlato(@PathParam("platosId") Long platosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PlatoResource getPlato: input: {0}", platosId);
        PlatoEntity platoEntity = platoLogic.getPlato(platosId);
        if (platoEntity == null) {
            throw new WebApplicationException("El recurso /platos/" + platosId + " no existe.", 404);
        }
        PlatoDTO platoDTO = new PlatoDTO(platoEntity);
        LOGGER.log(Level.INFO, "PlatoResource getPlato: output: {0}", platoDTO.toString());
        return platoDTO;
    }

    /**
     * crea un nuevo plato con la informacion dada
     *
     * @param plato el plato que se desea guardar
     * @return
     */
    @POST
    public PlatoDTO crearPlato(PlatoDTO plato) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PlatoResource crearPlato: input: {0}", plato.toString());
        PlatoDTO platoDTO = new PlatoDTO(platoLogic.createPlato(plato.toEntity()));
        LOGGER.log(Level.INFO, "PlatoResource crearPlato: output: {0}", platoDTO.toString());
        return platoDTO;
    }

    /**
     * Borra el plato con el ID asociado recibido en la URL.
     *
     * @param platosId Identificador del cliente que se desea borrar. Este debe
     * ser una cadena de dígitos.
     */
    @DELETE
    @Path("{platosId: \\d+}")
    public void deletePlato(@PathParam("platosId") Long platosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PlatoResource deletePlato: input: {0}", platosId);
        if (platoLogic.getPlato(platosId) == null) {
            throw new WebApplicationException("El recurso /clientes/" + platosId + " no existe.", 404);
        }
        platoLogic.deletePlato(platosId);
        LOGGER.info("PlatoResource deletePlato: output: void");

    }

    /**
     * Actualiza el plato con el id asociado recibido en la URL.
     *
     * @param platosId Identificador del plato. Este debe ser una cadena de
     * dígitos.
     */
    @PUT
    @Path("{platosId: \\d+}")
    public PlatoDTO updatePlato(@PathParam("platosId") Long platosId, PlatoDTO plato) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PlatoResource updatePlato: input: platosId: {0} , plato: {1}", new Object[]{platosId, plato.toString()});
        plato.setId(platosId);
        if (platoLogic.getPlato(platosId) == null) {
            throw new WebApplicationException("El recurso /platos/" + platosId + " no existe.", 404);
        }
        PlatoDTO detailDTO = new PlatoDTO(platoLogic.updatePlato(platosId, plato.toEntity()));
        LOGGER.log(Level.INFO, "PlatoResource updatePlato: output: {0}", detailDTO.toString());
        return detailDTO;
    }

    private List<PlatoDTO> listEntity2DetailDTO(List<PlatoEntity> entityList) {
        List<PlatoDTO> list = new ArrayList<>();
        for (PlatoEntity entity : entityList) {
            list.add(new PlatoDTO(entity));
        }
        return list;
    }
}
