/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;

import co.edu.uniandes.csw.restaurante.dtos.PuntoDTO;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author jp.hidalgo
 */
@Path("puntos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PuntoResource {
    
    /**
     *crea un nuevo punto
     * @param punto
     * @return
     */
    @POST
    public PuntoDTO createPunto(PuntoDTO punto)
    {
        return punto;
    }
    
    /**
     *
     * @param puntosId
     * @return
     */
    @GET
    @Path("puntosId: \\d+")
    public PuntoDTO getPunto(@PathParam("puntosId") Long puntosId){
        return null;
    }
    /**
     * @return retorna todos los puntos
     */
    @GET
    public List<PuntoDTO> getPuntos()
    {
        return null;
    }
}
