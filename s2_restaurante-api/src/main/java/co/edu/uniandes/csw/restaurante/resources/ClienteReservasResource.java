/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;

import co.edu.uniandes.csw.restaurante.dtos.ReservaDTO;
import co.edu.uniandes.csw.restaurante.ejb.ClienteLogic;
import co.edu.uniandes.csw.restaurante.ejb.ClienteReservasLogic;
import co.edu.uniandes.csw.restaurante.ejb.ReservaLogic;
import co.edu.uniandes.csw.restaurante.entities.ReservaEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Cliente y Reserva.
 * 
 * @author ja.ortega
 */

@Produces("application/json")
@Consumes("application/json")
@Stateless
public class ClienteReservasResource {
    
    private static final Logger LOGGER = Logger.getLogger(ClienteReservasResource.class.getName());

    @Inject
    private ClienteReservasLogic clienteReservaLogic;

    @Inject
    private ReservaLogic reservaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    @Inject
    private ClienteLogic clienteLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    
    /**
     * Asocia una reserva existente con un cliente existente
     *
     * @param clientesId El ID del cliente a el cual se le va a asociar la reserva
     * @param reservasId El ID de la reserva que se asocia
     * @return JSON {@link ReservaDTO} - La reserva asociada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reserva.
     */
    @POST
    @Path("{reservasId: \\d+}")
    public ReservaDTO addReserva(@PathParam("clientesId") Long clientesId, @PathParam("reservasId") Long reservasId) {
        LOGGER.log(Level.INFO, "ClienteReservaResource addReserva: input: clientesId {0} , reservasId {1}", new Object[]{clientesId, reservasId});
        if (reservaLogic.getReserva(reservasId) == null) {
            throw new WebApplicationException("El recurso /reservas/" + reservasId + " no existe.", 404);
        }
        ReservaDTO dto = new ReservaDTO(clienteReservaLogic.addReserva(clientesId, reservasId));
        LOGGER.log(Level.INFO, "ClienteReservaResource addReserva: output: {0}", dto);
        return dto;
    }
    
    /**
     * Busca y devuelve todas las reservas de un cliente.
     *
     * @param clientesId Identificador del cliente que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link ReservaDTO} - Las reservas encontradas del cliente
     * 
     */
    @GET
    public List<ReservaDTO> getReservas(@PathParam("clientesId") Long clientesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ClienteReservaResource getReservas: input: {0}", clientesId);
        if (clienteLogic.getCliente(clientesId) == null) {
            throw new WebApplicationException("El recurso /clientes/" + clientesId + " no existe.", 404);
        }
        List<ReservaDTO> listaDTO = reservaListEntity2DTO(clienteReservaLogic.getReservas(clientesId));
        LOGGER.log(Level.INFO, "ClienteReservaResource getReservas: output: {0}", listaDTO.toString());
        return listaDTO;
    }
    
        /**
     * Busca y devuelve todas las reservas de un cliente.
     *
     * @param clientesId Identificador del cliente que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link ReservaDTO} - Las reservas encontradas del cliente
     * 
     */
    
    @GET
    @Path("{reservasId: \\d+}") 
    public ReservaDTO getReserva(@PathParam("clientesId") Long clientesId, @PathParam("reservasId") Long reservasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ClienteReservaResource getReserva: input: {0}", clientesId);
        if (reservaLogic.getReserva(reservasId) == null) {
            throw new WebApplicationException("El recurso /reservas/" + reservasId + " no existe.", 404);
        }
        ReservaDTO reservaDTO = new ReservaDTO(clienteReservaLogic.getReserva(clientesId, reservasId));
        LOGGER.log(Level.INFO, "ClienteReservaResource getReserva: output: {0}", reservaDTO.getId());
        return reservaDTO;
    }
    
     /**
     * Convierte una lista de ReservaEntity a una lista de ReservaDTO.
     *
     * @param entityList Lista de ReservaEntity a convertir.
     * @return Lista de ReservaDTO convertida.
     */
    private List<ReservaDTO> reservaListEntity2DTO(List<ReservaEntity> entityList) {
        List<ReservaDTO> list = new ArrayList();
        for (ReservaEntity entity : entityList) {
            list.add(new ReservaDTO(entity));
        }
        return list;
    }
    
       /**
     * Convierte una lista de ReservaDTO a una lista de ReservaEntity.
     *
     * @param dtos Lista de ReservaDTO a convertir.
     * @return Lista de ReservaEntity convertida.
     */
    private List<ReservaEntity> reservaListDTO2Entity(List<ReservaDTO> dtos) {
        List<ReservaEntity> list = new ArrayList<>();
        for (ReservaDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
     /**
     * Elimina la conexión entre la reserva y el cliente recibidos en la URL.
     *
     * @param clientesId El ID del cliente al cual se le va a desasociar la reserva
     * @param reservasId El ID de la reserva que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reserva.
     */
    @DELETE
    @Path("{reservasId: \\d+}")
    public void removeReserva(@PathParam("clientesId") Long clientesId, @PathParam("reservasId") Long reservasId) {
        LOGGER.log(Level.INFO, "ClienteReservasResource deleteReserva: input: clientesId {0} , reservasId {1}", new Object[]{clientesId, reservasId});
        if (reservaLogic.getReserva(reservasId) == null) {
            throw new WebApplicationException("El recurso /reservas/" + reservasId + " no existe.", 404);
        }
        clienteReservaLogic.removeReserva(clientesId, reservasId);
        LOGGER.info("ClienteReservaResource deleteReserva: output: void");
    }
    
  

}
