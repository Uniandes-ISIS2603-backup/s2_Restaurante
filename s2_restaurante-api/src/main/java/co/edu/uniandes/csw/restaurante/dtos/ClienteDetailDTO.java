///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.edu.uniandes.csw.restaurante.dtos;
//
//import java.util.ArrayList;
//
///**
// *
// * @author j.prieto
// */
//public class ClienteDetailDTO extends ClienteDTO {
//
//    /**
//     * Tarjeta del cliente
//     */
//    private TarjetaDTO tarjeta;
//
//    /**
//     * Calificaci{on hecha por el cliente
//     */
//    private ArrayList<CalificacionDTO> calificaciones;
//
//    /**
//     * Lista con las reservas hechas por el cliente
//     */
//    private ArrayList<ReservaDTO> reservas;
//
//    /**
//     * Lista con los domicilios hechos por el cliente
//     */
//    private ArrayList<DomicilioDTO> domicilios;
//
//    /**
//     * Constructor para transformar un Entity a un DTO
//     *
//     * @param entity, ClienteEntity a converit a DTO
//     */
//    public ClienteDeatilDTO(ClienteEntity entity) {
//        //Crea un RestauranteDTO con el entity que llega
//        super(entity);
//        if (entity != null) {
//            tarjeta = entity.getTarjeta();
//            //Crea la lista de calificaciones y le adiciona las que tiene el entity
//            calificaciones = new ArrayList<>
//             for(CalificacionEntity entityCalificacion: entity.getCalificaciones()){
//                 calificaciones.add(new CalificacionDTO(entityCalificacion));
//             }
//            //Crea la lista de reservas y le adiciona las que tiene el entity
//            reservas = new ArrayList<>();
//            for (ReservaEntity entityReserva : entity.getReservas()) {
//                reservas.add(new ReservaDTO(entityReserva));
//            }
//            //Crea la lista de domicilios y le adiciona las que tiene el entity
//            domicilios = new ArrayList<>();
//            for (DomicilioEntity entityDomicilio : entity.getDomicilios()) {
//                domiclios.add(new DomicilioDTO(entityDomicilio));
//            }
//        }
//    }
//
//    /**
//     * Transformar un DTO a un Entity
//     *
//     * @return un ClienteEntity con base en el DTO actual
//     */
//    @Override
//    public ClienteEntity toEntity() {
//        ClienteEntity entity = super.toEntity();
//        //Agrega al nuevo Entity la tarjeta del DTO
//        if (tarjeta != null) {
//            entity.setTarjeta(tarjeta.toEntity());
//        }
//        //Agrega al nuevo Entity la lista de calificaciones del DTO
//        if (calificaciones != null) {
//            ArrayList<CalificacionEntity> calificacionesEntity = new ArrayList<>();
//            for (CalificacionDTO calificacionDTO : calificaciones) {
//                calificacionesEntity.add(calificacionDTO.toEntity());
//            }
//            entity.setCalificaciones(calificacionesEntity);
//        }
//        //Agrega al nuevo Entity la lista de domicilios del DTO
//        if (domicilios != null) {
//            ArrayList<DomicilioEntity> domiciliosEntity = new ArrayList<>();
//            for (DomicilioDTO domicilioDTO : domicilios) {
//                domiciliosEntity.add(domicilioDTO.toEntity());
//            }
//            entity.setDomicilios(domiciliosEntity);
//        }
//        //Agrega al nuevo Entity la lista de reservas del DTO 
//        if (reservas != null) {
//            ArrayList<ReservaEntity> reservasEntity = new ArrayList<>();
//            for (ReservaDTO reservaDTO : reservas) {
//                reservasEntity.add(reservaDTO.toEntity());
//            }
//            entity.setClientes(reservasEntity);
//        }
//        return entity;
//    }
//
//    /**
//     * Retorna la tarjeta del cliente
//     *
//     * @return - tarjeta del cliente
//     */
//    public TarjetaDTO getTarjeta() {
//        return this.tarjeta;
//    }
//
//    /**
//     * Modifica la tarjeta del cliente
//     *
//     * @param pTarjeta - tarjeta nueva
//     */
//    public void setTarjeta(TarjetaDTO pTarjeta) {
//        this.tarjeta = pTarjeta;
//    }
//
//    /**
//     * Retorna las calificaciones del cliente
//     *
//     * @return - Calificaciones del cliente
//     */
//    public ArrayList<CalificacionDTO> getCalificacion() {
//        return this.calificaciones;
//    }
//
//    /**
//     * Modifica las calificaciones del cliente
//     *
//     * @param pCalificaciones - Nuevas calificaciones
//     */
//    public void setCalificacion(ArrayList<CalificacionDTO> pCalificaciones) {
//        this.calificaciones = pCalificaciones;
//    }
//
//    /**
//     * Retorna los domicilios del cliente
//     *
//     * @return - Domicilios del cliente
//     */
//    public ArrayList<DomicilioDTO> getDomicilios() {
//        return this.domicilios;
//    }
//
//    /**
//     * Modifica los domicilios del cliente
//     *
//     * @param pDomicilios - Nuevos domicilios del cliente
//     */
//    public void setDomicilios(ArrayList<DomicilioDTO> pDomicilios) {
//        this.domicilios = pDomicilios;
//    }
//
//    /**
//     * Retorna las reservas del cliente
//     *
//     * @return - Reservas del cliente
//     */
//    public ArrayList<ReservaDTO> getReservas() {
//        return this.reservas;
//    }
//
//    /**
//     * Modifica las reservas del cliente
//     *
//     * @param pReservas - Nuevas reservas del cliente
//     */
//    public void setReservas(ArrayList<ReservaDTO> pReservas) {
//        this.reservas = pReservas;
//    }
//}
