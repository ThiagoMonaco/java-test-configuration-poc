package data.mappers;

import com.simplecrud.data.mappers.DateMapperImpl;
import com.simplecrud.domain.mapper.IDateMapper;
import configuration.CustomTestConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CustomTestConfiguration.class)
public class DateMapperTest {

    @Autowired
    private IDateMapper dateMapper;

    @Test
    @DisplayName("Should map a string to a date correctly")
    void mapStringToDateTest() throws ParseException {
        String date = "01/01/2000";

        LocalDate result = dateMapper.mapStringToLocalDate(date);

        Assertions.assertEquals(1, result.getDayOfMonth());
        Assertions.assertEquals(1, result.getMonthValue());
        Assertions.assertEquals(2000, result.getYear());
    }

    @Test
    @DisplayName("Should throw exception if date is in wrong format")
    void wrongDateFormatTest() {
        String date = "01-01-2000";

        Assertions.assertThrows(DateTimeParseException.class, () -> {
            dateMapper.mapStringToLocalDate(date);
        });
    }
}
