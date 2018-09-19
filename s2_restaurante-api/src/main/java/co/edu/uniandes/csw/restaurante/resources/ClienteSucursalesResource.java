///*
//MIT License
//
//Copyright (c) 2017 Universidad de los Andes - ISIS2603
//
//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:
//
//The above copyright notice and this permission notice shall be included in all
//copies or substantial portions of the Software.
//
//THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//SOFTWARE.
// */
//package co.edu.uniandes.csw.restaurante.resources;
//
//import co.edu.uniandes.csw.restaurante.dtos.SucursalDetailDTO;
//import co.edu.uniandes.csw.restaurante.ejb.ClienteSucursalesLogic;
//import co.edu.uniandes.csw.restaurante.entities.SucursalEntity;
//import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
//import co.edu.uniandes.csw.restaurante.mappers.WebApplicationExceptionMapper;
//import java.util.List;
//import javax.inject.Inject;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.ws.rs.WebApplicationException;
//
///**
// * Clase que implementa el recurso "clientes/{id}/sucursales".
// *
// * @author j.prieto
// * @version 1.0
// */
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
//public class ClienteSucursalesResource {
//
//    private static final Logger LOGGER = Logger.getLogger(ClienteSucursalesResource.class.getName());
//
//    @Inject
//    private ClienteSucursalesLogic clienteSucursalLogic;
//
////    @Inject
////    private SucursalLogic sucursalLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.
//    /**
//     * Asocia un sucursal existente con un cliente existente
//     *
//     * @param clientesId El ID del cliente al cual se le va a asociar una
//     * sucursal
//     * @param sucursalesId El ID de la sucursal que se asocia
//     * @return JSON {@link SucursalDTO} - La sucursal asociada.
//     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
//     * Error de lógica que se genera cuando no se encuentra la sucursal.
//     */
//    @POST
//    @Path("{sucursalesId: \\d+}")
//    public SucursalDetailDTO addSucursal(@PathParam("clientesId") Long clientesId, @PathParam("booksId") Long sucursalesId) {
////        LOGGER.log(Level.INFO, "ClienteSucursalesResource addSucursal: input: clientesId {0} , sucursalesId {1}", new Object[]{clientesId, sucursalesId});
////        if (clienteSucursalLogic.getSucursal(sucursalesId) == null) {
////            throw new WebApplicationException("El recurso /sucursales/" + sucursalesId + " no existe.", 404);
////        }
////        SucursalDetailDTO detailDTO = new SucursalDetailDTO(clienteSucursalLogic.addSucursal(clientesId, sucursalesId));
////        LOGGER.log(Level.INFO, "ClieneSucursalesResource addSucursal: output: {0}", detailDTO);
////        return detailDTO;
//        return null;
//    }
//
//    /**
//     * Busca y devuelve todos las sucursales que han prestado el servicio a un
//     * cliente.
//     *
//     * @param clientesId El ID del cliente del cual se buscan las sucursales
//     * @return JSONArray {@link SucursalDetailDTO} - Las sucursales encontradas
//     * en el cliente. Si no hay ninguna retorna una lista vacía.
//     */
//    @GET
//    public List<SucursalDetailDTO> getSucursales(@PathParam("clientesId") Long clientesId) {
//        LOGGER.log(Level.INFO, "ClienteSucursalesResource getSucursales: input: {0}", clientesId);
//        List<SucursalDetailDTO> lista = booksListEntity2DTO(clienteSucursalLogic.getSucursales(clientesId));
//        LOGGER.log(Level.INFO, "ClienteSucursalesResource getSucursales: output: {0}", lista.toString());
//        return lista;
//    }
//
//    /**
//     * Busca y devuelve la sucursal con el ID recibido en la URL, relativo a un
//     * cliente.
//     *
//     * @param clientesId El ID del cliente del cual se busca la sucursal
//     * @param sucursalesid El ID de la sucursal que se busca
//     * @return {@link SucursalDetailDTO} - la sucursal encontrada en el cliente.
//     * @throws co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException
//     * si el libro no está asociado al autor
//     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
//     * Error de lógica que se genera cuando no se encuentra la sucursal.
//     */
//    @GET
//    @Path("{sucursalesId: \\d+}")
//    public SucursalDetailDTO getSucursal(@PathParam("clientesId") Long clientesId, @PathParam("sucursalesId") Long sucursalesId) throws BusinessLogicException {
//        LOGGER.log(Level.INFO, "ClienteSucursalesResource getSucursal: input: clientesId {0} , sucursalesId {1}", new Object[]{clientesId, sucursalesId});
//        if (sucursalLogic.getSucursal(sucursalesId) == null) {
//            throw new WebApplicationException("El recurso /sucursales/" + sucursalesId + " no existe.", 404);
//        }
//        SucursalDetailDTO detailDTO = new SucursalDetailDTO(clienteSucursalLogic.getSucursal(clientesId, sucursalesId));
//        LOGGER.log(Level.INFO, "ClienteSucursalesResource getSucursal: output: {0}", detailDTO);
//        return detailDTO;
//    }
//
//    /**
//     * Actualiza la lista de sucursales de un cliente con la lista que se recibe
//     * en el cuerpo
//     *
//     * @param clientesId El ID del cliente al cual se le va a asociar la
//     * sucursal
//     * @param sucursales JSONArray {@link SucursalDetailDTO} - La lista de
//     * sucursales que se desea guardar.
//     * @return JSONArray {@link SucursalDetailDTO} - La lista actualizada.
//     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
//     * Error de lógica que se genera cuando no se encuentra la sucursal.
//     */
//    @PUT
//    public List<SucursalDetailDTO> replaceSucursales(@PathParam("clientesId") Long clientesId, List<SucursalDetailDTO> sucursales) {
//        LOGGER.log(Level.INFO, "ClienteSucursalesResource replaceSucursales: input: clientesId {0} , sucursales {1}", new Object[]{clientesId, sucursales.toString()});
//        for (SucursalDetailDTO sucursal : sucursales) {
//            if (sucursalLogic.getSucursal(sucursal.getId()) == null) {
//                throw new WebApplicationException("El recurso /sucursales/" + sucursal.getId() + " no existe.", 404);
//            }
//        }
//        List<SucursalDetailDTO> lista = booksListEntity2DTO(clienteSucursalLogic.replaceSucursales(clientesId, booksListDTO2Entity(sucursales)));
//        LOGGER.log(Level.INFO, "ClienteSucursalesResource replaceSucursales: output: {0}", lista.toString());
//        return lista;
//    }
//
//    /**
//     * Convierte una lista de SucursalEntity a una lista de SucursalDetailDTO.
//     *
//     * @param entityList Lista de SucursalEntity a convertir.
//     * @return Lista de SucursalDetailDTO convertida.
//     */
//    private List<SucursalDetailDTO> sucursalesListEntity2DTO(List<SucursalEntity> entityList) {
//        List<SucursalDetailDTO> list = new ArrayList<>();
//        for (SucursalEntity entity : entityList) {
//            list.add(new SucursalDetailDTO(entity));
//        }
//        return list;
//    }
//    
//    /**
//     * Convierte una lista de BookDetailDTO a una lista de BookEntity.
//     *
//     * @param dtos Lista de BookDetailDTO a convertir.
//     * @return Lista de BookEntity convertida.
//     */
//    private List<SucursalEntity> sucursalesListDTO2Entity(List<SucursalDetailDTO> dtos) {
//        List<SucursalEntity> list = new ArrayList<>();
////        for (SucursalDetailDTO dto : dtos) {
////            list.add(dto.toEntity());
////        }
//        return list;
//    }
//    
//    
//}
