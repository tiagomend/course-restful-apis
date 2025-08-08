package br.com.tiagomendonca.config;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Configuration
public class ObjectMapperConfig {

    public ObjectMapper objectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        SimpleFilterProvider filters = new SimpleFilterProvider()
            .addFilter("PersonFilter", SimpleBeanPropertyFilter.serializeAllExcept(
                "sensitiveData"
            ));
        mapper.setFilterProvider(filters);

        return mapper;
    }
}
