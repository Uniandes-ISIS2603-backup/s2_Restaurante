/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.uniandes.csw.restaurante.test.logic;

import co.edu.uniandes.csw.restaurante.ejb.ClienteLogic;
import co.edu.uniandes.csw.restaurante.ejb.ClienteReservasLogic;
import co.edu.uniandes.csw.restaurante.entities.ClienteEntity;
import co.edu.uniandes.csw.restaurante.entities.ReservaEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.restaurante.persistence.ClientePersistence;
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
 * @author ja.ortega
 */
@RunWith(Arquillian.class)

public class ClienteReservasLogicTest {
    
     private PodamFactory factory = new PodamFactoryImpl();
     @Inject
     private ClienteLogic clienteLogic;
     @Inject
     private ClienteReservasLogic clienteReservasLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ClienteEntity> data = new ArrayList<ClienteEntity>();

    private List<ReservaEntity> reservasData = new ArrayList<ReservaEntity>();
    
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ClienteEntity.class.getPackage())
                .addPackage(ClienteLogic.class.getPackage())
                .addPackage(ClientePersistence.class.getPackage())
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
        em.createQuery("delete from ClienteEntity").executeUpdate();
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
            ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                reservasData.get(i).setCliente(entity);
            }
        }
    }
    
    /**
     * Prueba para asociar una Reserva existente a una Sucursal.
     */
    @Test
    public void addReservasTest() {
        ClienteEntity entity = data.get(0);
        ReservaEntity reservaEntity = reservasData.get(1);
        ReservaEntity response = clienteReservasLogic.addReserva(entity.getId(),reservaEntity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(reservaEntity.getId(), response.getId());
    }
    
    /**
     * Prueba para obtener una colección de instancias de reservas asociadas a una
     * instancia de Sucursal.
     */
    @Test
    public void getReservasTest() {
        List<ReservaEntity> list = clienteReservasLogic.getReservas(data.get(0).getId());

        Assert.assertEquals(1, list.size());
    }
    
     /**
     * Prueba para obtener una instancia de reserva asociada a una instancia de
     * Cliente.
     *
     * @throws BusinessLogicException
     */
    @Test
    public void getReservaTest() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        ReservaEntity reservaEntity = reservasData.get(0);
        ReservaEntity response = clienteReservasLogic.getReserva(entity.getId(), reservaEntity.getId());

        Assert.assertEquals(reservaEntity.getId(), response.getId());
        Assert.assertEquals(reservaEntity.getCantidadPersonas(), response.getCantidadPersonas());
        Assert.assertEquals(reservaEntity.getHora(), response.getHora());
      
    }
    
    /**
     * Prueba para obtener una instancia de Reserva asociada a una instancia
     * Cliente que no le pertenece.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void getReservaNoAsociadaTest() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        ReservaEntity reservaEntity = reservasData.get(1);
        clienteReservasLogic.getReserva(entity.getId(), reservaEntity.getId());
    }
 
}
