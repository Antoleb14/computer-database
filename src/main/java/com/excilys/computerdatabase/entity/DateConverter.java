package com.excilys.computerdatabase.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DateConverter implements AttributeConverter<LocalDateTime, java.sql.Timestamp> {

    // @Override
    // public LocalDateTime convertToDatabaseColumn(Timestamp arg0) {
    // System.out.println("CONVERT LOCALDATE");
    // return arg0.toLocalDateTime();
    // }
    //
    // @Override
    // public Timestamp convertToEntityAttribute(LocalDateTime arg0) {
    // System.out.println("CONVERT TIMESTAMP");
    // return Timestamp.valueOf(arg0);
    // }

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime arg0) {
        return (arg0 == null ? null : Timestamp.valueOf(arg0));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp arg0) {
        return (arg0 == null ? null : arg0.toLocalDateTime());
    }

}