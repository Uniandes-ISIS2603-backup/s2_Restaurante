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
package co.uniandes.csw.restaurante.test.logic;

import co.edu.uniandes.csw.restaurante.ejb.ClienteLogic;
import co.edu.uniandes.csw.restaurante.entities.ClienteEntity;
import co.edu.uniandes.csw.restaurante.entities.DomicilioEntity;
import co.edu.uniandes.csw.restaurante.entities.ReservaEntity;
import co.edu.uniandes.csw.restaurante.entities.SucursalEntity;
import co.edu.uniandes.csw.restaurante.entities.TarjetaEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.restaurante.persistence.ClientePersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * Pruebas de logica de Cliente
 *
 * @author ISIS2603
 */
@RunWith(Arquillian.class)
public class ClienteLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ClienteLogic clienteLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

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
    public void createClienteTest()  {
        try {
            ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
            ClienteEntity result = clienteLogic.createCliente(newEntity);
            Assert.assertNotNull(result);
            ClienteEntity entity = em.find(ClienteEntity.class, result.getId());
            Assert.assertEquals(newEntity.getId(), entity.getId());
            Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
            Assert.assertEquals(newEntity.getMetodoPago(), entity.getMetodoPago());
        } catch (BusinessLogicException ex) {
           //nothing
        }
    }

    /**
     * Prueba para consultar la lista de Clientes.
     */
    @Test
    public void getClientesTest() {
        List<ClienteEntity> list = clienteLogic.getClientes();
        Assert.assertEquals(data.size(), list.size());
        for (ClienteEntity entity : list) {
            boolean found = false;
            for (ClienteEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
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
    public void getClienteTest() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        ClienteEntity resultEntity = clienteLogic.getCliente(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getMetodoPago(), resultEntity.getMetodoPago());
    }

    /**
     * Prueba para actualizar un Cliente.
     */
    @Test
    public void updateClienteTest() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);

        pojoEntity.setId(entity.getId());

        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);

        ClienteEntity resp = em.find(ClienteEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getMetodoPago(), resp.getMetodoPago());
    }

    /**
     * Prueba para eliminar un Cliente
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteClienteTest() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        clienteLogic.deleteCliente(entity.getId());
        ClienteEntity deleted = em.find(ClienteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}