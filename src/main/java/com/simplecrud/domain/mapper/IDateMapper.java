package com.simplecrud.domain.mapper;

import java.time.LocalDate;

public interface IDateMapper {
    LocalDate mapStringToLocalDate(String date);
}
