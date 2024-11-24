package com.publicacion.publicacion.service.publicacion;

import com.publicacion.publicacion.domain.estructura.Grupo;
import com.publicacion.publicacion.domain.publicacion.Publicacion;
import com.publicacion.publicacion.repository.PublicacionRepository;
import com.publicacion.publicacion.utils.UtilBoolean;
import com.publicacion.publicacion.utils.UtilUUID;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public final class PublicacionService {

    private final PublicacionRepository repository;

    public PublicacionService(PublicacionRepository repository){
        this.repository = repository;
    }
    public void publicar(Publicacion publicacion) {
        Optional<Publicacion> publicacionPrueba = repository.findByTitulo(publicacion.getTitulo());
        if(publicacionPrueba.isEmpty()) {
            do {
                UUID identificador = UtilUUID.generateNewUUID();
                publicacionPrueba = repository.findById(identificador);
                if (publicacionPrueba.isEmpty()) {
                    publicacion.setIdentificador(identificador);
                }
            } while (publicacionPrueba.isPresent());
            publicacion.setFechaPublicacion(Date.from(Instant.now())).setActivo(UtilBoolean.getTrue());
            repository.save(publicacion);
        }
    }
    public List<Publicacion> listarPorGrupo(Grupo grupo) {
        return repository.findByAutorGrupo(grupo);
    }


}
