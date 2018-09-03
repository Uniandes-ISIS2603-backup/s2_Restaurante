/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;
import co.edu.uniandes.csw.restaurante.dtos.ReservaDTO;
import java.util.logging.Level;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;

/**
 *
 * @author ja.ortega
 */
@Path("reservas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ReservaResource {
    
    
    @POST
    public ReservaDTO createReserva(ReservaDTO reserva)
    {
        return reserva;
    }
       /**
     * Retorna la reserva con el id asociado recibido en la URL.
     *
     * @param reservasId Identificador de la reserva que se desea borrar.
     * Este debe ser una cadena de dígitos.
     */    
    @GET
    @Path("{reservasId: \\d+}")
    public ReservaDTO getReserva(@PathParam("reservasId") Long reservasId) {
        return null;
    }
     /**
     * Retorna la colección de todas las reservas existentes.
     *
     */    
    @GET
    public ReservaDTO getReservas() {
        return null;
    }
    
     /**
     * Actualiza la reserva con el id asociado recibido en la URL.
     * 
     * @param reservasId Identificador de la reserva que se desea borrar.
     * Este debe ser una cadena de dígitos.
     */
    @PUT
    @Path("{reservasId: \\d+}")
    public ReservaDTO updateReserva(@PathParam("reservasId") Long reservasId) {
        return null;
    }
    
     /**
     * Borra la reserva con el id asociado recibido en la URL.
     *
     * @param reservasId Identificador de la reserva que se desea borrar.
     * Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{reservasId: \\d+}")
    public void deleteReserva(@PathParam("reservasId") Long reservasId) {
       
    }
}
