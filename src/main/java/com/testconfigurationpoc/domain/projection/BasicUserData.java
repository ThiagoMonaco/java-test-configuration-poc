package com.testconfigurationpoc.domain.projection;

import com.testconfigurationpoc.domain.entity.Tech;

import java.time.LocalDate;
import java.util.List;

public interface BasicUserData {
    String getUsername();
    Long getId();
    LocalDate getBirthDate();
    List<Tech> getFavoriteTechs();
}
