/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.uniandes.csw.restaurante.test.logic;

import co.edu.uniandes.csw.restaurante.ejb.SucursalLogic;
import co.edu.uniandes.csw.restaurante.ejb.SucursalReservasLogic;
import co.edu.uniandes.csw.restaurante.entities.ReservaEntity;
import co.edu.uniandes.csw.restaurante.entities.SucursalEntity;
import co.edu.uniandes.csw.restaurante.persistence.SucursalPersistence;
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
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class SucursalReservasTest {
       
     private PodamFactory factory = new PodamFactoryImpl();
     @Inject 
     private SucursalReservasLogic sucursalReservasLogic;
     @Inject
     private SucursalLogic sucursalLogic;
     
    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<SucursalEntity> data = new ArrayList<SucursalEntity>();

    private List<ReservaEntity> reservasData = new ArrayList<ReservaEntity>();
     
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SucursalEntity.class.getPackage())
                .addPackage(SucursalLogic.class.getPackage())
                .addPackage(SucursalPersistence.class.getPackage())
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
        em.createQuery("delete from SucursalEntity").executeUpdate();
    }
    
     /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ReservaEntity reservas = factory.manufacturePojo(ReservaEntity.class);
            em.persist(reservas);
            reservasData.add(reservas);
        }
        for (int i = 0; i < 3; i++) {
            SucursalEntity entity = factory.manufacturePojo(SucursalEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                reservasData.get(i).setSucursal(entity);
            }
        }
    }
    
    /**
     * Prueba para asociar una Reserva existente a una Sucursal.
     */
    @Test
    public void addReservasTest() {
        SucursalEntity entity = data.get(0);
        ReservaEntity reservaEntity = reservasData.get(1);
        ReservaEntity response = sucursalReservasLogic.addReserva(entity.getId(),reservaEntity.getId());
        Assert.assertNotNull(response);
        Assert.assertEquals(reservaEntity.getId(), response.getId());
    }
    

}
