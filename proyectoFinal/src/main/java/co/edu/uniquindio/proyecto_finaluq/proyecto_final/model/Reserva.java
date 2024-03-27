package co.edu.uniquindio.proyecto_finaluq.proyecto_final.model;

import java.time.LocalDateTime;

public class Reserva {
    private String id;
    private Usuario usuario;
    private Evento evento;
    private LocalDateTime fechaSolicitud;
    private String estado;

    public Reserva(String id, Usuario usuario, Evento evento, LocalDateTime fechaSolicitud, String estado) {
        this.id = id;
        this.usuario = usuario;
        this.evento = evento;
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
