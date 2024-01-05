package configuration;

import com.testconfigurationpoc.data.mappers.DateMapperImpl;
import com.testconfigurationpoc.data.repository.UserRepository;
import com.testconfigurationpoc.data.services.UserServiceImpl;
import com.testconfigurationpoc.data.services.ValidatorServiceImpl;
import com.testconfigurationpoc.domain.mapper.IDateMapper;
import com.testconfigurationpoc.domain.service.IUserService;
import com.testconfigurationpoc.domain.service.IValidatorService;
import data.stubs.mappers.DateMapperStub;
import data.stubs.repository.UserRepositoryStub;
import data.stubs.service.ValidatorServiceStub;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.*;


@TestConfiguration
@AllArgsConstructor
public class BasicTestConfiguration {

//    @Qualifier("userRepositoryStub")
//    private final UserRepositoryStub userRepositoryStub;

//    private final BeanFactory beanFactory;

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
        return mock(UserRepositoryStub.class);
    }
}
