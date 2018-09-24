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
package co.edu.uniandes.csw.restaurante.ejb;

import co.edu.uniandes.csw.restaurante.entities.PuntoEntity;
import co.edu.uniandes.csw.restaurante.entities.TarjetaEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.restaurante.persistence.TarjetaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 * Tarjeta.
 *
 * @author Juan Hidalgo
 */
@Stateless
public class TarjetaLogic {

    private static final Logger LOGGER = Logger.getLogger(TarjetaLogic.class.getName());

    @Inject
    private TarjetaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una Tarjeta en la persistencia.
     *
     * @param tarjetaEntity La entidad que representa la Tarjeta a
     * persistir.
     * @return La entiddad de la Tarjeta luego de persistirla.
     * @throws BusinessLogicException Si la Tarjeta a persistir ya existe.
     */
    public TarjetaEntity createTarjeta(TarjetaEntity tarjetaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la Tarjeta");
        
        //Verifica la regla de negocio de que una tarjeta siempre tenga cliente
        if (tarjetaEntity.getCliente() == null) {
            throw new BusinessLogicException("No se puede crear una tarjeta sin un cliente \"");
        }
//         Verifica la regla de negocio que dice que no puede haber dos Tarjetaes con el mismo nombre
        if (persistence.findByCliente(tarjetaEntity.getCliente()) != null) {
            throw new BusinessLogicException("Ya existe una Tarjeta con el cliente \"" + tarjetaEntity.getCliente()+"\"");
        }
        // Invoca la persistencia para crear la Tarjeta
        persistence.create(tarjetaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la Tarjeta");
        return tarjetaEntity;
    }

    /**
     *
     * Obtener todas las Tarjetaes existentes en la base de datos.
     *
     * @return una lista de Tarjetaes.
     */
    public List<TarjetaEntity> getTarjetas() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las Tarjetaes");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<TarjetaEntity> Tarjetas = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las Tarjetaes");
        return Tarjetas;
    }

    /**
     *
     * Obtener una Tarjeta por medio de su id.
     *
     * @param TarjetasId: id de la Tarjeta para ser buscada.
     * @return la Tarjeta solicitada por medio de su id.
     */
    public TarjetaEntity getTarjeta(Long TarjetasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la Tarjeta con id = {0}", TarjetasId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        TarjetaEntity TarjetaEntity = persistence.find(TarjetasId);
        if (TarjetaEntity == null) {
            LOGGER.log(Level.SEVERE, "La Tarjeta con el id = {0} no existe", TarjetasId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la Tarjeta con id = {0}", TarjetasId);
        return TarjetaEntity;
    }

    /**
     *
     * Actualizar una Tarjeta.
     *
     * @param TarjetasId: id de la Tarjeta para buscarla en la base de
     * datos.
     * @param TarjetaEntity: Tarjeta con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return la Tarjeta con los cambios actualizados en la base de datos.
     */
    public TarjetaEntity updateTarjeta(Long TarjetasId, TarjetaEntity TarjetaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la Tarjeta con id = {0}", TarjetasId);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        TarjetaEntity newEntity = persistence.update(TarjetaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la Tarjeta con id = {0}", TarjetaEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un Tarjeta
     *
     * @param tarjetasId: id de la Tarjeta a borrar
     * @throws BusinessLogicException Si la Tarjeta a eliminar tiene libros.
     */
    public void deleteTarjeta(Long tarjetasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la Tarjeta con id = {0}", tarjetasId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        
        
        if(getTarjeta(tarjetasId).getPuntos().isEmpty())
        {
            persistence.delete(tarjetasId);
            LOGGER.log(Level.INFO, "Termina proceso de borrar la Tarjeta con id = {0}", tarjetasId);  

        }
        else
            throw new BusinessLogicException("No se puede borrar la Tarjeta con id = " + tarjetasId + " porque tiene puntos asociados" + getTarjeta(tarjetasId).getPuntos().size() );

    }
}
