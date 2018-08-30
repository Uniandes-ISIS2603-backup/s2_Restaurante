/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;

import co.edu.uniandes.csw.restaurante.dtos.PlatoDTO;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author iy.barbosa
 */
public class PlatoResource {
    @Path("platos")
    @Produces("application/json")
    @Consumes("application/json")
    @RequestScoped
    public class CalificacionResources {
   
        @POST
    
        public PlatoDTO createCalificacion ( PlatoDTO  plato ) {
            return plato;
        }
    
    }
}