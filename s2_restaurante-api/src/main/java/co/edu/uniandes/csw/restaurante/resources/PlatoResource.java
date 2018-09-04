/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;

import co.edu.uniandes.csw.restaurante.dtos.PlatoDTO;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    public class PlatoResources {
        
        
         /**
     * Busca el plato con el ID asociado recibido en la URL y lo devuelve.
     *
     * @param platosId Identificador del plato que se esta buscando. 
     */
        @GET
        @Path("{platosId: \\d+}")
        public PlatoDTO getBook(@PathParam("platosId") Long clientesId) {
            return null;
        }
        /**
         * crea un nuevo plato con la informacion dada
         * @param plato el plato que se desea guardar
         * @return 
         */
   
        @POST
        public PlatoDTO PlatoCalificacion ( PlatoDTO  plato ) {
            return plato;
        }
         /**
     * Borra el plato con el ID asociado recibido en la URL.
     *
     * @param platosId Identificador del cliente que se desea borrar. Este debe ser
     * una cadena de dígitos.
     */
        @DELETE
         @Path("{platosId: \\d+}")
        public void deleteCliente(@PathParam("platosId") Long platosId)
        {
            
        }
        
         /**
     * Actualiza el plato con el id asociado recibido en la URL.
     * 
     * @param platosId Identificador del plato.
     * Este debe ser una cadena de dígitos.
     */
    @PUT
    @Path("{platosId: \\d+}")
    public PlatoDTO updateReserva(@PathParam("platosId") Long platosId) {
        return null;
    }
    
    }
}