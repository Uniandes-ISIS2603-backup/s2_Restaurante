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
import co.edu.uniandes.csw.restaurante.entities.DomicilioEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.restaurante.persistence.ClientePersistence;
import co.edu.uniandes.csw.restaurante.persistence.DomicilioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 * Domicilio.
 *
 * @author jp.hidalgo
 */
@Stateless
public class DomicilioLogic {

    private static final Logger LOGGER = Logger.getLogger(DomicilioLogic.class.getName());

    @Inject
    private DomicilioPersistence persistence;

    
    @Inject 
    private ClientePersistence clientePersistence;

    /**
     * Se encarga de crear un Domicilio en la base de datos.
     *
     * @param domicilioEntity Objeto de DomicilioEntity con los datos nuevos
     * @param clienteId id del cliente el cual sera padre del nuevo Domicilio.
     * @return Objeto de DomicilioEntity con los datos nuevos y su ID.
     * @throws BusinessLogicException si ClientesId no es el mismo que tiene el
     * entity.
     *
     */
    public DomicilioEntity createDomicilio(Long clienteId, DomicilioEntity domicilioEntity) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de crear Domicilio");
        ClienteEntity cliente = clientePersistence.find(clienteId);
        domicilioEntity.setCliente(cliente);
        LOGGER.log(Level.INFO, "Termina proceso de creación del Domicilio");
        return persistence.create(domicilioEntity);
    }

    /**
     * Obtiene la lista de los registros de Domicilio que pertenecen a un Cliente.
     *
     * @param clientesId id del Cliente el cual es padre de los Domicilios.
     * @return Colección de objetos de DomicilioEntity.
     */
    public List<DomicilioEntity> getDomicilios(Long clientesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los Domicilios asociados al Cliente con id = {0}", clientesId);
        ClienteEntity clienteEntity = clientePersistence.find(clientesId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar los Domicilios asociados al Cliente con id = {0}", clientesId);
        return clienteEntity.getDomicilios();
    }

    /**
     * Obtiene los datos de una instancia de Domicilio a partir de su ID. La
     * existencia del elemento padre Cliente se debe garantizar.
     *
     * @param domiciliosId Identificador de la Reseña a consultar
     * @return Instancia de DomicilioEntity con los datos del Domicilio consultado.
     *
     */
    public DomicilioEntity getDomicilio( Long domiciliosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el Domicilio con id = {0}", domiciliosId);
        return persistence.find( domiciliosId);
    }

    /**
     * Elimina una instancia de Domicilio de la base de datos.
     *
     * @param domiciliosId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException Si la reseña no esta asociada al cliente.
     *
     */
    public void deleteDomicilio( Long domiciliosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el Domicilio con id = {0}", domiciliosId);
        DomicilioEntity old = getDomicilio( domiciliosId);
        if (old == null) {
            throw new BusinessLogicException("El Domicilio con id = " + domiciliosId + " no esta ");
        }
        persistence.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar el Domicilio con id = {0}", domiciliosId);
    }
}
