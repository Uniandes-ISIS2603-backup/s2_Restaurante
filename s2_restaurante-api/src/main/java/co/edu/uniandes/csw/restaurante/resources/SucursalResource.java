/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;
import co.edu.uniandes.csw.restaurante.dtos.SucursalDTO;
import co.edu.uniandes.csw.restaurante.ejb.SucursalLogic;
import co.edu.uniandes.csw.restaurante.entities.SucursalEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import java.util.ArrayList;
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
    private static final Logger LOGGER = Logger.getLogger(SucursalResource.class.getName());

    @Inject
    private SucursalLogic sucursalLogic; 

   
    @POST
    public SucursalDTO createSucursal(SucursalDTO sucursal) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "SucursalResource createSucursal: input: {0}", sucursal.toString());
        SucursalEntity sucursalEntity = sucursal.toEntity();
        SucursalEntity nuevaSucursalEntity;
        nuevaSucursalEntity = sucursalLogic.createSucursal(sucursalEntity);
       
        SucursalDTO nuevaSucursalDTO = new SucursalDTO(nuevaSucursalEntity);
        LOGGER.log(Level.INFO, "SucursalResource createSucursal: output: {0}", nuevaSucursalDTO.toString());
        return nuevaSucursalDTO;
    }
       
    @GET
    @Path("{sucursalesId: \\d+}")
    public SucursalDTO getSucursal(@PathParam("sucursalesId") Long sucursalesId) {
       
        LOGGER.log(Level.INFO, "SucursalesResource getSucursal: input: {0}", sucursalesId);
        SucursalEntity sucursalEntity = sucursalLogic.getSucursal(sucursalesId);
        if (sucursalEntity == null) {
            throw new WebApplicationException("El recurso /sucursales/" + sucursalesId + " no existe.", 404);
        }
        SucursalDTO dto = new SucursalDTO(sucursalEntity);
        LOGGER.log(Level.INFO, "SucursalResource getSucursal: output: {0}", dto.toString());
        return dto;
    }
     
    
    @GET
    public List<SucursalDTO> getSucursales() {
        LOGGER.info("SucursalesResouce getSucursales: input: void");
        List<SucursalDTO> listaSucursales = (List<SucursalDTO>) listEntity2DetailDTO(sucursalLogic.getSucursales());
        LOGGER.log(Level.INFO, "SucursalResouce getSucursal: output: {0}", listaSucursales.toString());
        return listaSucursales;
    }
    
 
    @PUT
    @Path("{sucursalesId: \\d+}")
    public SucursalDTO updateSucursal(@PathParam("sucursalesId") Long sucursalesId, SucursalDTO sucursal)  throws WebApplicationException {
        LOGGER.log(Level.INFO, "SucursalResouce updateSucursal: input: id:{0} , direccion: {1}", new Object[]{sucursalesId, sucursal.toString()});
        sucursal.setId(sucursalesId);
        if (sucursalLogic.getSucursal(sucursalesId)== null) {
            throw new WebApplicationException("El recurso /sucursal/" + sucursalesId + " no existe.", 404);
        }
        SucursalDTO dto = new SucursalDTO(sucursalLogic.updateSucursal(sucursalesId, sucursal.toEntity()));
        LOGGER.log(Level.INFO, "SucursalResouce updateSucursal: output: {0}", dto.toString());
        return dto;
    }
    
    
    @DELETE
    @Path("{sucursalesId: \\d+}")
    public void deleteSucursal(@PathParam("sucursalesId") Long sucursalesId)  throws BusinessLogicException{
       LOGGER.log(Level.INFO, "SucursalResouce deleteSucursal: input: {0}", sucursalesId);
        if (sucursalLogic.getSucursal(sucursalesId) == null) {
            throw new WebApplicationException("El recurso /sucursales/" + sucursalesId + " no existe.", 404);
        }
        sucursalLogic.deleteSucursal(sucursalesId);
        LOGGER.info("SucursalResouce deleteSucursal: output: void");
    }
    
    
     /**
     * Conexión con el servicio de reservas para una sucursal.
     * {@link SucursalReservasResource}
     *
     * Este método conecta la ruta de /clientes con las rutas de /reservas que
     * dependen del cliente, es una redirección al servicio que maneja el
     * segmento de la URL que se encarga de las reservas.
     *
     * @param clientesId El ID del cliente con respecto al cual se accede al
     * servicio.
     * @return El servicio de reservas para ese cliente en paricular.
     */
    @Path("{sucursalesId: \\d+}/reservas")
    public Class<SucursalReservasResource> getReservaSucursalesResource(@PathParam("sucursalesId") Long sucursalesId) throws BusinessLogicException {
        if (sucursalLogic.getSucursal(sucursalesId) == null) {
            throw new WebApplicationException("El recurso /sucursales/" + sucursalesId + " no existe.", 404);
        }
        return SucursalReservasResource.class;
    }
    
    
    private List<SucursalDTO> listEntity2DetailDTO(List<SucursalEntity> entityList) {
        List<SucursalDTO> list = new ArrayList<>();
        for (SucursalEntity entity : entityList) {
            list.add(new SucursalDTO(entity));
        }
        return list;
    }
    
}
