/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;
import co.edu.uniandes.csw.restaurante.dtos.CalificacionDTO;
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
 * @author iy.barbosa
 */
@Path("calificaciones")
@Produces("calificacion/json")
@Consumes("calificacion/json")
@RequestScoped
public class CalificacionResources {
   
    @POST
    
    public CalificacionDTO createCalificacion (CalificacionDTO  calificacion ) {
        return calificacion;
    }
    
    
}
