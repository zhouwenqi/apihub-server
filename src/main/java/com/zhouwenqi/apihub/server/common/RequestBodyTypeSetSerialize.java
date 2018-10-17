package com.zhouwenqi.apihub.server.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.zhouwenqi.apihub.server.model.RequestBodyType;

import java.io.IOException;

/**
 * 请求body类型序列化赋值
 * Created by zhouwenqi on 2018/10/16.
 */
public class RequestBodyTypeSetSerialize extends JsonDeserializer<RequestBodyType> {
    @Override
    public RequestBodyType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException,JsonProcessingException {
        try {
            return Enum.valueOf(RequestBodyType.class,jsonParser.getText());
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
