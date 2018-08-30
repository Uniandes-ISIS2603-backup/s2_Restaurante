/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;

import co.edu.uniandes.csw.restaurante.dtos.ClienteDTO;
import co.edu.uniandes.csw.restaurante.dtos.ClienteDetailDTO;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.ws.rs.GET;

/**
 *
 * @author estudiante
 */
@Path("/restaurante")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class RestauranteResource {
    @Inject
//    private RestauranteLogic restauranteLogic; //Inyección de dependencias para acceder a la lógica.
//    private static final Logger LOGGER = Logger.getLogger(AuthorBooksResource.class.getName());
    
    
    
    /**
     * Devuelve el nombre del restaurante
     *
     * @return JSONArray {@link ClienteDetailDTO} - Los clientes encontrador en
     * la aplicación. Si no hay ningún cliente se retornará una lista vacía.
     */
    @GET
    public String getNombre() {
//        LOGGER.info("ClienteResource getClientes: input: void");
//        List<ClienteDetailDTO> listaClientes = listEntity2DTO(clienteLogic.getClientes());
//        LOGGER.log(Level.INFO, "ClienteResource getClientes: output: {0}", listaClientes.toString());
        return null;
}
