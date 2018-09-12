/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.persistence;


import co.edu.uniandes.csw.restaurante.entities.DomicilioEntity;
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
 * @author jp.hidalgo
 */

@Stateless
public class DomicilioPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(DomicilioPersistence.class.getName());
    @PersistenceContext(unitName = "StarbugsPU")
    protected EntityManager em;
    
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param domicilioEntity objeto domicilio que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public DomicilioEntity create(DomicilioEntity domicilioEntity) {
        LOGGER.log(Level.INFO, "Creando un domicilio nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la reserva en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(domicilioEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un domicilio nuevo");
        return domicilioEntity;
    }
    
    
    /**
     * Devuelve todas las reservas de la base de datos.
     *
     * @return una lista con todas las reservas que encuentre en la base de
     * datos, "select u from ReservaEntity u" es como un "select * from
     * ReservaEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<DomicilioEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los domicilios");
        // Se crea un query para buscar todas las reservas en la base de datos.
        TypedQuery query = em.createQuery("select u from DomicilioEntity u", DomicilioEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de reservas.
        return query.getResultList();
    }
    
    /**
     * Busca si hay alguna editorial con el id que se envía de argumento
     *
     * @param editorialsId: id correspondiente a la editorial buscada.
     * @return una editorial.
     */
    public DomicilioEntity find(Long editorialsId) {
        LOGGER.log(Level.INFO, "Consultando editorial con id={0}", editorialsId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from EditorialEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(DomicilioEntity.class, editorialsId);
    }
    /**
     * Actualiza una editorial.
     *
     * @param domicilioEntity: el domicilio que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una editorial con los cambios aplicados.
     */
    public DomicilioEntity update(DomicilioEntity domicilioEntity) {
        LOGGER.log(Level.INFO, "Actualizando domicilio con id = {0}", domicilioEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la editorial con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        LOGGER.log(Level.INFO, "Saliendo de actualizar el domicilio con id = {0}", domicilioEntity.getId());
        return em.merge(domicilioEntity);
    }
    /**
     *
     * Borra una editorial de la base de datos recibiendo como argumento el id
     * de la editorial
     *
     * @param domicilioId: id correspondiente a la editorial a borrar.
     */
    public void delete(Long domicilioId) {
        LOGGER.log(Level.INFO, "Borrando editorial con id = {0}", domicilioId);
        // Se hace uso de mismo método que esta explicado en public EditorialEntity find(Long id) para obtener la editorial a borrar.
        DomicilioEntity entity = em.find(DomicilioEntity.class, domicilioId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from EditorialEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la editorial con id = {0}", domicilioId);
    }
}


