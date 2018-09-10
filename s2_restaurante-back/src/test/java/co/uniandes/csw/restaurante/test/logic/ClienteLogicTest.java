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
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
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
 * Pruebas de logica de Authors
 *
 * @author Juliana Prieto Arcila
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
//        em.createQuery("delete from PrizeEntity").executeUpdate();
//        em.createQuery("delete from BookEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
//        for (int i = 0; i < 3; i++) {
//            ClienteEntity entity = factory.manufacturePojo(AuthorEntity.class);
//            em.persist(entity);
//            entity.setBooks(new ArrayList<>());
//            data.add(entity);
//        }
//        ClienteEntity author = data.get(2);
//        BookEntity entity = factory.manufacturePojo(BookEntity.class);
//        entity.getAuthors().add(author);
//        em.persist(entity);
//        author.getBooks().add(entity);
//
//        PrizeEntity prize = factory.manufacturePojo(PrizeEntity.class);
//        prize.setAuthor(data.get(1));
//        em.persist(prize);
//        data.get(1).getPrizes().add(prize);
    }

    /**
     * Prueba para crear un Cliente.
     */
    @Test
    public void createClienteTest() {
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        ClienteEntity result = clienteLogic.createCliente(newEntity);
        Assert.assertNotNull(result);
        ClienteEntity entity = em.find(ClienteEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getMetodoPago(), entity.getMetodoPago());
    }

    /**
     * Prueba para consultar la lista de Clientes.
     */
    @Test
    public void getClientesTest() {
//        List<ClienteEntity> list = clienteLogic.getAuthors();
//        Assert.assertEquals(data.size(), list.size());
//        for (ClienteEntity entity : list) {
//            boolean found = false;
//            for (AuthorEntity storedEntity : data) {
//                if (entity.getId().equals(storedEntity.getId())) {
//                    found = true;
//                }
//            }
//            Assert.assertTrue(found);
//        }
    }

    /**
     * Prueba para consultar un Author.
     */
    @Test
    public void getAuthorTest() {
//        ClienteEntity entity = data.get(0);
//        ClienteEntity resultEntity = authorLogic.getAuthor(entity.getId());
//        Assert.assertNotNull(resultEntity);
//        Assert.assertEquals(entity.getId(), resultEntity.getId());
//        Assert.assertEquals(entity.getName(), resultEntity.getName());
//        Assert.assertEquals(entity.getBirthDate(), resultEntity.getBirthDate());
    }

    /**
     * Prueba para actualizar un Author.
     */
    @Test
    public void updateAuthorTest() {
//        ClienteEntity entity = data.get(0);
//        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
//
//        pojoEntity.setId(entity.getId());
//
//        authorLogic.updateAuthor(pojoEntity.getId(), pojoEntity);
//
//        ClienteEntity resp = em.find(ClienteEntity.class, entity.getId());
//
//        Assert.assertEquals(pojoEntity.getId(), resp.getId());
//        Assert.assertEquals(pojoEntity.getName(), resp.getName());
//        Assert.assertEquals(pojoEntity.getBirthDate(), resp.getBirthDate());
    }

    /**
     * Prueba para eliminar un Author
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteAuthorTest() throws BusinessLogicException {
//        ClienteEntity entity = data.get(0);
//        authorLogic.deleteAuthor(entity.getId());
//        ClienteEntity deleted = em.find(ClienteEntity.class, entity.getId());
//        Assert.assertNull(deleted);
    }

    /**
     * Prueba para eliminar un Author asociado a un libro
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteAuthorConLibroTest() throws BusinessLogicException {
//        authorLogic.deleteAuthor(data.get(2).getId());
    }

    /**
     * Prueba para eliminar un Author asociado a un premio
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteAuthorConPremioTest() throws BusinessLogicException {
        //authorLogic.deleteAuthor(data.get(1).getId());
    }
}
