/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.restaurante.resources;

import co.edu.uniandes.csw.restaurante.dtos.PuntoDTO;
import co.edu.uniandes.csw.restaurante.dtos.TarjetaDTO;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */
@Path("tarjeta")
@Produces("tarjeta/json")
@Consumes("tarjeta/json")
@RequestScoped
public class TarjetaResource {
    @POST
    public TarjetaDTO createTarjeta(TarjetaDTO tarjeta)
    {
        return tarjeta;
    }
}