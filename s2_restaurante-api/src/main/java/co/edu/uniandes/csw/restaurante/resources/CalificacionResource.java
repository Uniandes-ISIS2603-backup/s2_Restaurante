/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;
import co.edu.uniandes.csw.restaurante.dtos.CalificacionDTO;
import co.edu.uniandes.csw.restaurante.ejb.CalificacionLogic;
import co.edu.uniandes.csw.restaurante.entities.CalificacionEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
@Path("calificaciones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CalificacionResource {
   private static final Logger LOGGER = Logger.getLogger(CalificacionResource.class.getName());
    /**
     * Crea una nueva calificacion con la informacion dada
     * @param calificacion la calificacion que se desea guardar
     * @return 
     */
    @Inject
    private CalificacionLogic calificacionLogic;
            
    @POST
    public CalificacionDTO createCalificacion (CalificacionDTO  calificacion ) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CalificacionResource createCalificacion: input: {0}", calificacion.toString());
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        CalificacionEntity calificacionEntity = calificacion.toEntity();
        // Invoca la lógica para crear la editorial nueva
        CalificacionEntity nuevaCalificacionEntity;
        nuevaCalificacionEntity = calificacionLogic.createCalificacion(calificacionEntity);
       
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        CalificacionDTO nuevaCalificacionDTO = new CalificacionDTO(nuevaCalificacionEntity);
        LOGGER.log(Level.INFO, "CalificacionResource createCalificacion: output: {0}", nuevaCalificacionDTO.toString());
        return nuevaCalificacionDTO;
    }
     /**
     * Busca la calificacion con el ID asociado recibido en la URL y lo devuelve.
     *
     * @param calificacionesId Identificador del plato que se esta buscando. 
     */
    @GET
    @Path("{calificacionesId: \\d+}")
        public CalificacionDTO getCalificacion(@PathParam("calificacionesId") Long calificacionId) {
            
           LOGGER.log(Level.INFO, "CalificacionResource getReserva: input: {0}", calificacionId);
        CalificacionEntity calificacionEntity = calificacionLogic.getCalificacion(calificacionId);
        if (calificacionEntity == null) {
            throw new WebApplicationException("El recurso /calificacion/" + calificacionId + " no existe.", 404);
        }
        CalificacionDTO dto = new CalificacionDTO(calificacionEntity);
        LOGGER.log(Level.INFO, "CalificacionResource getReserva: output: {0}", dto.toString());
        return dto;
        }
    
    
 /**
     * Actualiza la calificacion con el id asociado recibido en la URL.
     * 
     * @param calificacionesId Identificador de la calificaion.
     * Este debe ser una cadena de dígitos.
     */
    @PUT
    @Path("{calificacionesId: \\d+}")
    public CalificacionDTO updateCalificacion(@PathParam("califiacacionesId") Long calificacionesId, CalificacionDTO calificacion)  throws WebApplicationException
    {
         LOGGER.log(Level.INFO, "CalificacionResouce updateCalificaicon: input: id:{0} , editorial: {1}", new Object[]{calificacionesId, calificacion.toString()});
        calificacion.setId(calificacionesId);
        if (calificacionLogic.getCalificacion(calificacionesId)== null) {
            throw new WebApplicationException("El recurso /reservas/" + calificacionesId + " no existe.", 404);
        }
        CalificacionDTO dto = new CalificacionDTO(calificacionLogic.updateCalificacion(calificacionesId, calificacion.toEntity()));
        LOGGER.log(Level.INFO, "CalificaionResouce updateCalificacion: output: {0}", dto.toString());
        return dto;
    }
    
    }
    
