package com.publicacion.publicacion.service.publicacion;

import com.publicacion.publicacion.domain.publicacion.Publicacion;
import com.publicacion.publicacion.repository.estructura.PublicacionRepository;
import com.publicacion.publicacion.utils.UtilDate;
import org.springframework.stereotype.Service;

@Service
public final class PublicacionService {

    private final PublicacionRepository repository;

    public PublicacionService(PublicacionRepository repository){
        this.repository = repository;
    }
    public void publicar(Publicacion publicacion) {
        repository.save(publicacion);
    }


}
