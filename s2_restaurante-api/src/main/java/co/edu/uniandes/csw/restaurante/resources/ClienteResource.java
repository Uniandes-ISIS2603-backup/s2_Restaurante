/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;

import co.edu.uniandes.csw.restaurante.dtos.ClienteDTO;
import co.edu.uniandes.csw.restaurante.dtos.ClienteDetailDTO;
import co.edu.uniandes.csw.restaurante.ejb.ClienteLogic;
import co.edu.uniandes.csw.restaurante.entities.ClienteEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author j.prieto
 */
@Path("clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ClienteResource {

//    
    @Inject
    private ClienteLogic clienteLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(ClienteResource.class.getName());

    /**
     * Crea un nuevo cliente con la información que se recibe en el cuerpo de la
     * petición y se regresa un objeto idéntico con un ID auto-generado por la
     * base de datos.
     *
     * @param cliente {@link ClienteDTO} - El cliente que se desea guardar.
     * @return JSON {@link ClienteDTO} - El autor guardado con el atributo ID
     * auto-generado.
     */
    @POST
    public ClienteDTO crearCliente(ClienteDTO cliente) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ClienteResource crearCliente: input: {0}", cliente.toString());
        ClienteDTO clienteDTO = new ClienteDTO(clienteLogic.createCliente(cliente.toEntity()));
        LOGGER.log(Level.INFO, "ClienteResource crearCliente: output: {0}", clienteDTO.toString());
        return clienteDTO;
    }

    /**
     * Busca y devuelve todos los clientes que existen en la aplicación.
     *
     * @return JSONArray {@link ClienteDetailDTO} - Los clientes encontrador en
     * la aplicación. Si no hay ningún cliente se retornará una lista vacía.
     */
    @GET
    public List<ClienteDetailDTO> getClientes() {
        LOGGER.info("ClienteResource getClientes: input: void");
        List<ClienteDetailDTO> listaClientes = listEntity2DetailDTO(clienteLogic.getClientes());
        LOGGER.log(Level.INFO, "ClienteResource getClientes: output: {0}", listaClientes.toString());
        return listaClientes;
    }

    /**
     * Busca el cliente con el ID asociado recibido en la URL y lo devuelve.
     *
     * @param clientesId Identificador del cliente que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link ClienteDetailDTO} - El cliente buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el cliente.
     */
    @GET
    //Como recibimos dígitos, ponemos d+
    @Path("{clientesId: \\d+}")
    public ClienteDetailDTO getCliente(@PathParam("clientesId") Long clientesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ClienteResource getCliente: input: {0}", clientesId);
        ClienteEntity clienteEntity = clienteLogic.getCliente(clientesId);
        if (clienteEntity == null) {
            throw new WebApplicationException("El recurso /clientes/" + clientesId + " no existe.", 404);
        }
        ClienteDetailDTO detailDTO = new ClienteDetailDTO(clienteEntity);
        LOGGER.log(Level.INFO, "ClienteResource getCliente: output: {0}", detailDTO.toString());
        return detailDTO;
    }

    /**
     * Actualiza el cliente con el ID recibido en la URL con la información que
     * se recibe en el cuerpo de la petición.
     *
     * @param clientesId Identificador del cliente que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param cliente {@link ClienteDTO} El cliente que se desea guardar.
     * @return JSON {@link ClienteDetailDTO} - El cliente guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el cliente a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el cliente.
     */
    @PUT 
    @Path("{clientesId: \\d+}")
    public ClienteDetailDTO updateCliente(@PathParam("clientesId") Long clientesId, ClienteDTO cliente) throws BusinessLogicException /*throws BusinessLogicException*/ {
        LOGGER.log(Level.INFO, "ClienteResource updateCliente: input: clientesId: {0} , cliente: {1}", new Object[]{clientesId, cliente.toString()});
        cliente.setId(clientesId);
        ClienteDetailDTO buscado= new ClienteDetailDTO(clienteLogic.getCliente(clientesId));
        if (buscado == null) {
            throw new WebApplicationException("El recurso /clientes/" + clientesId + " no existe.", 404);
        }
        buscado.setNombre(cliente.getNombre());
        buscado.setMetodoPago(cliente.getMetodoPago());
        ClienteDetailDTO detailDTO = new ClienteDetailDTO(clienteLogic.updateCliente(clientesId, buscado.toEntity()));
        LOGGER.log(Level.INFO, "ClienteResource updateCliente: output: {0}", detailDTO.toString());
        return detailDTO;
    }

    /**
     * Borra el cliente con el id asociado recibido en la URL.
     *
     * @param clientesId Identificador del cliente que se desea borrar. Este
     * debe ser una cadena de dígitos.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * si el autor tiene libros asociados
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el autor a borrar.
     */
    @DELETE
    @Path("{clientesId: \\d+}")
    public void deleteCliente(@PathParam("clientesId") Long clientesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ClienteResource deleteCliente: input: {0}", clientesId);
        if (clienteLogic.getCliente(clientesId) == null) {
            throw new WebApplicationException("El recurso /clientes/" + clientesId + " no existe.", 404);
        }
        clienteLogic.deleteCliente(clientesId);
        LOGGER.info("ClienteResource deleteCliente: output: void");
    }

    private List<ClienteDetailDTO> listEntity2DetailDTO(List<ClienteEntity> entityList) {
        List<ClienteDetailDTO> list = new ArrayList<>();
        for (ClienteEntity entity : entityList) {
            list.add(new ClienteDetailDTO(entity));
        }
        return list;
    }

    /**
     * Conexión con el servicio de reservas para un cliente.
     * {@link ClienteReservasResource}
     *
     * Este método conecta la ruta de /clientes con las rutas de /reservas que
     * dependen del cliente, es una redirección al servicio que maneja el
     * segmento de la URL que se encarga de las reservas.
     *
     * @param clientesId El ID del cliente con respecto al cual se accede al
     * servicio.
     * @return El servicio de reservas para ese cliente en paricular.
     */
    @Path("{clientesId: \\d+}/reservas")
    public Class<ClienteReservasResource> getClienteSucursalesResource(@PathParam("clientesId") Long clientesId) throws BusinessLogicException {
        if (clienteLogic.getCliente(clientesId) == null) {
            throw new WebApplicationException("El recurso /clientes/" + clientesId + " no existe.", 404);
        }
        return ClienteReservasResource.class;
    }
}
