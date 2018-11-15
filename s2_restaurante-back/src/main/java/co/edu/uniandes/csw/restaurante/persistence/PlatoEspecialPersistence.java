/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.persistence;

import co.edu.uniandes.csw.restaurante.entities.PlatoEspecialEntity;
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
public class PlatoEspecialPersistence {

    
    private static final Logger LOGGER = Logger.getLogger(PlatoEspecialPersistence.class.getName());

    @PersistenceContext(unitName = "StarbugsPU")
    protected EntityManager em;

    public PlatoEspecialEntity create(PlatoEspecialEntity platoEntity) {
        LOGGER.log(Level.INFO, "Creando un nuevo plato");
        em.persist(platoEntity);
        LOGGER.log(Level.INFO, "Plato creado");
        return platoEntity;
    }

    public List<PlatoEspecialEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los platos");
        TypedQuery query = em.createQuery("select u from PlatoEspecialEntity u ", PlatoEspecialEntity.class);
        return query.getResultList();
    }

    public PlatoEspecialEntity find(Long platoId) {
        LOGGER.log(Level.INFO, "Consultando el plato con id={0}", platoId);
        return em.find(PlatoEspecialEntity.class, platoId);
    }
    
    public void delete(Long platoId) {
        LOGGER.log(Level.INFO, "Borrando el plato con id = {0}", platoId);
        PlatoEspecialEntity entity = em.find(PlatoEspecialEntity.class, platoId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar el plato con id = {0}", platoId);
    }
    
    public PlatoEspecialEntity update(PlatoEspecialEntity platoEntity) {
        LOGGER.log(Level.INFO, "Actualizando el plato con id = {0}", platoEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar el plato con id = {0}", platoEntity.getId());
        return em.merge(platoEntity);
    }
    
    public List<PlatoEspecialEntity> darPlatosDeUnaSucursal(Long sucursalId)
    {
        LOGGER.log(Level.INFO, "Consultando los platos de la sucursal n\u00famero {0}", sucursalId);
        TypedQuery query = em.createQuery("select u from PaltoEspecialEntity u where u.sucursal.id = :id", PlatoEspecialEntity.class);
         query.setParameter("id", sucursalId);
        return query.getResultList();
    }
    
}