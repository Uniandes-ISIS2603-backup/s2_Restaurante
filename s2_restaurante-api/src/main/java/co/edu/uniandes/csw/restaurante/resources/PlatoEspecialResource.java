/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;

import co.edu.uniandes.csw.restaurante.dtos.PlatoEspecialDTO;
import co.edu.uniandes.csw.restaurante.ejb.PlatoEspecialLogic;
import co.edu.uniandes.csw.restaurante.entities.PlatoEspecialEntity;
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
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import java.util.ArrayList;
/**
 *
 * @author estudiante
 */
@Path("platosEspeciales")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PlatoEspecialResource {
   private static final Logger LOGGER = Logger.getLogger(PlatoEspecialResource.class.getName());

    @Inject
    private PlatoEspecialLogic platoLogic;
    
    @POST
    public PlatoEspecialDTO createPlatoEspecial(PlatoEspecialDTO plato) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "PlatoEspecialResource createPlatoEspecial: input: {0}", plato.toString());
        PlatoEspecialEntity platoEntity = plato.toEntity();
        PlatoEspecialEntity nuevaEntity;
        nuevaEntity = platoLogic.createPlatoEspecial(platoEntity);
        PlatoEspecialDTO nuevaDTO = new PlatoEspecialDTO(nuevaEntity);
        LOGGER.log(Level.INFO, "PlatoResource createPlatoEspecial output: {0}", nuevaDTO.toString());
        return nuevaDTO;
    }
    @GET
    @Path("{platosEspecialesId: \\d+}")
    public PlatoEspecialDTO getPlatoEspecial(@PathParam("platosEspecialesId") Long platosId) {
       
        LOGGER.log(Level.INFO, "PlatoEspecialResource getPlatoEspecial: input: {0}", platosId);
        PlatoEspecialEntity platoEntity = platoLogic.getPlatoEspecial(platosId);
        if (platoEntity == null) {
            throw new WebApplicationException("El recurso /platosEspeciales/" + platosId + " no existe.", 404);
        }
        PlatoEspecialDTO dto = new PlatoEspecialDTO(platoEntity);
        LOGGER.log(Level.INFO, "PlatoEspecialResource getPlatoEspecial: output: {0}", dto.toString());
        return dto;
    }
    
    @GET
    public List<PlatoEspecialDTO> getPlatosEspeciales() {
        LOGGER.info("PlatoEspecialResouce getPlatosEspeciales: input: void");
        List<PlatoEspecialDTO> listaPlatos = listEntity2DetailDTO(platoLogic.getPlatosEspeciales());
        LOGGER.log(Level.INFO, "PlatoEspecialResouce getPlatosEspecials: output: {0}", listaPlatos.toString());
        return listaPlatos;
    }
    @PUT
    @Path("{platosEspecialesId: \\d+}")
    public PlatoEspecialDTO updatePlatoEspecial(@PathParam("platosEspecialesId") Long platosEspecialesId, PlatoEspecialDTO plato)  throws WebApplicationException {
        LOGGER.log(Level.INFO, "PlatoEspecialResouce updatePlatoEspecial: input: id:{0} , numero: {1}", new Object[]{platosEspecialesId, plato.toString()});
        plato.setId(platosEspecialesId);
        if (platoLogic.getPlatoEspecial(platosEspecialesId)== null) {
            throw new WebApplicationException("El recurso /platosEspeciales/" + platosEspecialesId + " no existe.", 404);
        }
        PlatoEspecialDTO dto = new PlatoEspecialDTO(platoLogic.updatePlatoEspecial(platosEspecialesId, plato.toEntity()));
        LOGGER.log(Level.INFO, "PlatoEspecialResouce updatePlatoEspecial: output: {0}", dto.toString());
        return dto;
    }
    
    @DELETE
    @Path("{platosEspecialesId: \\d+}")
    public void deletePlatoEspecial(@PathParam("platosEspecialesId") Long platosId)  throws BusinessLogicException{
       LOGGER.log(Level.INFO, "PlatoEspecialResouce deletePlatoEspecial: input: {0}", platosId);
        if (platoLogic.getPlatoEspecial(platosId) == null) {
            throw new WebApplicationException("El recurso /platosEspecialaes/" + platosId + " no existe.", 404);
        }
        platoLogic.deletePlatoEspecial(platosId);
        LOGGER.info("PlatoEspecialResouce deletePlatoEspecial: output: void");
    }
    
    private List<PlatoEspecialDTO> listEntity2DetailDTO(List<PlatoEspecialEntity> entityList) {
        List<PlatoEspecialDTO> list = new ArrayList<>();
        for (PlatoEspecialEntity entity : entityList) {
            list.add(new PlatoEspecialDTO(entity));
        }
        return list;
    }
}