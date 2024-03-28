package com.tienda_m.service;

import jakarta.mail.MessagingException;

public interface CorreoService {
    //firma para poder enviar html
    public void enviarCorreoHtml(String para, String asunto, String contenidoHtml) throws MessagingException;

}
