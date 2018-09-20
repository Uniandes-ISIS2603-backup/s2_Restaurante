package co.edu.uniandes.csw.restaurante.ejb;


import co.edu.uniandes.csw.restaurante.entities.ClienteEntity;
import co.edu.uniandes.csw.restaurante.entities.SucursalEntity;
import co.edu.uniandes.csw.restaurante.persistence.ClientePersistence;
import co.edu.uniandes.csw.restaurante.persistence.SucursalPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Sucursal y Cliente.
 *
 * @author j.prieto
 */
@Stateless
public class SucursalClientesLogic {

    private static final Logger LOGGER = Logger.getLogger(SucursalClientesLogic.class.getName());

    @Inject
    private SucursalPersistence sucursalPersistence;

    @Inject
    private ClientePersistence clientePersistence;

    /**
     * Asocia un Cliente existente a un Book
     *
     * @param sucursalesId Identificador de la instancia de Sucursal
     * @param clientesId Identificador de la instancia de Cliente
     * @return Instancia de ClienteEntity que fue asociada a Sucursal
     */
    public ClienteEntity addCliente(Long sucursalesId, Long clientesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un cliente a la sucursal con id = {0}", sucursalesId);
        ClienteEntity clienteEntity = clientePersistence.find(clientesId);
        SucursalEntity sucursalEntity = sucursalPersistence.find(sucursalesId);
        sucursalEntity.getClientes().add(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un cliente a la sucursal con id = {0}", sucursalesId);
        return clientePersistence.find(clientesId);
    }

    /**
     * Obtiene una colección de instancias de ClienteEnttiy asociadas a una
     * instancia de Sucursal
     *
     * @param sucursalesId Identificador de la instancia de Sucursal
     * @return Colección de instancias de ClienteEntity asociadas a la instancia
     * de Sucursal
     */
    public List<ClienteEntity> getClientes(Long sucursalesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los clientes de la sucursal con id = {0}", sucursalesId);
        return sucursalPersistence.find(sucursalesId).getClientes();
    }

    /**
     * Obtiene una instancia de ClienteEntity asociada a una instancia de Sucursal
     *
     * @param sucursalesId Identificador de la instancia de Sucursal
     * @param clientesId Identificador de la instancia de Cliente
     * @return La entidad del Cliente asociada al libro
     */
    public ClienteEntity getCliente(Long sucursalesId, Long clientesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un cliente de la sucursal con id = {0}", sucursalesId);
        List<ClienteEntity> clientes = sucursalPersistence.find(sucursalesId).getClientes();
        ClienteEntity clienteEntity = clientePersistence.find(clientesId);
        int index = clientes.indexOf(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar un cliente de la sucursal con id = {0}", sucursalesId);
        if (index >= 0) {
            return clientes.get(index);
        }
        return null;
    }

    /**
     * Remplaza las instancias de Cliente asociadas a una instancia de Sucursal
     *
     * @param sucursalesId Identificador de la instancia de Sucursal
     * @param list Colección de instancias de CilenteEntity a asociar a instancia
     * de Sucursal
     * @return Nueva colección de ClienteEntity asociada a la instancia de Sucursal
     */
    public List<ClienteEntity> replaceClientes(Long sucursalesId, List<ClienteEntity> list) {
 LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los clientes de la sucursal con id = {0}", sucursalesId);
        SucursalEntity sucursalEntity = sucursalPersistence.find(sucursalesId);
        sucursalEntity.setClientes(list);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los clientes de la sucursal con id = {0}", sucursalesId);
        return sucursalPersistence.find(sucursalesId).getClientes();
    }

    /**
     * Desasocia un Cliente existente de una Sucursal existente
     *
     * @param sucursalesId Identificador de la instancia de Sucursal
     * @param clientesId Identificador de la instancia de Cliente
     */
    public void removeCliente(Long sucursalesId, Long clientesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un cliente de la sucursal con id = {0}", sucursalesId);
        ClienteEntity clienteEntity = clientePersistence.find(clientesId);
        SucursalEntity sucursalEntity = sucursalPersistence.find(sucursalesId);
        sucursalEntity.getClientes().remove(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un cliente de la sucursal con id = {0}", sucursalesId);
    }
}
