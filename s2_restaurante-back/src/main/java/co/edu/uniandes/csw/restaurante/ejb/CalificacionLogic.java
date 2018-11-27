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

import co.edu.uniandes.csw.restaurante.entities.SucursalEntity;
import co.edu.uniandes.csw.restaurante.entities.CalificacionEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.restaurante.persistence.SucursalPersistence;
import co.edu.uniandes.csw.restaurante.persistence.CalificacionPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 * Calificacion(Calificacion).
 *
 * @author ISIS2603
 */
@Stateless
public class CalificacionLogic {

    private static final Logger LOGGER = Logger.getLogger(CalificacionLogic.class.getName());

    @Inject
    private CalificacionPersistence persistence;

    @Inject
    private SucursalPersistence sucursalPersistence;

    /**
     * Se encarga de crear un Calificacion en la base de datos.
     *
     * @param calificacionEntity Objeto de CalificacionEntity con los datos nuevos
     * @param sucursalId id del Sucursal el cual sera padre del nuevo Calificacion.
     * @return Objeto de CalificacionEntity con los datos nuevos y su ID.
     * @throws BusinessLogicException si sucursalId no es el mismo que tiene el
     * entity.
     *
     */
    public CalificacionEntity createCalificacion(Long sucursalId, CalificacionEntity calificacionEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de crear review");
        SucursalEntity book = sucursalPersistence.find(sucursalId);
        calificacionEntity.setSucursal(book);
        LOGGER.log(Level.INFO, "Termina proceso de creación del review");
        return persistence.create(calificacionEntity);
    }

    /**
     * Obtiene la lista de los registros de Calificacion que pertenecen a un Sucursal.
     *
     * @param sucursalId id del Sucursal el cual es padre de las calificaciones.
     * @return Colección de objetos de CalificacionEntity.
     */
    public List<CalificacionEntity> getCalificaciones(Long sucursalId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los calificaciones asociadas a la sucursal con id = {0}", sucursalId);
        SucursalEntity sucursalEntity = sucursalPersistence.find(sucursalId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar los calificaciones asociadas a la sucursal con id = {0}", sucursalId);
        return sucursalEntity.getCalificaciones();
    }

    /**
     * Obtiene los datos de una instancia de Calificacion a partir de su ID. La
     * existencia del elemento padre Sucursal se debe garantizar.
     *
     * @param sucursalId El id del Libro buscado
     * @param calificacionId Identificador de la Calificacion a consultar
     * @return Instancia de CalificacionEntity con los datos del Calificacion consultado.
     *
     */
    public CalificacionEntity getCalificacion(Long sucursalId, Long calificacionId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el review con id = {0}de la sucursal con id = " + sucursalId, calificacionId);
        return persistence.find(sucursalId, calificacionId);
    }

    /**
     * Actualiza la información de una instancia de Calificacion.
     *
     * @param calificacionEntity Instancia de CalificacionEntity con los nuevos datos.
     * @param sucursalId id del Sucursal el cual sera padre del Calificacion actualizado.
     * @return Instancia de CalificacionEntity con los datos actualizados.
     *
     */
    public CalificacionEntity updateCalificacion(Long sucursalId, CalificacionEntity calificacionEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el review con id = {0}de la sucursal con id = " + sucursalId, calificacionEntity.getId());
        SucursalEntity sucursalEntity = sucursalPersistence.find(sucursalId);
        calificacionEntity.setSucursal(sucursalEntity);
        persistence.update(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el review con id = {0}de la sucursal con id = " + sucursalId, calificacionEntity.getId());
        return calificacionEntity;
    }

    /**
     * Elimina una instancia de Calificacion de la base de datos.
     *
     * @param calificacionId Identificador de la instancia a eliminar.
     * @param sucursalId id del Sucursal el cual es padre del Calificacion.
     * @throws BusinessLogicException Si la reseña no esta asociada al libro.
     *
     */
    public void deleteCalificacion(Long sucursalId, Long calificacionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el review con id = {0}de la sucursal con id = " + sucursalId, calificacionId);
        CalificacionEntity old = getCalificacion(sucursalId, calificacionId);
        if (old == null) {
            throw new BusinessLogicException("El review con id = " + calificacionId + " no esta asociado a el libro con id = " + sucursalId);
        }
        persistence.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar el review con id = {0}de la sucursal con id = " + sucursalId, calificacionId);
    }
}
