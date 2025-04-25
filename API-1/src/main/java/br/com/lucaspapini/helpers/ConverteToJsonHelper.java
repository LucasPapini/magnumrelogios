package br.com.lucaspapini.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class utilitaria
 */

public class ConverteToJsonHelper {
    private static  final ObjectMapper mapper = new ObjectMapper();

    public  static String toJson(Object obj){
        try{
            return mapper.writeValueAsString(obj);
        }catch (Exception e){
            throw  new RuntimeException("Erro ao converter para JSON", e);
       }
    }
}
