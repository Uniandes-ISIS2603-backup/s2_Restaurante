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
ClienteS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.uniandes.csw.restaurante.test.logic;


import co.edu.uniandes.csw.restaurante.ejb.PuntoLogic;
import co.edu.uniandes.csw.restaurante.entities.ClienteEntity;
import co.edu.uniandes.csw.restaurante.entities.PuntoEntity;
import co.edu.uniandes.csw.restaurante.entities.TarjetaEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.restaurante.persistence.PuntoPersistence;
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
 * Pruebas de logica de Puntos
 *
 * @Cliente Juan Hidalgo
 */
@RunWith(Arquillian.class)
public class PuntoLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private PuntoLogic puntoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<PuntoEntity> data = new ArrayList<PuntoEntity>();

    private List<TarjetaEntity> tarjetaData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PuntoEntity.class.getPackage())
                .addPackage(PuntoLogic.class.getPackage())
                .addPackage(PuntoPersistence.class.getPackage())
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
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            TarjetaEntity Tarjeta = factory.manufacturePojo(TarjetaEntity.class);
            em.persist(Tarjeta);
            tarjetaData.add(Tarjeta);
        }
        for (int i = 0; i < 3; i++) {
            PuntoEntity entity = factory.manufacturePojo(PuntoEntity.class);
            entity.setTarjeta(tarjetaData.get(0));

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Punto
     *
     * @throws co.edu.uniandes.csw.Puntostore.exceptions.BusinessLogicException
     */
    @Test
    public void createPuntoTest() throws BusinessLogicException {
        PuntoEntity newEntity = factory.manufacturePojo(PuntoEntity.class);
        newEntity.setTarjeta(tarjetaData.get(0));
        PuntoEntity result = puntoLogic.createPunto(newEntity);
        Assert.assertNotNull(result);
        PuntoEntity entity = em.find(PuntoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getFecha(), entity.getFecha());
    }

    
    /**
     * Prueba para crear un Punto con una Tarjeta que no existe.
     *
     * @throws co.edu.uniandes.csw.Puntostore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createPuntoTestConTarjetaInexistente() throws BusinessLogicException {
        PuntoEntity newEntity = factory.manufacturePojo(PuntoEntity.class);
        TarjetaEntity TarjetaEntity = new TarjetaEntity();
        TarjetaEntity.setId(Long.MIN_VALUE);
        newEntity.setTarjeta(TarjetaEntity);
        puntoLogic.createPunto(newEntity);
    }

    /**
     * Prueba para crear un Punto con Tarjeta en null.
     *
     * @throws co.edu.uniandes.csw.Puntostore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createPuntoTestConNullTarjeta() throws BusinessLogicException {
        PuntoEntity newEntity = factory.manufacturePojo(PuntoEntity.class);
        newEntity.setTarjeta(null);
        puntoLogic.createPunto(newEntity);
    }

    /**
     * Prueba para consultar la lista de Puntos.
     */
    @Test
    public void getPuntosTest() {
        List<PuntoEntity> list = puntoLogic.getPuntos();
        Assert.assertEquals(data.size(), list.size());
        for (PuntoEntity entity : list) {
            boolean found = false;
            for (PuntoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para eliminar un Punto.
     *
     * @throws co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException
     */
    @Test
    public void deletePuntoTest() throws BusinessLogicException {
        PuntoEntity entity = data.get(0);
        puntoLogic.deletePunto(entity.getId());
        PuntoEntity deleted = em.find(PuntoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
