/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.persistence;
import co.edu.uniandes.csw.restaurante.entities.MesaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
/**
 *
 * @author jp.romero12
 */
@Stateless
public class MesaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(MesaPersistence.class.getName());

    @PersistenceContext(unitName = "StarbugsPU")
    protected EntityManager em;

    public MesaEntity create(MesaEntity mesaEntity) {
        LOGGER.log(Level.INFO, "Creando una mesa nueva");
        em.persist(mesaEntity);
        LOGGER.log(Level.INFO, "Mesa creada");
        return mesaEntity;
    }

    public List<MesaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las mesas");
        TypedQuery query = em.createQuery("select u from MesaEntity u", MesaEntity.class);
        return query.getResultList();
    }

    public MesaEntity find(Long mesasId) {
        LOGGER.log(Level.INFO, "Consultando la mesa con id={0}", mesasId);
        return em.find(MesaEntity.class, mesasId);
    }
    
    public void delete(Long mesaId) {
        LOGGER.log(Level.INFO, "Borrando mesa con id = {0}", mesaId);
        MesaEntity entity = em.find(MesaEntity.class, mesaId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la mesa con id = {0}", mesaId);
    }
    
    public MesaEntity update(MesaEntity mesaEntity) {
        LOGGER.log(Level.INFO, "Actualizando mesa con id = {0}", mesaEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar la mesa con id = {0}", mesaEntity.getId());
        return em.merge(mesaEntity);
    }
    
}
