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
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CalificacionResource {
   
    /**
     * Crea una nueva calificacion con la informacion dada
     * @param calificacion la calificacion que se desea guardar
     * @return 
     */
    @POST
    
    public CalificacionDTO createCalificacion (CalificacionDTO  calificacion ) {
        return calificacion;
    }
     /**
     * Busca la calificacion con el ID asociado recibido en la URL y lo devuelve.
     *
     * @param calificacionesId Identificador del plato que se esta buscando. 
     */
    @GET
    @Path("{calificacionesId: \\d+}")
        public CalificacionDTO getCalificacion(@PathParam("calificacionesId") Long calificacionesId) {
            return null;
        }
        
        /**
     * Borra la calificacion con el ID asociado recibido en la URL.
     *
     * @param calificacionesId Identificador del cliente que se desea borrar. Este debe ser
     * una cadena de dígitos.
     */
    
    @DELETE
         @Path("{calificacionesId: \\d+}")
        public void deleteCalificacion (@PathParam("calificacionesId") Long calificacionesId)
        {
            
        }
       
            /**
     * Actualiza la calificacion con el id asociado recibido en la URL.
     * 
     * @param calificacionesId Identificador de la calificaion.
     * Este debe ser una cadena de dígitos.
     */
    @PUT
    @Path("{calificacionesId: \\d+}")
    public CalificacionDTO updateReserva(@PathParam("calificacionesId") Long calificacionesId)
    {
        return null;
    }
    
    }
    
