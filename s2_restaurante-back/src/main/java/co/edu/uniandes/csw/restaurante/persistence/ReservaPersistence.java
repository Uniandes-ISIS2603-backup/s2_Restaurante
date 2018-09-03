/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.persistence;


import co.edu.uniandes.csw.restaurante.entities.ReservaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
/**
 * Clase que maneja la persistencia para una Reserva. Se conecta a través Entity
 * Manager de javax.persistance con la base de datos SQL.
 *
 * @author Juan Ortega
 */

@Stateless
public class ReservaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ReservaPersistence.class.getName());
    @PersistenceContext(unitName = "StarbugsPU")
    protected EntityManager em;
    
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param reservaEntity objeto reserva que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ReservaEntity create(ReservaEntity reservaEntity) {
        LOGGER.log(Level.INFO, "Creando una reserva nueva");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la reserva en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(reservaEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una reserva nueva");
        return reservaEntity;
    }
    
    
    /**
     * Devuelve todas las reservas de la base de datos.
     *
     * @return una lista con todas las reservas que encuentre en la base de
     * datos, "select u from ReservaEntity u" es como un "select * from
     * ReservaEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<ReservaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las reservas");
        // Se crea un query para buscar todas las reservas en la base de datos.
        TypedQuery query = em.createQuery("select u from ReservaEntity u", ReservaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de reservas.
        return query.getResultList();
    }
    
    /**
     * Busca si hay alguna reserva con el id que se envía de argumento
     *
     * @param reservaId: id correspondiente a la reserva buscada.
     * @return una reserva.
     */
    public ReservaEntity find(Long reservaId) {
        LOGGER.log(Level.INFO, "Consultando reserva con id={0}", reservaId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from ReservaEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(ReservaEntity.class, reservaId);
    }
    
    /**
     * Actualiza una reserva.
     *
     * @param reservaEntity : la reserva que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una reserva con los cambios aplicados.
     */
    public ReservaEntity update(ReservaEntity reservaEntity) {
        LOGGER.log(Level.INFO, "Actualizando reserva con id = {0}", reservaEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la reserva con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        LOGGER.log(Level.INFO, "Saliendo de actualizar la reserva con id = {0}", reservaEntity.getId());
        return em.merge(reservaEntity);
    }
    
    /**
     * Borra una reserva de la base de datos recibiendo como argumento el id
     * de la reserva
     *
     * @param reservaId: id correspondiente a la reserva a borrar.
     */
    public void delete(Long reservaId) {
        LOGGER.log(Level.INFO, "Borrando reserva con id = {0}", reservaId);
        // Se hace uso de mismo método que esta explicado en public ReservaEntity find(Long id) para obtener la reserva a borrar.
        ReservaEntity entity = em.find(ReservaEntity.class, reservaId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from ReservaEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la reserva con id = {0}", reservaId);
    }
    
    
    /**
     * Busca si hay alguna reserva con el nombre que se envía de argumento
     *
     * @param name: Nombre de la reserva que se está buscando
     * @return null si no existe ninguna reserva con el nombre del argumento.
     * Si existe alguna devuelve la primera.
     */
    public ReservaEntity findById(Integer id) {
        LOGGER.log(Level.INFO, "Consultando reserva por id ", id);
        // Se crea un query para buscar reserva con el nombre que recibe el método como argumento. ":id" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From ReservaEntity e where e.id = :id", ReservaEntity.class);
        // Se remplaza el placeholder ":id" con el valor del argumento 
        query = query.setParameter("id", id);
        // Se invoca el query se obtiene la lista resultado
        List<ReservaEntity> sameId = query.getResultList();
        ReservaEntity result;
        if (sameId == null) {
            result = null;
        } else if (sameId.isEmpty()) {
            result = null;
        } else {
            result = sameId.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar reserva por id ", id);
        return result;
    }
    
}


