package configuration;

import com.testconfigurationpoc.data.mappers.DateMapperImpl;
import com.testconfigurationpoc.data.repository.UserRepository;
import com.testconfigurationpoc.data.services.UserServiceImpl;
import com.testconfigurationpoc.data.services.ValidatorServiceImpl;
import com.testconfigurationpoc.domain.mapper.IDateMapper;
import com.testconfigurationpoc.domain.service.IUserService;
import com.testconfigurationpoc.domain.service.IValidatorService;
import data.repository.UserRepositoryStub;
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
    public IUserService userService (
            IValidatorService validatorServiceStub,
            IDateMapper dateMapperStub,
            UserRepository userRepositoryStub) {
        return new UserServiceImpl(validatorServiceStub, dateMapperStub, userRepositoryStub);
    }

    @Bean
    public UserRepository userRepositoryStub() {
        return new UserRepositoryStub();
    }
}
