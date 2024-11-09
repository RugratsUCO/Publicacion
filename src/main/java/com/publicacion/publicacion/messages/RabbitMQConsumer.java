package com.publicacion.publicacion.messages;


import com.publicacion.publicacion.domain.publicacion.Publicacion;
import com.publicacion.publicacion.service.publicacion.PublicacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@Component
public class RabbitMQConsumer {
    private final PublicacionService service;
    private final Logger log = LoggerFactory.getLogger(RabbitMQConsumer.class);
    public RabbitMQConsumer(PublicacionService service){
        this.service = service;
    }

    @RabbitListener(queues = "publicar_queue")
    public HttpStatus publicar(Publicacion publicacion){
        try{
            service.publicar(publicacion);
            return HttpStatus.OK;
        }catch (Exception e){
            log.error(e.getMessage());
            return HttpStatus.CONFLICT;
        }
    }

}
