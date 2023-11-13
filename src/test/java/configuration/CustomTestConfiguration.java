package configuration;

import com.simplecrud.data.mappers.DateMapperImpl;
import com.simplecrud.data.services.UserServiceImpl;
import com.simplecrud.data.services.ValidatorServiceImpl;
import com.simplecrud.domain.mapper.IDateMapper;
import com.simplecrud.domain.service.IUserService;
import com.simplecrud.domain.service.IValidatorService;
import data.stubs.mappers.DateMapperStub;
import data.stubs.service.ValidatorServiceStub;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class CustomTestConfiguration {

    @Bean
    public IValidatorService validatorServiceStub() {
        return new ValidatorServiceStub();
    }

    @Bean
    public IValidatorService validatorService() {
        return new ValidatorServiceImpl();
    }

    @Bean
    public IDateMapper dateMapperStub() {
        return new DateMapperStub();
    }

    @Bean
    public IDateMapper dateMapper() {
        return new DateMapperImpl();
    }

    @Bean
    public IUserService userService(IValidatorService validatorServiceStub, IDateMapper dateMapperStub) {
        return new UserServiceImpl(validatorServiceStub, dateMapperStub);
    }
}
