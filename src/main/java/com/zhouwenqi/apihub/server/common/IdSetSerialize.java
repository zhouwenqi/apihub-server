package com.zhouwenqi.apihub.server.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.bson.types.ObjectId;

import java.io.IOException;

/**
 * ObjectId 序列化赋值
 * Created by zhouwenqi on 2018/1/29.
 */
public class IdSetSerialize extends JsonDeserializer<ObjectId> {
    @Override
    public ObjectId deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException,JsonProcessingException {
        try {
            return new ObjectId(jsonParser.getText());
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
