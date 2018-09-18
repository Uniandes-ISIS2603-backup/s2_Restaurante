/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;

import co.edu.uniandes.csw.restaurante.dtos.ReservaDTO;
import co.edu.uniandes.csw.restaurante.ejb.ClienteReservaLogic;
import co.edu.uniandes.csw.restaurante.ejb.ReservaLogic;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Cliente y Reserva.
 * 
 * @author ja.ortega
 */

@Stateless
public class ClienteReservaResource {
    
        private static final Logger LOGGER = Logger.getLogger(ClienteReservaResource.class.getName());

    @Inject
    private ClienteReservaLogic clienteReservaLogic;

    @Inject
    private ReservaLogic reservaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    
    /**
     * Asocia un libro existente con un autor existente
     *
     * @param authorsId El ID del autor al cual se le va a asociar el libro
     * @param booksId El ID del libro que se asocia
     * @return JSON {@link BookDetailDTO} - El libro asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el libro.
     */
    @POST
    @Path("{reservasId: \\d+}")
    public ReservaDTO addReserva(@PathParam("clientesId") Long clientesId, @PathParam("reservasId") Long reservasId) {
        LOGGER.log(Level.INFO, "ClienteReservaResource addReserva: input: clientesId {0} , reservasId {1}", new Object[]{clientesId, reservasId});
        if (reservaLogic.getReserva(reservasId) == null) {
            throw new WebApplicationException("El recurso /reservas/" + reservasId + " no existe.", 404);
        }
        ReservaDTO dto = new ReservaDTO(clienteReservaLogic.addReserva(clientesId, reservasId));
        LOGGER.log(Level.INFO, "ClienteReservaResource addBook: output: {0}", dto);
        return dto;
    }

}
