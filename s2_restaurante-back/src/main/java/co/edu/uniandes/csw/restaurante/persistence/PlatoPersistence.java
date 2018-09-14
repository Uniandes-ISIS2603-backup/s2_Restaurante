/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.persistence;

import co.edu.uniandes.csw.restaurante.entities.PlatoEntity;
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
public class PlatoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(PlatoPersistence.class.getName());

    @PersistenceContext(unitName = "StarbugsPU")
    protected EntityManager em;
    
     /**
     * Crea una Plato en la base de datos
     *
     * @param platoEntity objeto cliente que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PlatoEntity create(PlatoEntity platoEntity) {
        LOGGER.log(Level.INFO, "Creando un Plato nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la author en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(platoEntity);
        LOGGER.log(Level.INFO, "Plato creado");
        return platoEntity;
    }
    
    /**
     * Devuelve todos los Platos de la base de datos.
     *
     * @return una lista con todos los clientes que encuentre en la base de
     * datos, "select u from ClienteEntity u" es como un "select * from
     * ClienteEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<PlatoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los Platos");
        // Se crea un query para buscar todos los clientes en la base de datos.
        TypedQuery query = em.createQuery("select u from PlatoEntity u", PlatoEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de Plato.
        return query.getResultList();
    }
    
    /**
     * Busca si hay algun Plato id que se envía de argumento
     *
     * @param platoId: id correspondiente al Plato buscado.
     * @return un Plato.
     */
    public PlatoEntity find(Long platoId) {
        LOGGER.log(Level.INFO, "Consultando el Plato con id={0}", platoId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from AuthorEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(PlatoEntity.class, platoId);
    }

    /**
     * Actualiza un Plato.
     *
     * @param platoEntity: la calificacion que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un Plato con los cambios aplicados.
     */
    public PlatoEntity update(PlatoEntity platoEntity) {
        LOGGER.log(Level.INFO, "Actualizando el Plato con id={0}", platoEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la author con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(platoEntity);
    }

    /**
     * Borra un Plato de la base de datos recibiendo como argumento el id del
     *Plato.
     *
     * @param platoId: id correspondiente al Plato a borrar.
     */
    public void delete(Long platoId) {

        LOGGER.log(Level.INFO, "Borrando el calificacion con id={0}", platoId);
        // Se hace uso de mismo método que esta explicado en public AuthorEntity find(Long id) para obtener la author a borrar.
        PlatoEntity platoEntity = em.find(PlatoEntity.class, platoId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from AuthorEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(platoEntity);
    }
    
}
