package domain.stub.entity;

import com.testconfigurationpoc.domain.entity.Tech;

public final class TechStub {
    public static Tech getTechStub() {
        return new Tech(
                "techName",
                "0"
        );
    }
}
