package com.teamvocealuga.vocealuga.configs;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CustomDateDeserializer extends JsonDeserializer<Date> {
    private SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss.SSS");

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        String dateAsString = jsonParser.getText();
        try {
            return formatter.parse(dateAsString);
        } catch (ParseException e) {
            throw new IOException("Erro ao desserializar a data", e);
        }
    }
}
