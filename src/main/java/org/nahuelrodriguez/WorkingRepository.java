package org.nahuelrodriguez;

import io.micronaut.data.annotation.Repository;
import io.micronaut.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public abstract class WorkingRepository {
    public List<Integer> workingFetch() {
        return List.of(5, 10, 15);
    }
}
