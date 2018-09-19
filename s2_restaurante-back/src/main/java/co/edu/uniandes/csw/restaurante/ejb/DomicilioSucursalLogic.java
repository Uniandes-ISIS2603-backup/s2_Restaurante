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

import co.edu.uniandes.csw.restaurante.entities.DomicilioEntity;
import co.edu.uniandes.csw.restaurante.entities.SucursalEntity;
import co.edu.uniandes.csw.restaurante.persistence.DomicilioPersistence;
import co.edu.uniandes.csw.restaurante.persistence.SucursalPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Sucursal y Domicilio.
 *
 * @author Juan Hidalgo
 */
@Stateless
public class DomicilioSucursalLogic {

    private static final Logger LOGGER = Logger.getLogger(DomicilioSucursalLogic.class.getName());

    @Inject
    private DomicilioPersistence domicilioPersistence;

    @Inject
    private SucursalPersistence sucursalPersistence;

    /**
     * Agregar un Domicilio a la Sucursal
     *
     * @param domiciliosId El id Domicilio a guardar
     * @param sucursalsId El id de la Sucursal en la cual se va a guardar el
     * Domicilio.
     * @return El Domicilio creado.
     */
    public DomicilioEntity addDomicilio(Long domiciliosId, Long sucursalsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un Domicilio a la Sucursal con id = {0}", sucursalsId);
        SucursalEntity sucursalEntity = sucursalPersistence.find(sucursalsId);
        DomicilioEntity domicilioEntity = domicilioPersistence.find(domiciliosId);
        domicilioEntity.setSucursal(sucursalEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un Domicilio a la Sucursal con id = {0}", sucursalsId);
        return domicilioEntity;
    }

    /**
     * Retorna todos los Domicilios asociados a una Sucursal
     *
     * @param sucursalsId El ID de la Sucursal buscada
     * @return La lista de Domicilios de la Sucursal
     */
    public List<DomicilioEntity> getDomicilios(Long sucursalsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los Domicilios asociados a la Sucursal con id = {0}", sucursalsId);
        return sucursalPersistence.find(sucursalsId).getDomicilios();
    }
}
