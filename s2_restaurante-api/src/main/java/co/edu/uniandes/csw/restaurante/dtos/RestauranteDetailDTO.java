/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;

import java.util.ArrayList;

/**
 *
 * @author j.prieto
 */
public class RestauranteDetailDTO extends RestauranteDTO {

    /**
     * Lista de sucursales del restuarante
     */
    private ArrayList<SucursalDTO> sucursales;

    /**
     * Lista de clientes del restaurante
     */
    private ArrayList<ClienteDTO> clientes;

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity, RestauranteEntity a conventir a DTO
     */
    public RestauranteDetailDTO(RestauranteEntity entity) {
        //Crea un RestauranteDTO con el entity que llega
        super(entity);
        if (entity != null) {
            //Crea la lista de sucursales y le adiciona las que tiene el entity
            sucursales = new ArrayList<>();
            for (SucursalEntity entitySucursal : entity.getSucursales()) {
                sucursales.add(new SucursalDTO(entitySucursal));
            }
            //Crea la lista de clientes y le adiciona las que tiene el entity
            clientes = new ArrayList<>();
            for (ClienteEntity entityCliente : entity.getClientes()) {
                clientes.add(new ClienteDTO(entityCliente));
            }
        }
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return un RestauranteEntity con base en el DTO actual
     */
    @Override
    public RestauranteEntity toEntity() {
        RestauranteEntity entity = super.toEntity();
        //Agrega al nuevo Entity la lista de sucursals del DTO
        if (sucursales != null) {
            ArrayList<SucursalEntity> sucursalesEntity = new ArrayList<>();
            for (SucursalDTO sucusalDTO : sucursales) {
                sucusalesEntity.add(sucursalDTO.toEntity());
            }
            entity.setSucursales(sucursalesEntity);
        }
        //Agrega al nuevo Entity la lista de clientes del DTO 
        if (clientes != null) {
            ArrayList<ClienteEntity> clientesEntity = new ArrayList<>();
            for (ClienteDTO clienteDTO : clientes) {
                clientesEntity.add(clienteDTO.toEntity());
            }
            entity.setClientes(clientesEntity);
        }
        return entity;
    }
}
