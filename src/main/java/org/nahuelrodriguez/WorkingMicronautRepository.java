package org.nahuelrodriguez;

import io.micronaut.data.annotation.Repository;
import java.util.List;

@Repository
@io.micronaut.transaction.annotation.Transactional
public abstract class WorkingMicronautRepository {
    public List<Integer> workingFetch() {
        return List.of(5, 10, 15);
    }
}
