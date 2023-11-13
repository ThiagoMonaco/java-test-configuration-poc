package domain.stub.entity;

import com.simplecrud.domain.entity.Tech;
import com.simplecrud.domain.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public final class UserStub {
    public static User getUserStub() {
        List<Tech> techDtoStubs = new ArrayList<>();
        techDtoStubs.add(TechStub.getTechStub());
        return new User(
                "username",
                "password",
                LocalDate.of(2000,01,01),
                techDtoStubs);
    }
}
