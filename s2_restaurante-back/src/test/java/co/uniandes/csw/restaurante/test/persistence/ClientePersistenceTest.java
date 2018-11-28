/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.uniandes.csw.restaurante.test.persistence;

import co.edu.uniandes.csw.restaurante.entities.ClienteEntity;
import co.edu.uniandes.csw.restaurante.persistence.ClientePersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Pruebas de persistencia de Cliente
 *
 * @author j.prieto
 */
@RunWith(Arquillian.class)
public class ClientePersistenceTest {

    @Inject
    private ClientePersistence clientePersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<ClienteEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ClienteEntity.class.getPackage())
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
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Cliente.
     */
    @Test
    public void createClienteTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        ClienteEntity result = clientePersistence.create(newEntity);

        Assert.assertNotNull(result);

        ClienteEntity entity = em.find(ClienteEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para consultar la lista de Authors.
     */
    @Test
    public void getClientesTest() {
        List<ClienteEntity> list = clientePersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ClienteEntity ent : list) {
            boolean found = false;
            for (ClienteEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Cliente.
     */
    
    @Test
    public void getClienteTest() {
        ClienteEntity entity = data.get(0);
        ClienteEntity newEntity = clientePersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * Prueba para actualizar un Cliente.
     */
    @Test
    public void updateClienteTest(){
    ClienteEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);

        newEntity.setId(entity.getId());

        clientePersistence.update(newEntity);

        ClienteEntity resp = em.find(ClienteEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

    /**
     * Prueba para eliminar un Cliente.
     */
    @Test
    public void deleteClienteTest() {
        ClienteEntity entity = data.get(0);
        clientePersistence.delete(entity.getId());
        ClienteEntity deleted = em.find(ClienteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
