/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.ejb;

import co.edu.uniandes.csw.restaurante.entities.MesaEntity;
import co.edu.uniandes.csw.restaurante.entities.ReservaEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.restaurante.persistence.ClientePersistence;
import co.edu.uniandes.csw.restaurante.persistence.MesaPersistence;
import co.edu.uniandes.csw.restaurante.persistence.ReservaPersistence;
import co.edu.uniandes.csw.restaurante.persistence.SucursalPersistence;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Ortega
 */

@Stateless
public class ReservaLogic {
    
     private static final Logger LOGGER = Logger.getLogger(ReservaLogic.class.getName());

    @Inject
    private ReservaPersistence persistence;
    
    @Inject
    private ClientePersistence clientePersistence;
    
    @Inject
    private SucursalPersistence sucursalPersistence;
    
    @Inject
    private MesaPersistence mesaPersistence;
    /**
     * Se encarga de crear una Reservan en la base de datos.
     *
     * @param reservaEntity Objeto de ReservaEntity con los datos nuevos
     * @return Objeto de ReservaEntity con los datos nuevos y su ID.
     */
    public ReservaEntity createReserva(ReservaEntity reservaEntity) throws BusinessLogicException {
        
        ReservaEntity newReservaEntity = new ReservaEntity();
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la reserva");
      
        if(reservaEntity == null || reservaEntity.getCantidadPersonas() == null || reservaEntity.getHora() == null)
        {
            throw new BusinessLogicException("La información que se recibió no es suficiente para crear la reserva");
        }
        
        Calendar hora = new GregorianCalendar();
        hora.setTime(reservaEntity.getHora());
        
        if( !( (hora.get(Calendar.HOUR_OF_DAY)>=12 && hora.get(Calendar.HOUR_OF_DAY)<15) || (hora.get(Calendar.HOUR_OF_DAY)>=18 && hora.get(Calendar.HOUR_OF_DAY)<22) ) )
        {
           throw new BusinessLogicException("El restaurante solo atiende de 12M a 3 PM y de 6PM A 10PM");
        }
            
        if (reservaEntity.getCliente()== null || clientePersistence.find(reservaEntity.getCliente().getId()) == null) {
          throw new BusinessLogicException("El cliente es inválido");
        }
        
        if (reservaEntity.getSucursal()== null || sucursalPersistence.find(reservaEntity.getSucursal().getId()) == null) {
          throw new BusinessLogicException("La sucursal es inválida");
        }
        
        if (reservaEntity.getMesa()== null || mesaPersistence.find(reservaEntity.getMesa().getId()) == null) {
          throw new BusinessLogicException("La mesa es inválida");
        }
        
        
        if(persistence.sePuedeReservar(reservaEntity))
        {
            newReservaEntity = persistence.create(reservaEntity);
            LOGGER.log(Level.INFO, "Termina proceso de creación de la reserva");
           
        }
        
        else{
            throw new BusinessLogicException("Ya existe una reserva con la misma sucursal, en la misma mesa y a la misma hora");
        }
         return newReservaEntity;      
    }
    
    /**
     * Obtiene la lista de las reservas.
     *
     * @return Colección de objetos de ReservaEntity.
     */
    public List<ReservaEntity> getReservas() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las reservas");
        List<ReservaEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las reservas");
        return lista;
    }
    
    /**
     * Obtiene los datos de una reserva a partir de su ID.
     *
     * @param reservaId Identificador de la instancia a consultar
     * @return Instancia de ReservaEntity.
     */
    public ReservaEntity getReserva(Long reservaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la reserva con id = {0}", reservaId);
        ReservaEntity reservaEntity = persistence.find(reservaId);
        if (reservaEntity == null) {
            LOGGER.log(Level.SEVERE, "La reserva con el id = {0} no existe", reservaId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la reserva con id = {0}", reservaId);
        return reservaEntity;
    }
    
        /**
     * Actualiza la información de una reserva.
     *
     * @param reservaId Identificador de la instancia a actualizar
     * @param reservaEntity Instancia de ReservaEntity con los nuevos datos.
     * @return Instancia de ReservaEntity con los datos actualizados.
     */
    public ReservaEntity updateReserva(Long reservaId, ReservaEntity reservaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la reserva id = {0}", reservaId);
        ReservaEntity newReservaEntity = persistence.update(reservaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la reserva con id = {0}", reservaId);
        return newReservaEntity;
    }
    
     /**
     * Eliminar una reserva por ID
     *
     * @param reservasId El ID d la reserva a eliminar
     * 
     */
    public void deleteReserva(Long reservasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la reserva con id = {0}", reservasId);
      
        persistence.delete(reservasId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la reserva con id = {0}", reservasId);
    }
}
