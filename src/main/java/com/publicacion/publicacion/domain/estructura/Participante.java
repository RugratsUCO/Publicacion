package com.publicacion.publicacion.domain.estructura;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.publicacion.publicacion.domain.persona.Persona;
import com.publicacion.publicacion.utils.UtilBoolean;
import com.publicacion.publicacion.utils.UtilObject;
import com.publicacion.publicacion.utils.UtilUUID;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Participante")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Participante {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "identificador", nullable = false)
    private UUID identificador;

    @ManyToOne
    @JoinColumn(name = "persona")
    private Persona persona;

    @Column(name = "activo")
    private boolean activo;

    public Participante() {
        super();
        setIdentificador(UtilUUID.getDefaultValue());
        setPersona(Persona.create());
        setActivo(UtilBoolean.getDefaultValue());
    }

    public Participante(final UUID identificador, final Persona persona, final boolean estaActivo) {
        super();
        setIdentificador(identificador);
        setPersona(persona);
        setActivo(estaActivo);
    }

    public Participante setIdentificador(final UUID identificador) {
        this.identificador = UtilUUID.getDefault(identificador);
        return this;
    }

    public Participante setPersona(final Persona persona) {
        this.persona = UtilObject.getDefault(persona, Persona.create());
        return this;
    }

    public Participante setActivo(final boolean estaActivo) {
        this.activo = UtilObject.getDefault(estaActivo, UtilBoolean.getDefaultValue());
        return this;
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public Persona getPersona() {
        return persona;
    }

    public boolean getActivo() {
        return activo;
    }

    public static Participante create() {
        return new Participante();
    }

}

