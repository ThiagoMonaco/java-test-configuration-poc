package data.stubs.mappers;

import com.simplecrud.domain.mapper.IDateMapper;
import org.springframework.boot.test.context.TestComponent;

import java.time.LocalDate;

@TestComponent
public class DateMapperStub implements IDateMapper {
    @Override
    public LocalDate mapStringToLocalDate(String date) {
        return LocalDate.of(2000, 1, 1);
    }
}
