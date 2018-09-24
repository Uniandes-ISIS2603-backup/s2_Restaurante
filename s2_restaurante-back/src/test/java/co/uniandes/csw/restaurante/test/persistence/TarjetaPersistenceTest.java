/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.uniandes.csw.restaurante.test.persistence;

import co.edu.uniandes.csw.restaurante.entities.TarjetaEntity;
import co.edu.uniandes.csw.restaurante.persistence.TarjetaPersistence;
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
public class TarjetaPersistenceTest {
    @Inject 
    private TarjetaPersistence tarjetaPersistence;
    @PersistenceContext
    private EntityManager em;
     @Inject
    UserTransaction utx;
     
     private List<TarjetaEntity> data = new ArrayList<>();
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaEntity.class.getPackage())
                .addPackage(TarjetaPersistence.class.getPackage())
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
        em.createQuery("delete from TarjetaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TarjetaEntity entity = factory.manufacturePojo(TarjetaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createTarjetaTest(){
        PodamFactory factory = new PodamFactoryImpl();
        TarjetaEntity newEntity = factory.manufacturePojo(TarjetaEntity.class);
        TarjetaEntity result = tarjetaPersistence.create(newEntity);
        
        Assert.assertNotNull(result);
        
        TarjetaEntity entity = em.find(TarjetaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getCliente(), entity.getCliente());
        
    }
     /**
     * Prueba para consultar la lista de Tarjetas.
     */
    @Test
    public void getTarjetasTest() {
        List<TarjetaEntity> list = tarjetaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (TarjetaEntity ent : list) {
            boolean found = false;
            for (TarjetaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una Tarjeta.
     */
    @Test
    public void getTarjetaTest() {
        TarjetaEntity entity = data.get(0);
        TarjetaEntity newEntity = tarjetaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * Prueba para eliminar una Tarjeta.
     */
    @Test
    public void deleteTarjetaTest() {
        TarjetaEntity entity = data.get(0);
        tarjetaPersistence.delete(entity.getId());
        TarjetaEntity deleted = em.find(TarjetaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una Tarjeta.
     */
    @Test
    public void updateTarjetaTest() {
        TarjetaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TarjetaEntity newEntity = factory.manufacturePojo(TarjetaEntity.class);

        newEntity.setId(entity.getId());

        tarjetaPersistence.update(newEntity);

        TarjetaEntity resp = em.find(TarjetaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
}
