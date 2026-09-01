package com.pinodesk.toolbox.jackson.deserializer;

import java.io.IOException;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.pinodesk.toolbox.data.DateTimeUtils;

public class MultipleFormatOffsetDateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {

    @Override
    public Class<?> handledType() {
        return OffsetDateTime.class;
    }

    @Override
    public OffsetDateTime deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
        return DateTimeUtils.parseOffsetDateTimeWithDefaultFormatters(jp.getText());
    }

}
