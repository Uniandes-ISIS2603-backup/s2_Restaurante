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
    
    @GET
    @Path("{reservasId: \\d+}")
    public ReservaDTO deleteEditorial(@PathParam("reservasId") Long reservasId) {
        return null;
    }
}
