package configuration;

import com.testconfigurationpoc.mappers.DateMapperImpl;
import com.testconfigurationpoc.repository.UserRepository;
import com.testconfigurationpoc.services.UserServiceImpl;
import com.testconfigurationpoc.services.ValidatorServiceImpl;
import com.testconfigurationpoc.domain.mapper.IDateMapper;
import com.testconfigurationpoc.domain.service.IUserService;
import com.testconfigurationpoc.domain.service.IValidatorService;
import stubs.mappers.DateMapperStub;
import stubs.repository.UserRepositoryStub;
import stubs.services.ValidatorServiceStub;
import lombok.AllArgsConstructor;
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
