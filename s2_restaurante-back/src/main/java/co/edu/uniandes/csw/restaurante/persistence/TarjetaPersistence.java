/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.persistence;


import co.edu.uniandes.csw.restaurante.entities.DomicilioEntity;
import co.edu.uniandes.csw.restaurante.entities.TarjetaEntity;
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
 * @author Juan Hidalgo
 */

@Stateless
public class TarjetaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(TarjetaPersistence.class.getName());
    @PersistenceContext(unitName = "StarbugsPU")
    protected EntityManager em;
    
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param tarjetaEntity objeto domicilio que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public TarjetaEntity create(TarjetaEntity tarjetaEntity) {
        LOGGER.log(Level.INFO, "Creando una tarjeta nueva");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la reserva en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(tarjetaEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una tarjeta nuevo");
        return tarjetaEntity;
    }
    
    
    /**
     * Devuelve todas las reservas de la base de datos.
     *
     * @return una lista con todas las reservas que encuentre en la base de
     * datos, "select u from ReservaEntity u" es como un "select * from
     * ReservaEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<TarjetaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las reservas");
        // Se crea un query para buscar todas las reservas en la base de datos.
        TypedQuery query = em.createQuery("select u from TajetaEntity u", TarjetaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de reservas.
        return query.getResultList();
    }
    /**
     * Busca si hay alguna editorial con el id que se envía de argumento
     *
     * @param puntoId: id correspondiente a la editorial buscada.
     * @return una editorial.
     */
    public TarjetaEntity find(Long puntoId) {
        LOGGER.log(Level.INFO, "Consultando editorial con id={0}", puntoId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from EditorialEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(TarjetaEntity.class, puntoId);
    }
    /**
     * Actualiza una editorial.
     *
     * @param tarjetaEntity: la editorial que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una editorial con los cambios aplicados.
     */
    public TarjetaEntity update(TarjetaEntity tarjetaEntity) {
        LOGGER.log(Level.INFO, "Actualizando tarjeta con id = {0}", tarjetaEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la editorial con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        LOGGER.log(Level.INFO, "Saliendo de actualizar la tarjeta con id = {0}", tarjetaEntity.getId());
        return em.merge(tarjetaEntity);
    }
    /**
     *
     * Borra una editorial de la base de datos recibiendo como argumento el id
     * de la editorial
     *
     * @param tarjetaId: id correspondiente a la editorial a borrar.
     */
    public void delete(Long tarjetaId) {
        LOGGER.log(Level.INFO, "Borrando editorial con id = {0}", tarjetaId);
        // Se hace uso de mismo método que esta explicado en public EditorialEntity find(Long id) para obtener la editorial a borrar.
        TarjetaEntity entity = em.find(TarjetaEntity.class, tarjetaId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from EditorialEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la editorial con id = {0}", tarjetaId);
    }
    
}


