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
 * Clase que maneja la persistencia para Calificacion. Se conecta a través del Entity
 * Manager de javax.persistance con la base de datos SQL.
 *
 * @author j.prieto
 */
@Stateless
public class CalificacionPersistence {

    private static final Logger LOGGER = Logger.getLogger(CalificacionPersistence.class.getName());

    @PersistenceContext(unitName = "StarbugsPU")
    protected EntityManager em;

    /**
     * Crear una calificacion
     *
     * Crea una nueva calificacion con la información recibida en la entidad.
     *
     * @param calificacionEntity La entidad que representa la nueva calificacion
     * @return La entidad creada
     */
    public CalificacionEntity create(CalificacionEntity calificacionEntity) {
        LOGGER.log(Level.INFO, "Creando una calificacion nueva");
        em.persist(calificacionEntity);
        LOGGER.log(Level.INFO, "Calificacion creada");
        return calificacionEntity;
    }

    /**
     * Actualizar una calificacion
     *
     * Actualiza la entidad que recibe en la base de datos
     *
     * @param calificacionEntity La entidad actualizada que se desea guardar
     * @return La entidad resultante luego de la actualización
     */
    public CalificacionEntity update(CalificacionEntity calificacionEntity) {
        LOGGER.log(Level.INFO, "Actualizando calificacion con id = {0}", calificacionEntity.getId());
        return em.merge(calificacionEntity);
    }

    /**
     * Eliminar una calificacion
     *
     * Elimina la calificacion asociada al ID que recibe
     *
     * @param calificacionId El ID de la calificacion que se desea borrar
     */
    public void delete(Long calificacionId) {
        LOGGER.log(Level.INFO, "Borrando calificacion con id = {0}", calificacionId);
        CalificacionEntity calificacionEntity = em.find(CalificacionEntity.class, calificacionId);
        em.remove(calificacionEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar El calificacion con id = {0}", calificacionId);
    }

    /**
     * Buscar una calificacion
     *
     * Busca si hay alguna calificacion asociada a un libro y con un ID específico
     *
     * @param sucursalId El ID de la sucursal con respecto al cual se busca
     * @param calificacionId El ID de la calificacion buscada
     * @return La calificacion encontrada o null. Nota: Si existe una o más calificacions
     * devuelve siempre la primera que encuentra
     */
    public CalificacionEntity find(Long sucursalId, Long calificacionId) {
        LOGGER.log(Level.INFO, "Consultando el calificacion con id = {0} de la sucursal con id = " + sucursalId, calificacionId);
        TypedQuery<CalificacionEntity> q = em.createQuery("select p from CalificacionEntity p where (p.sucursal.id = :sucursalId) and (p.id = :calificacionId)", CalificacionEntity.class);
        q.setParameter("sucursalid", sucursalId);
        q.setParameter("calificacionId", calificacionId);
        List<CalificacionEntity> results = q.getResultList();
        CalificacionEntity calificacion = null;
        if (results == null) {
            calificacion = null;
        } else if (results.isEmpty()) {
            calificacion = null;
        } else if (results.size() >= 1) {
            calificacion = results.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar el calificacion con id = {0} de la sucursal con id =" + sucursalId, calificacionId);
        return calificacion;
    }
}
