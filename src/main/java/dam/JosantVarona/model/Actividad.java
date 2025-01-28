package dam.JosantVarona.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "actividad", schema = "carbono")
public class Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actividad", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria")
    private dam.JosantVarona.model.Categoria idCategoria;

    @OneToMany(mappedBy = "idActividad")
    private Set<dam.JosantVarona.model.Habito> habitos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idActividad")
    private Set<dam.JosantVarona.model.Huella> huellas = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public dam.JosantVarona.model.Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(dam.JosantVarona.model.Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Set<dam.JosantVarona.model.Habito> getHabitos() {
        return habitos;
    }

    public void setHabitos(Set<dam.JosantVarona.model.Habito> habitos) {
        this.habitos = habitos;
    }

    public Set<dam.JosantVarona.model.Huella> getHuellas() {
        return huellas;
    }

    public void setHuellas(Set<dam.JosantVarona.model.Huella> huellas) {
        this.huellas = huellas;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", idCategoria=" + idCategoria +
                //", habitos=" + habitos +
                //", huellas=" + huellas +
                '}';
    }
}