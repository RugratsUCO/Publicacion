package com.publicacion.publicacion.messages;


import com.publicacion.publicacion.domain.estructura.Grupo;
import com.publicacion.publicacion.domain.publicacion.Comentario;
import com.publicacion.publicacion.domain.publicacion.Publicacion;
import com.publicacion.publicacion.service.publicacion.ComentarioService;
import com.publicacion.publicacion.service.publicacion.PublicacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class RabbitMQConsumer {
    private final PublicacionService publicacionService;
    private final ComentarioService comentarioService;
    private final Logger log = LoggerFactory.getLogger(RabbitMQConsumer.class);
    public RabbitMQConsumer(PublicacionService publicacionService, ComentarioService comentarioService){
        this.publicacionService = publicacionService;
        this.comentarioService = comentarioService;
    }

    @RabbitListener(queues = "publicar_queue")
    public HttpStatus publicar(Publicacion publicacion){
        try{
            publicacionService.publicar(publicacion);
            return HttpStatus.OK;
        }catch (Exception e){
            log.error(e.getMessage());
            return HttpStatus.CONFLICT;
        }
    }
    @RabbitListener(queues = "listar_grupo_queue")
    public List<Publicacion> consultarPorGrupo(Grupo grupo){
        try{
            return publicacionService.listarPorGrupo(grupo);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ArrayList<>();
    }
    @RabbitListener(queues = "comentar_queue")
    public HttpStatus comentar(Comentario comentario){
        try{
            comentarioService.comentar(comentario);
            return HttpStatus.OK;
        }catch (Exception e){
            log.error(e.getMessage());
            return HttpStatus.CONFLICT;
        }
    }
    @RabbitListener(queues = "listar_por_publicacion_queue")
    public List<Comentario> consultarPorPublicacion(Publicacion publicacion){
        try{
            return comentarioService.listarPorComentario(publicacion);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ArrayList<>();
    }


}
