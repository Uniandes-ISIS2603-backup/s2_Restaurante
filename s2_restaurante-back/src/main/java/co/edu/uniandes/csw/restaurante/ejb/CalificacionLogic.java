/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.ejb;

import co.edu.uniandes.csw.restaurante.entities.CalificacionEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.restaurante.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.restaurante.persistence.ClientePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author iy.barbosa
 * 
 */
@Stateless
public class CalificacionLogic 
{
    
    private static final Logger LOGGER = Logger.getLogger(CalificacionLogic.class.getName());
    
    @Inject
    private CalificacionPersistence persistence;
    
    /**
     * Se encarga de crear una calificacion en la base de datos.
     *
     * @param calificacionEntity Objeto de CalificacionEntity con los datos nuevos
     * @return Objeto de CalificacionEntity con los datos nuevos y su ID.
     */
    public CalificacionEntity createCalificacion(CalificacionEntity calificacionEntity) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la calificacion");
        /*Valida la regla de negocio de que el cliente no puede ser nulo */
        if (calificacionEntity.getCliente()==null) {
            throw new BusinessLogicException("el cliente no puede ser nulo");
        }
        /*Valida la regla de negocio del rango de puntaje */
        if (calificacionEntity.getPuntaje()>= 5.0 || calificacionEntity.getPuntaje() <= 0.0  )
        {
            throw new BusinessLogicException("el puntaje no esta en el rango");
        }
            
        CalificacionEntity newCalificacionEntity = persistence.create(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la calificacion");
        return newCalificacionEntity; 
    }
    /**
     * Actualiza la información de una calificacion
     *
     * @param calificacionId Identificador de la instancia a actualizar
     * @param calificacionEntity Instancia de CalificaiconEntity con los nuevos datos.
     * @return Instancia de CalificacionEntity con los datos actualizados.
     */
    public CalificacionEntity updateCalificacion(Long calificacionId, CalificacionEntity calificacionEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la calificacion con id = {0}", calificacionId);
        CalificacionEntity newCalificacionEntity = persistence.update(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el cliente con id = {0}", calificacionId);
        return newCalificacionEntity;
    }
    /**
     * Obtiene la lista de los registros de calificaciones .
     *
     * @return Colección de objetos de CalificacionEntity.
     */
    public List<CalificacionEntity> getCalificaciones() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las califiacaciones");
        List<CalificacionEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las calificaciones ");
        return lista;
    }
    
}
