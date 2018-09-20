/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;

import co.edu.uniandes.csw.restaurante.dtos.TarjetaDTO;
import co.edu.uniandes.csw.restaurante.dtos.TarjetaDetailDTO;
import co.edu.uniandes.csw.restaurante.ejb.TarjetaLogic;
import co.edu.uniandes.csw.restaurante.entities.TarjetaEntity;
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
@Path("tarjetas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TarjetaResource{

   @Inject
    private TarjetaLogic tarjetaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(TarjetaResource.class.getName());

    /**
     * Crea un nuevo Tarjeta con la información que se recibe en el cuerpo de la
     * petición y se regresa un objeto idéntico con un ID auto-generado por la
     * base de datos.
     *
     * @param Tarjeta {@link TarjetaDTO} - El Tarjeta que se desea guardar.
     * @return JSON {@link TarjetaDTO} - El autor guardado con el atributo ID
     * auto-generado.
     */
    @POST
    public TarjetaDTO crearTarjeta(TarjetaDTO Tarjeta) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TarjetaResource crearTarjeta: input: {0}", Tarjeta.toString());
        TarjetaDTO TarjetaDTO = new TarjetaDTO(tarjetaLogic.createTarjeta(Tarjeta.toEntity()));
        LOGGER.log(Level.INFO, "TarjetaResource crearTarjeta: output: {0}", TarjetaDTO.toString());
        return TarjetaDTO;
    }

    /**
     * Busca y devuelve todos los Tarjetas que existen en la aplicación.
     *
     * @return JSONArray {@link TarjetaDetailDTO} - Los Tarjetas encontrador en
     * la aplicación. Si no hay ningún Tarjeta se retornará una lista vacía.
     */
    @GET
    public List<TarjetaDetailDTO> getTarjetas() {
        LOGGER.info("TarjetaResource getTarjetas: input: void");
        List<TarjetaDetailDTO> listaTarjetas = listEntity2DetailDTO(tarjetaLogic.getTarjetas());
        LOGGER.log(Level.INFO, "TarjetaResource getTarjetas: output: {0}", listaTarjetas.toString());
        return listaTarjetas;
    }

    /**
     * Busca el Tarjeta con el ID asociado recibido en la URL y lo devuelve.
     *
     * @param TarjetasId Identificador del Tarjeta que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link TarjetaDetailDTO} - El Tarjeta buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el Tarjeta.
     */
    @GET
    //Como recibimos dígitos, ponemos d+
    @Path("{TarjetasId: \\d+}")
    public TarjetaDetailDTO getTarjeta(@PathParam("TarjetasId") Long TarjetasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TarjetaResource getTarjeta: input: {0}", TarjetasId);
        TarjetaEntity tarjetaEntity = tarjetaLogic.getTarjeta(TarjetasId);
        if (tarjetaEntity == null) {
            throw new WebApplicationException("El recurso /Tarjetas/" + TarjetasId + " no existe.", 404);
        }
        TarjetaDetailDTO detailDTO = new TarjetaDetailDTO(tarjetaEntity);
        LOGGER.log(Level.INFO, "TarjetaResource getTarjeta: output: {0}", detailDTO.toString());
        return detailDTO;
    }

    /**
     * Actualiza el Tarjeta con el ID recibido en la URL con la información que
     * se recibe en el cuerpo de la petición.
     *
     * @param TarjetasId Identificador del Tarjeta que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param Tarjeta {@link TarjetaDTO} El Tarjeta que se desea guardar.
     * @return JSON {@link TarjetaDetailDTO} - El Tarjeta guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el Tarjeta a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el Tarjeta.
     */
    @PUT
    @Path("{TarjetasId: \\d+}")
    public TarjetaDetailDTO updateTarjeta(@PathParam("TarjetasId") Long TarjetasId, TarjetaDTO Tarjeta) throws BusinessLogicException /*throws BusinessLogicException*/ {
        LOGGER.log(Level.INFO, "TarjetaResource updateTarjeta: input: TarjetasId: {0} , Tarjeta: {1}", new Object[]{TarjetasId, Tarjeta.toString()});
        Tarjeta.setId(TarjetasId);
        if (tarjetaLogic.getTarjeta(TarjetasId) == null) {
            throw new WebApplicationException("El recurso /Tarjetas/" + TarjetasId + " no existe.", 404);
        }
        TarjetaDetailDTO detailDTO = new TarjetaDetailDTO(tarjetaLogic.updateTarjeta(TarjetasId, Tarjeta.toEntity()));
        LOGGER.log(Level.INFO, "TarjetaResource updateTarjeta: output: {0}", detailDTO.toString());
        return detailDTO;
    }

    /**
     * Borra el Tarjeta con el id asociado recibido en la URL.
     *
     * @param TarjetasId Identificador del Tarjeta que se desea borrar. Este
     * debe ser una cadena de dígitos.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * si el autor tiene libros asociados
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el autor a borrar.
     */
    @DELETE
    @Path("{TarjetasId: \\d+}")
    public void deleteTarjeta(@PathParam("TarjetasId") Long TarjetasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "TarjetaResource deleteTarjeta: input: {0}", TarjetasId);
        if (tarjetaLogic.getTarjeta(TarjetasId) == null) {
            throw new WebApplicationException("El recurso /Tarjetas/" + TarjetasId + " no existe.", 404);
        }
        tarjetaLogic.deleteTarjeta(TarjetasId);
        LOGGER.info("TarjetaResource deleteTarjeta: output: void");
    }

    private List<TarjetaDetailDTO> listEntity2DetailDTO(List<TarjetaEntity> entityList) {
        List<TarjetaDetailDTO> list = new ArrayList<>();
        for (TarjetaEntity entity : entityList) {
            list.add(new TarjetaDetailDTO(entity));
        }
        return list;
    }

}
