package com.publicacion.publicacion.domain.estructura;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.publicacion.publicacion.domain.organizacion.Organizacion;
import com.publicacion.publicacion.utils.UtilBoolean;
import com.publicacion.publicacion.utils.UtilObject;
import com.publicacion.publicacion.utils.UtilText;
import com.publicacion.publicacion.utils.UtilUUID;
import jakarta.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "Estructura")
public final class Estructura {
    @Id
    //@GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "identificador", nullable = false)
    private UUID identificador;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "organizacion")
    private Organizacion organizacion;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "\"estructuraPadre\"")
    private Estructura estructuraPadre;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    @Column(name = "\"tienePadre\"", nullable = false)
    private boolean tienePadre;

    private static final Estructura PADRE = null;

    @JsonCreator
    public Estructura(
            @JsonProperty("identificador") UUID identificador,
            @JsonProperty("organizacion") Organizacion organizacion,
            @JsonProperty("estructuraPadre") Estructura estructuraPadre,
            @JsonProperty("nombre") String nombre,
            @JsonProperty("activo") boolean activo,
            @JsonProperty("tienePadre") boolean tienePadre) {

        setIdentificador(identificador);
        setOrganizacion(organizacion);
        setTienePadre(tienePadre);
        setEstructuraPadre(estructuraPadre);
        setNombre(nombre);
        setActivo(activo);
        setTienePadre(tienePadre);
    }

    public Estructura() {
        super();
        setIdentificador(UtilUUID.getDefaultValue());
        setOrganizacion(Organizacion.create());
        setTienePadre(UtilBoolean.getDefaultValue());
        setEstructuraPadre(PADRE);
        setNombre(UtilText.getDefaultValue());
        setActivo(UtilBoolean.getDefaultValue());
    }

    public final boolean isTienePadre() {
        return tienePadre;
    }

    public Estructura setTienePadre(boolean tienePadre) {
        this.tienePadre = UtilBoolean.isNull(tienePadre);
        return this;
    }

    public Estructura setIdentificador(final UUID identificador) {
        this.identificador = UtilUUID.getDefault(identificador);
        return this;
    }

    public Estructura setOrganizacion(final Organizacion organizacion) {
        this.organizacion = UtilObject.getDefault(organizacion, Organizacion.create());
        return this;
    }

    public Estructura setEstructuraPadre(final Estructura estructuraPadre) {
        if (isTienePadre()) {
            this.estructuraPadre = UtilObject.getDefault(estructuraPadre, Estructura.create());
            return this;
        } else {
            this.estructuraPadre = null;
        }
        return this;
    }


    public Estructura setNombre(final String nombre) {
        this.nombre = UtilText.applyTrim(nombre);
        return this;
    }

    public Estructura setActivo(final boolean estaActivo) {
        this.activo = UtilObject.getDefault(estaActivo, UtilBoolean.getDefaultValue());
        return this;
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public Estructura getEstructuraPadre() {
        return estructuraPadre;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean getActivo() {
        return activo;
    }

    public static Estructura create() {
        return new Estructura();
    }

}

