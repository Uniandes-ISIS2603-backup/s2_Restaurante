/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;

import co.edu.uniandes.csw.restaurante.dtos.TarjetaDTO;
import co.edu.uniandes.csw.restaurante.ejb.ClienteTarjetaLogic;
import co.edu.uniandes.csw.restaurante.ejb.TarjetaLogic;
import co.edu.uniandes.csw.restaurante.entities.TarjetaEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Cliente y Tarjeta.
 *
 * @author ja.ortega
 */
@Stateless
public class ClienteTarjetaResource {

    private static final Logger LOGGER = Logger.getLogger(ClienteTarjetaResource.class.getName());

    @Inject
    private ClienteTarjetaLogic clienteTarjetaLogic;

    @Inject
    private TarjetaLogic TarjetaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Asocia un Tarjeta existente con un cliente existente
     *
     * @param clientesId El ID del cliente a el cual se le va a asociar la
     * Tarjeta
     * @param TarjetasId El ID de la Tarjeta que se asocia
     * @return JSON {@link TarjetaDTO} - La Tarjeta asociada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la Tarjeta.
     */
    @POST
    @Path("{TarjetaId: \\d+}")
    public TarjetaDTO addTarjeta(@PathParam("clientesId") Long clientesId, @PathParam("tarjetasId") Long TarjetasId) {
        LOGGER.log(Level.INFO, "ClienteTarjetaResource addTarjeta: input: clientesId {0} , TarjetasId {1}", new Object[]{clientesId, TarjetasId});
        if (TarjetaLogic.getTarjeta(TarjetasId) == null) {
            throw new WebApplicationException("El recurso /tarjetas/" + TarjetasId + " no existe.", 404);
        }
        TarjetaDTO dto = new TarjetaDTO(clienteTarjetaLogic.addTarjeta(clientesId, TarjetasId));
        LOGGER.log(Level.INFO, "ClienteTarjetaResource addTarjeta: output: {0}", dto);
        return dto;
    }

    /**
     * Elimina la conexión entre la Tarjeta y el cliente recibidos en la URL.
     *
     * @param clientesId El ID del cliente al cual se le va a desasociar la
     * Tarjeta
     * @param TarjetasId El ID de la Tarjeta que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la Tarjeta.
     */
    @DELETE
    @Path("{TarjetasId: \\d+}")
    public void removeBook(@PathParam("clientesId") Long clientesId, @PathParam("TarjetasId") Long TarjetasId) {
        LOGGER.log(Level.INFO, "AuthorBooksResource deleteTarjeta: input: clientesId {0} , TarjetasId {1}", new Object[]{clientesId, TarjetasId});
        if (TarjetaLogic.getTarjeta(TarjetasId) == null) {
            throw new WebApplicationException("El recurso /Tarjetas/" + TarjetasId + " no existe.", 404);
        }
        clienteTarjetaLogic.removeTarjeta(clientesId, TarjetasId);
        LOGGER.info("ClienteTarjetaResource deleteBook: output: void");
    }

}
