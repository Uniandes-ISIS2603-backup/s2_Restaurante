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

import co.edu.uniandes.csw.restaurante.entities.PuntoEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.restaurante.persistence.PuntoPersistence;
import co.edu.uniandes.csw.restaurante.persistence.TarjetaPersistence;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de Punto.
 *
 * @author Juan Hidalgo
 */
@Stateless
public class PuntoLogic {

    private static final Logger LOGGER = Logger.getLogger(PuntoLogic.class.getName());

    @Inject
    private PuntoPersistence persistence;

    @Inject
    private TarjetaPersistence tarjetaPersistence;

    /**
     * Guardar un nuevo punto
     *
     * @param puntoEntity La entidad de tipo punto del nuevo punto a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el ISBN es inválido o ya existe en la
     * persistencia.
     */
    public PuntoEntity createPunto(PuntoEntity puntoEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del punto");
        if (puntoEntity.getTarjeta() == null || tarjetaPersistence.find(puntoEntity.getTarjeta().getId()) == null) {
            throw new BusinessLogicException("La Tarjeta es inválida");
        }
        if (!validateFecha(puntoEntity.getFecha())) {
            throw new BusinessLogicException("El ISBN es inválido");
        }
        persistence.create(puntoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del punto");
        return puntoEntity;
    }

    /**
     * Devuelve todos los puntos que hay en la base de datos.
     *
     * @return Lista de entidades de tipo punto.
     */
    public List<PuntoEntity> getPuntos() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los puntos");
        List<PuntoEntity> Puntos = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los puntos");
        return Puntos;
    }

    /**
     * Busca un punto por ID
     *
     * @param PuntosId El id del punto a buscar
     * @return El punto encontrado, null si no lo encuentra.
     */
    public PuntoEntity getPunto(Long PuntosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el punto con id = {0}", PuntosId);
        PuntoEntity PuntoEntity = persistence.find(PuntosId);
        if (PuntoEntity == null) {
            LOGGER.log(Level.SEVERE, "El punto con el id = {0} no existe", PuntosId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el punto con id = {0}", PuntosId);
        return PuntoEntity;
    }

    /**
     * Actualizar un punto por ID
     *
     * @param PuntosId El ID del punto a actualizar
     * @param PuntoEntity La entidad del punto con los cambios deseados
     * @return La entidad del punto luego de actualizarla
     * @throws BusinessLogicException Si el IBN de la actualización es inválido
     */
    public PuntoEntity updatePunto(Long PuntosId, PuntoEntity PuntoEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el punto con id = {0}", PuntosId);
        if (!validateFecha(PuntoEntity.getFecha())) {
            throw new BusinessLogicException("La fecha es inválida");
        }
        PuntoEntity newEntity = persistence.update(PuntoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el punto con id = {0}", PuntoEntity.getId());
        return newEntity;
    }

    /**
     * Eliminar un punto por ID
     *
     * @param puntosId El ID del punto a eliminar
     * @throws BusinessLogicException si el punto tiene autores asociados
     */
    public void deletePunto(Long puntosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el punto con id = {0}", puntosId);
        persistence.delete(puntosId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el punto con id = {0}", puntosId);
    }

    /**
     * Verifica que el ISBN no sea invalido.
     *
     * @param isbn a verificar
     * @return true si el ISBN es valido.
     */
    private boolean validateFecha(Date isbn) {
        return !(isbn == null);
    }
}
