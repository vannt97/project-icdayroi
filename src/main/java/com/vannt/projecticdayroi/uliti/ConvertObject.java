package com.vannt.projecticdayroi.uliti;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import org.hibernate.type.AnyType;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConvertObject {

    private Gson gson = new Gson();

    public String convertJson(Object obj){
        return  gson.toJson(obj);
    }

    public Object convertJsonToObject(String json,Class<?> tClass ){
        String noQuotes = json.replaceAll("^\"|\"$", "");
        return gson.fromJson(noQuotes,tClass);
    }

    public void convertListObjectToJsonString(List list) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(list);
        System.out.println(json);
    }

    public void convertObjectToJsonString(Object object) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(object);
        System.out.println("Json " + json);
    }
}
