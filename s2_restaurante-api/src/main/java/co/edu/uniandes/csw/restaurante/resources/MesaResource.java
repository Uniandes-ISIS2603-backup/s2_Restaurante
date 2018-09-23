/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.restaurante.resources;
import co.edu.uniandes.csw.restaurante.dtos.MesaDTO;
import co.edu.uniandes.csw.restaurante.ejb.MesaLogic;
import co.edu.uniandes.csw.restaurante.entities.MesaEntity;
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
@Path("mesas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class MesaResource {
   private static final Logger LOGGER = Logger.getLogger(MesaResource.class.getName());

    @Inject
    private MesaLogic mesaLogic;
    
    @POST
    public MesaDTO createMesa(MesaDTO mesa) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "MesaResource createMesa: input: {0}", mesa.toString());
        MesaEntity mesaEntity = mesa.toEntity();
        MesaEntity nuevaMesaEntity;
        nuevaMesaEntity = mesaLogic.createMesa(mesaEntity);
        MesaDTO nuevaMesaDTO = new MesaDTO(nuevaMesaEntity);
        LOGGER.log(Level.INFO, "MesaResource createMesa output: {0}", nuevaMesaDTO.toString());
        return nuevaMesaDTO;
    }
    
    @GET
    @Path("{mesasId: \\d+}")
    public MesaDTO getMesa(@PathParam("mesasId") Long mesasId) {
       
        LOGGER.log(Level.INFO, "MesaResource getMesa: input: {0}", mesasId);
        MesaEntity mesaEntity = mesaLogic.getMesa(mesasId);
        if (mesaEntity == null) {
            throw new WebApplicationException("El recurso /mesas/" + mesasId + " no existe.", 404);
        }
        MesaDTO dto = new MesaDTO(mesaEntity);
        LOGGER.log(Level.INFO, "MesaResource getMesa: output: {0}", dto.toString());
        return dto;
    }
    
    @GET
    public List<MesaDTO> getMesas() {
        LOGGER.info("MesaResouce getMesas: input: void");
        List<MesaDTO> listaMesas = listEntity2DetailDTO(mesaLogic.getMesas());
        LOGGER.log(Level.INFO, "MesaResouce getMesas: output: {0}", listaMesas.toString());
        return listaMesas;
    }
    
    @PUT
    @Path("{mesasId: \\d+}")
    public MesaDTO updateMesa(@PathParam("mesasId") Long mesasId, MesaDTO mesa)  throws WebApplicationException {
        LOGGER.log(Level.INFO, "MesaResouce updateMesa: input: id:{0} , numero: {1}", new Object[]{mesasId, mesa.toString()});
        mesa.setId(mesasId);
        if (mesaLogic.getMesa(mesasId)== null) {
            throw new WebApplicationException("El recurso /mesas/" + mesasId + " no existe.", 404);
        }
        MesaDTO dto = new MesaDTO(mesaLogic.updateMesa(mesasId, mesa.toEntity()));
        LOGGER.log(Level.INFO, "MesaResouce updateMesa: output: {0}", dto.toString());
        return dto;
    }
    
    @DELETE
    @Path("{mesasId: \\d+}")
    public void deleteMesa(@PathParam("mesasId") Long mesasId)  throws BusinessLogicException{
       LOGGER.log(Level.INFO, "MesaResouce deleteMesa: input: {0}", mesasId);
        if (mesaLogic.getMesa(mesasId) == null) {
            throw new WebApplicationException("El recurso /mesas/" + mesasId + " no existe.", 404);
        }
        mesaLogic.deleteMesa(mesasId);
        LOGGER.info("MesaResouce deleteMesa: output: void");
    }
    
    private List<MesaDTO> listEntity2DetailDTO(List<MesaEntity> entityList) {
        List<MesaDTO> list = new ArrayList<>();
        for (MesaEntity entity : entityList) {
            list.add(new MesaDTO(entity));
        }
        return list;
    }
}
