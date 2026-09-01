package com.pinodesk.toolbox.jackson;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.pinodesk.toolbox.jackson.deserializer.MultipleFormatInstantDeserializer;
import com.pinodesk.toolbox.jackson.deserializer.MultipleFormatOffsetDateTimeDeserializer;
import com.pinodesk.toolbox.jackson.deserializer.MultipleFormatZonedDateTimeDeserializer;

import lombok.Data;

@Data
public class ObjectConverter {

    private ObjectMapper mapper;

    /**
     * Creates a new ObjectConverter instance with default ObjectMapper
     * configuration.
     */
    public ObjectConverter() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Instant.class, new MultipleFormatInstantDeserializer());
        module.addDeserializer(ZonedDateTime.class, new MultipleFormatZonedDateTimeDeserializer());
        module.addDeserializer(OffsetDateTime.class, new MultipleFormatOffsetDateTimeDeserializer());
        this.mapper = new ObjectMapper();
        this.mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        this.mapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.mapper.findAndRegisterModules();
        this.mapper.registerModule(module);
    }

    /**
     * Creates a new ObjectConverter instance with specified ObjectMapper.
     * 
     * @param mapper The ObjectMapper that will be used for object conversion.
     */
    public ObjectConverter(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Converts an object instance to another type of object.
     * 
     * @param <T>    the type of converted object
     * @param object an instance of object that will be converted
     * @param c      the type of object to be returned
     * 
     * @return The converted object instance
     */
    public <T> T convertObject(Object object, Class<T> c) {
        return mapper.convertValue(object, c);
    }

    /**
     * Optionally converts an object if present, or null when empty.
     * 
     * @param <T>      the type of converted object
     * @param optional the Optional instance containing an object to convert
     * @param c        the type of object to be returned
     * 
     * @return The converted object from Optional content, or null if empty. To
     *         throw an exception on empty Optional, use
     *         <code>convertOptionalOrThrow()</code>.
     */
    public <T> T convertOptional(Optional<?> optional, final Class<T> c) {
        return optional.map(object -> convertObject(object, c)).orElse(null);
    }

    /**
     * Optionally converts an object if present, or throws an exception when empty.
     * 
     * @param <T>      the type of converted object
     * @param <X>      the type of exception to throw
     * @param optional the Optional instance containing an object to convert
     * @param c        the type of object to be returned
     * @param x        the exception instance to throw when Optional is empty
     * 
     * @return The converted object from Optional content if present.
     * 
     * @throws X The exception instance provided to this method.
     */
    public <T, X extends Exception> T convertOptionalOrThrow(Optional<?> optional, final Class<T> c, final X x)
            throws X {
        return optional.map(object -> convertObject(object, c)).orElseThrow(() -> x);
    }

    /**
     * Converts a list to another list of speciefied type.
     * 
     * @param <T>  the type of converted object
     * @param list a list instance that will be converted
     * @param c    the type of object to be returned
     * 
     * @return The converted list of specified type.
     */
    public <T> List<T> convertList(List<?> list, Class<T> c) {
        return mapper.convertValue(list, mapper.getTypeFactory().constructCollectionType(List.class, c));
    }

    /**
     * Converts an object instance to Map.
     * 
     * @param <K>    the type of Map key
     * @param <V>    the type of Map value
     * @param object an instance of object that will be converted to Map
     * 
     * @return A new Map consist of object properties.
     */
    public <K, V> Map<K, V> convertToMap(Object object) {
        return mapper.convertValue(object, new TypeReference<Map<K, V>>() {
        });
    }

}
