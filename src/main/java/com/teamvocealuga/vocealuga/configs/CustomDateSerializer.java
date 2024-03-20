package com.teamvocealuga.vocealuga.configs;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateSerializer extends JsonSerializer<Date> {
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        gen.writeString(formatter.format(value));
    }
}
