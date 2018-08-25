/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;

import co.edu.uniandes.csw.restaurante.dtos.DomicilioDTO;
import co.edu.uniandes.csw.restaurante.dtos.PuntoDTO;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */
@Path("punto")
@Produces("punto/json")
@Consumes("punto/json")
@RequestScoped
public class PuntoResource {
    @POST
    public PuntoDTO createPunto(PuntoDTO punto)
    {
        return punto;
    }
}
