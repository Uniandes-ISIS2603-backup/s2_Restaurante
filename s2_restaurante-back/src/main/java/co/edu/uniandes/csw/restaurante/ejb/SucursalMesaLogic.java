/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.ejb;

import co.edu.uniandes.csw.restaurante.entities.SucursalEntity;
import co.edu.uniandes.csw.restaurante.entities.MesaEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.restaurante.persistence.SucursalPersistence;
import co.edu.uniandes.csw.restaurante.persistence.MesaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Sucursal y Mesa.
 * @author Juan Hidalgo
 * */
public class SucursalMesaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(SucursalMesaLogic.class.getName());

    @Inject
    private MesaPersistence MesaPersistence;

    @Inject
    private SucursalPersistence SucursalPersistence;

    
       /**
     * Asocia una Mesa existente a un Sucursal
     *
     * @param SucursalsId Identificador de la instancia de Clietne
     * @param MesasId Identificador de la instancia de Mesa
     * @return Instancia de MesaEntity que fue asociada a un Sucursal
     */
    public MesaEntity addMesa(Long SucursalsId, Long MesasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle una Mesa al Sucursal con id = {0}", SucursalsId);
        SucursalEntity SucursalEntity = SucursalPersistence.find(SucursalsId);
        MesaEntity MesaEntity = MesaPersistence.find(MesasId);
        MesaEntity.setSucursal(SucursalEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle una Mesa al Sucursal con id = {0}", SucursalsId);
        return MesaPersistence.find(MesasId);
    }
    
       /**
     * Retorna una Mesa asociada a un Sucursal
     *
     * @param SucursalsId El id del Sucursal a buscar.
     * @param MesasId El id de la Mesa a buscar
     * @return La Mesa encontrada del Sucursal dado.
     * @throws BusinessLogicException Si la Mesa no se encuentra asociada al Sucursal 
     */
    public MesaEntity getMesa(Long SucursalsId, Long MesasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la Mesa con id = {0} del Sucursal con id = " + SucursalsId, MesasId);
        MesaEntity MesaEntity = MesaPersistence.find(MesasId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la Mesa con id = {0} del Sucursal con id = " + SucursalsId, MesasId);
        if(MesaEntity ==null)
        {
            return MesaEntity;
        }
        throw new BusinessLogicException("La Mesa no está asociada a el Sucursal");
    }
    /**
     * Desasocia una Mesa existente de un Sucursal existente
     *
     * @param SucursalsId Identificador de la instancia de Sucursal
     * @param MesasId Identificador de la instancia de la Mesa
     */
    public void removeMesa(Long SucursalsId, Long MesasId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una Mesa con id = {0}", SucursalsId);
        SucursalEntity SucursalEntity = SucursalPersistence.find(SucursalsId);
        MesaEntity MesaEntity = MesaPersistence.find(MesasId);
        SucursalEntity.getMesas().remove(MesaEntity);
        MesaPersistence.delete(MesasId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar una Mesa del Sucursal con id = {0}", SucursalsId);
    }
    
    
}
