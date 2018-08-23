/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;

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
