/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;
import co.edu.uniandes.csw.restaurante.dtos.ReservaDTO;
import co.edu.uniandes.csw.restaurante.ejb.ReservaLogic;
import co.edu.uniandes.csw.restaurante.entities.ReservaEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ja.ortega
 */
@Path("reservas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ReservaResource {
    
    
    
    private static final Logger LOGGER = Logger.getLogger(ReservaResource.class.getName());

    @Inject
    private ReservaLogic reservaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una nueva reserva con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param reserva {@link ReservaDTO} - La reserva que se desea
     * guardar.
     * @return JSON {@link ReservaDTO} - La reserva guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la reserva.
     */
    @POST
    public ReservaDTO createReserva(ReservaDTO reserva) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "ReservaResource createReserva: input: {0}", reserva.toString());
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        ReservaEntity reservaEntity = reserva.toEntity();
        // Invoca la lógica para crear la editorial nueva
        ReservaEntity nuevaReservaEntity;
        nuevaReservaEntity = reservaLogic.createReserva(reservaEntity);
       
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        ReservaDTO nuevaReservaDTO = new ReservaDTO(nuevaReservaEntity);
        LOGGER.log(Level.INFO, "ReservaResource createReserva: output: {0}", nuevaReservaDTO.toString());
        return nuevaReservaDTO;
    }
       /**
     * Retorna la reserva con el id asociado recibido en la URL.
     * 
     * @param reservasId Identificador de la reserva que se desea buscar.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link ReservaDTO} - La reserva buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reserva.
     */ 
    @GET
    @Path("{reservasId: \\d+}")
    public ReservaDTO getReserva(@PathParam("reservasId") Long reservasId) {
       
        LOGGER.log(Level.INFO, "ReservaResource getReserva: input: {0}", reservasId);
        ReservaEntity reservaEntity = reservaLogic.getReserva(reservasId);
        if (reservaEntity == null) {
            throw new WebApplicationException("El recurso /reservas/" + reservasId + " no existe.", 404);
        }
        ReservaDTO dto = new ReservaDTO(reservaEntity);
        LOGGER.log(Level.INFO, "ReservaResource getReserva: output: {0}", dto.toString());
        return dto;
    }
     /**
     * Retorna la colección de todas las reservas existentes.
     ** @return JSONArray {@link ReservaDTO} - Las reservas
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */    
    @GET
    public List<ReservaDTO> getReservas() {
        LOGGER.info("ReservaResouce getReservas: input: void");
        List<ReservaDTO> listaReservas = listEntity2DetailDTO(reservaLogic.getReservas());
        LOGGER.log(Level.INFO, "ReservaResouce getReservas: output: {0}", listaReservas.toString());
        return listaReservas;
    }
    
     /**
     * Actualiza la reserva con el id asociado recibido en la URL.
     * 
     * @param reservasId Identificador de la reserva que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @param reserva {@link ReservaDTO} La reserva que se desea
     * guardar.
     * @return JSON {@link ReservaDTO} - La reserva guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reserva a
     * actualizar.
     */
    @PUT
    @Path("{reservasId: \\d+}")
    public ReservaDTO updateReserva(@PathParam("reservasId") Long reservasId, ReservaDTO reserva)  throws WebApplicationException {
        LOGGER.log(Level.INFO, "ReservaResouce updateReserva: input: id:{0} , editorial: {1}", new Object[]{reservasId, reserva.toString()});
        reserva.setId(reservasId);
        if (reservaLogic.getReserva(reservasId)== null) {
            throw new WebApplicationException("El recurso /reservas/" + reservasId + " no existe.", 404);
        }
        ReservaDTO dto = new ReservaDTO(reservaLogic.updateReserva(reservasId, reserva.toEntity()));
        LOGGER.log(Level.INFO, "ReservaResouce updateReserva: output: {0}", dto.toString());
        return dto;
    }
    
     /**
     * Borra la reserva con el id asociado recibido en la URL.
     *
     * @param reservasId Identificador de la reserva que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la reserva.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reserva.
     */
    @DELETE
    @Path("{reservasId: \\d+}")
    public void deleteReserva(@PathParam("reservasId") Long reservasId)  throws BusinessLogicException{
       LOGGER.log(Level.INFO, "ReservaResouce deleteReserva: input: {0}", reservasId);
        if (reservaLogic.getReserva(reservasId) == null) {
            throw new WebApplicationException("El recurso /reservas/" + reservasId + " no existe.", 404);
        }
        reservaLogic.deleteReserva(reservasId);
        LOGGER.info("ReservaResouce deleteReserva: output: void");
    }
    
     /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos ReservaEntity a una lista de
     * objetos ReservaDTO (json)
     *
     * @param entityList corresponde a la lista de reservas de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de reservas en forma DTO (json)
     */
    private List<ReservaDTO> listEntity2DetailDTO(List<ReservaEntity> entityList) {
        List<ReservaDTO> list = new ArrayList<>();
        for (ReservaEntity entity : entityList) {
            list.add(new ReservaDTO(entity));
        }
        return list;
    }
}
