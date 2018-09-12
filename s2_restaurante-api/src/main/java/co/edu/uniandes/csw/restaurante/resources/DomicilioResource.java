/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;
import co.edu.uniandes.csw.restaurante.dtos.DomicilioDTO;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
/**
 *
 * @author estudiante
 */
@Path("domicilios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class DomicilioResource {
    /**
     * injecta la logica del domicilio
     */
    //@Inject DomiclilioLogic domocilioLogic;
    
    /**
     *
     * @param domicilio
     * @return
     */
    @POST
    public DomicilioDTO createDomicilio(DomicilioDTO domicilio)
    {
        return domicilio;
    }
}
