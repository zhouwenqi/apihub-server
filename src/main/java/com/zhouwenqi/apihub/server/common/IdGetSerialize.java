package com.zhouwenqi.apihub.server.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.zhouwenqi.apihub.server.utils.aesUtils;
import org.bson.types.ObjectId;

import java.io.IOException;

/**
 * ObjectId 序列化取值
 * Created by zhouwenqi on 2018/1/29.
 */
public class IdGetSerialize extends JsonSerializer<ObjectId> {
    @Override
    public void serialize(ObjectId id, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException,JsonProcessingException {
        try {
            jsonGenerator.writeString(id.toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
