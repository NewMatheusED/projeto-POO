package com.poo.demo.infrastructure.client;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poo.demo.application.service.ResponseFormatConverter;
import com.poo.demo.application.service.UniversalXmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Cliente HTTP inteligente que detecta e converte automaticamente formatos de resposta
 * Segue o princípio de responsabilidade única (SRP) e Open/Closed (OCP)
 */
@Component
public class SmartHttpClient {

    private final RestTemplate restTemplate;
    private final ResponseFormatConverter formatConverter;
    private final UniversalXmlConverter universalConverter;
    private final ObjectMapper jsonMapper;

    @Autowired
    public SmartHttpClient(RestTemplate restTemplate, ResponseFormatConverter formatConverter, UniversalXmlConverter universalConverter) {
        this.restTemplate = restTemplate;
        this.formatConverter = formatConverter;
        this.universalConverter = universalConverter;
        this.jsonMapper = new ObjectMapper();
    }

    /**
     * Faz uma requisição GET e retorna sempre no formato JSON padronizado
     * @param url URL da requisição
     * @param responseType Tipo da resposta esperada
     * @param arrayPath Caminho opcional para extrair o array (ex: "ListaParlamentarEmExercicio.Parlamentares.Parlamentar")
     * @return Objeto da resposta sempre em formato JSON
     */
    public <T> T get(String url, Class<T> responseType, String arrayPath) {
        try {
            // Faz a requisição HTTP uma única vez
            ResponseEntity<String> response = restTemplate.exchange(
                url, 
                HttpMethod.GET, 
                new HttpEntity<>(createHeaders()), 
                String.class
            );
            
            String responseBody = response.getBody();
            
            // Se a resposta for XML, converte para JSON
            if (formatConverter.isXmlResponse(responseBody)) {
                return handleXmlResponse(responseBody, responseType);
            }
            
            System.out.println("responseBody: " + responseBody);
            // Se já for JSON, converte diretamente
            if (formatConverter.isJsonResponse(responseBody)) {
                if (responseType.isArray()) {
                    // Para arrays, usa o caminho fornecido ou busca automaticamente
                    return handleJsonArrayResponse(responseBody, responseType, arrayPath);
                } else {
                    // Para objetos únicos, usa o caminho fornecido se existir
                    return handleJsonObjectResponse(responseBody, responseType, arrayPath);
                }
            }
            
            // Caso não seja nenhum dos formatos conhecidos, tenta fazer o parse direto
            return restTemplate.getForObject(url, responseType);
            
        } catch (Exception e) {
            throw new RuntimeException("Erro na requisição HTTP: " + e.getMessage(), e);
        }
    }

    /**
     * Faz uma requisição POST e retorna sempre no formato JSON padronizado
     * @param url URL da requisição
     * @param request Objeto da requisição
     * @param responseType Tipo da resposta esperada
     * @return Objeto da resposta sempre em formato JSON
     */
    public <T> T post(String url, Object request, Class<T> responseType) {
        try {
            // Faz a requisição HTTP uma única vez
            ResponseEntity<String> response = restTemplate.exchange(
                url, 
                HttpMethod.POST, 
                new HttpEntity<>(request, createHeaders()), 
                String.class
            );
            
            String responseBody = response.getBody();
            
            // Se a resposta for XML, converte para JSON
            if (formatConverter.isXmlResponse(responseBody)) {
                return handleXmlResponse(responseBody, responseType);
            }
            
            // Se já for JSON, converte diretamente
            if (formatConverter.isJsonResponse(responseBody)) {
                if (responseType.isArray()) {
                    // Para arrays, precisa extrair o array aninhado do JSON
                    return handleJsonArrayResponse(responseBody, responseType, null);
                } else {
                    return jsonMapper.readValue(responseBody, responseType);
                }
            }
            
            // Caso não seja nenhum dos formatos conhecidos, tenta fazer o parse direto
            return restTemplate.postForObject(url, request, responseType);
            
        } catch (Exception e) {
            throw new RuntimeException("Erro na requisição HTTP: " + e.getMessage(), e);
        }
    }

    /**
     * Trata respostas XML convertendo-as para JSON usando conversor universal
     * @param xmlResponse Resposta XML como string
     * @param responseType Tipo da resposta esperada
     * @return Objeto convertido para JSON
     */
    @SuppressWarnings("unchecked")
    private <T> T handleXmlResponse(String xmlResponse, Class<T> responseType) {
        try {
            if (responseType.isArray()) {
                // Para arrays, converte para lista genérica e depois para array
                Class<?> componentType = responseType.getComponentType();
                List<Map<String, Object>> jsonList = universalConverter.convertXmlToGenericJsonList(xmlResponse);
                
                // Converte cada Map para o DTO usando Jackson
                List<Object> results = new ArrayList<>();
                for (Map<String, Object> jsonMap : jsonList) {
                    Object dto = jsonMapper.convertValue(jsonMap, componentType);
                    results.add(dto);
                }
                
                // Converte para array
                return (T) results.toArray((Object[]) java.lang.reflect.Array.newInstance(componentType, results.size()));
                
            } else {
                // Para objetos únicos, usa conversão direta
                return universalConverter.convertXmlToDto(xmlResponse, responseType);
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter XML para JSON: " + e.getMessage(), e);
        }
    }

    /**
     * Trata respostas JSON que contêm arrays aninhados
     * @param jsonResponse Resposta JSON como string
     * @param responseType Tipo da resposta esperada
     * @param arrayPath Caminho para extrair o array (ex: "ListaParlamentarEmExercicio.Parlamentares.Parlamentar")
     * @return Array convertido para o tipo esperado
     */
    @SuppressWarnings("unchecked")
    private <T> T handleJsonArrayResponse(String jsonResponse, Class<T> responseType, String arrayPath) {
        try {
            if (!responseType.isArray()) {
                throw new RuntimeException("Tipo de resposta não é um array: " + responseType);
            }
            
            Class<?> componentType = responseType.getComponentType();
            
            // Converte JSON para Map para extrair o array aninhado
            Map<String, Object> jsonMap = jsonMapper.readValue(jsonResponse, Map.class);
            
            // Extrai array usando o caminho fornecido
            List<Object> arrayData = extractArrayFromJsonMap(jsonMap, arrayPath);
            
            // Converte cada item para o DTO usando Jackson
            List<Object> results = new ArrayList<>();
            for (Object item : arrayData) {
                Object dto = jsonMapper.convertValue(item, componentType);
                results.add(dto);
            }
            
            // Converte para array
            return (T) results.toArray((Object[]) java.lang.reflect.Array.newInstance(componentType, results.size()));
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter JSON para array: " + e.getMessage(), e);
        }
    }

    /**
     * Trata respostas JSON que contêm objetos únicos
     * @param jsonResponse Resposta JSON como string
     * @param responseType Tipo da resposta esperada
     * @param arrayPath Caminho para extrair o objeto (pode ser null)
     * @return Objeto convertido para o tipo esperado
     */
    @SuppressWarnings("unchecked")
    private <T> T handleJsonObjectResponse(String jsonResponse, Class<T> responseType, String arrayPath) {
        try {
            // Converte JSON para Map
            Map<String, Object> jsonMap = jsonMapper.readValue(jsonResponse, Map.class);

            // Extrai o objeto usando o caminho fornecido
            Object result = extractObjectFromJsonMap(jsonMap, arrayPath);

            // Converte para o DTO usando Jackson
            T converted = jsonMapper.convertValue(result, responseType);
            
            return converted;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter JSON para objeto: " + e.getMessage(), e);
        }
    }

    /**
     * Extrai objeto de um Map JSON usando caminho específico ou busca automática
     * @param jsonMap Map JSON
     * @param objectPath Caminho para extrair o objeto (ex: "ListaParlamentarEmExercicio.Parlamentares.Parlamentar")
     * @return Objeto extraído
     */
    private Object extractObjectFromJsonMap(Map<String, Object> jsonMap, String objectPath) {
        // Se foi fornecido um caminho específico, usa ele
        if (objectPath != null && !objectPath.trim().isEmpty()) {
            return extractObjectByPath(jsonMap, objectPath);
        }
        
        // Caso contrário, faz busca automática (fallback)
        return extractObjectAutomatically(jsonMap);
    }

    /**
     * Extrai objeto usando caminho específico (ex: "a.b.c")
     * @param jsonMap Map JSON
     * @param objectPath Caminho separado por pontos
     * @return Objeto extraído
     */
    @SuppressWarnings("unchecked")
    private Object extractObjectByPath(Map<String, Object> jsonMap, String objectPath) {
        try {
            System.out.println("objectPath: " + objectPath);
            System.out.println("jsonMap: " + jsonMap);
            String[] pathParts = objectPath.split("\\.");
            System.out.println("pathParts: " + Arrays.toString(pathParts));
            Object current = jsonMap;
            
            // Navega pelo caminho
            for (String part : pathParts) {
                if (current instanceof Map) {
                    current = ((Map<String, Object>) current).get(part);
                } else if (current instanceof List) {
                    current = ((List<Object>) current).get(Integer.parseInt(part));
                } else {
                    return null; // Caminho inválido
                }
            }
            
            // Se encontrou um objeto, retorna
            return current;
            
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Busca automaticamente por objetos no JSON (fallback)
     * @param jsonMap Map JSON
     * @return Objeto extraído
     */
    @SuppressWarnings("unchecked")
    private Object extractObjectAutomatically(Map<String, Object> jsonMap) {
        // Procura por qualquer chave que contenha objeto
        for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Map) {
                return value;
            } else if (value instanceof List) {
                // Se for uma lista, procura no primeiro item se for um Map
                List<Object> list = (List<Object>) value;
                if (!list.isEmpty() && list.get(0) instanceof Map) {
                    return extractObjectAutomatically((Map<String, Object>) list.get(0));
                }
            }
        }
        
        // Se não encontrou objeto, trata o próprio objeto como item único
        return jsonMap;
    }

    /**
     * Extrai array de um Map JSON usando caminho específico ou busca automática
     * @param jsonMap Map JSON
     * @param arrayPath Caminho para extrair o array (ex: "ListaParlamentarEmExercicio.Parlamentares.Parlamentar")
     * @return Lista com os dados do array
     */
    private List<Object> extractArrayFromJsonMap(Map<String, Object> jsonMap, String arrayPath) {
        // Se foi fornecido um caminho específico, usa ele
        if (arrayPath != null && !arrayPath.trim().isEmpty()) {
            return extractArrayByPath(jsonMap, arrayPath);
        }
        
        // Caso contrário, faz busca automática (fallback)
        return extractArrayAutomatically(jsonMap);
    }

    /**
     * Extrai array usando caminho específico (ex: "a.b.c")
     * @param jsonMap Map JSON
     * @param arrayPath Caminho separado por pontos
     * @return Lista com os dados do array
     */
    @SuppressWarnings("unchecked")
    private List<Object> extractArrayByPath(Map<String, Object> jsonMap, String arrayPath) {
        try {
            System.out.println("arrayPath: " + arrayPath);
            System.out.println("jsonMap: " + jsonMap);
            String[] pathParts = arrayPath.split("\\.");
            System.out.println("pathParts: " + Arrays.toString(pathParts));
            Object current = jsonMap;
            
            // Navega pelo caminho
            for (String part : pathParts) {
                if (current instanceof Map) {
                    current = ((Map<String, Object>) current).get(part);
                } else if (current instanceof List) {
                    current = ((List<Object>) current).get(Integer.parseInt(part));
                } else {
                    return new ArrayList<>(); // Caminho inválido
                }
            }
            
            // Se encontrou um array, retorna
            if (current instanceof List) {
                return (List<Object>) current;
            } else if (current instanceof Map) {
                // Se for um único item, cria uma lista
                List<Object> singleItem = new ArrayList<>();
                singleItem.add(current);
                return singleItem;
            }
            
            return new ArrayList<>();
            
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Busca automaticamente por arrays no JSON (fallback)
     * @param jsonMap Map JSON
     * @return Lista com os dados do array
     */
    @SuppressWarnings("unchecked")
    private List<Object> extractArrayAutomatically(Map<String, Object> jsonMap) {
        // Procura por qualquer chave que contenha array
        for (Map.Entry<String, Object> entry : jsonMap.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof List) {
                return (List<Object>) value;
            } else if (value instanceof Map) {
                // Recursivamente procura em objetos aninhados
                List<Object> nestedArray = extractArrayAutomatically((Map<String, Object>) value);
                if (!nestedArray.isEmpty()) {
                    return nestedArray;
                }
            }
        }
        
        // Se não encontrou array, trata o próprio objeto como item único
        List<Object> singleItem = new ArrayList<>();
        singleItem.add(jsonMap);
        return singleItem;
    }

    

    /**
     * Obtém a resposta como objeto JSON, convertendo XML se necessário
     * @param url URL da requisição
     * @return Object com JSON válido (sempre objeto, nunca string)
     */
    public Object getAsJsonObject(String url) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(
                url, 
                HttpMethod.GET, 
                new HttpEntity<>(createHeaders()), 
                String.class
            );
            
            String responseBody = response.getBody();
            
            // Se a resposta for XML, converte para JSON válido
            if (formatConverter.isXmlResponse(responseBody)) {
                // Conversão universal sem dependências específicas
                return universalConverter.convertXmlToJsonMap(responseBody);
            }
            
            // Se já for JSON, converte para objeto para evitar escape duplo
            if (formatConverter.isJsonResponse(responseBody)) {
                return jsonMapper.readValue(responseBody, Object.class);
            }
            
            // Caso não seja nenhum dos formatos conhecidos, retorna como está
            return responseBody;
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter JSON válido: " + e.getMessage(), e);
        }
    }

    /**
     * Cria headers HTTP com suporte a múltiplos formatos
     * @return HttpHeaders configurados
     */
    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json, application/xml, text/xml, */*");
        headers.add("Content-Type", "application/json");
        return headers;
    }
}
