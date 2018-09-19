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

import co.edu.uniandes.csw.restaurante.ejb.TarjetaLogic;
import co.edu.uniandes.csw.restaurante.entities.PuntoEntity;
import co.edu.uniandes.csw.restaurante.entities.TarjetaEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.restaurante.persistence.TarjetaPersistence;
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
 * Pruebas de logica de Tarjetas
 *
 * @author Juan Hidalgo
 */
@RunWith(Arquillian.class)
public class TarjetaLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private TarjetaLogic tarjetaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<TarjetaEntity> data = new ArrayList<TarjetaEntity>();

    private List<PuntoEntity> puntosData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaEntity.class.getPackage())
                .addPackage(TarjetaLogic.class.getPackage())
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
        em.createQuery("delete from PuntoEntity").executeUpdate();
        em.createQuery("delete from TarjetaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PuntoEntity Puntos = factory.manufacturePojo(PuntoEntity.class);
            em.persist(Puntos);
            puntosData.add(Puntos);
        }
        for (int i = 0; i < 3; i++) {
            TarjetaEntity entity = factory.manufacturePojo(TarjetaEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                puntosData.get(i).setTarjeta(entity);
            }
        }
    }

    /**
     * Prueba para crear un Tarjeta.
     *
     * @throws co.edu.uniandes.csw.Puntostore.exceptions.BusinessLogicException
     */
    @Test
    public void createTarjetaTest() throws BusinessLogicException {
        TarjetaEntity newEntity = factory.manufacturePojo(TarjetaEntity.class);
        TarjetaEntity result = tarjetaLogic.createTarjeta(newEntity);
        Assert.assertNotNull(result);
        TarjetaEntity entity = em.find(TarjetaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getClienteID(), entity.getClienteID());
    }

    /**
     * Prueba para crear un Tarjeta con el mismo nombre de un Tarjeta que ya
     * existe.
     *
     * @throws co.edu.uniandes.csw.Puntostore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createTarjetaConMismoNombreTest() throws BusinessLogicException {
        TarjetaEntity newEntity = factory.manufacturePojo(TarjetaEntity.class);
        newEntity.setClienteID(data.get(0).getClienteID());
        tarjetaLogic.createTarjeta(newEntity);
    }

    /**
     * Prueba para consultar la lista de Tarjetas.
     */
    @Test
    public void getTarjetasTest() {
        List<TarjetaEntity> list = tarjetaLogic.getTarjetas();
        Assert.assertEquals(data.size(), list.size());
        for (TarjetaEntity entity : list) {
            boolean found = false;
            for (TarjetaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Tarjeta.
     */
    @Test
    public void getTarjetaTest() {
        TarjetaEntity entity = data.get(0);
        TarjetaEntity resultEntity = tarjetaLogic.getTarjeta(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getClienteID(), resultEntity.getClienteID());
    }

    /**
     * Prueba para actualizar un Tarjeta.
     */
    @Test
    public void updateTarjetaTest() {
        TarjetaEntity entity = data.get(0);
        TarjetaEntity pojoEntity = factory.manufacturePojo(TarjetaEntity.class);
        pojoEntity.setId(entity.getId());
        tarjetaLogic.updateTarjeta(pojoEntity.getId(), pojoEntity);
        TarjetaEntity resp = em.find(TarjetaEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getClienteID(), resp.getClienteID());
    }

    /**
     * Prueba para eliminar un Tarjeta.
     *
     * @throws co.edu.uniandes.csw.Puntostore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteTarjetaTest() throws BusinessLogicException {
        TarjetaEntity entity = data.get(1);
        tarjetaLogic.deleteTarjeta(entity.getId());
        TarjetaEntity deleted = em.find(TarjetaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para eliminar un Tarjeta con Puntos asociados.
     *
     * @throws co.edu.uniandes.csw.Puntostore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteTarjetaConPuntosAsociadosTest() throws BusinessLogicException {
        TarjetaEntity entity = data.get(0);
        tarjetaLogic.deleteTarjeta(entity.getId());
    }
}
