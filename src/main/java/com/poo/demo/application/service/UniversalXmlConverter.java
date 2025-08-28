package com.poo.demo.application.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Conversor universal que converte qualquer XML para JSON
 */
@Service
public class UniversalXmlConverter {

    private final XmlMapper xmlMapper;
    private final ObjectMapper jsonMapper;

    public UniversalXmlConverter() {
        this.xmlMapper = new XmlMapper();
        this.jsonMapper = new ObjectMapper();
    }

    /**
     * Converte qualquer XML para JSON genérico (Map<String, Object>)
     * @param xmlResponse String XML de qualquer estrutura
     * @return Map representando o JSON convertido
     */
    public Map<String, Object> convertXmlToGenericJson(String xmlResponse) throws Exception {
        try {
            // Converte XML para JsonNode
            JsonNode xmlNode = xmlMapper.readTree(xmlResponse);
            
            // Converte para Map genérico
            return convertJsonNodeToMap(xmlNode);
            
        } catch (Exception e) {
            throw new Exception("Erro na conversão universal XML para JSON: " + e.getMessage());
        }
    }

    /**
     * Converte qualquer XML para List<Map<String, Object>> (para arrays)
     * @param xmlResponse String XML de qualquer estrutura
     * @return Lista de Maps representando o JSON convertido
     */
    public List<Map<String, Object>> convertXmlToGenericJsonList(String xmlResponse) throws Exception {
        try {
            // Converte XML para JsonNode
            JsonNode xmlNode = xmlMapper.readTree(xmlResponse);
            
            // Se for um array, retorna lista
            if (xmlNode.isArray()) {
                return convertJsonNodeArrayToMapList(xmlNode);
            }
            
            // Se não for array, procura por elementos que podem ser arrays
            List<Map<String, Object>> results = new ArrayList<>();
            
            // Procura por elementos que podem conter arrays
            for (String key : getPossibleArrayKeys(xmlNode)) {
                JsonNode arrayNode = xmlNode.get(key);
                if (arrayNode != null && arrayNode.isArray()) {
                    results.addAll(convertJsonNodeArrayToMapList(arrayNode));
                }
            }
            
            // Se não encontrou arrays, trata como item único
            if (results.isEmpty()) {
                Map<String, Object> singleItem = convertJsonNodeToMap(xmlNode);
                if (!singleItem.isEmpty()) {
                    results.add(singleItem);
                }
            }
            
            return results;
            
        } catch (Exception e) {
            throw new Exception("Erro na conversão universal XML para JSON List: " + e.getMessage());
        }
    }

    /**
     * Converte qualquer XML para um DTO específico usando mapeamento automático
     * @param xmlResponse String XML de qualquer estrutura
     * @param targetClass Classe do DTO de destino
     * @return DTO preenchido automaticamente
     */
    public <T> T convertXmlToDto(String xmlResponse, Class<T> targetClass) throws Exception {
        try {
            // Primeiro converte para Map genérico
            Map<String, Object> jsonMap = convertXmlToGenericJson(xmlResponse);
            
            // Converte o Map para o DTO usando Jackson
            return jsonMapper.convertValue(jsonMap, targetClass);
            
        } catch (Exception e) {
            throw new Exception("Erro na conversão XML para DTO: " + e.getMessage());
        }
    }

    /**
     * Converte qualquer XML para uma lista de DTOs específicos
     * @param xmlResponse String XML de qualquer estrutura
     * @param targetClass Classe do DTO de destino
     * @return Lista de DTOs preenchidos automaticamente
     */
    public <T> List<T> convertXmlToDtoList(String xmlResponse, Class<T> targetClass) throws Exception {
        try {
            // Primeiro converte para List<Map> genérico
            List<Map<String, Object>> jsonList = convertXmlToGenericJsonList(xmlResponse);
            
            // Converte cada Map para o DTO usando Jackson
            List<T> results = new ArrayList<>();
            for (Map<String, Object> jsonMap : jsonList) {
                T dto = jsonMapper.convertValue(jsonMap, targetClass);
                results.add(dto);
            }
            
            return results;
            
        } catch (Exception e) {
            throw new Exception("Erro na conversão XML para List<DTO>: " + e.getMessage());
        }
    }

    /**
     * Converte JsonNode para Map genérico
     * @param node JsonNode a ser convertido
     * @return Map representando o JSON
     */
    private Map<String, Object> convertJsonNodeToMap(JsonNode node) {
        Map<String, Object> result = new HashMap<>();
        
        if (node.isObject()) {
            node.fieldNames().forEachRemaining(key -> {
                JsonNode value = node.get(key);
                result.put(key, convertJsonNodeValue(value));
            });
        }
        
        return result;
    }

    /**
     * Converte JsonNode para valor Java apropriado
     * @param node JsonNode a ser convertido
     * @return Valor Java (String, Integer, Boolean, Map, List, etc.)
     */
    private Object convertJsonNodeValue(JsonNode node) {
        if (node.isNull()) {
            return null;
        } else if (node.isTextual()) {
            return node.asText();
        } else if (node.isNumber()) {
            if (node.isInt()) {
                return node.asInt();
            } else if (node.isLong()) {
                return node.asLong();
            } else if (node.isDouble()) {
                return node.asDouble();
            } else {
                return node.asText(); // Fallback para string
            }
        } else if (node.isBoolean()) {
            return node.asBoolean();
        } else if (node.isArray()) {
            return convertJsonNodeArrayToList(node);
        } else if (node.isObject()) {
            return convertJsonNodeToMap(node);
        } else {
            return node.asText(); // Fallback para string
        }
    }

    /**
     * Converte array JsonNode para List
     * @param arrayNode Array JsonNode
     * @return Lista de valores convertidos
     */
    private List<Object> convertJsonNodeArrayToList(JsonNode arrayNode) {
        List<Object> result = new ArrayList<>();
        
        for (JsonNode item : arrayNode) {
            result.add(convertJsonNodeValue(item));
        }
        
        return result;
    }

    /**
     * Converte array JsonNode para List<Map<String, Object>>
     * @param arrayNode Array JsonNode
     * @return Lista de Maps
     */
    private List<Map<String, Object>> convertJsonNodeArrayToMapList(JsonNode arrayNode) {
        List<Map<String, Object>> result = new ArrayList<>();
        
        for (JsonNode item : arrayNode) {
            if (item.isObject()) {
                result.add(convertJsonNodeToMap(item));
            } else {
                // Se não for objeto, cria um Map com valor
                Map<String, Object> singleValue = new HashMap<>();
                singleValue.put("value", convertJsonNodeValue(item));
                result.add(singleValue);
            }
        }
        
        return result;
    }

    /**
     * Obtém chaves possíveis que podem conter arrays
     * @param rootNode Nó raiz
     * @return Lista de chaves possíveis
     */
    private List<String> getPossibleArrayKeys(JsonNode rootNode) {
        List<String> keys = new ArrayList<>();
        
        if (rootNode.isObject()) {
            rootNode.fieldNames().forEachRemaining(keys::add);
        }
        
        return keys;
    }

    /**
     * Converte XML para String JSON puro
     * @param xmlResponse String XML
     * @return Object JSON formatado
     */
    public Object convertXmlToJsonMap(String xmlResponse) throws Exception {
        try {
            Map<String, Object> jsonMap = convertXmlToGenericJson(xmlResponse);
            return jsonMap;
        } catch (Exception e) {
            throw new Exception("Erro ao converter XML para String JSON: " + e.getMessage());
        }
    }

    /**
     * Converte XML para String JSON puro (formato compacto)
     * @param xmlResponse String XML
     * @return String JSON compacto
     */
    public String convertXmlToJsonStringCompact(String xmlResponse) throws Exception {
        try {
            Map<String, Object> jsonMap = convertXmlToGenericJson(xmlResponse);
            return jsonMapper.writeValueAsString(jsonMap);
        } catch (Exception e) {
            throw new Exception("Erro ao converter XML para String JSON compacto: " + e.getMessage());
        }
    }
}
