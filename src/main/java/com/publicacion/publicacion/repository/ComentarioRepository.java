package com.publicacion.publicacion.repository;

import com.publicacion.publicacion.domain.publicacion.Comentario;
import com.publicacion.publicacion.domain.publicacion.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ComentarioRepository extends JpaRepository<Comentario, UUID> {

    List<Comentario> findByPublicacion(Publicacion publicacion);
}
