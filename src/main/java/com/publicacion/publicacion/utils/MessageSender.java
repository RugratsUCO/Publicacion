package com.publicacion.publicacion.utils;

public interface MessageSender<T> {
    void execute(T message, String idMessage);
}