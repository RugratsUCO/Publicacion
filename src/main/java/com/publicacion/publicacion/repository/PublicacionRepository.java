package com.publicacion.publicacion.repository;

import com.publicacion.publicacion.domain.estructura.Grupo;
import com.publicacion.publicacion.domain.publicacion.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PublicacionRepository extends JpaRepository<Publicacion, UUID> {
    List<Publicacion> findByAutorGrupo(Grupo grupo);
    Optional<Publicacion> findByTitulo(String titulo);
}
