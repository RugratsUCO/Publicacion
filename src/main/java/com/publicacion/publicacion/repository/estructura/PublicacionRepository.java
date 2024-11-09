package com.publicacion.publicacion.repository.estructura;

import com.publicacion.publicacion.domain.publicacion.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PublicacionRepository extends JpaRepository<Publicacion, UUID> {
}
