/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.uniandes.csw.restaurante.test.persistence;

import co.edu.uniandes.csw.restaurante.entities.DomicilioEntity;
import co.edu.uniandes.csw.restaurante.persistence.DomicilioPersistence;
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
public class DomicilioPeristenceTest {
    @Inject 
    private DomicilioPersistence domicilioPersistence;
    @PersistenceContext
    private EntityManager em;
     @Inject
    UserTransaction utx;
     
     private List<DomicilioEntity> data = new ArrayList<>();
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DomicilioEntity.class.getPackage())
                .addPackage(DomicilioPersistence.class.getPackage())
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
        em.createQuery("delete from DomicilioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            DomicilioEntity entity = factory.manufacturePojo(DomicilioEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createDomicilioTest(){
        PodamFactory factory = new PodamFactoryImpl();
        DomicilioEntity newEntity = factory.manufacturePojo(DomicilioEntity.class);
        DomicilioEntity result = domicilioPersistence.create(newEntity);
        
        Assert.assertNotNull(result);
        
        DomicilioEntity entity = em.find(DomicilioEntity.class, result.getId());
        Assert.assertEquals(newEntity.getPrecio(), entity.getPrecio());
        
    }
     /**
     * Prueba para consultar la lista de Domicilios.
     */
    @Test
    public void getDomiciliosTest() {
        List<DomicilioEntity> list = domicilioPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (DomicilioEntity ent : list) {
            boolean found = false;
            for (DomicilioEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una Domicilio.
     */
    @Test
    public void getDomicilioTest() {
        DomicilioEntity entity = data.get(0);
        DomicilioEntity newEntity = domicilioPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * Prueba para eliminar una Domicilio.
     */
    @Test
    public void deleteDomicilioTest() {
        DomicilioEntity entity = data.get(0);
        domicilioPersistence.delete(entity.getId());
        DomicilioEntity deleted = em.find(DomicilioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una Domicilio.
     */
    @Test
    public void updateDomicilioTest() {
        DomicilioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        DomicilioEntity newEntity = factory.manufacturePojo(DomicilioEntity.class);

        newEntity.setId(entity.getId());

        domicilioPersistence.update(newEntity);

        DomicilioEntity resp = em.find(DomicilioEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
}
