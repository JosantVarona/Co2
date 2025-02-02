package dam.JosantVarona.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "huella", schema = "carbono")
public class Huella {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private dam.JosantVarona.model.Usuario idUsuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_actividad")
    private Actividad idActividad;

    @Column(name = "valor", precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "unidad", length = 50)
    private String unidad;

    @Column(name = "fecha")
    private LocalDate fecha;

    public Huella(Usuario idUsuario, Actividad idActividad, BigDecimal valor, String unidad, LocalDate fecha) {
        this.idUsuario = idUsuario;
        this.idActividad = idActividad;
        this.valor = valor;
        this.unidad = unidad;
        this.fecha = fecha;
    }
    public Huella() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Huella{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                //", idActividad=" + idActividad +
                ", valor=" + valor +
                ", unidad='" + unidad + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}