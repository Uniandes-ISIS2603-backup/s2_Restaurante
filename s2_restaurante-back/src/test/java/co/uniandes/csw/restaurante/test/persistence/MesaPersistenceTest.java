/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.uniandes.csw.restaurante.test.persistence;
import co.edu.uniandes.csw.restaurante.entities.MesaEntity;
import co.edu.uniandes.csw.restaurante.persistence.MesaPersistence;
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
 * @author jp.romero12
 */
@RunWith(Arquillian.class)
public class MesaPersistenceTest {
    
     @Inject
    private MesaPersistence mesaPersistencia;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<MesaEntity> data = new ArrayList<MesaEntity>();

    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MesaEntity.class.getPackage())
                .addPackage(MesaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    
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

    
    private void clearData() {
        em.createQuery("delete from MesaEntity").executeUpdate();
    }

    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            MesaEntity entity = factory.manufacturePojo(MesaEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }

    
    @Test
    public void createMesaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        MesaEntity newEntity = factory.manufacturePojo(MesaEntity.class);
        MesaEntity result = mesaPersistencia.create(newEntity);
        
        Assert.assertNotNull(result);

        MesaEntity entity = em.find(MesaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
     
    
    @Test
    public void getMesasTest() {
        List<MesaEntity> list = mesaPersistencia.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (MesaEntity ent : list) {
            boolean found = false;
            for (MesaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getMesaTest() {
        MesaEntity entity = data.get(0);
        MesaEntity newEntity = mesaPersistencia.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

   
    @Test
    public void deleteMesaTest() {
        MesaEntity entity = data.get(0);
        mesaPersistencia.delete(entity.getId());
        MesaEntity deleted = em.find(MesaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    @Test
    public void updateMesaTest() {
        MesaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MesaEntity newEntity = factory.manufacturePojo(MesaEntity.class);

        newEntity.setId(entity.getId());

        mesaPersistencia.update(newEntity);

        MesaEntity resp = em.find(MesaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    
}
