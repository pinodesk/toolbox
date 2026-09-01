package com.pinodesk.toolbox.jackson.deserializer;

import java.io.IOException;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.pinodesk.toolbox.data.DateTimeUtils;

public class MultipleFormatZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {

    @Override
    public Class<?> handledType() {
        return ZonedDateTime.class;
    }

    @Override
    public ZonedDateTime deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
        return DateTimeUtils.parseZonedDateTimeWithDefaultFormatters(jp.getText());
    }

}
