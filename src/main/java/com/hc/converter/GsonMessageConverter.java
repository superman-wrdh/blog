package com.hc.converter;

import com.hc.converter.conversion.Convert;
import com.google.gson.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.UUID;

/**
 * gson message converter
 *
 * hc
 */
public class GsonMessageConverter extends GsonHttpMessageConverter {

    public GsonMessageConverter() {
        setGson(gson);
    }

    private static class DateDeserializeAdapter implements JsonDeserializer<Date>, JsonSerializer<Date> {

        public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            String str = jsonElement.getAsString();
            if(StringUtils.isBlank(str))
                return null;
            return Convert.toDate(str);
        }


        public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(date == null ? null : Convert.toString(date));
        }
    }

    private static class IntegerDeserializeAdapter implements JsonDeserializer<Integer> {

        public Integer deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            String str = jsonElement.getAsString();
            if(StringUtils.isBlank(str))
                return null;
            return Integer.valueOf(str);
        }
    }

    private static class DoubleDeserializeAdapter implements JsonDeserializer<Double> {

        public Double deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            String str = jsonElement.getAsString();
            if(StringUtils.isBlank(str))
                return null;
            return Double.valueOf(str);
        }
    }

    private static class FloatDeserializeAdapter implements JsonDeserializer<Float> {

        public Float deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            String str = jsonElement.getAsString();
            if(StringUtils.isBlank(str))
                return null;
            return Float.valueOf(str);
        }
    }

    private static class BooleanDeserializeAdapter implements JsonDeserializer<Boolean> {

        public Boolean deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            String str = jsonElement.getAsString();
            if(StringUtils.isBlank(str))
                return false;
            return Convert.toBoolean(str);
        }
    }

    private static class UUIDDeserializeAdapter implements JsonDeserializer<UUID> {


        public UUID deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            String str = jsonElement.getAsString();
            if(StringUtils.isBlank(str))
                return null;
            return UUID.fromString(str);
        }
    }

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Date.class, new DateDeserializeAdapter())
            .registerTypeAdapter(Integer.class, new IntegerDeserializeAdapter())
            .registerTypeAdapter(Double.class, new DoubleDeserializeAdapter())
            .registerTypeAdapter(Float.class, new FloatDeserializeAdapter())
            .registerTypeAdapter(Boolean.class, new BooleanDeserializeAdapter())
            .registerTypeAdapter(UUID.class, new UUIDDeserializeAdapter())
            .create();

}
