package dam.JosantVarona.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "habito", schema = "carbono")
public class Habito {
    @EmbeddedId
    private HabitoId id;

    @MapsId("idUsuario")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private dam.JosantVarona.model.Usuario idUsuario;
    @MapsId("idActividad")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_actividad", nullable = false)
    private Actividad idActividad;

    @Column(name = "frecuencia")
    private Integer frecuencia;

    @Lob
    @Column(name = "tipo")
    private String tipo;

    @Column(name = "ultima_fecha")
    private LocalDate ultimaFecha;

    public Habito(Usuario idUsuario, Actividad idActividad, Integer frecuencia, String tipo, LocalDate ultimaFecha) {
        this.idUsuario = idUsuario;
        this.idActividad = idActividad;
        this.frecuencia = frecuencia;
        this.tipo = tipo;
        this.ultimaFecha = ultimaFecha;
    }
    public Habito() {

    }

    public HabitoId getId() {
        return id;
    }

    public void setId(HabitoId id) {
        this.id = id;
    }

    public dam.JosantVarona.model.Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(dam.JosantVarona.model.Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Actividad getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Actividad idActividad) {
        this.idActividad = idActividad;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getUltimaFecha() {
        return ultimaFecha;
    }

    public void setUltimaFecha(LocalDate ultimaFecha) {
        this.ultimaFecha = ultimaFecha;
    }

    @Override
    public String toString() {
        return "Habito{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", idActividad=" + idActividad +
                ", frecuencia=" + frecuencia +
                ", tipo='" + tipo + '\'' +
                ", ultimaFecha=" + ultimaFecha +
                '}';
    }
}