/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.uniandes.csw.restaurante.test.persistence;

import co.edu.uniandes.csw.restaurante.entities.PuntoEntity;
import co.edu.uniandes.csw.restaurante.persistence.PuntoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Juan Hidalgo
 */
@RunWith(Arquillian.class)
public class PuntoPersistenceTest {
    @Inject 
    private PuntoPersistence puntoPersistence;
    @PersistenceContext
    private EntityManager em;
     @Inject
    UserTransaction utx;
     
     private List<PuntoEntity> data = new ArrayList<>();
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PuntoEntity.class.getPackage())
                .addPackage(PuntoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
     /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from PuntoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PuntoEntity entity = factory.manufacturePojo(PuntoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createPuntoTest(){
        PodamFactory factory = new PodamFactoryImpl();
        PuntoEntity newEntity = factory.manufacturePojo(PuntoEntity.class);
        PuntoEntity result = puntoPersistence.create(newEntity);
        
        Assert.assertNotNull(result);
        
        PuntoEntity entity = em.find(PuntoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getFecha(), entity.getFecha());
        
    }
     /**
     * Prueba para consultar la lista de Puntos.
     */
    @Test
    public void getPuntosTest() {
        List<PuntoEntity> list = puntoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PuntoEntity ent : list) {
            boolean found = false;
            for (PuntoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una Punto.
     */
    @Test
    public void getPuntoTest() {
        PuntoEntity entity = data.get(0);
        PuntoEntity newEntity = puntoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * Prueba para eliminar una Punto.
     */
    @Test
    public void deletePuntoTest() {
        PuntoEntity entity = data.get(0);
        puntoPersistence.delete(entity.getId());
        PuntoEntity deleted = em.find(PuntoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una Punto.
     */
    @Test
    public void updatePuntoTest() {
        PuntoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PuntoEntity newEntity = factory.manufacturePojo(PuntoEntity.class);

        newEntity.setId(entity.getId());

        puntoPersistence.update(newEntity);

        PuntoEntity resp = em.find(PuntoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
}
