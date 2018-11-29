/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;

import co.edu.uniandes.csw.restaurante.dtos.PlatoDTO;
import co.edu.uniandes.csw.restaurante.ejb.SucursalLogic;
import co.edu.uniandes.csw.restaurante.ejb.SucursalPlatosLogic;
import co.edu.uniandes.csw.restaurante.ejb.PlatoLogic;
import co.edu.uniandes.csw.restaurante.entities.PlatoEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * 
 * 
 * @author jp.romero12
 */

@Produces("application/json")
@Consumes("application/json")
@Stateless
public class SucursalPlatosResource {
    
    private static final Logger LOGGER = Logger.getLogger(SucursalPlatosResource.class.getName());

    @Inject
    private SucursalPlatosLogic sucursalPlatosLogic;

    @Inject
    private PlatoLogic platoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    @Inject
    private SucursalLogic sucursalLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    
    
    @POST
    @Path("{platosId: \\d+}")
    public PlatoDTO addPlato(@PathParam("sucursalesId") Long sucursalesId, @PathParam("platosId") Long platosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "SucursalPlatoResource addPlato: input: sucursalesId {0} , platosId {1}", new Object[]{sucursalesId, platosId});
        if (platoLogic.getPlato(platosId) == null) {
            throw new WebApplicationException("El recurso /platos/" + platosId + " no existe.", 404);
        }
        PlatoDTO dto = new PlatoDTO(sucursalPlatosLogic.addPlato(sucursalesId, platosId));
        LOGGER.log(Level.INFO, "SucursalPlatoResource addPlato: output: {0}", dto);
        return dto;
    }
    
    
    @GET
    public List<PlatoDTO> getPlatos(@PathParam("sucursalesId") Long sucursalesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "SucursalPlatoResource getPlatos: input: {0}", sucursalesId);
        if (sucursalLogic.getSucursal(sucursalesId) == null) {
            throw new WebApplicationException("El recurso /sucursales/" + sucursalesId + " no existe.", 404);
        }
        List<PlatoDTO> listaDTO = platoListEntity2DTO(sucursalPlatosLogic.getPlatos(sucursalesId));
        LOGGER.log(Level.INFO, "SucursalPlatoResource getPlatos: output: {0}", listaDTO.toString());
        return listaDTO;
    }
    
        
    
    @GET
    @Path("{platosId: \\d+}") 
    public PlatoDTO getPlato(@PathParam("sucursalesId") Long sucursalesId, @PathParam("platosId") Long platosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "SucursalPlatoResource getPlato: input: {0}", platosId);
        if (platoLogic.getPlato(platosId) == null) {
            throw new WebApplicationException("El recurso /platos/" + platosId + " no existe.", 404);
        }
        PlatoDTO platoDTO = new PlatoDTO(sucursalPlatosLogic.getPlato(sucursalesId, platosId));
        LOGGER.log(Level.INFO, "SucursalPlatoResource getPlato: output: {0}", platoDTO.getId());
        return platoDTO;
    }
    
     
    private List<PlatoDTO> platoListEntity2DTO(List<PlatoEntity> entityList) {
        List<PlatoDTO> list = new ArrayList();
        for (PlatoEntity entity : entityList) {
            list.add(new PlatoDTO(entity));
        }
        return list;
    }
    
       
    private List<PlatoEntity> platoListDTO2Entity(List<PlatoDTO> dtos) {
        List<PlatoEntity> list = new ArrayList<>();
        for (PlatoDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
     
    @DELETE
    @Path("{platosId: \\d+}")
    public void removePlato(@PathParam("platosId") Long sucursalesId, @PathParam("platosId") Long platosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "SucursalPlatosResource deletePlato: input: sucursalesId {0} , platosId {1}", new Object[]{sucursalesId, platosId});
        if (platoLogic.getPlato(platosId) == null) {
            throw new WebApplicationException("El recurso /platos/" + platosId + " no existe.", 404);
        }
        sucursalPlatosLogic.removePlato(sucursalesId, platosId);
        LOGGER.info("SucursalPlatoResource deletePlato: output: void");
    }
    
  

}
