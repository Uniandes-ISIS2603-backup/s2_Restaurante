/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;
import co.edu.uniandes.csw.restaurante.dtos.SucursalDetailDTO;
import co.edu.uniandes.csw.restaurante.dtos.SucursalDTO;
import java.util.List;
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
 * @author jp.romero12
 */
@Path("sucursales")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class SucursalResource {
    
    @POST
    public SucursalDTO createSucursal(SucursalDTO sucursal)
    {
        return sucursal;
    }
   
    @GET
    public List<SucursalDetailDTO> getSucursales(){
        return null;    
    }
    
    @GET
    @Path("{sucursalesId: \\d+}")
    public SucursalDTO getSucursal(@PathParam("sucursalesId") Long sucursalesId) {
        return null;
    }
    
    @PUT
    @Path("{sucursalesId: \\d+}")
    public SucursalDetailDTO updateSucursal(@PathParam("sucursalesId") Long sucursalesId, SucursalDTO sucursal)
    {
        return null;
    }
    
    @DELETE
    @Path("{sucursalesId: \\d+}")
    public void deleteSucursal(@PathParam("sucursalesId") Long sucursalesId)
    {
        
    }
    
}
