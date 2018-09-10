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
        LOGGER.log(Level.INFO, "Consultando todas las reservas");
        // Se crea un query para buscar todas las reservas en la base de datos.
        TypedQuery query = em.createQuery("select u from DomicilioEntity u", DomicilioEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de reservas.
        return query.getResultList();
    }
    
    
}


