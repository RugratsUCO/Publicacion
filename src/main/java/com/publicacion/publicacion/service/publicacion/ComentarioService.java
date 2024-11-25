package com.publicacion.publicacion.service.publicacion;

import com.publicacion.publicacion.domain.publicacion.Comentario;
import com.publicacion.publicacion.domain.publicacion.Publicacion;
import com.publicacion.publicacion.repository.ComentarioRepository;
import com.publicacion.publicacion.utils.UtilBoolean;
import com.publicacion.publicacion.utils.UtilUUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class ComentarioService {
    private final ComentarioRepository repository;
    private final Logger log = LoggerFactory.getLogger(ComentarioService.class);


    public ComentarioService(ComentarioRepository repository){
        this.repository = repository;
    }
    public void comentar(Comentario comentario) throws Exception {
        try{
            Optional<Comentario> comentarioId;
            do {
                UUID identificador = UtilUUID.generateNewUUID();
                comentarioId = repository.findById(identificador);
                if (comentarioId.isEmpty()) {
                    comentario.setIdentificador(identificador).setFechaComentario(Date.from(Instant.now())).setActivo(UtilBoolean.getTrue());
                    repository.save(comentario);
                }
            } while (comentarioId.isPresent());
        }catch (Exception e){
            log.error("Error al momento de comentar, verificar la integridad de datos");
            throw new Exception();
        }
    }
    public List<Comentario> listarPorComentario(Publicacion publicacion) {
        return repository.findByPublicacion(publicacion);
    }

}
