package stubs.domain.projection;

import com.testconfigurationpoc.domain.entity.Tech;
import com.testconfigurationpoc.domain.projection.BasicUserData;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class BasicUserDataStub implements BasicUserData {
    private String username;
    private Long id;
    private LocalDate birthDate;
    private List<Tech> favoriteTechs;
}
