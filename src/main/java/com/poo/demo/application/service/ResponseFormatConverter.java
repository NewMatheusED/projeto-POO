package com.poo.demo.application.service;

import org.springframework.stereotype.Service;

/**
 * Serviço responsável por detectar formatos de resposta
 * Segue o princípio de responsabilidade única (SRP)
 */
@Service
public class ResponseFormatConverter {

    /**
     * Detecta se a resposta é XML baseado no conteúdo
     * @param response String da resposta
     * @return true se for XML, false caso contrário
     */
    public boolean isXmlResponse(String response) {
        if (response == null || response.trim().isEmpty()) {
            return false;
        }
        
        String trimmed = response.trim();
        return trimmed.startsWith("<?xml") || trimmed.startsWith("<");
    }

    /**
     * Detecta se a resposta é JSON baseado no conteúdo
     * @param response String da resposta
     * @return true se for JSON, false caso contrário
     */
    public boolean isJsonResponse(String response) {
        if (response == null || response.trim().isEmpty()) {
            return false;
        }
        
        String trimmed = response.trim();
        return trimmed.startsWith("{") || trimmed.startsWith("[");
    }
}
