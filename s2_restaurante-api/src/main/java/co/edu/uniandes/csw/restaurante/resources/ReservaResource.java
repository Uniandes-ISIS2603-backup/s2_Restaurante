/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;
import co.edu.uniandes.csw.restaurante.dtos.ReservaDTO;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;

/**
 *
 * @author ja.ortega
 */
@Path("reservas")
@Produces("reserva/json")
@Consumes("reserva/json")
@RequestScoped
public class ReservaResource {
    @POST
    public ReservaDTO createDomicilio(ReservaDTO reserva)
    {
        return reserva;
    }
}
