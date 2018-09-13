/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.uniandes.csw.restaurante.test.logic;

import co.edu.uniandes.csw.restaurante.entities.ReservaEntity;
import co.edu.uniandes.csw.restaurante.persistence.ReservaPersistence;
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
 * @author Juan Ortega
 */
@RunWith(Arquillian.class)
public class ReservaLogicTest {
    
    @Inject
    private ReservaPersistence reservaPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<ReservaEntity> data = new ArrayList<ReservaEntity>();
    PodamFactory factory = new PodamFactoryImpl();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReservaEntity.class.getPackage())
                .addPackage(ReservaPersistence.class.getPackage())
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
        em.createQuery("delete from ReservaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ReservaEntity entity = factory.manufacturePojo(ReservaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear una Reserva.
     */
    @Test
    public void createReservaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        ReservaEntity result = reservaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ReservaEntity entity = em.find(ReservaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getCantidadPersonas(), entity.getCantidadPersonas());
        Assert.assertEquals(newEntity.getHora(), entity.getHora());
      
    }

    /**
     * Prueba para consultar la lista de Books.
     */
    @Test
    public void getReservasTest() {
        List<ReservaEntity> list = reservaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ReservaEntity ent : list) {
            boolean found = false;
            for (ReservaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Book.
     */
    @Test
    public void getReservaTest() {
        ReservaEntity entity = data.get(0);
        ReservaEntity newEntity = reservaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getCantidadPersonas(), entity.getCantidadPersonas());
        Assert.assertEquals(newEntity.getHora(), entity.getHora());
       
    }

    /**
     * Prueba para eliminar unaReserva.
     */
    @Test
    public void deleteReservaTest() {
        ReservaEntity entity = data.get(0);
        reservaPersistence.delete(entity.getId());
        ReservaEntity deleted = em.find(ReservaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una Reserva.
     */
    @Test
    public void updateReservaTest() {
        ReservaEntity entity = data.get(0);
       
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        
        newEntity.setId(entity.getId());
        
        reservaPersistence.update(newEntity);
        
        ReservaEntity resp = em.find(ReservaEntity.class, entity.getId());
        
        Assert.assertEquals(newEntity.getCantidadPersonas(), resp.getCantidadPersonas());
        Assert.assertEquals(newEntity.getHora(), resp.getHora());
        

    }
    
    

 
    
}
