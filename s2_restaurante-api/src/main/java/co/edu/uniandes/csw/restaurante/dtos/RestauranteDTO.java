/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.dtos;

/**
 *
 * @author j.prieto
 */
public class RestauranteDTO {

    /**
     * Nombre del restaurante
     */
    private String nombre;

    /**
     * Retorna el nombre del restaurante
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Modifica el nombre del restaurante
     */
    public void setNombre(String pNombre) {
        this.nombre = pNombre;
    }

    /**
     * Construye un RestauranteDTO a partir de un RestauranteEntity
     *
     * @param entity RestauranteEntity
     */
//    public RestauranteDTO(RestauranteEntity entity) {
//        this.nombre = entity.getNombre();
//    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
//    public RestauranteEntity toEntity() {
//        RestauranteEntity entity = new RestauranteEntity();
//        entity.setNombre(this.nombre);
//        return entity;
//    }
}
