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
package co.edu.uniandes.csw.restaurante.ejb;

import co.edu.uniandes.csw.restaurante.entities.ClienteEntity;
import co.edu.uniandes.csw.restaurante.entities.SucursalEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.restaurante.persistence.ClientePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 * Cliente.
 *
 * @author j.prieto
 */
@Stateless
public class ClienteLogic {

    private static final Logger LOGGER = Logger.getLogger(ClienteLogic.class.getName());

    @Inject
    private ClientePersistence persistence;

    /**
     * Se encarga de crear un Cliente en la base de datos.
     *
     * @param clienteEntity Objeto de ClienteEntity con los datos nuevos
     * @return Objeto de ClienteEntity con los datos nuevos y su ID.
     */
    public ClienteEntity createCliente(ClienteEntity clienteEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del cliente");
        /*Valida la regla de negocio de que los nombres de los clientes no 
        pueden contener números*/
        if (clienteEntity.getNombre().matches(".*\\d+.*")) {
            throw new BusinessLogicException("El nombre del cliente no puede contener números");
        }
        ClienteEntity newClienteEntity = persistence.create(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del cliente");
        return newClienteEntity;
    }

    /**
     * Obtiene la lista de los registros de Cliente.
     *
     * @return Colección de objetos de ClienteEntity.
     */
    public List<ClienteEntity> getClientes() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los clientes");
        List<ClienteEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los clientes");
        return lista;
    }

    /**
     * Obtiene los datos de una instancia de Cliente a partir de su ID.
     *
     * @param clientesId Identificador de la instancia a consultar
     * @return Instancia de ClienteEntity con los datos del Cliente consultado.
     */
    public ClienteEntity getCliente(Long clientesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el cliente con id = {0}", clientesId);
        ClienteEntity clienteEntity = persistence.find(clientesId);
        if (clienteEntity == null) {
            LOGGER.log(Level.SEVERE, "El cliente con el id = {0} no existe", clientesId);
            throw new BusinessLogicException("El cliente con el ID " + clientesId + " no existe.");
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el cliente con id = {0}", clientesId);
        return clienteEntity;
    }

    /**
     * Actualiza la información de una instancia de Cliente.
     *
     * @param clientesId Identificador de la instancia a actualizar
     * @param clienteEntity Instancia de AuthorEntity con los nuevos datos.
     * @return Instancia de ClienteEntity con los datos actualizados.
     */
    public ClienteEntity updateCliente(Long clientesId, ClienteEntity clienteEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el cliente con id = {0}", clientesId);
        ClienteEntity newClienteEntity = persistence.update(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el cliente con id = {0}", clientesId);
        return newClienteEntity;
    }

    /**
     * Elimina una instancia de Cliente de la base de datos.
     *
     * @param clientesId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si el autor tiene libros asociados.
     */
    public void deleteCliente(Long clientesId) throws BusinessLogicException {

        LOGGER.log(Level.INFO, "Inicia proceso de borrar el cliente con id = {0}", clientesId);

        ClienteEntity cliente = persistence.find(clientesId);
        if (cliente == null) {
            throw new BusinessLogicException("No se encontró el cliente con el ID: " + clientesId + ".");
        }
        persistence.delete(clientesId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el cliente con id = {0}", clientesId);
    }    
}
