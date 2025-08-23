package com.poo.demo.infrastructure.client;

/**
 * Interface para cliente HTTP
 * Segue o princípio de segregação de interface (ISP)
 */
public interface HttpClient {
    
    /**
     * Faz uma requisição GET para a URL especificada
     * @param url URL da requisição
     * @param responseType Tipo da resposta esperada
     * @return Objeto da resposta
     */
    <T> T get(String url, Class<T> responseType);
    
    /**
     * Faz uma requisição POST para a URL especificada
     * @param url URL da requisição
     * @param request Objeto da requisição
     * @param responseType Tipo da resposta esperada
     * @return Objeto da resposta
     */
    <T> T post(String url, Object request, Class<T> responseType);
}
