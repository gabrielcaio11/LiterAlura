package br.com.gabrielcaio.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados {
    private static ObjectMapper objectMapper = new ObjectMapper();


    public static <T> T obterDados(String json, Class<T> classe)  {
        try {
            return objectMapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
