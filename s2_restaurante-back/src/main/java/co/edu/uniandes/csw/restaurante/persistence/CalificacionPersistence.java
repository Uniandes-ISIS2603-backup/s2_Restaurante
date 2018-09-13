/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.persistence;

import co.edu.uniandes.csw.restaurante.entities.CalificacionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author iy.barbosa
 */
@Stateless
public class CalificacionPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(CalificacionPersistence.class.getName());

    @PersistenceContext(unitName = "StarbugsPU")
    protected EntityManager em;
    
     /**
     * Crea una calificacion en la base de datos
     *
     * @param calificacionEntity objeto cliente que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public CalificacionEntity create(CalificacionEntity clienteEntity) {
        LOGGER.log(Level.INFO, "Creando una calificacion nueva");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la author en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(clienteEntity);
        LOGGER.log(Level.INFO, "Calificacion creada");
        return clienteEntity;
    }
    
    /**
     * Devuelve todas las calificaciones de la base de datos.
     *
     * @return una lista con todos los clientes que encuentre en la base de
     * datos, "select u from ClienteEntity u" es como un "select * from
     * ClienteEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<CalificacionEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las califaciones ");
        // Se crea un query para buscar todos los clientes en la base de datos.
        TypedQuery query = em.createQuery("select u from CalificacionEntity u", CalificacionEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de clientes.
        return query.getResultList();
    }
    
    /**
     * Busca si hay alguna calificacion id que se envía de argumento
     *
     * @param calificacionId: id correspondiente a la calificacion buscada.
     * @return un calificacion.
     */
    public CalificacionEntity find(Long calificacionId) {
        LOGGER.log(Level.INFO, "Consultando la calificacion con id={0}", calificacionId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from AuthorEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(CalificacionEntity.class, calificacionId);
    }

    /**
     * Actualiza una calificacion.
     *
     * @param calificacionEntity: la calificacion que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una calificacion con los cambios aplicados.
     */
    public CalificacionEntity update(CalificacionEntity calificacionEntity) {
        LOGGER.log(Level.INFO, "Actualizando la calificaacion con id={0}", calificacionEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la author con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(calificacionEntity);
    }

    /**
     * Borra una calificacion de la base de datos recibiendo como argumento el id del
     * cliente.
     *
     * @param calificacionId: id correspondiente al cliente a borrar.
     */
    public void delete(Long calificacionId) {

        LOGGER.log(Level.INFO, "Borrando el calificacion con id={0}", calificacionId);
        // Se hace uso de mismo método que esta explicado en public AuthorEntity find(Long id) para obtener la author a borrar.
        CalificacionEntity calificacionEntity = em.find(CalificacionEntity.class, calificacionId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from AuthorEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(calificacionEntity);
    }
}