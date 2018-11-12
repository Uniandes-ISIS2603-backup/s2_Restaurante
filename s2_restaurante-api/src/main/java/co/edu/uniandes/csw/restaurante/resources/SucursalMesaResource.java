/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;

import co.edu.uniandes.csw.restaurante.dtos.MesaDTO;
import co.edu.uniandes.csw.restaurante.ejb.SucursalMesaLogic;
import co.edu.uniandes.csw.restaurante.ejb.MesaLogic;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Cliente y Mesa.
 * 
 * @author ja.ortega
 */

@Produces("application/json")
@Consumes("application/json")
@Stateless
public class SucursalMesaResource {
    
    private static final Logger LOGGER = Logger.getLogger(SucursalMesaResource.class.getName());

    @Inject
    private SucursalMesaLogic SucursalMesaLogic;

    @Inject
    private MesaLogic MesaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
    
    
    /**
     * Asocia un Mesa existente con un cliente existente
     *
     * @param clientesId El ID del cliente a el cual se le va a asociar la Mesa
     * @param MesasId El ID de la Mesa que se asocia
     * @return JSON {@link MesaDTO} - La Mesa asociada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la Mesa.
     */
    @POST
    @Path("{MesaId: \\d+}")
    public MesaDTO addMesa(@PathParam("clientesId") Long clientesId, @PathParam("MesasId") Long MesasId) {
        LOGGER.log(Level.INFO, "SucursalMesaResource addMesa: input: clientesId {0} , MesasId {1}", new Object[]{clientesId, MesasId});
        if (MesaLogic.getMesa(MesasId) == null) {
            throw new WebApplicationException("El recurso /Mesas/" + MesasId + " no existe.", 404);
        }
        MesaDTO dto = new MesaDTO(SucursalMesaLogic.addMesa(clientesId, MesasId));
        LOGGER.log(Level.INFO, "SucursalMesaResource addMesa: output: {0}", dto);
        return dto;
    }
   
    
     /**
     * Elimina la conexión entre la Mesa y el cliente recibidos en la URL.
     *
     * @param clientesId El ID del cliente al cual se le va a desasociar la Mesa
     * @param MesasId El ID de la Mesa que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la Mesa.
     */
    @DELETE
    @Path("{MesasId: \\d+}")
    public void removeBook(@PathParam("clientesId") Long clientesId, @PathParam("MesasId") Long MesasId) {
        LOGGER.log(Level.INFO, "AuthorBooksResource deleteMesa: input: clientesId {0} , MesasId {1}", new Object[]{clientesId, MesasId});
        if (MesaLogic.getMesa(MesasId) == null) {
            throw new WebApplicationException("El recurso /Mesas/" + MesasId + " no existe.", 404);
        }
        SucursalMesaLogic.removeMesa(clientesId, MesasId);
        LOGGER.info("SucursalMesaResource deleteBook: output: void");
    }
    
  

}
