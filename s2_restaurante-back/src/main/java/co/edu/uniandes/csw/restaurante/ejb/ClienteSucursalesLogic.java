package co.edu.uniandes.csw.restaurante.ejb;

import co.edu.uniandes.csw.restaurante.entities.ClienteEntity;
import co.edu.uniandes.csw.restaurante.entities.SucursalEntity;
import co.edu.uniandes.csw.restaurante.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.restaurante.persistence.ClientePersistence;
import co.edu.uniandes.csw.restaurante.persistence.SucursalPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Cliente y Sucursal.
 *
 * @author j.prieto
 */
@Stateless
public class ClienteSucursalesLogic {

    private static final Logger LOGGER = Logger.getLogger(ClienteSucursalesLogic.class.getName());

    @Inject
    private SucursalPersistence sucursalPersistence;

    @Inject
    private ClientePersistence clientePersistence;

    /**
     * Asocia un Book existente a un Author
     *
     * @param clientesId Identificador de la instancia de Cliente
     * @param sucursalId Identificador de la instancia de Sucursal
     * @return Instancia de SucursalEntity que fue asociada a Cliente
     */
    public SucursalEntity addSucursal(Long clientesId, Long sucursalId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle una sucursal al cliente con ID = {0}", clientesId);
        ClienteEntity clienteEntity = clientePersistence.find(clientesId);
        SucursalEntity sucursalEntity = sucursalPersistence.find(sucursalId);
        sucursalEntity.getClientes().add(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle una sucursal al cliente con ID = {0}", clientesId);
        return sucursalPersistence.find(clientesId);
    }

    /**
     * Obtiene una colección de instancias de SucursalEntity asociadas a una
     * instancia de Cliente
     *
     * @param clientesId Identificador de la instancia de Cliente
     * @return Colección de instancias de SucursalEntity asociadas a la instancia de
     * Cliente
     */
    public List<SucursalEntity> getSucursales(Long clientesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las sucursales del cliente con id = {0}", clientesId);
        return clientePersistence.find(clientesId).getSucursales();
    }

    /**
     * Obtiene una instancia de SucursalEnttiy asociada a una instancia de Cliente
     *
     * @param clientesId Identificador de la instancia de Cliente
     * @param sucursalesId Identificador de la instancia de Sucursal
     * @return La entidadd de Sucursal del Cliente
     * @throws BusinessLogicException Si la sucursal no está asociado al cliente
     */
    public SucursalEntity getSucursal(Long clientesId, Long sucursalesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la sucursal con id = {0} del cliente con id = " + clientesId, sucursalesId);
        List<SucursalEntity> sucursales = clientePersistence.find(clientesId).getSucursales();
        SucursalEntity sucursalEntity = sucursalPersistence.find(sucursalesId);
        int index = sucursales.indexOf(sucursalEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la sucursal con id = {0} del cliente con id = " + clientesId, sucursalesId);
        if (index >= 0) {
            return sucursales.get(index);
        }
        throw new BusinessLogicException("La sucursal no está asociada al cliente");
    }

    /**
     * Remplaza las instancias de Sucursal asociadas a una instancia de Cliente
     *
     * @param clienteId Identificador de la instancia de Author
     * @param sucursales Colección de instancias de SucursalEntity a asociar a instancia
     * de Cliente
     * @return Nueva colección de Sucursal asociada a la instancia de Cliente
     */
    public List<SucursalEntity> replaceSucursales(Long clienteId, List<SucursalEntity> sucursales) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar las sucursales asociadas al cliente con id = {0}", clienteId);
        ClienteEntity clienteEntity = clientePersistence.find(clienteId);
        List<SucursalEntity> sucursalList = sucursalPersistence.findAll();
        for (SucursalEntity sucursal : sucursalList) {
            if (sucursales.contains(sucursal)) {
                if (!sucursal.getClientes().contains(clienteEntity)) {
                    sucursal.getClientes().add(clienteEntity);
                }
            } else {
                sucursal.getClientes().remove(clienteEntity);
            }
        }
        clienteEntity.setSucursales(sucursales);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar las sucursales asociadas al cliente con id = {0}", clienteId);
        return clienteEntity.getSucursales();
    }

    /**
     * Desasocia una Sucursal existente de un Cliente existente
     *
     * @param clientesId Identificador de la instancia de Cliente
     * @param sucursalesId Identificador de la instancia de Sucursal
     */
    public void removeSucursal(Long clientesId, Long sucursalesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar una sucursal del cliente con id = {0}", clientesId);
        ClienteEntity clienteEntity = clientePersistence.find(clientesId);
        SucursalEntity sucursalEntity = sucursalPersistence.find(sucursalesId);
        sucursalEntity.getClientes().remove(clientesId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un libro del author con id = {0}", clientesId);
    }
}

