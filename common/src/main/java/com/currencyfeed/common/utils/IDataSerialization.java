package com.currencyfeed.common.utils;

public interface IDataSerialization<T>  {
    public String serialize(T object);
    public T deserialize(String jsonString);
}
