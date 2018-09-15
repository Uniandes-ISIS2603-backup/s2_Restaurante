/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.ejb;

import co.edu.uniandes.csw.restaurante.entities.PlatoEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.restaurante.persistence.PlatoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author iy.barbosa
 */
@Stateless
public class PlatoLogic {
     private static final Logger LOGGER = Logger.getLogger(PlatoLogic.class.getName());

    @Inject
    private PlatoPersistence persistence;

    /**
     * Se encarga de crear un Plato en la base de datos.
     *
     * @param platoEntity Objeto de PlatoEntity con los datos nuevos
     * @return Objeto de PlatoEntity con los datos nuevos y su ID.
     */
    public PlatoEntity createPlato(PlatoEntity platoEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del palto");
        /*Valida la regla de negocio de que los nombres de los platos no 
        pueden contener ser nulos*/
        if (platoEntity.getNombre().equals(null)) {
            throw new BusinessLogicException("El nombre del plato no puede ser nulo");
        }
        if (platoEntity.getPrecio().equals(null)|| platoEntity.getPrecio()<0.0) {
            throw new BusinessLogicException("El nombre del precio no puede ser nulo o negativo");
        }
        if (platoEntity.getSucursal().equals(null)) {
            throw new BusinessLogicException("el plato tiene que pertenecer a una sucuarsal");
        }
        PlatoEntity newPlatoEntity = persistence.create(platoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del plato");
        return newPlatoEntity;
    }

    /**
     * Obtiene la lista de los platos .
     *
     * @return Colección de objetos de PlatoEntity.
     */
    public List<PlatoEntity> getPaltos() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los platos");
        List<PlatoEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los platos");
        return lista;
    }

    /**
     * Obtiene los datos de una instancia de platos a partir de su ID.
     *
     * @param platoId Identificador de la instancia a consultar
     * @return Instancia de PlatoEntity con los datos del Cliente consultado.
     */
    public PlatoEntity getPlato(Long platoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el plato con id = {0}", platoId);
        PlatoEntity platoEntity = persistence.find(platoId);
        if (platoEntity == null) {
            LOGGER.log(Level.SEVERE, "El plato con el id = {0} no existe", platoId);
            throw new BusinessLogicException("El plato con el ID " + platoId + " no existe.");
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el plato con id = {0}", platoId);
        return platoEntity;
    }

    /**
     * Actualiza la información de una plato.
     *
     * @param platoId Identificador de la instancia a actualizar
     * @param platoEntity Instancia de PlatoEntity con los nuevos datos.
     * @return Instancia de PlatoEntity con los datos actualizados.
     */
    public PlatoEntity updatePlato(Long platoId, PlatoEntity platoEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el plato con id = {0}", platoId);
        PlatoEntity newPlatoEntity = persistence.update(platoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el cliente con id = {0}", platoId);
        return newPlatoEntity;
    }

    /**
     * Elimina una instancia de plato de la base de datos.
     *
     * @param platoId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si el autor tiene libros asociados.
     */
    public void deletePlato(Long platoId) throws BusinessLogicException {

        LOGGER.log(Level.INFO, "Inicia proceso de borrar el plato con id = {0}", platoId);

        PlatoEntity plato = persistence.find(platoId);
        if (plato == null) {
            throw new BusinessLogicException("No se encontró el palto con el ID: " + platoId + ".");
        }
        persistence.delete(platoId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el cliente con id = {0}", platoId);
    }
    
}
