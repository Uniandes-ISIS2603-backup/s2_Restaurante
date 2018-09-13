/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.persistence;
import co.edu.uniandes.csw.restaurante.entities.SucursalEntity;
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
public class SucursalPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(SucursalPersistence.class.getName());

    @PersistenceContext(unitName = "StarbugsPU")
    protected EntityManager em;

    public SucursalEntity create(SucursalEntity sucursalEntity) {
        LOGGER.log(Level.INFO, "Creando una sucursal nueva");
        em.persist(sucursalEntity);
        LOGGER.log(Level.INFO, "Sucursal creada");
        return sucursalEntity;
    }

    public List<SucursalEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las sucursales");
        TypedQuery query = em.createQuery("select u from SucursalEntity u", SucursalEntity.class);
        return query.getResultList();
    }

    public SucursalEntity find(Long sucursalesId) {
        LOGGER.log(Level.INFO, "Consultando la sucursal con id={0}", sucursalesId);
        return em.find(SucursalEntity.class, sucursalesId);
    }

    public SucursalEntity update(SucursalEntity sucursalEntity) {
        LOGGER.log(Level.INFO, "Actualizando la sucursal con id={0}", sucursalEntity.getId());
        return em.merge(sucursalEntity);
    }

    public void delete(Long sucursalId) {

        LOGGER.log(Level.INFO, "Borrando la sucursal con id={0}", sucursalId);
        SucursalEntity sucursalEntity = em.find(SucursalEntity.class, sucursalId);
        em.remove(sucursalEntity);
    }
}