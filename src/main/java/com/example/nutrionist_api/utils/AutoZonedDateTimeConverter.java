package com.example.nutrionist_api.utils;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AutoZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime zonedDateTime) {
        return zonedDateTime == null ? null : Timestamp.from(zonedDateTime.toInstant());
    }
    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp timestamp) {
        return timestamp == null ? null : ZonedDateTime.ofInstant(timestamp.toInstant(), ZoneOffset.UTC);
    }

    
}
