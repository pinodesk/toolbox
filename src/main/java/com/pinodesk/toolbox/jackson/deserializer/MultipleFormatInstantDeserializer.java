package com.pinodesk.toolbox.jackson.deserializer;

import java.io.IOException;
import java.time.Instant;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.pinodesk.toolbox.data.DateTimeUtils;

public class MultipleFormatInstantDeserializer extends JsonDeserializer<Instant> {

    @Override
    public Class<?> handledType() {
        return Instant.class;
    }

    @Override
    public Instant deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
        return DateTimeUtils.parseInstantWithDefaultFormatters(jp.getText());
    }

}
