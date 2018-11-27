/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.restaurante.resources;

import co.edu.uniandes.csw.restaurante.dtos.CalificacionDTO;
import co.edu.uniandes.csw.restaurante.ejb.CalificacionLogic;
import co.edu.uniandes.csw.restaurante.entities.CalificacionEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.restaurante.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.restaurante.mappers.WebApplicationExceptionMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * Clase que implementa el recurso "calificaciones".
 *
 * @author j.prieto
 * @version 1.0
 */
@Produces("application/json")
@Consumes("application/json")
public class CalificacionResource {

    private static final Logger LOGGER = Logger.getLogger(CalificacionResource.class.getName());

    @Inject
    private CalificacionLogic calificacionLogic;

    /**
     * Crea una nueva calificacion con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param sucursalId El ID de la sucursal del cual se le agrega la calificacion
     * @param calificacion {@link CalificacionDTO} - La calificacion que se desea guardar.
     * @return JSON {@link CalificacionDTO} - La calificacion guardada con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la calificacion.
     */
    @POST
    public CalificacionDTO createCalificacion(@PathParam("sucursalId") Long sucursalId, CalificacionDTO calificacion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CalificacionResource createCalificacion: input: {0}", calificacion.toString());
        CalificacionDTO nuevaCalificacionDTO = new CalificacionDTO(calificacionLogic.createCalificacion(sucursalId, calificacion.toEntity()));
        LOGGER.log(Level.INFO, "CalificacionResource createCalificacion: output: {0}", nuevaCalificacionDTO.toString());
        return nuevaCalificacionDTO;
    }

    /**
     * Busca y devuelve todas las calificaciones que existen en un sucursal.
     *
     * @param sucursalId El ID de la sucursal del cual se buscan las calificaciones
     * @return JSONArray {@link CalificacionDTO} - Las calificacions encontradas en el
     * sucursal. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<CalificacionDTO> getCalificaciones(@PathParam("sucursalId") Long sucursalId) {
        LOGGER.log(Level.INFO, "CalificacionResource getCalificaciones: input: {0}", sucursalId);
        List<CalificacionDTO> listaDTOs = listEntity2DTO(calificacionLogic.getCalificaciones(sucursalId));
        LOGGER.log(Level.INFO, "CalificacionResource getCalificaciones: output: {0}", listaDTOs.toString());
        return listaDTOs;
    }

    /**
     * Busca y devuelve la calificacion con el ID recibido en la URL, relativa a un
     * sucursal.
     *
     * @param sucursalId El ID de la sucursal del cual se buscan las calificaciones
     * @param calificacionId El ID de la calificacion que se busca
     * @return {@link CalificacionDTO} - La calificacion encontradas en la sucursal.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la sucursal.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificacion.
     */
    @GET
    @Path("{calificacionId: \\d+}")
    public CalificacionDTO getCalificacion(@PathParam("sucursalId") Long sucursalId, @PathParam("calificacionId") Long calificacionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CalificacionResource getCalificacion: input: {0}", calificacionId);
        CalificacionEntity entity = calificacionLogic.getCalificacion(sucursalId, calificacionId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /sucursales/" + sucursalId + "/calificaciones/" + calificacionId + " no existe.", 404);
        }
        CalificacionDTO calificacionDTO = new CalificacionDTO(entity);
        LOGGER.log(Level.INFO, "CalificacionResource getCalificacion: output: {0}", calificacionDTO.toString());
        return calificacionDTO;
    }

    /**
     * Actualiza una calificacion con la informacion que se recibe en el cuerpo de la
     * petición y se regresa el objeto actualizado.
     *
     * @param sucursalId El ID de la sucursal del cual se guarda la calificacion
     * @param calificacionId El ID de la calificacion que se va a actualizar
     * @param calificacion {@link CalificacionDTO} - La calificacion que se desea guardar.
     * @return JSON {@link CalificacionDTO} - La calificacion actualizada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la calificacion.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificacion.
     */
    @PUT
    @Path("{calificacionId: \\d+}")
    public CalificacionDTO updateCalificacion(@PathParam("sucursalId") Long sucursalId, @PathParam("calificacionId") Long calificacionId, CalificacionDTO calificacion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CalificacionResource updateCalificacion: input: sucursalId: {0} , calificacionId: {1} , calificacion:{2}", new Object[]{sucursalId, calificacionId, calificacion.toString()});
        if (calificacionId.equals(calificacion.getId())) {
            throw new BusinessLogicException("Los ids del Review no coinciden.");
        }
        CalificacionEntity entity = calificacionLogic.getCalificacion(sucursalId, calificacionId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /sucursales/" + sucursalId + "/calificaciones/" + calificacionId + " no existe.", 404);

        }
        CalificacionDTO calificacionDTO = new CalificacionDTO(calificacionLogic.updateCalificacion(sucursalId, calificacion.toEntity()));
        LOGGER.log(Level.INFO, "CalificacionResource updateCalificacion: output:{0}", calificacionDTO.toString());
        return calificacionDTO;

    }

    /**
     * Borra la calificacion con el id asociado recibido en la URL.
     *
     * @param sucursalId El ID de la sucursal del cual se va a eliminar la calificacion.
     * @param calificacionId El ID de la calificacion que se va a eliminar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la calificacion.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificacion.
     */
    @DELETE
    @Path("{calificacionId: \\d+}")
    public void deleteCalificacion(@PathParam("sucursalId") Long sucursalId, @PathParam("calificacionId") Long calificacionId) throws BusinessLogicException {
        CalificacionEntity entity = calificacionLogic.getCalificacion(sucursalId, calificacionId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /sucursales/" + sucursalId + "/calificaciones/" + calificacionId + " no existe.", 404);
        }
        calificacionLogic.deleteCalificacion(sucursalId, calificacionId);
    }

    /**
     * Lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos PrizeEntity a una lista de
     * objetos CalificacionDTO (json)
     *
     * @param entityList corresponde a la lista de calificacions de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de calificacions en forma DTO (json)
     */
    private List<CalificacionDTO> listEntity2DTO(List<CalificacionEntity> entityList) {
        List<CalificacionDTO> list = new ArrayList<CalificacionDTO>();
        for (CalificacionEntity entity : entityList) {
            list.add(new CalificacionDTO(entity));
        }
        return list;
    }
}
