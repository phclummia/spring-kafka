package com.currencyfeed.common.utils;

import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;



@Component()
@RequiredArgsConstructor
@Slf4j
public class DataSerialization<T> implements IDataSerialization<T>{

    private final Class<T> type;

    @Override
    public String serialize(T object){

        String result = new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(object);
        log.info("Object serialized : {}", result);
        return result;
    }

    @Override
    public T deserialize(String jsonString){
        T result =  new GsonBuilder()
                .create()
                .fromJson(jsonString, this.type);
        log.info("Object deserialized : {}", result);
        return result;
    }
}
