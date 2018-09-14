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
import co.edu.uniandes.csw.restaurante.persistence.PuntoPersistence;
import co.edu.uniandes.csw.restaurante.persistence.TarjetaPersistence;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Tarjeta y Punto.
 *
 * @author Juan Hidalgo
 */
@Stateless
public class TarjetaPuntoLogic {

    private static final Logger LOGGER = Logger.getLogger(TarjetaPuntoLogic.class.getName());

    @Inject
    private PuntoPersistence puntoPersistence;

    @Inject
    private TarjetaPersistence tarjetaPersistence;

    /**
     * Agregar un Punto a la Tarjeta
     *
     * @param puntosId El id punto a guardar
     * @param tarjetasId El id de la Tarjeta en la cual se va a guardar el
     * punto.
     * @return El punto creado.
     */
    public PuntoEntity addPunto(Long puntosId, Long tarjetasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un punto a la Tarjeta con id = {0}", tarjetasId);
        TarjetaEntity tarjetaEntity = tarjetaPersistence.find(tarjetasId);
        PuntoEntity puntoEntity = puntoPersistence.find(puntosId);
        puntoEntity.setTarjeta(tarjetaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un punto a la Tarjeta con id = {0}", tarjetasId);
        return puntoEntity;
    }

    /**
     * Retorna todos los Puntos asociados a una Tarjeta
     *
     * @param tarjetasId El ID de la Tarjeta buscada
     * @return La lista de puntos de la Tarjeta
     */
    public List<PuntoEntity> getPuntos(Long tarjetasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los puntos asociados a la Tarjeta con id = {0}", tarjetasId);
        return tarjetaPersistence.find(tarjetasId).getPuntos();
    }

    

    /**
     * Borrar Puntos de una Tarjeta
     *
     * @param tarjetasId El id de la Tarjeta que se quiere actualizar.
     * @return La lista de puntos actualizada.
     */
    public List<PuntoEntity> borrarPuntos(Long tarjetasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la Tarjeta con id = {0}", tarjetasId);
        TarjetaEntity tarjetaEntity = tarjetaPersistence.find(tarjetasId);
        List<PuntoEntity> puntoList = tarjetaEntity.getPuntos();
        for (PuntoEntity punto : puntoList) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.YEAR, -1);
            if(punto.getFecha().before(c)){
                punto.setTarjeta(null);
                puntoList.remove(punto);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la Tarjeta con id = {0}", tarjetasId);
        return puntoList;
    }
}
