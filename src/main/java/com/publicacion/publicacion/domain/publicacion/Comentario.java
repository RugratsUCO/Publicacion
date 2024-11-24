package com.publicacion.publicacion.domain.publicacion;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.publicacion.publicacion.domain.estructura.ParticipanteGrupo;
import com.publicacion.publicacion.utils.*;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;
@Entity
@Table(name = "comentario")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comentario {
    private static final Comentario PADRE = null;
    @Id
    @Column(name = "identificador", nullable = false)
    private UUID identificador;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "\"publicacion\"")
    private Publicacion publicacion;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "\"comentarioPadre\"")
    private Comentario comentarioPadre;
    @Column(name = "fechaComentario")
    private Date fechaComentario;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "\"autor\"")
    private ParticipanteGrupo autor;
    @Column(name = "contenido", nullable = false)
    private String contenido;
    @Column(name = "tienePadre")
    private boolean tienePadre;
    @Column(name = "activo", nullable = false)
    private boolean activo;
    public Comentario() {
        super();
        setIdentificador(UtilUUID.getDefaultValue());
        setActivo(UtilBoolean.getDefaultValue());
        setContenido(UtilText.getDefaultValue());
        setFechaComentario(UtilDate.getDefaultValueDate());
        setAutor(ParticipanteGrupo.create());
        setComentarioPadre(PADRE);


    }
    @JsonCreator
    public Comentario(
            @JsonProperty("identificador") UUID identificador,
            @JsonProperty("contenido") String contenido,
            @JsonProperty("comentarioPadre") Comentario comentarioPadre,
            @JsonProperty("fechaComentario") Date fechaComentario,
            @JsonProperty("activo") boolean activo,
            @JsonProperty("autor") ParticipanteGrupo autor,
            @JsonProperty("tienePadre") boolean tienePadre) {

        setIdentificador(identificador);
        setTienePadre(tienePadre);
        setContenido(contenido);
        setComentarioPadre(comentarioPadre);
        setFechaComentario(fechaComentario);
        setAutor(autor);
        setActivo(activo);
        setTienePadre(tienePadre);
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public Comentario setIdentificador(UUID identificador) {
        this.identificador = identificador;
        return this;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public Comentario setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
        return this;
    }

    public Comentario getComentarioPadre() {
        return comentarioPadre;
    }

    public Comentario setComentarioPadre(Comentario comentarioPadre) {
        if (isTienePadre()) {
            this.comentarioPadre = UtilObject.getDefault(comentarioPadre, Comentario.create());
        } else {
            this.comentarioPadre = PADRE;
        }
        return this;
    }

    public Date getFechaComentario() {
        return fechaComentario;
    }

    public Comentario setFechaComentario(Date fechaComentario) {
        this.fechaComentario = fechaComentario;
        return this;
    }

    public ParticipanteGrupo getAutor() {
        return autor;
    }

    public Comentario setAutor(ParticipanteGrupo autor) {
        this.autor = autor;
        return this;
    }

    public String getContenido() {
        return contenido;
    }

    public Comentario setContenido(String contenido) {
        this.contenido = contenido;
        return this;
    }

    public boolean isTienePadre() {
        return tienePadre;
    }

    public Comentario setTienePadre(boolean tienePadre) {
        this.tienePadre = UtilBoolean.isNull(tienePadre);
        return this;
    }

    public boolean isActivo() {
        return activo;
    }

    public Comentario setActivo(boolean activo) {
        this.activo = activo;
        return this;
    }
    public static Comentario create(){
        return new Comentario();
    }
}
