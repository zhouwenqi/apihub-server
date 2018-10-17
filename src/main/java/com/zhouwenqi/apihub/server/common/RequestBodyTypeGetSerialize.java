package com.zhouwenqi.apihub.server.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zhouwenqi.apihub.server.model.RequestBodyType;

import java.io.IOException;

/**
 * 请求body类型序列化取值
 * Created by zhouwenqi on 2018/10/16.
 */
public class RequestBodyTypeGetSerialize extends JsonSerializer<RequestBodyType> {
    @Override
    public void serialize(RequestBodyType type, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException,JsonProcessingException {
        try {
            jsonGenerator.writeString(type.getValue());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
