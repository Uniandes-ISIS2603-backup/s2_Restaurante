/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.uniandes.csw.restaurante.test.persistence;

import co.edu.uniandes.csw.restaurante.entities.PuntoEntity;
import co.edu.uniandes.csw.restaurante.persistence.PuntoPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
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
    public PuntoPersistenceTest() {
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
}
