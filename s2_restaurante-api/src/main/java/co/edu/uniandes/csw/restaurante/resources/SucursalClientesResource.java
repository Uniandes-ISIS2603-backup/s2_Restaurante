/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.restaurante.resources;

import co.edu.uniandes.csw.restaurante.ejb.ClienteLogic;
import co.edu.uniandes.csw.restaurante.ejb.SucursalClientesLogic;
import co.edu.uniandes.csw.restaurante.mappers.WebApplicationExceptionMapper;
import co.edu.uniandes.csw.restaurante.dtos.ClienteDetailDTO;
import co.edu.uniandes.csw.restaurante.entities.ClienteEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "sucursales/{id}/clientes".
 *
 * @author j.prieto
 * @version 1.0
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SucursalClientesResource {

    private static final Logger LOGGER = Logger.getLogger(SucursalClientesResource.class.getName());

    @Inject
    private SucursalClientesLogic sucursalClientesLogic;

    @Inject
    private ClienteLogic clienteLogic;

    /**
     * Asocia un cliente existente con una sucursal existente
     *
     * @param clientesId El ID del cliente que se va a asociar
     * @param sucursalesId El ID de la sucursal al cual se le va a asociar el cliente
     * @return JSON {@link ClienteDetailDTO} - El cliente asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el cliente.
     */
    @POST
    @Path("{clientesId: \\d+}")
    public ClienteDetailDTO addCliente(@PathParam("sucursalesId") Long sucursalesId, @PathParam("clientesId") Long clientesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "SucursalClientesResource addCliente: input: sucursalesId {0} , clientesId {1}", new Object[]{sucursalesId, clientesId});
        if (clienteLogic.getCliente(clientesId) == null) {
            throw new WebApplicationException("El recurso /clientes/" + clientesId + " no existe.", 404);
        }
        ClienteDetailDTO detailDTO = new ClienteDetailDTO(sucursalClientesLogic.addCliente(sucursalesId, clientesId));
        LOGGER.log(Level.INFO, "SucursalClientesResource addCliente: output: {0}", detailDTO.toString());
        return detailDTO;
    }

    /**
     * Busca y devuelve todos los clientes que ha tenido una sucursal.
     *
     * @param sucursalId El ID de la sucursal del cual se buscan los clientes
     * @return JSONArray {@link ClienteDetailDTO} - Los clientes encontrados en la sucursal.
     * Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ClienteDetailDTO> getClientes(@PathParam("sucursalesId") Long sucursalesId) {
        LOGGER.log(Level.INFO, "SucursalClientesResource getClientes: input: {0}", sucursalesId);
        List<ClienteDetailDTO> lista = clientesListEntity2DTO(sucursalClientesLogic.getClientes(sucursalesId));
        LOGGER.log(Level.INFO, "SucursalClientesResource getClientes: output: {0}", lista.toString());
        return lista;
    }

    /**
     * Busca y devuelve el cliente con el ID recibido en la URL, relativo a una
     * sucursal.
     *
     * @param clientesId El ID del cliente que se busca
     * @param sucursalesId El ID de la sucursal del cual se busca el cliente
     * @return {@link ClienteDetailDTO} - El cliente encontrado en el libro.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el cliente.
     */
    @GET
    @Path("{clientesId: \\d+}")
    public ClienteDetailDTO getCliente(@PathParam("sucursalesId") Long sucursalesId, @PathParam("clientesId") Long clientesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "SucursalClientesResource getCliente: input: sucursalesId {0} , clientesId {1}", new Object[]{sucursalesId, clientesId});
        if (clienteLogic.getCliente(clientesId) == null) {
            throw new WebApplicationException("El recurso /clientes/" + clientesId + " no existe.", 404);
        }
        ClienteDetailDTO detailDTO = new ClienteDetailDTO(sucursalClientesLogic.getCliente(clientesId, sucursalesId));
        LOGGER.log(Level.INFO, "SucursalClientesResource getCliente: output: {0}", detailDTO.toString());
        return detailDTO;
    }

    /**
     * Actualiza la lista de clientes de una sucursal con la lista que se recibe en
     * el cuerpo.
     *
     * @param sucursalesId El ID de la sucursal a la cual se le va a asociar la lista de
     * clientes
     * @param clientes JSONArray {@link ClienteDetailDTO} - La lista de clientes
     * que se desea guardar.
     * @return JSONArray {@link ClienteDetailDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el cliente.
     */
    @PUT
    public List<ClienteDetailDTO> replaceClientes(@PathParam("sucursalesId") Long sucursalesId, List<ClienteDetailDTO> clientes) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "SucursalClientesResource replaceClientes: input: sucursalesId {0} , clientes {1}", new Object[]{sucursalesId, clientes.toString()});
        for (ClienteDetailDTO cliente : clientes) {
            if (clienteLogic.getCliente(cliente.getId()) == null) {
                throw new WebApplicationException("El recurso /clientes/" + cliente.getId() + " no existe.", 404);
            }
        }
        List<ClienteDetailDTO> lista = clientesListEntity2DTO(sucursalClientesLogic.replaceClientes(sucursalesId, clientesListDTO2Entity(clientes)));
        LOGGER.log(Level.INFO, "SucursalClientesResource replaceClientes: output:{0}", lista.toString());
        return lista;
    }

    /**
     * Elimina la conexión entre el cliente y la sucursal recibidos en la URL.
     *
     * @param sucursalesId El ID de la sucursal a la cual se le va a desasociar el cliente
     * @param clientesId El ID del cliente que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el cliente.
     */
    @DELETE
    @Path("{clientesId: \\d+}")
    public void removeCliente(@PathParam("sucursalesId") Long sucursalesId, @PathParam("clientesId") Long clientesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "SucursalClientesResource removeCliente: input: booksId {0} , clientesId {1}", new Object[]{sucursalesId, clientesId});
        if (clienteLogic.getCliente(clientesId) == null) {
            throw new WebApplicationException("El recurso /clientes/" + clientesId + " no existe.", 404);
        }
        sucursalClientesLogic.removeCliente(sucursalesId, clientesId);
        LOGGER.info("SucursalClientesResource removeAuthor: output: void");
    }

    /**
     * Convierte una lista de AuthorEntity a una lista de AuthorDetailDTO.
     *
     * @param entityList Lista de AuthorEntity a convertir.
     * @return Lista de AuthorDetailDTO convertida.
     */
    private List<ClienteDetailDTO> clientesListEntity2DTO(List<ClienteEntity> entityList) {
        List<ClienteDetailDTO> list = new ArrayList<>();
        for (ClienteEntity entity : entityList) {
            list.add(new ClienteDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de AuthorDetailDTO a una lista de AuthorEntity.
     *
     * @param dtos Lista de AuthorDetailDTO a convertir.
     * @return Lista de AuthorEntity convertida.
     */
    private List<ClienteEntity> clientesListDTO2Entity(List<ClienteDetailDTO> dtos) {
        List<ClienteEntity> list = new ArrayList<>();
        for (ClienteDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
