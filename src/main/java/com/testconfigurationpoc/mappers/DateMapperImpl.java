package com.testconfigurationpoc.mappers;

import com.testconfigurationpoc.domain.mapper.IDateMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DateMapperImpl implements IDateMapper {
    public LocalDate mapStringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }
}
